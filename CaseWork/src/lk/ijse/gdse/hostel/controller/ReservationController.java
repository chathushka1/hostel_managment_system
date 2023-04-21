package lk.ijse.gdse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel.model.ReservationDTO;

public class ReservationController {

    @FXML
    private AnchorPane ancReservation;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    private JFXTextField txtStatus;

    @FXML
    private JFXButton btnSaveID;

    @FXML
    private TableView<ReservationDTO> tblReservation;

    @FXML
    private TableColumn<?, ?> collReservationID;

    @FXML
    private TableColumn<?, ?> collDate;

    @FXML
    private TableColumn<?, ?> collStudentID;

    @FXML
    private TableColumn<?, ?> collRoomID;

    @FXML
    private TableColumn<?, ?> collStatus;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private Label lblDebtorId;

    @FXML
    private JFXTextField txtReservationId;

    @FXML
    private JFXButton btnNewReservationId;

    @FXML
    private ComboBox<String> cmbStudentID;

    @FXML
    private ComboBox<String> cmbRoomID;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnGenarateNewReservationIdOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

    }

}
