import com.sun.net.httpserver.Request;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class IntroToRequest {
    public static void main(String[] args) {
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg1NDgzMzgsImlhdCI6MTcxNTk1NjMzOCwidXNlcm5hbWUiOiJhYi5udXJ6YWRhQGdtYWlsLmNvbSJ9.O8dqPBHqT_dUaqitfUwziLXWYEM2cZb3bF36nLDmp50dCtztrbwxCXjQIfWoehbz0PbxIGX76x8S-MErlTLNfw";

        JSONObject requestBody=new JSONObject();
        requestBody.put("product_title", "Smart book");
        requestBody.put("product_price", 5.5);
        requestBody.put("service_type_id", 2);
        requestBody.put("category_id", 1026);
        requestBody.put("product_description", "new");
        requestBody.put("date_of_payment", "2024-05-20");
        requestBody.put("reminder_before_day", 2);
        requestBody.put("do_reminder_every_month", "REPEAT_EVERY_MONTH");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .body(requestBody.toString())
                .when()
                .post("/myaccount/products")
                .then()
                .statusCode(201)
                .body("product_title", equalTo("Smart book"))
                .body("product_price", equalTo(5.5));






    }
}
