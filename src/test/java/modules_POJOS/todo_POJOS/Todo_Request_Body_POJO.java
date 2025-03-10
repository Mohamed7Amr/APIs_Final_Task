package modules_POJOS.todo_POJOS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo_Request_Body_POJO {

/******************************************ATTRIBUTES*********************************************/
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    private String item;

    /************************************CONSTRUCTORS***************************************/

    public Todo_Request_Body_POJO(Boolean isCompleted, String item) {
        this.isCompleted = isCompleted;
        this.item = item;
    }
    public Todo_Request_Body_POJO(String item) {
        this.item = item;
    }

    /**********************************METHODS******************************************/
    @JsonProperty("isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @JsonProperty("isCompleted")
    public void setIsCompleted(Boolean isCompleted) {
        isCompleted = isCompleted;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
