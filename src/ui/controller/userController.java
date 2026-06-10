package ui.controller;

import dao.userDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Produk;
import model.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class userController {

    private int idLevel;
    private int idUser;

    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    public void setLevel(int idLevel){
        this.idLevel = idLevel;
    }

    @FXML
    public void backBtnToDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/dashboard.fxml"));

        Parent root = loader.load();

        dasboardController controller = loader.getController();

        controller.setLevel(idLevel);

        controller.setIdUser(idUser);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, Integer> colNo;

    @FXML
    private TableColumn<User, String> colNama;

    @FXML
    private TableColumn<User, String> colLevel;

    @FXML
    private TableColumn<User, Void> colOpsi;

    public void loadTable(){
        colNo.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(tableUser.getItems().indexOf(cellData.getValue())+1));

        colNama.setCellValueFactory(
                new PropertyValueFactory<>("username")
        );
        colLevel.setCellValueFactory(
                new PropertyValueFactory<>("level")
        );
        colOpsi.setCellFactory(param ->
                new TableCell<>(){
                    private final Button btnEdit = new Button("edit");
                    private final Button btnDelete = new Button("delete");
                    private HBox pane = new HBox(5, btnEdit, btnDelete);
                    {
                        btnEdit.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            System.out.println("id : " + user);
                        });

                        btnDelete.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            System.out.println("id : " + user);

//                            int id = user.getId();
//                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//
//                            alert.setTitle("Konfimasi");
//
//                            alert.setHeaderText("Hapus Data User");
//
//                            alert.setContentText(
//                                    "Apakah anda yakin akan menghapus menu " + user.getUsername() + " ?"
//                            );
//                            Optional<ButtonType> result = alert.showAndWait();
//                            if (result.isPresent() && result.get() == ButtonType.OK){
//                                userDAO dao = new userDAO();
//
//                                boolean hapusData = dao.hapusUser(id);
//                                if (hapusData){
//                                    loadTable();
//                                }
//                            }
                        });

                    };
                    @Override
                    protected  void updateItem(Void item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty){
                            setGraphic(null);
                        }else {
                            setGraphic(pane);
                        }
                    }

                });

            userDAO dao = new userDAO();
        ObservableList<User> data = FXCollections.observableList(dao.getAllUser());
        tableUser.setItems(data);
    }


    public void initialize(){
        if (tableUser != null){
            loadTable();
        }
    }

    public void tambahData(){

    }

    public void backBtnToDashboard(){

    }


}
