import database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.Connection;


public class Main extends Application{
    @Override
    public void start(Stage stage)throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/login.fxml"));
        Scene scene = new Scene(loader.load(),800,600);
        stage.setScene(scene);
        stage.setTitle("BrewSpace");
        stage.show();

    }

    public static void main(String[] args){
        launch();
    }
}