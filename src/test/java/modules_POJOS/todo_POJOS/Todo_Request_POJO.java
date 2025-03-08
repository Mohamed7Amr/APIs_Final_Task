package modules_POJOS.todo_POJOS;

public class Todo_Request_POJO {
    private String isCompleted;
    private String item;

    public Todo_Request_POJO(String isCompleted, String item) {
        this.isCompleted = isCompleted;
        this.item = item;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
