package tests.user_tests.registration_tests;

import static data_reader.Load_Properties.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import modules_POJOS.registration_POJOS.Registration_Request_POJO;
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

        Response res = given()
                .baseUri(base_URI)
                .log().all()
                .contentType(ContentType.JSON)
                .body(register_Request_POJO)
                .log().all()
        .when()
                .post(user_Register_Endpoint)
        .then()
                .log().all()
                .extract().response();

        assertThat(res.statusCode(),equalTo(201));
        assertThat(res.path("firstName"),equalTo("Mohamed"));

    }

    @Test(dataProvider = "registration_Data_Set")
    public void should_Not_Be_Able_To_Register_With_Existing_Email(String email)
    {
        register_Request_POJO = new Registration_Request_POJO(firstName,lastName,email,password);

        Response res = given()
                .baseUri(base_URI)
                .contentType(ContentType.JSON)
                .body(register_Request_POJO)
                .log().all()
                .when()
                .post(user_Register_Endpoint)
                .then()
                .log().all()
                .extract().response();

        assertThat(res.statusCode(),equalTo(400));
        assertThat(res.path("message"),equalTo("Email is already exists in the Database"));
    }

}
