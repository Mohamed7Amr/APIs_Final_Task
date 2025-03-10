package APIs.todo_APIs;

import common_steps.User_Common_Steps.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.todo_POJOS.Todo_Request_Body_POJO;

import static common_steps.User_Common_Steps.get_User_Id;
import static common_steps.User_Common_Steps.get_User_Token;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * This class is to write down different requests for the same API depending on the what's needed to be tested.
 */
public class Todo_APIs {

    public static Response add_Todo(Todo_Request_Body_POJO todo_Request_Body_POJO, String firstName,
                                    String lastName, String email, String password)
    {
        return given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .log().all()
                .contentType(ContentType.JSON)
                .auth().oauth2(get_User_Token(firstName,lastName,email,password))
                .body(todo_Request_Body_POJO)
                .when()
                .post("/api/v1/tasks")
                .then()
                .log().all()
                .extract().response();
    }

    public static Response cannot_Add_Todo_Without_Token(Todo_Request_Body_POJO todo_Request_Body_POJO){
        return given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .log().all()
                .contentType(ContentType.JSON)
                .body(todo_Request_Body_POJO)
                .when()
                .post("/api/v1/tasks")
                .then()
                .log().all()
                .extract().response();
    }

    public static Response get_ToDo (String firstName,
                                                    String lastName, String email, String password){

        return   given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .auth().oauth2(get_User_Token(firstName, lastName, email, password))
                .log().all()
                .when()
                .get("/api/v1/tasks"+ get_User_Id(firstName, lastName, email, password))
                .then()
                .log().all()
                .extract().response();
    }

}
