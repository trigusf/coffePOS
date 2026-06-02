package ui.controller;

import dao.productDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Product;

import java.io.IOException;

public class produkController {





//    tambah data
    @FXML
    private TextField txtNamaProduk;

    @FXML
    private TextField txtHarga;

    @FXML
    private TextField txtStock;

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

    public void submitProduk(ActionEvent event) throws IOException{
        String namaProduk = txtNamaProduk.getText();
        double harga = Double.parseDouble(txtHarga.getText());
        int stock = Integer.parseInt(txtStock.getText());
        String kategori = cbKategori.getValue();

        Product product = new Product(
                namaProduk,
                harga,
                stock,
                kategori
        );

        productDAO dao = new productDAO();

        if (dao.tambahProduk(product)){
            System.out.println("berhasil");

            Parent root = FXMLLoader.load(getClass().getResource("/ui/dataProduk.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        }else {
            System.out.println("gagal");
        }


    }



//    end of tambah data

    @FXML
    public void tambahData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/tambahDataProduk.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }




}
