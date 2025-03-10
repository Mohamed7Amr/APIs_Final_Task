package APIs.user_APIs.registration_APIs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.registration_POJOS.Registration_Request_Body_POJO;
import static io.restassured.RestAssured.given;

/**
 * This class is to write down different requests for the same API depending on the what's needed to be tested.
 */
public class Registration_APIs {

    public static Response register(Registration_Request_Body_POJO register_Request_Body_POJO)
    {

        return given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .log().all()
                .contentType(ContentType.JSON)
                .body(register_Request_Body_POJO)
                .when()
                .post("/api/v1/users/register")
                .then()
                .log().all()
                .extract().response();
    }


}
