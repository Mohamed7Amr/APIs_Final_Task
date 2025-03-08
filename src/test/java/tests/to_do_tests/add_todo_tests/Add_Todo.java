package tests.to_do_tests.add_todo_tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
//import modules_POJOS.todo_POJOS.Todo_Request_POJO;
import modules_POJOS.todo_POJOS.Todo_Request_POJO;
import org.testng.annotations.Test;

import java.io.File;

import static data_reader.Load_Properties.environment_Data;
import static data_reader.Load_Properties.todo_Request_Body_Data;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Add_Todo {
    /*******************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Todo_Request_POJO todo_Request_POJO;
    /********************************************TEST_DATA*********************************************/
    private final String base_URI = environment_Data.getProperty("base_URI");
    private final String to_Do_Endpoint = environment_Data.getProperty("to_Do_Endpoint");
    private final String item = todo_Request_Body_Data.getProperty("item");
    private final String token = todo_Request_Body_Data.getProperty("token");
    private final String message = todo_Request_Body_Data.getProperty("message");

    /*********************************************DATA_SETS****************************************/
//    @DataProvider(name = "dataSet")
//    public Object[][] test_Data()
//    {
//        return new Object[][]{
//                {invalid_email, valid_Password},
//                {valid_Email, invalid_Password}
//        };
//    }

    /**********************************************TESTS*********************************************/
    @Test
    public void should_Be_Able_To_Add_Todo()
    {
        todo_Request_POJO = new Todo_Request_POJO( false, item);
//        HashMap<String>
//    File add_Todo_Body = new File("src/test/resources/Json_Files/Todo.json");
        Response res = given()
                .baseUri(base_URI)
                .log().all()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(todo_Request_POJO)//add_Todo_Body)
                .when()
                .post(to_Do_Endpoint)
                .then()
                .log().all()
//                .body("isCompleted",equalTo(false))
                .extract().response();

    }

    @Test
    public void should_Not_Be_Able_To_Add_Todo_Without_Token()
    {


        Response res = given()
                .baseUri(base_URI)
                .log().all()
                .body(todo_Request_POJO)
                .when()
                .post(to_Do_Endpoint)
                .then()
                .log().all()
                .body("message",equalTo(message))
                .extract().response();

    }

    @Test
    public void should_Not_Be_Able_To_Send_Without_isCompleted()
    {
        todo_Request_POJO = new Todo_Request_POJO(item);

        Response res = given()
                .baseUri(base_URI)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(todo_Request_POJO)
                .when()
                .post(to_Do_Endpoint)
                .then()
                .log().all()
                .statusCode(400)
                .body( "message", equalTo("\"isCompleted\" is required"))//,not(hasItem("isCompleted")))
                .extract().response();

    }

}
