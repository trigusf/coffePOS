package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_coffepos";
    private static final String username = "root";
    private static final String password = "";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(
                    URL,
                    username,
                    password
            );
        }catch (Exception e){
            e.printStackTrace();
        };
    return null;
    }

}