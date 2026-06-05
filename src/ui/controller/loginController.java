package ui.controller;

import dao.userDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.User;

import java.io.IOException;

public class loginController {
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void login(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        userDAO dao = new userDAO();

        User user = dao.login(username, password);

        if (user != null){
            try{
                System.out.println(user.getUsername());
                System.out.println(user.getIdLevel());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/dashboard.fxml"));

                Parent root = loader.load();

                dasboardController controller =
                        loader.getController();

                controller.setUsername(user.getUsername());
                controller.setLevel(user.getIdLevel());

                Stage stage =
                        (Stage)((Node)event.getSource())
                                .getScene()
                                .getWindow();

                stage.setScene(new Scene(root));
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            System.out.println("login gagal");
        }


    }
}
