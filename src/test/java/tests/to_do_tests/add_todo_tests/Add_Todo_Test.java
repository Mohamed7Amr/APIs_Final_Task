package tests.to_do_tests.add_todo_tests;

import static APIs.todo_APIs.Todo_APIs.*;
import static common_steps.Todo_Common_Steps.*;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import modules_POJOS.todo_POJOS.Todo_Errors_POJO;
import modules_POJOS.todo_POJOS.Todo_Request_Body_POJO;
import modules_POJOS.todo_POJOS.Todo_Response_Body_POJO;
import org.testng.annotations.Test;
import static data_reader.Load_Properties.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Add_Todo_Test {
    /*******************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Todo_Request_Body_POJO todo_Request_Body_POJO;
    private Todo_Response_Body_POJO todo_Response_Body_POJO;
    private Todo_Errors_POJO todo_Errors_POJO;
    private Faker fake_Data = new Faker();
    /********************************************TEST_DATA*********************************************/
    private final String firstName = fake_Data.name().firstName();
    private final String lastName = fake_Data.name().lastName();
    private final String email = fake_Data.internet().emailAddress();
    private final String password = registration_Request_Body_Data.getProperty("password");
    private final String item = fake_Data.harryPotter().spell();
    private final boolean isCompleted = true;
    private final String message = todo_Request_Body_Data.getProperty("message");

    /**********************************************TESTS*********************************************/
    @Test
    public void should_Be_Able_To_Add_Todo()
    {
        todo_Request_Body_POJO = generate_Todo_Data(isCompleted,item);
        Response res = add_Todo(todo_Request_Body_POJO,firstName,lastName,email,password);
        todo_Response_Body_POJO = res.body().as(Todo_Response_Body_POJO.class);
        assertThat(res.statusCode(),equalTo(201));
        assertThat(todo_Response_Body_POJO.getItem(),equalTo(todo_Request_Body_POJO.getItem()));
    }

    @Test
    public void should_Not_Be_Able_To_Add_Todo_Without_Token()
    {
        todo_Request_Body_POJO = generate_Todo_Data(isCompleted,item);
        Response res = cannot_Add_Todo_Without_Token(todo_Request_Body_POJO);
        todo_Errors_POJO = res.body().as(Todo_Errors_POJO.class);
        assertThat(res.statusCode(),equalTo(401));
        assertThat(todo_Errors_POJO.getMessage(),equalTo(message));
    }

    @Test
    public void should_Not_Be_Able_To_Send_Without_isCompleted()
    {
        todo_Request_Body_POJO = generate_Todo_Data(null,item);
        Response res = add_Todo(todo_Request_Body_POJO,firstName,lastName,email,password);
        todo_Errors_POJO = res.body().as(Todo_Errors_POJO.class);
        assertThat(todo_Errors_POJO.getMessage(),equalTo("\"isCompleted\" is required"));

    }

}
