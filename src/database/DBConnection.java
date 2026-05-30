package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    public static Connection getConnection(){
        try{
            Connection koneksi = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_coffepos",
                    "root",
                    ""
            );
            return koneksi;
        }catch (Exception e){
            System.out.println("Database gagal connect");
            e.printStackTrace();
        }return null;
    }

}