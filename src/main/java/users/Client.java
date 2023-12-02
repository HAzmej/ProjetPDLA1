package users;

public class Client extends User{
    private int id;
    private String Name;
    private String Surname ;
    private String Email ;
    private String Password ;
    private Mission UserMission;

    public Client(int id,String name, String surname, String email, String password, String name1, String surname1, String email1, String password1, Mission UserMission) {
        super(id,name, surname, email, password);
        this.UserMission = UserMission;
    }


    public Mission getMission (){ return this.UserMission; }

    public void setMission (Mission Mission){
        this.UserMission=Mission;
    }

}
