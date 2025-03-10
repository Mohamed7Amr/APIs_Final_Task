package tests.user_tests.login_tests;

import APIs.user_APIs.login_APIs.Login_APIs;
import com.github.javafaker.Faker;
import common_steps.User_Common_Steps;
import io.restassured.response.Response;
import modules_POJOS.login_POJOS.Login_Request_Body_POJO;
import modules_POJOS.login_POJOS.Login_Response_Body_POJO;
import modules_POJOS.registration_POJOS.Registration_Request_Body_POJO;
import org.testng.annotations.Test;
import static data_reader.Load_Properties.environment_Data;
import static data_reader.Load_Properties.registration_Request_Body_Data;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class User_Login_Test {
    /*******************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Faker fake_Data = new Faker();
    private Login_Request_Body_POJO login_Request_POJO;
    /********************************************TEST_DATA*********************************************/
    private final String firstName = fake_Data.name().firstName();
    private final String lastName = fake_Data.name().lastName();
    private final String email = fake_Data.internet().emailAddress();
    private final String password = registration_Request_Body_Data.getProperty("password");
    private final String invalid_Email = fake_Data.numerify("memo##@yahoo.com");

    /**********************************************TESTS*********************************************/
    @Test
    public void should_Be_Able_To_Login()
    {
        Registration_Request_Body_POJO register_Request_Body_POJO = User_Common_Steps.register_New_User(firstName,lastName,email,password);

        Login_Request_Body_POJO login_Request_Body_POJO = new Login_Request_Body_POJO(register_Request_Body_POJO.getEmail(),
                register_Request_Body_POJO.getPassword());

        Response res = Login_APIs.login(login_Request_Body_POJO);

        Login_Response_Body_POJO login_Response_Body_POJO = res.body().as(Login_Response_Body_POJO.class);

        assertThat(res.statusCode(),equalTo(200));
        assertThat(login_Response_Body_POJO.getAccess_token(),notNullValue());

    }

//    @Test(dataProvider = "dataSet")
//    public void should_Not_Be_Able_To_Login(String email, String password)
//    {
//        HashMap<String,String> login_Creds = new HashMap<>();
//        login_Creds.put("email", email);
//        login_Creds.put("password",password);
//
//        given()
//                .baseUri(users_Base_URI)
//                .contentType(ContentType.JSON)
//                .body(login_Creds)
//                .log().all()
//                .when()
//                .post(user_Login_Endpoint)
//                .then()
//                .log().all()
//                .assertThat()
//                .body("message",anyOf(equalTo(invalid_Email_Message),equalTo(invalid_Password_Message)))
//                .statusCode(400);
//    }

}
