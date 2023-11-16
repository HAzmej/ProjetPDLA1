package users;

public class Benev extends User{
    private String task;
    private String Name;
    private String Surname ;
    private String Email ;
    private String Password ;

    public Benev(String name, String surname, String email, String password, String name1, String surname1, String email1, String password1, String task) {
        super(name, surname, email, password);
        this.task = task;
    }
    public void setMission (String task) {
        this.task=task;
    }
    public String acceptMission() {
        return this.task;
    }

}
