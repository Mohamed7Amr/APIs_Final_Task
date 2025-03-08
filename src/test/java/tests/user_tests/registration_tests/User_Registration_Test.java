package tests.user_tests.registration_tests;

import static data_reader.Load_Properties.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
//import modules.Users_APIs_Actions;
//import modules_POJOS.User_POJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import modules_POJOS.login_POJOS.Login_Request_POJO;
import modules_POJOS.login_POJOS.Login_Response_POJO;
import modules_POJOS.registration_POJOS.Registration_Request_POJO;
import modules_POJOS.registration_POJOS.Registration_Response_POJO;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;

public class User_Registration_Test {

    /************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Registration_Request_POJO register_Request_POJO;
    private Registration_Response_POJO register_Response_POJO;
    private File user_Registration;
    private Faker fake_Data = new Faker();

    /********************************************TEST_DATA*********************************************/
    private final String users_Base_URI = environment_Data.getProperty("users_Base_URI");
    private final String user_Register_Endpoint = environment_Data.getProperty("user_Register_Endpoint");
    private final String firstName = registration_Request_Body_Data.getProperty("firstName");
    private final String lastName = registration_Request_Body_Data.getProperty("lastName");
    private final String email = fake_Data.numerify("memo##@yahoo.com");
    private final String password = registration_Request_Body_Data.getProperty("password");

    /*********************************************DATA_SETS****************************************/
    @DataProvider(name = "registration_Data_Set")
    public Object[][] test_Data()
    {
        return new Object[][]{
                {email},
                {email}
        };
    }

    /**********************************************TESTS*********************************************/

    @Test
    public void should_Be_Able_To_Register()
    {
        register_Request_POJO = new Registration_Request_POJO(firstName,lastName,email,password);
        register_Response_POJO = new Registration_Response_POJO();

        Response res = given()
                .baseUri(users_Base_URI)
                .log().all()
                .contentType(ContentType.JSON)
                .body(register_Request_POJO)
                .log().all()
        .when()
                .post(user_Register_Endpoint)
        .then()
                .log().all()
                .assertThat()
                    .statusCode(201)
                    .body("firstName",equalTo(firstName))
                .extract().response();
        register_Response_POJO.setFirstName(res.path("firstName"));

    }

    @Test(dataProvider = "registration_Data_Set")
    public void should_Not_Be_Able_To_Register_With_Existing_Email(String email)
    {
        register_Request_POJO = new Registration_Request_POJO(firstName,lastName,email,password);

        given()
                .baseUri(users_Base_URI)
                .contentType(ContentType.JSON)
                .body(register_Request_POJO)
                .log().all()
                .when()
                .post(user_Register_Endpoint)
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .body("message",equalTo("Email is already exists in the Database"));
    }

}
