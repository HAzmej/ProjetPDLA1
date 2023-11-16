package users;

public class Client extends User{
    private String Mission;
    public String getMission (){
        return this.Mission;
    }
    public void setMission (String Mission){
        this.Mission=Mission;
    }

}
