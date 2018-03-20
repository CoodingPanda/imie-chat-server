import Actions.Action;

public class Inscription extends Action {

    private String Name;
    private String Firstname;
    private String Mail;
    private String City;
    private String Pseudo;
    private String Password;

    public String getName() {
        return Name;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getMail() {
        return Mail;
    }

    public String getCity() {
        return City;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public String getPassword() {
        return Password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setPseudo(String pseudo) {
        Pseudo = pseudo;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
