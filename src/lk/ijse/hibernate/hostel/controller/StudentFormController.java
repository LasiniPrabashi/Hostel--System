package lk.ijse.hibernate.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hibernate.hostel.bo.BOFactory;
import lk.ijse.hibernate.hostel.bo.custom.StudentBO;
import lk.ijse.hibernate.hostel.dto.StudentDTO;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StudentFormController {


    public JFXTextField txtStId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXComboBox cmbGender;
    public JFXDatePicker txtDate;
    public TableView tblStudent;
    public TableColumn colstId;
    public TableColumn colStName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDob;
    public TableColumn colGender;

    private Session session;
    private StudentBO studentBO = (StudentBO) BOFactory.getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(URL location, ResourceBundle resources) {
        setGender ();
        setTableStudent ();
        loadAllStudent ();
        setStID ();
    }

    private void setStID() {
    }

    private void setTableStudent() {
        colstId.setCellValueFactory (new PropertyValueFactory<>("student_id"));
        colStName.setCellValueFactory (new PropertyValueFactory<> ("stName"));
        colAddress.setCellValueFactory (new PropertyValueFactory<> ("address"));
        colDob.setCellValueFactory (new PropertyValueFactory<> ("dob"));
        colContact.setCellValueFactory (new PropertyValueFactory<> ("contactNo"));
        colGender.setCellValueFactory (new PropertyValueFactory<> ("gender"));
    }



    private void setGender() {
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("FeMale");
    }

    public void onActionUpdate (ActionEvent actionEvent){
                String dob = String.valueOf (txtDate.getValue ());
                String gender = cmbGender.getValue ().toString ();
                StudentDTO studentDTO = new StudentDTO (txtStId.getText (), txtName.getText (), txtAddress.getText (), txtContact.getText (), dob, gender);

                boolean isUpdate=studentBO.updateStudent (studentDTO);

                if (isUpdate){
                    new Alert (Alert.AlertType.INFORMATION, "Student Update Succuss").show ();
                    tblStudent.getItems ().clear ();
                    clearData ();
                    loadAllStudent ();
                   // setStID ();
                }else {
                    new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
                }

            }

    private void loadAllStudent() {
        try {
            List allStudents = studentBO.loadAll ();
            ObservableList observableList = FXCollections.observableArrayList (allStudents);
            tblStudent.setItems (observableList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }

    private void clearData() { 
                txtStId.clear();
                txtName.clear();
                txtAddress.clear();
                txtContact.clear();
                txtDate.setValue(null);
                cmbGender.setValue(null);
                
    }

    public void onActionDelete (ActionEvent actionEvent){
        String dob = String.valueOf (txtDate.getValue ());
        String gender = cmbGender.getValue ().toString ();
        StudentDTO studentDTO = new StudentDTO (
                txtStId.getText (), txtName.getText (), txtAddress.getText (), txtContact.getText (), dob, gender);

        boolean isDeleted=studentBO.deleteStudent (studentDTO);

        if (isDeleted){
            new Alert (Alert.AlertType.INFORMATION, "Student Delete Succuss").show ();
            tblStudent.getItems ().clear ();
            clearData ();
            loadAllStudent ();
            //setStID ();
        }else{
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    public void onActionSave(ActionEvent actionEvent) {

    }
}



