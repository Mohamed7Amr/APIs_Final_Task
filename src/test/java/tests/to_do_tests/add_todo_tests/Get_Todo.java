package tests.to_do_tests.add_todo_tests;

import io.restassured.http.ContentType;
import modules_POJOS.todo_POJOS.Todo_Request_POJO;
import org.testng.annotations.Test;
import static data_reader.Load_Properties.environment_Data;
import static data_reader.Load_Properties.todo_Request_Body_Data;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get_Todo {

    /*******************************************OBJECTS_DECLARATIONS/INSTANTIATIONS*****************************/
    private Todo_Request_POJO todo_Request_POJO;

    /********************************************TEST_DATA*********************************************/
    private final String base_URI = environment_Data.getProperty("base_URI");
    private final String to_Do_Endpoint = environment_Data.getProperty("to_Do_Endpoint");
    private final String item = todo_Request_Body_Data.getProperty("item");
    private final String token = todo_Request_Body_Data.getProperty("token");
    private final String message = todo_Request_Body_Data.getProperty("message");

    /**********************************************TESTS*********************************************/
    @Test
    public void should_Be_Able_To_Get_Todo()
    {

        given()
                .baseUri(base_URI)
                .contentType(ContentType.JSON)
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY3YzJmNjhiYzdhNzgwMDAxNGQ1YWMwOSIsImZpcnN0TmFtZSI6Ik1vaGFtZWQiLCJsYXN0TmFtZSI6IkFtciIsImlhdCI6MTc0MDg0Mzc5Nn0.IWcPsPEeI02vzayOG9PJlQlZ9rJd-4KQg4WqId9h7JU")
                .log().all()
                .when()
                .get(to_Do_Endpoint+"/67c32b54c7a7800014d5ad2f")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("item",equalTo("Learn Appium"),
                        "isCompleted", equalTo(false));
    }
}
