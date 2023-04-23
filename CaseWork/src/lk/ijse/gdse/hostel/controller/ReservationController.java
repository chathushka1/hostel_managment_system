package lk.ijse.gdse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel.bo.BOFactory;
import lk.ijse.gdse.hostel.bo.custom.ReservationBO;
import lk.ijse.gdse.hostel.model.ReservationDTO;
import lk.ijse.gdse.hostel.model.RoomDTO;
import lk.ijse.gdse.hostel.model.StudentDTO;
import lk.ijse.gdse.hostel.projection.StudentDetailsDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationController {

    public JFXTextField txtRoom;
    public CheckBox cbxStatus;
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


    private StudentDTO studentData;
    private RoomDTO roomData;
    private String id;

    ReservationBO reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.RESERVATIONBO);

    public void initialize() throws Exception {
        //loadAll();
        loadAllRooms();
        loadAllStudents();
        //setAllProjection();
       // setCheckStatus();

        collReservationID.setCellValueFactory(new PropertyValueFactory<>("resId"));
        collStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        collRoomID.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        collDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        collStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


        /*colReservation.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));*/

    }
    void setCheckStatus(){
        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSaveID.setText(newValue!=null ? "update" : "save");

            if (newValue!=null){
                if (newValue != null) {
                    id= newValue.getResId();
                }
            }
        });
    }

    private void  loadAllStudents(){
        try {
            ArrayList<StudentDTO> allStudent= (ArrayList<StudentDTO>) reservationBO.geAllStudents();
            for (StudentDTO s : allStudent){
                cmbStudentID.getItems().add(s.getStudent_id());
                if (s.getStudent_id().equals(cmbStudentID.getValue())){
                    txtStudentName.setText(s.getAddress());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void  loadAllRooms(){
        try {
            ArrayList<RoomDTO>allRooms= (ArrayList<RoomDTO>) reservationBO.getAllRooms();
            for (RoomDTO r : allRooms){
                cmbRoomID.getItems().add(r.getRoom_type_id());
                if (r.getRoom_type_id().equals(cmbRoomID.getValue())){
                    txtRoom.setText(String.valueOf(r.getQty()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private StudentDTO getStudentDTO() throws Exception {
        String studentId=cmbStudentID.getSelectionModel().getSelectedItem();
        return reservationBO.getStudents(studentId);
    }

    private RoomDTO getRoomDTO() throws Exception {
        String roomId=cmbRoomID.getSelectionModel().getSelectedItem();
        return reservationBO.getRooms(roomId);
    }
    private void loadAll() throws Exception {
        List<ReservationDTO> reservationDTOList=reservationBO.getAllReservation();
        ObservableList<ReservationDTO> dtoObservableList= FXCollections.observableList(reservationDTOList);

        tblReservation.setItems(dtoObservableList);

    }

    private String generateNewIds(){
        try {
            return reservationBO.generateReservationId();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "RES-001";
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtReservationId.clear();
        txtRoom.clear();
        cmbStudentID.getSelectionModel().clearSelection();
        cmbRoomID.getSelectionModel().clearSelection();
        txtStudentName.clear();
    }

    @FXML
    void btnGenarateNewReservationIdOnAction(ActionEvent event) {
        txtReservationId.setText(generateNewIds());

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws Exception {
        if (btnSaveID.getText().equals("Save Reservation")){
            boolean isSaved=reserveRoom(getData());
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Reserved").show();
                tblReservation.getItems().clear();
                tblReservation.refresh();
                loadAll();

            }else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        }else if(btnSaveID.getText().equals("update")){
            if (cbxStatus.isSelected()) {
                btnSaveID.setDisable(false);
                String status = "paid";

                boolean isUpdated = reservationBO.checkStatusClick(id, status);
                tblReservation.refresh();
                if (isUpdated) {
                    tblReservation.getItems().clear();
                    tblReservation.getItems().clear();

                    new Alert(Alert.AlertType.CONFIRMATION, "Status updated").show();

                    loadAll();
                    setAllProjection();


                    btnSaveID.setDisable(true);
                    tblReservation.refresh();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }
        }
    }
    private void setAllProjection(){
        List<StudentDetailsDTO>list=reservationBO.getAllProjection();
        ObservableList<StudentDetailsDTO>studentDetailsDTOS=FXCollections.observableList(list);

        tblReservation.refresh();
       // tblReservation.setItems(studentDetailsDTOS);

    }
    private ReservationDTO getData() throws Exception {

        String status="unPaid";
        if (cbxStatus.isSelected()){
            status="paid";
        }

        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        StudentDTO studentData= getStudentDTO();
        RoomDTO roomData=getRoomDTO();

        return new ReservationDTO(
                txtReservationId.getText(),
                sqlDate,
                status,
                studentData,
                roomData
        );
    }

    private boolean reserveRoom(ReservationDTO reservationDTO) throws Exception {
        boolean isSaved= Boolean.parseBoolean(reservationBO.saveReservation(reservationDTO));

        if (!isSaved){
            return false;
        }

        RoomDTO roomDTO=reservationDTO.getRoomDTO();
        roomDTO.setQty(roomDTO.getQty()-1);

        boolean isUpdate=reservationBO.updateRoomQty(roomDTO);

        if (!isUpdate){
            return false;
        }
        return true;
    }
    public void cbxStatusOnAction(ActionEvent event) {
        if (btnSaveID.getText().equals("Update")){
            if (cbxStatus.isSelected()){
                btnSaveID.setDisable(false);

                String status="paid";

            }else if (!cbxStatus.isSelected())btnSaveID.setDisable(true);
        }
    }

    public void cmbStudentOnAction(ActionEvent actionEvent) throws Exception {
        studentData=getStudentDTO();
        txtStudentName.setText(studentData.getName());
    }

    public void cmbRoomOnAction(ActionEvent actionEvent) throws Exception {
        roomData=getRoomDTO();
        txtRoom.setText(String.valueOf(roomData.getQty()));
    }
}
