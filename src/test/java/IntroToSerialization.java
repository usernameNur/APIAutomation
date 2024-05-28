import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Tag;

import java.util.Arrays;

public class IntroToSerialization {

    @Test
    public void testing() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg1NDgzMzgsImlhdCI6MTcxNTk1NjMzOCwidXNlcm5hbWUiOiJhYi5udXJ6YWRhQGdtYWlsLmNvbSJ9.O8dqPBHqT_dUaqitfUwziLXWYEM2cZb3bF36nLDmp50dCtztrbwxCXjQIfWoehbz0PbxIGX76x8S-MErlTLNfw";

        Tag tag = new Tag();
        tag.setName_tag("ppple");
        tag.setDescription("this is apple");
        Gson gson = new Gson();
        String requestBodyInJson = gson.toJson(tag);
        System.out.println(requestBodyInJson);


        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .body(requestBodyInJson)
                .when()
                .post("/myaccount/tags");

        System.out.println(response.statusCode());
        System.out.println(response.prettyPrint());
        /*
        deserialize json to java
         */
        String responseInJson=response.asString();
        gson=new Gson();
        Tag tagResponse=gson.fromJson(responseInJson,Tag.class);

        System.out.println(tagResponse.getMessage());
        System.out.println(Arrays.toString(tagResponse.getDetails()));



    }

}
