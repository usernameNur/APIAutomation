package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;

public class ProductSteps {
    RequestSpecification request;
    Response response;
    String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg1NDgzMzgsImlhdCI6MTcxNTk1NjMzOCwidXNlcm5hbWUiOiJhYi5udXJ6YWRhQGdtYWlsLmNvbSJ9.O8dqPBHqT_dUaqitfUwziLXWYEM2cZb3bF36nLDmp50dCtztrbwxCXjQIfWoehbz0PbxIGX76x8S-MErlTLNfw";

    JSONObject requestBody=new JSONObject();


    @Given("base url {string}\\/\"")
    public void base_url(String baseUrl) {

        request= RestAssured.given().baseUri((baseUrl)).contentType(ContentType.JSON);

    }

    @Given("I have access")
    public void i_have_access() {
        request= request.auth().oauth2(token);

    }

    @Given("I have the endpoint {string}")
    public void i_have_the_endpoint(String endPoint) {
        request=request.basePath(endPoint);

    }

    @Given("I have {string} with {string} in request body")
    public void i_have_with_in_request_body(String key, String value) {
        requestBody.put(key,value);

    }

    @When("I send POST request")
    public void i_send_post_request() {

        response=request.body(requestBody.toString()).post();
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("verify I have {string} with {string} in response body")
    public void verify_i_have_with_in_response_body(String key, String value) {

        response.then()
                .body(key, Matchers.equalTo(value));

    }
    @Then("I delete the product")
    public void i_delete_the_product() {
        String id=response.jsonPath().getString("product_id");

        response=RestAssured.given()
                .baseUri("https://backend.cashwise.us/api/myaccount")
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .when()
                .delete("/products/"+id);

        Assert.assertEquals(response.statusCode(), 200);
    }
    @Then("verify I have {string} with null in response body")
    public void verify_i_have_with_null_in_response_body(String key) {
        response.then()
                .body(key, Matchers.equalTo(null));

    }

}
