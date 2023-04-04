package lk.ijse.gdse.hostel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.gdse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

import static javafx.application.Application.launch;

public class AppInitializer{
    public static void main(String[] args) {
        //launch(args);

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


    }
  /*  @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/gdse/hostel/view/Login.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Hostel Management System");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }*/
}
