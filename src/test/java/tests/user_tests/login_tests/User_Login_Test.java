package tests.user_tests.login_tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.login_POJOS.Login_Request_POJO;
import org.testng.annotations.Test;
import static data_reader.Load_Properties.environment_Data;
import static data_reader.Load_Properties.login_Request_Body_Data;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class User_Login_Test {
    /*******************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Faker fake_Data = new Faker();
    private Login_Request_POJO login_Request_POJO;
    /********************************************TEST_DATA*********************************************/
    private final String base_URI = environment_Data.getProperty("base_URI");
    private final String user_Login_Endpoint = environment_Data.getProperty("user_Login_Endpoint");
    private final String email = login_Request_Body_Data.getProperty("email");
    private final String password = login_Request_Body_Data.getProperty("password");
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

        login_Request_POJO = new Login_Request_POJO(email,password);

        Response res = given()
                .baseUri(base_URI)
                .contentType(ContentType.JSON)
                .body(login_Request_Body_Data)
                .log().all()
                .when()
                .post(user_Login_Endpoint)
                .then()
                .log().all()
                .extract().response();

        assertThat(res.statusCode(),equalTo(200));
        assertThat(res.path("access_Token"),notNullValue());

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
