package modules_POJOS.todo_POJOS;

public class Todo_Response_POJO {

    private String isCompleted;
    private String _id;
    private String item;
    private String userID;
    private String createdAt;
    private String __v;

    public Todo_Response_POJO(String isCompleted, String _id, String item, String userID, String createdAt, String __v) {
        this.isCompleted = isCompleted;
        this._id = _id;
        this.item = item;
        this.userID = userID;
        this.createdAt = createdAt;
        this.__v = __v;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String get_id() {
        return _id;
    }

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

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
