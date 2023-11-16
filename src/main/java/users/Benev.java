package users;

public class Benev extends User{
    private String task;
    public void setMission (String task) {
        this.task=task;
    }
    public String acceptMission() {
        return this.task;
    }

}
