package tests.user_tests.login_tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.registration_POJOS.Registration_Response_POJO;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;

import static data_reader.Load_Properties.environment_Data;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class User_Login_Test {
    /*******************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Faker fake_Data = new Faker();
    private File login_Request_Body;
    private Registration_Response_POJO register_Response_POJO;
    /********************************************TEST_DATA*********************************************/
    private final String users_Base_URI = environment_Data.getProperty("users_Base_URI");
    private final String user_Login_Endpoint = environment_Data.getProperty("user_Login_Endpoint");
    private final String invalid_Email = fake_Data.numerify("memo##@yahoo.com");

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
    public void should_Be_Able_To_Login()
    {
        login_Request_Body = new File("src/test/resources/Json_Files/User/User_Login_Request_Body.json");
        register_Response_POJO = new Registration_Response_POJO();

        Response res = given()
                .baseUri(users_Base_URI)
                .contentType(ContentType.JSON)
                .body(login_Request_Body)
                .log().all()
                .when()
                .post(user_Login_Endpoint)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("firstName",equalTo(register_Response_POJO.getFirstName()))
                .body("access_token",notNullValue()).extract().response();

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
