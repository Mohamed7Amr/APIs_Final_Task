package modules_POJOS.todo_POJOS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This annotation makes sure that jackson will exclude any attributes that have value of null while serializing
 * the POJO class to send
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo_Response_POJO {
    /*****************************************ATTRIBUTES****************************************/
    private String isCompleted;
    @JsonProperty("_id")
    private String _id;
    private String item;
    private String userID;
    private String createdAt;
    /**
     * Having some keys in json with special characters can cause a trouble while serialization the attributes
     * to send the body, so we use a specific annotation that helps
     */
    @JsonProperty("__v")
    private String __v;

    /*****************************************CONSTRUCTORS****************************************/

    public Todo_Response_POJO(String isCompleted, String _id, String item, String userID, String createdAt, String __v) {
        this.isCompleted = isCompleted;
        this._id = _id;
        this.item = item;
        this.userID = userID;
        this.createdAt = createdAt;
        this.__v = __v;
    }

    /*****************************************SETTERS_AND_GETTERS****************************************/

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    @JsonProperty("_id")
    public String get_id() {
        return _id;
    }

    @JsonProperty("_id")
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("__v")
    public String get__v() {
        return __v;
    }

    @JsonProperty("__v")
    public void set__v(String __v) {
        this.__v = __v;
    }
}
