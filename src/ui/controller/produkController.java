package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class produkController {


    @FXML
    private ComboBox<String> cbKategori;
    @FXML
    public void initialize(){
        if (cbKategori != null){
            cbKategori.getItems().addAll(
                    "Coffe",
                    "Non Coffe",
                    "Food"
            );
        }
    }


    @FXML
    public void tambahData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/tambahDataProduk.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }



    @FXML
    public void submitProduk(){

    }


}
