package APIs.todo_APIs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.todo_POJOS.Todo_Request_POJO;
import static io.restassured.RestAssured.given;

public class Todo_APIs {

    public static Response add_Todo(Todo_Request_POJO todo_Request_POJO, String base_URI, String token, String to_Do_Endpoint)
    {
        return given()
                .baseUri(base_URI)
                .log().all()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(todo_Request_POJO)//add_Todo_Body)
                .when()
                .post(to_Do_Endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response cannot_Add_Todo_Without_Token(Todo_Request_POJO todo_Request_POJO, String base_URI, String to_Do_Endpoint){
        return given()
                .baseUri(base_URI)
                .log().all()
                .body(todo_Request_POJO)
                .when()
                .post(to_Do_Endpoint)
                .then()
                .log().all()
                .extract().response();
    }

}
