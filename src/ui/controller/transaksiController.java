package ui.controller;

import dao.produkDAO;
import dao.transaksiDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Keranjang;
import model.Produk;
import model.Riwayat;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class transaksiController {

    public void backBtnToDashboard(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/dashboard.fxml"));

        Parent root = loader.load();

        dasboardController controller = loader.getController();

        controller.setLevel(idLevel);
        controller.setIdUser(idUser);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private int idUser;
    private int idLevel;
    public void setIdLevel(int idLevel){
        this.idLevel = idLevel;
    }
    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

//    tampil data

    @FXML
    private TableView<Produk> tableProduk;

    @FXML
    private TableColumn<Produk, Integer> colNo;

    @FXML
    private TableColumn<Produk, String> colProduk;

    @FXML
    private TableColumn<Produk, Integer> colStock;

    @FXML
    private TableColumn<Produk, Double> colHarga;

    @FXML
    private TableColumn<Produk, Void> colAksi;

    public void loadTable(){

        colNo.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(
                        tableProduk.getItems().indexOf(cellData.getValue())+1
                )
        );
        colProduk.setCellValueFactory(
                new PropertyValueFactory<>("namaProduk")
        );
        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );
        colHarga.setCellValueFactory(
                new PropertyValueFactory<>("harga")
        );
        colAksi.setCellFactory(param -> new TableCell<Produk, Void>(){
                private final Button btnAdd = new Button("+");

                {
                    btnAdd.setOnAction(event -> {
                        Produk produk = getTableView().getItems().get(getIndex());

                        Keranjang isi = null;

                        for (Keranjang item : keranjang){
                            if (item.getProduk().getIdProduk() == produk.getIdProduk()){
                                isi = item;
                                break;
                            }
                        }

                        if (isi != null){
                            if (isi.getQty() < produk.getStock()){
                                isi.setQty(isi.getQty()+1);
                                tableKeranjang.refresh();
                            }else{
                                System.out.println("Stock tidak mencukupi");
                            }
                        }else{
                            if (produk.getStock() > 0){
                                keranjang.add(new Keranjang(produk, 1));
                            }else {
                                System.out.println("stock habis");
                            }
                        }
                        updateTotal();
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                    }else{
                        setGraphic(btnAdd);
                    }
                }
                });


        produkDAO dao = new produkDAO();

        List<Produk> listProduk = dao.getAllProduk();

        ObservableList<Produk> data = FXCollections.observableArrayList(listProduk);

        tableProduk.setItems(data);
    }

    public void initialize(){
        tableKeranjang.setItems(keranjang);
        loadTable();
        loadTableKeranjang();
    }

//    end of tampil data

//    Keranjang
    @FXML
    private TableView<Keranjang> tableKeranjang;

    @FXML
    private TableColumn<Keranjang, Integer> colNoKeranjang;

    @FXML
    private TableColumn<Keranjang, String> colProdukKeranjang;

    @FXML
    private TableColumn<Keranjang, Double> colHargaKeranjang;

    @FXML
    private TableColumn<Keranjang, Integer> colQtykeranjang;

    @FXML
    private TableColumn<Keranjang, Double> colSubtotalKeranjang;

    @FXML
    private TableColumn<Keranjang, Void> colAksikeranjang;

    public void loadTableKeranjang(){
        colNoKeranjang.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(
                        tableKeranjang.getItems().indexOf(cellData.getValue())+1
                )
        );
        colProdukKeranjang.setCellValueFactory(
                new PropertyValueFactory<>("namaProduk")
        );
        colHargaKeranjang.setCellValueFactory(
                new PropertyValueFactory<>("harga")
        );
        colQtykeranjang.setCellValueFactory(
                new PropertyValueFactory<>("qty")
        );
        colSubtotalKeranjang.setCellValueFactory((
                new PropertyValueFactory<>("subtotal")
        ));

        colAksikeranjang.setCellFactory(param -> new TableCell<>(){
            private final Button btnMin = new Button("-");

            @Override
            protected void updateItem(Void item, boolean empty){
                super.updateItem(item, empty);
                {
                    btnMin.setOnAction(event -> {
                        Keranjang isi = getTableView().getItems().get(getIndex());

                        if (isi.getQty() > 1){
                            isi.setQty(isi.getQty()-1);
                        }else{
                            keranjang.remove(isi);
                        }
                        tableKeranjang.refresh();
                        updateTotal();
                    });
                }
                if (empty){
                    setGraphic(null);
                }else {
                    setGraphic(btnMin);
                }
            }
        });

    }

    private ObservableList<Keranjang> keranjang = FXCollections.observableArrayList();


//    end of Keranjang

//    hitung total

    @FXML
    private Label txtTotal;

    @FXML
    private TextField txtBayar;

    @FXML
    private Label txtKembalian;

    public double hitungTotal(){
        double total = 0;

        for(Keranjang item : keranjang){
            total += item.getSubtotal();
        }
        return total;
    }

    public double updateTotal(){
        double total = hitungTotal();

        txtTotal.setText(String.valueOf(total));

        return total;
    }

    @FXML
    public void checkout(ActionEvent event)throws IOException{
        double total = hitungTotal();

        double bayar = Double.parseDouble(txtBayar.getText());

        if (bayar < total){
            System.out.print("bayar nya kurang kocak");
            txtKembalian.setText("uang kurang");
            return;
        }
        double kembali = bayar - total;

        txtKembalian.setText(String.valueOf(kembali));
        transaksiDAO dao = new transaksiDAO();

        int idTransaksi = dao.insertTransaksi(
                idUser,total,bayar,kembali
        );
        System.out.println("id transaksi = " + idTransaksi);

        if (idTransaksi == 0){
            System.out.println("gagal ambil id transaksi");
            return;
        }

        dao.insertDetail(
                idTransaksi,keranjang
        );

        dao.updateStock(keranjang);

        Riwayat riwayat =
                new Riwayat(
                        idTransaksi,
                        idUser,
                        bayar,
                        total,
                        kembali,
                        new Date()
                );

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/riwayatPage/detailRiwayatTransaksi.fxml"));

        Parent root =
                loader.load();

        riwayatController controller = loader.getController();

        controller.setData(riwayat);
        controller.setLevel(idLevel);
        controller.setIdUser(idUser);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(
                new Scene(root)
        );

        stage.show();


//        keranjang.clear();
//        tableKeranjang.refresh();
//        updateTotal();
//        txtBayar.clear();


    }

//    end of hitung total



    public void btnReset(){

        System.out.println("reset");
        keranjang.clear();
        tableKeranjang.refresh();
        updateTotal();
        txtBayar.clear();
        txtKembalian.setText("0");
    }



    public void setLevel(int idLevel) {
        this.idLevel = idLevel;
    }
}
