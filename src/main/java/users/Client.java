package users;

public class Client extends User{
    private String Name;
    private String Surname ;
    private String Email ;
    private String Password ;
    private String Mission;

    public Client(String name, String surname, String email, String password, String name1, String surname1, String email1, String password1, String mission) {
        super(name, surname, email, password);
        this.Mission = mission;
    }


    public String getMission (){
        return this.Mission;
    }
    public void setMission (String Mission){
        this.Mission=Mission;
    }

}
