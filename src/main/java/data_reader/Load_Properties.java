package data_reader;

import lombok.SneakyThrows;
import java.io.FileReader;
import java.util.Properties;

/**
 * @author Mohamed_Amr
 *
 */
public class Load_Properties {

    @SneakyThrows
    private static Properties load_Properties_Data(String properties_File_Name)
    {
        FileReader fr = new FileReader(properties_File_Name);
        Properties pro = new Properties();
        pro.load(fr);
        return pro;
    }

    public static Properties environment_Data =
            load_Properties_Data("src/test/resources/Properties/Environment_Data/EnvironmentData.properties");
    public static Properties registration_Request_Body_Data =
            load_Properties_Data("src/test/resources/Properties/Request_Bodies_Data/Registration/Registration_Request_Body_Data.properties");
    public static Properties todo_Request_Body_Data =
            load_Properties_Data("src/test/resources/Properties/Request_Bodies_Data/Todo/Todo_Request_Body_Data.properties");
    public static Properties login_Request_Body_Data =
            load_Properties_Data("src/test/resources/Properties/Request_Bodies_Data/Login/Login_Request_Body_Data.properties");
    public static Properties registration_ErrorS_Messages_Data =
            load_Properties_Data("src/test/resources/Properties/Errors_Messages_Data/Registration_Errors.properties");


}
