package users;

public class User {
    private int user;
    private String Name;
    private String Surname ;
    private String Email ;
    private String Password ;

    public User(String name, String surname, String email, String password) {
        this.user++;
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
        this.Password = password;
    }
     public User(String name, String surname) {
        this.user++;
        this.Name = name;
        this.Surname = surname;
       
    }
     public User(String name, String surname, String email) {
        this.user++;
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
      
    }

    public String getName(){
        return this.Name;
    }
    public int getid(){return this.user;}

    public String getSurname(){
        return this.Surname;
    }
    public String getEmail(){
        return this.Email;
    }
    public String getPassword(){
        return this.Password;
    }
    public void setName(String Name) {
        this.Name=Name;
    }
    public void setSurname(String Surname) {
        this.Surname=Surname;
    }
    public void setEmail(String Email) {
        this.Email=Email;
    }
    public void setPassword(String Password) {
        this.Password=Password;
    }
}
