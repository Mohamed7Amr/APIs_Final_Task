package tests.to_do_tests.add_todo_tests;

import APIs.todo_APIs.Todo_APIs;
import APIs.user_APIs.login_APIs.Login_APIs;
import common_steps.User_Common_Steps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.todo_POJOS.Todo_Errors_POJO;
import modules_POJOS.todo_POJOS.Todo_Request_POJO;
import modules_POJOS.todo_POJOS.Todo_Response_POJO;
import org.testng.annotations.Test;
import static data_reader.Load_Properties.environment_Data;
import static data_reader.Load_Properties.todo_Request_Body_Data;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Add_Todo {
    /*******************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Todo_Request_POJO todo_Request_POJO;
    /********************************************TEST_DATA*********************************************/
    private final String base_URI = environment_Data.getProperty("base_URI");
    private final String to_Do_Endpoint = environment_Data.getProperty("to_Do_Endpoint");
    private final String item = todo_Request_Body_Data.getProperty("item");
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

        Response res = Todo_APIs.add_Todo(todo_Request_POJO, base_URI, User_Common_Steps.get_User_Token(), to_Do_Endpoint);

        Todo_Response_POJO todo_Response_POJO = res.body().as(Todo_Response_POJO.class);

        assertThat(res.statusCode(),equalTo(201));
        assertThat(todo_Response_POJO.getItem(),equalTo("Appium"));

    }

    @Test
    public void should_Not_Be_Able_To_Add_Todo_Without_Token()
    {
        todo_Request_POJO = new Todo_Request_POJO(true,item);

        Response res = Todo_APIs.cannot_Add_Todo_Without_Token(todo_Request_POJO, base_URI, to_Do_Endpoint);

        Todo_Errors_POJO todo_Errors_POJO = res.body().as(Todo_Errors_POJO.class);

        assertThat(todo_Errors_POJO.getMessage(),equalTo(message));

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
