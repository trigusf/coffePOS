package ui.controller;

import com.sun.javafx.scene.traversal.ParentTraversalEngine;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class transaksiController {

    public void backBtnToDashboard(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/dashboard.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }



    public void btnCheckout(){
        System.out.println("checkout");
    }

    public void btnReset(){
        System.out.println("reset");
    }


    public void setLevel(int idLevel) {
    }
}
