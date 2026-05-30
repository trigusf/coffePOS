package ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class dasboardController {

    @FXML
    private Button btnDataProduk;

    public void setLevel(int id_level){
        System.out.println(id_level);
        if (id_level == 2){
            btnDataProduk.setVisible(false);
            btnDataProduk.setManaged(false);
        }
    }

    @FXML
    public void openDataProduk(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(
                getClass().getResource("/ui/dataProduk.fxml")
        );
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openTransaksi(){

    }

    public void openRiwayat(){

    }

    public void logout(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(
                getClass().getResource("/ui/login.fxml")
        );
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene((root)));
        stage.show();
    }
}
