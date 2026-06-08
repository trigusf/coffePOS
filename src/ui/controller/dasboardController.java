package ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class dasboardController {

    @FXML
    private Label welcomeUsername;

    public void setUsername(String username){
        welcomeUsername.setText("Selamat Datang, " + username);
    }

    private int idLevel;

    private int idUser;

    public void setLevel(int idLevel){
        this.idLevel = idLevel;
        System.out.println("Dashboard dapet level: " + idLevel);
    }

    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    @FXML
    public void openDataProduk(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/produkPage/dataProduk.fxml"));

        Parent root = loader.load();

        produkController controller = loader.getController();

        controller.setIdLevel(idLevel);
        controller.setIdUser(idUser);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.show();
    }

    public void openTransaksi(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/transaksiPage/transaksi.fxml"));

        Parent root = loader.load();

        transaksiController controller = loader.getController();

        controller.setIdLevel(idLevel);
        controller.setIdUser(idUser);


        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
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
