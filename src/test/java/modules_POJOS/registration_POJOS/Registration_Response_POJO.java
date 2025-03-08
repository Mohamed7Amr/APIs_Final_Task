package modules_POJOS.registration_POJOS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Registration_Response_POJO {

    /*****************************************ATTRIBUTES**********************************************/
    @JsonProperty("access_token")
    private String access_token;
    private String userID;
    private String firstName;

    /*****************************************CONSTRUCTORS**********************************************/

    public Registration_Response_POJO(String access_token, String userID, String firstName) {
        this.access_token = access_token;
        this.userID = userID;
        this.firstName = firstName;
    }

    /*****************************************METHODS**********************************************/
    @JsonProperty("access_token")
    public String getAccess_token() {
        return access_token;
    }

    @JsonProperty("access_token")
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
