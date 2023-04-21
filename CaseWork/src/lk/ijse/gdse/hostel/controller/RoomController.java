package lk.ijse.gdse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel.bo.BOFactory;
import lk.ijse.gdse.hostel.bo.custom.RoomBO;
import lk.ijse.gdse.hostel.model.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class RoomController {

    public TableColumn collRoomID;
    public TableColumn collRoomType;
    public TableColumn collKeyMoney;
    public TableColumn collQty;
    @FXML
    private AnchorPane ancRoom;

    @FXML
    private JFXTextField txtRoomType;

    @FXML
    private JFXTextField txtKeyMoney;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXButton btnSaveID;

    @FXML
    private TableView<RoomDTO> tblRoom;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private Label lblDebtorId;

    @FXML
    private JFXTextField txtRoomId;

    @FXML
    private JFXButton btnNewRoomID;

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ROOMBO);

    public void initialize(){
        lordAllRoom();

        collRoomID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        collRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        collKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        collQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSaveID.setText(newValue != null ? "Update" : "save");

            if (newValue!=null){
                txtRoomId.setText(newValue.getRoom_type_id());
                txtRoomType.setText(newValue.getType());
                txtKeyMoney.setText(newValue.getKey_money());
                txtQuantity.setText(String.valueOf(newValue.getQty()));
            }
        });

    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtRoomId.clear();
        txtQuantity.clear();
        txtRoomType.clear();
        txtKeyMoney.clear();

    }

    private String generateNewIds() throws Exception {
        try {
            return roomBO.genarateRooms();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "R00-001";
    }
    private void lordAllRoom(){
        tblRoom.getItems().clear();

        try {
            ArrayList<RoomDTO>roomDTOArrayList= (ArrayList<RoomDTO>) roomBO.getAllRooms();
            System.out.println(roomDTOArrayList);
            for (RoomDTO  r : roomDTOArrayList){
                tblRoom.getItems().add(new RoomDTO(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getQty()));
            }

        }catch (Exception e){

        }

    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        RoomDTO roomDTO=new RoomDTO();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Delete ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            try {
                roomBO.deleteRooms(roomDTO);
                tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
                tblRoom.getSelectionModel().clearSelection();
            }catch (Exception e){

            }
        }
    }

    @FXML
    void btnGenarateNewRoomIdOnAction(ActionEvent event) throws Exception {
        txtRoomId.setText(generateNewIds());
        //btnSaveID.setText("Save");
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String roomId=txtRoomId.getText();
        String type=txtRoomType.getText();
        String keyMoney=txtKeyMoney.getText();
        int qty= Integer.parseInt(txtQuantity.getText());

        if (!type.matches("[A-Za-z0-9 ]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid Type").show();
            txtRoomType.requestFocus();
            return;
        }else if (!keyMoney.matches("^[0-9]+[.]?[0-9]*$")){
            new Alert(Alert.AlertType.ERROR, "Invalid keyMoney").show();
            txtKeyMoney.requestFocus();
            return;
        }else if (!txtQuantity.getText().matches("^\\d+$")){
            new Alert(Alert.AlertType.ERROR, "Invalid keyMoney").show();
            txtQuantity.getText();
            return;
        }

        if (btnSaveID.getText().equalsIgnoreCase("Save")){
            try {
                roomBO.saveRooms(new RoomDTO(roomId,type,keyMoney,qty));

                tblRoom.getItems().add(new RoomDTO(roomId,type,keyMoney,qty));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                e.printStackTrace();
            }

        }else {
            try {
                roomBO.updateRooms(new RoomDTO(roomId,type,keyMoney,qty));
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Failed to update the Room " + roomId + e.getMessage()).show();
            }
            RoomDTO roomDTO= tblRoom.getSelectionModel().getSelectedItem();
            roomDTO.setType(type);
            roomDTO.setKey_money(keyMoney);
            roomDTO.setQty(qty);
            tblRoom.refresh();
        }
       // btnSaveID.fire();
    }

}
