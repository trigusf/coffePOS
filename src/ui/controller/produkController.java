package ui.controller;

import dao.productDAO;
import javafx.beans.property.ReadOnlyMapProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Product;

import java.io.IOException;

public class produkController {

//    button kembali
    @FXML
    public void  backBtnToDataProduk(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/dataProduk.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void backBtnToDashboard(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/dashboard.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
//    end of button kembali

//    button tambah data

    @FXML
    private Button btnTambahData;

    public void setLevel(int id_level){
        if (id_level == 2){
            btnTambahData.setVisible(false);
            btnTambahData.setManaged(false);
        }
    }

//    end of button tambah data





//  path ke tambah data
    @FXML
    public void tambahData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/tambahDataProduk.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
//    end of path ke tambah data



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

        if(tableProduk != null){
            loadTable();
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


//    tampil data

    @FXML
    private TableView<Product> tableProduk;

    @FXML
    private TableColumn<Product, Integer> colNo;

    @FXML
    private TableColumn<Product, String> colNama;

    @FXML
    private TableColumn<Product, Double> colHarga;

    @FXML
    private TableColumn<Product, Integer> colStock;

    @FXML
    private TableColumn<Product, String> colKategori;



    public void loadTable(){
        colNo.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(
                        tableProduk.getItems().indexOf(cellData.getValue())+1
                )
        );

        colNama.setCellValueFactory(
                new PropertyValueFactory<>(
                        "namaProduk"
                )
        );

        colHarga.setCellValueFactory(
                new PropertyValueFactory<>(
                        "harga"
                )
        );

        colStock.setCellValueFactory(
                new PropertyValueFactory<>(
                        "stock"
                )
        );

        colKategori.setCellValueFactory(
                new PropertyValueFactory<>(
                        "kategori"
                )
        );

        productDAO dao = new productDAO();

        ObservableList<Product> data = FXCollections.observableList(dao.getAllProduk());

        tableProduk.setItems(data);
    }

//    end of tampil data





}
