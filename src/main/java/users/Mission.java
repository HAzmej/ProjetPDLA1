package users;

public class Mission {

    private int iduser;
    private String description;
    private int date;
    private User user;
    private States state;
    private String Commentaire;

    public Mission(int iduser, String description, int date, States state, String comm ){
            this.description = description;
            this.iduser=iduser;
            this.date=date;
            this.state=state;
            this.Commentaire=comm;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getDate() { return date; }

    public States getState() { return state; }



}
