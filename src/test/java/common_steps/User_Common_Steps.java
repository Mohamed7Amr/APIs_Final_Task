package common_steps;

import APIs.user_APIs.registration_APIs.Registration_APIs;
import modules_POJOS.registration_POJOS.Registration_Request_Body_POJO;

public class User_Common_Steps {
    public static Registration_Request_Body_POJO generate_User_Registration_Data(String firstName,
         String lastName, String email, String password)
    {
        return new Registration_Request_Body_POJO(firstName,lastName,email,password);
    }

    /**
     * This method is created for the TC "should_Not_Be_Able_To_Register_With_Existing_Email";
     * to register in the TC with the same request body that he had just used to register a new user.
     * @return
     */
    public static Registration_Request_Body_POJO register_New_User(String firstName,
                                                                   String lastName, String email, String password)
    {
        Registration_Request_Body_POJO rrbp = generate_User_Registration_Data(firstName,lastName,email,password);
        Registration_APIs.register(rrbp);
        return rrbp;
    }

    public static String get_User_Token(String firstName,
                                        String lastName, String email, String password)
    {
        return Registration_APIs.register(generate_User_Registration_Data(firstName,lastName,email,password)).path("access_token");
    }

    public static String get_User_Id(String firstName,
                                        String lastName, String email, String password)
    {
        return Registration_APIs.register(generate_User_Registration_Data(firstName,lastName,email,password)).path("userID");  }
}
