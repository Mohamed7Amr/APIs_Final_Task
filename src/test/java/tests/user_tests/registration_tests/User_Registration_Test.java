package tests.user_tests.registration_tests;

import static APIs.user_APIs.registration_APIs.Registration_APIs.register;
import static data_reader.Load_Properties.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.github.javafaker.Faker;
import static common_steps.User_Common_Steps.*;
import io.restassured.response.Response;
import modules_POJOS.registration_POJOS.Registration_Errors_POJO;
import modules_POJOS.registration_POJOS.Registration_Request_Body_POJO;
import modules_POJOS.registration_POJOS.Registration_Response_Body_POJO;
import org.testng.annotations.Test;

public class User_Registration_Test {

    /************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Registration_Request_Body_POJO register_Request_Body_POJO;
    private Faker fake_Data = new Faker();

    /********************************************TEST_DATA*********************************************/
    private final String firstName = fake_Data.name().firstName();
    private final String lastName = fake_Data.name().lastName();
    private final String email = fake_Data.internet().emailAddress();
    private final String password = registration_Request_Body_Data.getProperty("password");
    private final String register_With_Existing_Email_Error_Message = registration_ErrorS_Messages_Data
            .getProperty("register_With_Existing_Email_Error_Message");

    /**********************************************TESTS*********************************************/

    @Test
    public void should_Be_Able_To_Register()
    {
        register_Request_Body_POJO = generate_User_Registration_Data(firstName,lastName,email,password);
        Response res = register(register_Request_Body_POJO);
        Registration_Response_Body_POJO register_Response_Body_POJO = res.body().as(Registration_Response_Body_POJO.class);
        assertThat(res.statusCode(),equalTo(201));
        assertThat(register_Response_Body_POJO.getFirstName(),equalTo(register_Request_Body_POJO.getFirstName()));
    }

    @Test()
    public void should_Not_Be_Able_To_Register_With_Existing_Email()
    {
        register_Request_Body_POJO = generate_User_Registration_Data(firstName,lastName,email,password);
        Response res = register(register_New_User(firstName,lastName,email,password));
        Registration_Errors_POJO register_Errors_POJO = res.body().as(Registration_Errors_POJO.class);
        assertThat(res.statusCode(),equalTo(400));
        assertThat(register_Errors_POJO.getMessage(),equalTo(register_With_Existing_Email_Error_Message));
    }

}
