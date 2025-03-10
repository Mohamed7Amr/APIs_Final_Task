package modules_POJOS.login_POJOS;

public class Login_Request_Body_POJO {

    private String email;
    private String password;


    public Login_Request_Body_POJO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
