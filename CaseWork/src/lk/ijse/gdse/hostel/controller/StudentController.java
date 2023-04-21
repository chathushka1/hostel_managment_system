package lk.ijse.gdse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostel.bo.BOFactory;
import lk.ijse.gdse.hostel.bo.custom.StudentBO;
import lk.ijse.gdse.hostel.model.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class StudentController {
    public AnchorPane ancStudent;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTelephone;
    public JFXTextField txtSearch;
    public JFXButton btnNewStudentId;
    public JFXTextField txtStudentID;
    public ComboBox <String>cmbGender;
    public JFXTextField txtDOB;
    public TableColumn collStudentID;
    public TableColumn collName;
    public TableColumn collAddress;
    public TableColumn collDOB;
    public TableColumn collGender;
    public TableColumn collTelephone;
    public JFXButton btnSaveID;
    public TableView <StudentDTO> tblStudent;

    StudentBO studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENTBO);

    public void initialize(){
        setCmdGender();
        loadAllStudent();


        collStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        collDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        collGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        collTelephone.setCellValueFactory(new PropertyValueFactory<>("contact_no"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSaveID.setText(newValue != null ? "Update" : "save");

            if(newValue != null){
                txtStudentID.setText(newValue.getStudent_id());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtTelephone.setText(newValue.getContact_no());
                txtDOB.setText(newValue.getDob());

                if(newValue.getGender().equals("Male")){
                    setCmdGender();
                    cmbGender.getSelectionModel().select(0);
                }else {
                    cmbGender.getSelectionModel().select(1);
                }
            }
        });

    }
    public void loadAllStudent(){
        tblStudent.getItems().clear();

        try{
            ArrayList<StudentDTO>allStudents = (ArrayList<StudentDTO>) studentBO.getAllStudent();
            for(StudentDTO s : allStudents){
                tblStudent.getItems().add(new StudentDTO(s.getStudent_id(),s.getName(),s.getAddress(),s.getContact_no(),s.getDob(),s.getGender()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCmdGender(){
        ArrayList<String> genders=new ArrayList<>();
        genders.add("Male");
        genders.add("Female");

        ObservableList<String> observableList= FXCollections.observableList(genders);
        cmbGender.setItems(observableList);
    }
    public void btnGenarateNewIdOnAction(ActionEvent actionEvent) {
        txtStudentID.setText(generateNewIds());
        btnSaveID.setText("Save");

    }

    private String generateNewIds(){
        try {
            return studentBO.genarateStudentID();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "S00-001";
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {

        String id = txtStudentID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtTelephone.getText();
        String dob = txtDOB.getText();
        String gender = cmbGender.getSelectionModel().getSelectedItem();

        if (!name.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }else if (!address.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Address should not ").show();
            txtAddress.requestFocus();
            return;
        }else if (!contact.matches(".*(?:7|0|(?:\\\\+94))[0-9]{9,10}")){
            new Alert(Alert.AlertType.ERROR, "Contact should not ").show();
            txtTelephone.requestFocus();
            return;
        }else if (!dob.matches("\\b\\d{4}-\\d{2}-\\d{2}\\b")) {
            new Alert(Alert.AlertType.ERROR, "Dob should not ").show();
            txtDOB.requestFocus();
            return;
        }
        if(btnSaveID.getText().equalsIgnoreCase("Save")){
            try{
                studentBO.saveStudent(new StudentDTO(id,name,address,contact,dob,gender));
                tblStudent.getItems().add(new StudentDTO(id,name,address,contact,dob,gender));

            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            try{
                studentBO.updateStudent(new StudentDTO(id,name,address,contact,dob,gender));
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
                e.printStackTrace();
            }
            StudentDTO studentDTO = tblStudent.getSelectionModel().getSelectedItem();
            studentDTO.setName(name);
            studentDTO.setAddress(address);
            studentDTO.setContact_no(contact);
            studentDTO.setDob(dob);
            studentDTO.setGender(gender);
            tblStudent.refresh();
        }
        btnNewStudentId.fire();
    }
    public void btnClearOnAction(ActionEvent actionEvent) {
        txtStudentID.clear();
        txtAddress.clear();
        txtName.clear();
        txtDOB.clear();
        txtTelephone.clear();
        cmbGender.getSelectionModel().clearSelection();
    }
 
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO();
        String id = tblStudent.getSelectionModel().getSelectedItem().getStudent_id();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Delete ? ", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if(buttonType.get()== ButtonType.YES){

            try{
                studentBO.deleteStudent(new StudentDTO(txtStudentID.getText(),txtName.getText(),txtAddress.getText(),
                        txtDOB.getText(),txtTelephone.getText(),cmbGender.getSelectionModel().getSelectedItem()));
                tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
                tblStudent.getSelectionModel().clearSelection();

            }catch(Exception e){

            }
        }
    }


}
