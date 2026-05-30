package model;

public class User {
    private int id;
    private String username;
    private String password;
    private int idLevel;

    public User(int id, String username, String password, int idLevel){
        this.id = id;
        this.username = username;
        this.password = password;
        this.idLevel = idLevel;
    }

    public int getId(){return id;}
    public String getUsername() {
        return username;
    }
    public int getIdLevel(){
        return idLevel;
    }
}
