

public class User {


    private String Email;
    private String Password;
    private int User_id;
    private String Username;
    private StringBuffer key_session;
    private String type;

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public StringBuffer getKey_session() {
        return key_session;
    }

    public void setKey_session(StringBuffer key_session) {
        this.key_session = key_session;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
