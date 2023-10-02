package models;

public class Users {

    private Integer id;
    private String user;
    private String password;

    public Users(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }
}
