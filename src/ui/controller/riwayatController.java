package ui.controller;

import dao.riwayatDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.List;

import model.DetailTransaksi;
import model.Riwayat;

public class riwayatController {

    private int idLevel;
    private int idUser;

    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    public void setLevel(int idLevel){
        this.idLevel = idLevel;
    }

    public void btnBackToDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/dashboard.fxml"));

        Parent root = loader.load();

        dasboardController controller = loader.getController();

        controller.setLevel(idLevel);

        controller.setIdUser(idUser);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

//    tampil data transaksi
    @FXML
    private TableView<Riwayat> tableRiwayat;
    @FXML
    private TableColumn<Riwayat, Integer> colNo;
    @FXML
    private TableColumn<Riwayat, String> colKasir;
    @FXML
    private TableColumn<Riwayat, Double> colBayar;
    @FXML
    private TableColumn<Riwayat, Double> colTotal;
    @FXML
    private TableColumn<Riwayat, Double> colKembali;
    @FXML
    private TableColumn<Riwayat, String> colTanggal;
    @FXML
    private TableColumn<Riwayat, Void> colDetail;

    public void loadTableTransaksi(){
        colNo.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(
                        tableRiwayat.getItems().indexOf(cellData.getValue())+1
                )
        );
        colKasir.setCellValueFactory(
                new PropertyValueFactory<>("username")
        );
        colBayar.setCellValueFactory(
                new PropertyValueFactory<>("bayar")
        );
        colTotal.setCellValueFactory(
                new PropertyValueFactory<>("total")
        );
        colKembali.setCellValueFactory(
                new PropertyValueFactory<>("kembali")
        );
        colTanggal.setCellValueFactory(
                new PropertyValueFactory<>("tanggal")
        );
        colDetail.setCellFactory(param ->
                new TableCell<>() {
                    private final Button btnDetail = new Button("!");
                    {
                        btnDetail.setOnAction(event -> {

                            try {
                                Riwayat riwayat = getTableView().getItems().get(getIndex());
                                System.out.println("id : " + riwayat.getIdTransaksi());

                                int id = riwayat.getIdKasir();

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/riwayatPage/detailRiwayatTransaksi.fxml"));

                                Parent root = loader.load();

                                riwayatController controller = loader.getController();

                                controller.setLevel(idLevel);
                                controller.setIdUser(idUser);

                                controller.setData(riwayat);

                                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                                stage.setScene(new Scene(root));
                                stage.show();

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        });

                    }
                    @Override
                    protected void updateItem(Void item, boolean empty){
                        super.updateItem(item, empty);
                        if (empty){
                            setGraphic(null);
                        }else {
                            setGraphic(btnDetail);
                        }
                    }

                }
        );



        riwayatDAO dao = new riwayatDAO();

        ObservableList<Riwayat> data = FXCollections.observableList(dao.getAllRiwayat());
        tableRiwayat.setItems(data);


    }
//   end of tampil data transaksi

//    tampil data detail transaksi
    @FXML
    private VBox detailTransaksi;
    @FXML
    private Text noStruk;
    @FXML
    private Text dateTransaksi;
    @FXML
    private Text totalHarga;
    @FXML
    private Text totalBayar;
    @FXML
    private Text totalKembali;


    private Riwayat selectedRiwayat;
    public void setData(Riwayat riwayat){
        selectedRiwayat = riwayat;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd");
        noStruk.setText(String.valueOf("No. Struk : "+ riwayat.getIdTransaksi()));
        dateTransaksi.setText(sdf.format(riwayat.getTanggal()));
        totalHarga.setText(String.valueOf("Total : " + riwayat.getTotal()));
        totalBayar.setText(String.valueOf("Bayar : " + riwayat.getBayar()));
        totalKembali.setText(String.valueOf("Kembalian : "+ riwayat.getKembali()));
        loadDetailbarang();
    }

    private void loadDetailbarang(){
        riwayatDAO dao = new riwayatDAO();
        List<DetailTransaksi> detail = dao.detailTransaksi(
                selectedRiwayat.getIdTransaksi()
        );
        detailTransaksi.getChildren().clear();

        for(DetailTransaksi item : detail){

            Label label = new Label(
                    item.getNamaProduk() + "   " + item.getHarga() + "   " + "   " + item.getQty() + "x   " + item.getSubtotal()
            );

            detailTransaksi.getChildren().add(label);
        }
    }

//    end of tampil data detail transaksi



    public void initialize(){
        if(tableRiwayat != null){
            loadTableTransaksi();
        }
    }

}
