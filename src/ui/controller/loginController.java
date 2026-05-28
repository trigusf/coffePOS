package ui.controller;

import dao.userDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void login(){
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        userDAO dao = new userDAO();

        boolean success = dao.login(username, password);

        if (success){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/dashboard.fxml"));
                Scene scene = new Scene(loader.load(),800,600);
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                stage.setScene(scene);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            System.out.println("login gagal");
        }


    }
}
