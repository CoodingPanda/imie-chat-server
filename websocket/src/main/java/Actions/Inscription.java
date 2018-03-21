package Actions;


public class Inscription extends Action {

    private String Name;
    private String FirstName;
    private String Email;
    private String City;
    private String Username;
    private String Password;

    public String getName() {
        return Name;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getEmail() {
        return Email;
    }

    public String getCity() {
        return City;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
