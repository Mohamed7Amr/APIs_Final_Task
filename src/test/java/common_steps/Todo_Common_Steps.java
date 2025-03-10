package common_steps;

import APIs.todo_APIs.Todo_APIs;
import com.github.javafaker.Faker;
import modules_POJOS.todo_POJOS.Todo_Request_Body_POJO;

public class Todo_Common_Steps {

    public static Todo_Request_Body_POJO generate_Todo_Data(Boolean isCompleted, String item)
    {
        return new Todo_Request_Body_POJO(isCompleted,item);
    }

}
