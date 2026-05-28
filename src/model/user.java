package model;

public class user {
    private int id;
    private String username;
    private String password;
    int idLevel;

    public user(int id, String username, String password, int idLevel){
        this.id = id;
        this.username = username;
        this.password = password;
        this.idLevel = idLevel;
    }

    public String getUsername() {
        return username;
    }
    public int getIdLevel(){
        return idLevel;
    }
}
