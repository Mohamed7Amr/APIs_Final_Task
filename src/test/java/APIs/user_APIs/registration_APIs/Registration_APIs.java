package APIs.user_APIs.registration_APIs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.registration_POJOS.Registration_Request_POJO;
import static io.restassured.RestAssured.given;

public class Registration_APIs {

    public static Response register(Registration_Request_POJO register_Request_POJO)//, String base_URI, String user_Register_Endpoint)
    {

        return given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .log().all()
                .contentType(ContentType.JSON)
                .body(register_Request_POJO)
                .log().all()
                .when()
                .post("/api/v1/users/register")
                .then()
                .log().all()
                .extract().response();
    }


}
