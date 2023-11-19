package users;

public class Mission {

    private String description;
    private String task;
    private String date;
    private User user;
    private States state;

    public Mission(User user, String description, String task, String date, States state ){
            this.description = description;
            this.task = task;
            this.date=date;
            this.state=state;
            this.user=user;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getTask() { return task; }

    public User getUser(){ return user; }

    public String getDate() { return date; }

    public States getState() { return state; }



}
