package dao;
import java.sql.ResultSet;
import database.DBConnection;
import model.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class userDAO {

    public User login(String username, String password){
        String query = "SELECT users.*, level.level FROM users JOIN level ON users.id_level = level.id_level WHERE username = ? AND password = ?";

        try{
            Connection koneksi = DBConnection.getConnection();

            PreparedStatement ps = koneksi.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new User(
                        rs.getInt("id_user"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("id_level")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
