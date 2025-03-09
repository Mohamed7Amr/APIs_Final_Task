package tests.user_tests.registration_tests;

import static data_reader.Load_Properties.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import APIs.user_APIs.registration_APIs.Registration_APIs;
import com.github.javafaker.Faker;
import static common_steps.User_Common_Steps.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.registration_POJOS.Registration_Errors_POJO;
import modules_POJOS.registration_POJOS.Registration_Request_POJO;
import modules_POJOS.registration_POJOS.Registration_Response_POJO;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class User_Registration_Test {

    /************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Registration_Request_POJO register_Request_POJO;
    private Faker fake_Data = new Faker();

    /********************************************TEST_DATA*********************************************/
    private final String base_URI = environment_Data.getProperty("base_URI");
    private final String user_Register_Endpoint = environment_Data.getProperty("user_Register_Endpoint");
    private final String firstName = registration_Request_Body_Data.getProperty("firstName");
    private final String lastName = registration_Request_Body_Data.getProperty("lastName");
    private final String email = fake_Data.numerify("memo##@yahoo.com");
    private final String password = registration_Request_Body_Data.getProperty("password");

    /**********************************************TESTS*********************************************/

    @Test
    public void should_Be_Able_To_Register()
    {


        Response res = Registration_APIs.register(generate_User_Registration_Data());

        Registration_Response_POJO register_Response_POJO = res.body().as(Registration_Response_POJO.class);

        assertThat(res.statusCode(),equalTo(201));
        assertThat(register_Response_POJO.getFirstName(),equalTo(register_Request_POJO.getFirstName()));

    }

    @Test()
    public void should_Not_Be_Able_To_Register_With_Existing_Email(String email)
    {

        Response res = Registration_APIs.register(register_New_User());

        Registration_Errors_POJO register_Errors_POJO = res.body().as(Registration_Errors_POJO.class);

        assertThat(res.statusCode(),equalTo(400));
        assertThat(register_Errors_POJO.getMessage(),equalTo("Email is already exists in the Database"));
    }

}
