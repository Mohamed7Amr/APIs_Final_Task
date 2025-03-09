package common_steps;

import APIs.user_APIs.registration_APIs.Registration_APIs;
import com.github.javafaker.Faker;
import modules_POJOS.registration_POJOS.Registration_Request_POJO;

public class User_Common_Steps {
    public static Registration_Request_POJO generate_User_Registration_Data(){
        Faker fake_Data = new Faker();
        String firstName = fake_Data.name().firstName();
        String lastName = fake_Data.name().lastName();
        String email = fake_Data.internet().emailAddress();
        String password = "P@ssw0rd";
        return new Registration_Request_POJO(firstName,lastName,email,password);
    }

    public static Registration_Request_POJO register_New_User()
    {
        Registration_Request_POJO rrp = generate_User_Registration_Data();
        Registration_APIs.register(rrp);
        return rrp;
    }

    public static String get_User_Token()
    {
        return Registration_APIs.register(generate_User_Registration_Data()).path("access_token");
    }
}
