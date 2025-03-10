package modules_POJOS.registration_POJOS;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Registration_Request_Body_POJO {
    /*****************************************ATTRIBUTES****************************************/
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /*****************************************CONSTRUCTORS****************************************/
    public Registration_Request_Body_POJO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    /*****************************************SETTERS_AND_GETTERS****************************************/
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
