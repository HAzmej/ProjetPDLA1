package users;

public class User {
    private String Name;
    private String Surname ;
    private String Email ;
    private String Password ;

    public User(String name, String surname, String email, String password) {
        Name = name;
        Surname = surname;
        Email = email;
        Password = password;
    }

    public String getName(){
        return this.Name;
    }
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
