package dao;
import java.sql.ResultSet;
import database.DBConnection;
import javafx.beans.binding.When;
import model.Produk;
import model.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class userDAO {
    Connection koneksi = DBConnection.getConnection();

//    login
    public User login(String username, String password){
        String query = "SELECT users.*, level.level FROM users JOIN level ON users.id_level = level.id_level WHERE username = ? AND password = ?";

        try{

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
//    end of login
//    tampil data
        public List<User> getAllUser(){
            List<User> list = new ArrayList<>();
            String query = "SELECT users.*, level.level FROM users JOIN level ON users.id_level = level.id_level";

            try(PreparedStatement ps = koneksi.prepareStatement(query);
            ResultSet rs = ps.executeQuery();){
                while (rs.next()){
                    User user = new User(
                            rs.getString("username"),
                            rs.getString("level")
                    );

                    list.add(user);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return list;
        }
//    end of tampil data
//    hapus data
        public void hapusUser(){

        }
//    end of hapus data
}
