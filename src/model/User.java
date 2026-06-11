package model;

public class User {
    private int id;
    private String username;
    private String password;
    private int idLevel;
    private String level;

    public User(int id, String username, String password, int idLevel){
        this.id = id;
        this.username = username;
        this.password = password;
        this.idLevel = idLevel;
    }

    public User(String username, String level){
        this.username = username;
        this.level = level;
    }

    public User(String username, String password, int idLevel){
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
    public String getPassword(){return password;}
    public String getLevel(){return level;}
}
