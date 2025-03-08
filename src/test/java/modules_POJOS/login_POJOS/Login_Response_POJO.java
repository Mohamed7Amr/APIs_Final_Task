package modules_POJOS.login_POJOS;

public class Login_Response_POJO {
    private String access_token;
    private String userID;
    private String firstName;

    public Login_Response_POJO()
    {

    }

    public Login_Response_POJO(String access_token, String userID, String firstName) {
        this.access_token = access_token;
        this.userID = userID;
        this.firstName = firstName;
    }

    public String getAccess_token() {
        return access_token;
    }

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
