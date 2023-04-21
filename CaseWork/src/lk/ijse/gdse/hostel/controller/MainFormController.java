package lk.ijse.gdse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel.util.Navigation;
import lk.ijse.gdse.hostel.util.Routers;

import java.io.IOException;

public class MainFormController {
    public AnchorPane ancMainForm;
    public AnchorPane ancSideAnchorPane;

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.STUDENT,ancSideAnchorPane);
    }

    public void btnRoomOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.ROOMS,ancSideAnchorPane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
    }
}
