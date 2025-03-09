package APIs.user_APIs.login_APIs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.login_POJOS.Login_Request_POJO;

import static data_reader.Load_Properties.login_Request_Body_Data;
import static io.restassured.RestAssured.given;

public class Login_APIs {

    public static Response login(Login_Request_POJO login_Request_POJO)//, String base_URI, String user_Login_Endpoint)
    {
        return given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(login_Request_Body_Data)
                .log().all()
                .when()
                .post("/api/v1/users/login")
                .then()
                .log().all()
                .extract().response();
    }
}
