package lk.ijse.gdse.hostel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane ancBackground;
    public static void navigation(Routers routers, AnchorPane ancBackground) throws IOException {
        Navigation.ancBackground=ancBackground;
        Navigation.ancBackground.getChildren().clear();
        Stage window = (Stage) Navigation.ancBackground.getScene().getWindow();

        switch(routers){
            case STUDENT:
                window.setTitle("Student");
                initUi("Student.fxml");
                break;
            case ROOMS:
                window.setTitle("Room");
                initUi("Room.fxml");
                break;
        }
    }
    private static void initUi(String location) throws IOException {
        Navigation.ancBackground.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/gdse/hostel/view/"+location)));

    }
}
