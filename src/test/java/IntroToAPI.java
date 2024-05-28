import com.sun.net.httpserver.Request;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class IntroToAPI {

    public static void main(String[] args) {

        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg1NDgzMzgsImlhdCI6MTcxNTk1NjMzOCwidXNlcm5hbWUiOiJhYi5udXJ6YWRhQGdtYWlsLmNvbSJ9.O8dqPBHqT_dUaqitfUwziLXWYEM2cZb3bF36nLDmp50dCtztrbwxCXjQIfWoehbz0PbxIGX76x8S-MErlTLNfw";
        RestAssured
                .given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/sellers/all")
                .then()
                .statusCode(200);

        RestAssured
                .given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/tags/all")
                .then()
                .statusCode(200);

        RestAssured
                .given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .and()
                .queryParam("page","1")
                .queryParam("size", 4)
                .when()
                .get("/myaccount/reminder/requests")
                .then()
                .statusCode(200);

        RestAssured
                .given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/reminder/notifications")
                .then()
                .statusCode(200);

        RestAssured
                .given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/tags/all")
                .then()
                .statusCode(200);


    }

}
