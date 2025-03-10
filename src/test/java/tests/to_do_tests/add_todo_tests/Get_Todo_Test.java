package tests.to_do_tests.add_todo_tests;

import APIs.todo_APIs.Todo_APIs;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.todo_POJOS.Todo_Request_Body_POJO;
import modules_POJOS.todo_POJOS.Todo_Response_Body_POJO;
import org.testng.annotations.Test;

import static APIs.todo_APIs.Todo_APIs.get_ToDo;
import static data_reader.Load_Properties.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Get_Todo_Test {

    /*******************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Todo_Request_Body_POJO todo_Request_Body_POJO;
    private Todo_Response_Body_POJO  todo_Response_Body_POJO;
    private Faker fake_Data = new Faker();

    /********************************************TEST_DATA*********************************************/
    private final String firstName = fake_Data.name().firstName();
    private final String lastName = fake_Data.name().lastName();
    private final String email = fake_Data.internet().emailAddress();
    private final String password = registration_Request_Body_Data.getProperty("password");
    private final String item = todo_Request_Body_Data.getProperty("item");
    private final String token = todo_Request_Body_Data.getProperty("token");
    private final String message = todo_Request_Body_Data.getProperty("message");

    /**********************************************TESTS*********************************************/
    @Test
    public void should_Be_Able_To_Get_Todo()
    {
        Response res = get_ToDo(firstName,lastName,email,password);
        todo_Response_Body_POJO = res.body().as(Todo_Response_Body_POJO.class);

//        assertThat();
//                    .assertThat()
//            .statusCode(200)
//            .body("item",equalTo("Learn Appium"),
//                    "isCompleted", equalTo(false))
    }
}
