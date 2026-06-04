package ui.controller;

import dao.productDAO;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import model.Product;

import java.io.IOException;

public class produkController {

//    button kembali
    @FXML
    public void  backBtnToDataProduk(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/produkPage/dataProduk.fxml"));
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
        if (id_level == 2 && btnTambahData != null){
            btnTambahData.setVisible(false);
            btnTambahData.setManaged(false);
        }
        if (colOpsi != null){
            tableProduk
                    .getColumns()
                    .remove(colOpsi);
        }
    }

//    end of button tambah data





//  path ke tambah data
    @FXML
    public void tambahData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/produkPage/tambahDataProduk.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
//    end of path ke tambah data




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

//  tambah data(

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

                    Parent root = FXMLLoader.load(getClass().getResource("/ui/produkPage/dataProduk.fxml"));
                    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));

        }else {
            System.out.println("gagal");
        }


    }

//    end of tambah data

//    edit data

        private Product selectedProduct;

        public void setData(Product product){
            selectedProduct = product;

            txtNamaProduk.setText(product.getNamaProduk());

            txtHarga.setText(String.valueOf(product.getHarga()));

            txtStock.setText(String.valueOf(product.getStock()));

            cbKategori.setValue(product.getKategori());
        }

        public void editProduk(){

        }

//    end of edit data


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

    @FXML
    private TableColumn<Product, Void> colOpsi;




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

        colOpsi.setCellFactory(param ->
                new TableCell<>(){
                    private final Button btnEdit = new Button("edit");

                    private final Button btnDelete = new Button("delete");

                    private final HBox pane = new HBox(5, btnEdit, btnDelete);
                    {

// Edit data
                        btnEdit.setOnAction(event -> {

                            try{
                                Product product = getTableView().getItems().get(getIndex());
                                System.out.println("edit : " + product.getIdProduk());

                                int id = product.getIdProduk();

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/produkPage/editDataProduk.fxml"));

                                Parent root = loader.load();

                                produkController controller = loader.getController();

                                controller.setData(product);

                                Stage stage = (Stage)getTableView().getScene().getWindow();

                                stage.setScene(new Scene(root));

                                stage.show();

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        });

// End of Edit data

//  Delete data
                        btnDelete.setOnAction(event -> {
                            Product product = getTableView().getItems().get(getIndex());
                            System.out.println("delete : " + product.getIdProduk());

                            int id = product.getIdProduk();

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                            alert.setTitle("Konfirmasi");

                            alert.setHeaderText("Hapus Produk");

                            alert.setContentText(
                                    "Apakah anda yakin akan menghapus menu " + product.getNamaProduk() + " ?"
                            );

                            Optional<ButtonType> result = alert.showAndWait();

                            if (result.isPresent() && result.get() == ButtonType.OK){
                                productDAO dao = new productDAO();

                                boolean hapusData = dao.deleteProduk(id);

                                if (hapusData){
                                    loadTable();
                                }
                            }


                        });
                    };

//  End of Delete Data


                    @Override
                    protected void updateItem(
                            Void item, boolean empty
                    ){
                        super.updateItem(item, empty);

                        if(empty){
                            setGraphic(null);
                        }else {
                            setGraphic(pane);
                        }
                    }
                });

        productDAO dao = new productDAO();

        ObservableList<Product> data = FXCollections.observableList(dao.getAllProduk());

        tableProduk.setItems(data);
    }

//    end of tampil data





}
