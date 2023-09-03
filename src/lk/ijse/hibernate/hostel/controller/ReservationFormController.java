package lk.ijse.hibernate.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hibernate.hostel.bo.BOFactory;
import lk.ijse.hibernate.hostel.bo.custom.ReservationBO;
import lk.ijse.hibernate.hostel.dto.ReservationDTO;
import lk.ijse.hibernate.hostel.dto.RoomDTO;
import lk.ijse.hibernate.hostel.dto.StudentDTO;
import lk.ijse.hibernate.hostel.dto.tm.ReservationTm;
import lk.ijse.hibernate.hostel.util.Navigation;
import lk.ijse.hibernate.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationFormController implements Initializable {
    public JFXTextField txtResId;
    public JFXTextField txtStName;
    public JFXComboBox cmbStatus;
    public JFXTextField txtQty;
    public JFXComboBox cmbROOMId;
    public JFXComboBox cmbSTId;
    public JFXTextField txtRoomType;
    public TableColumn colResId;
    public TableColumn colStId;
    public TableColumn colStName;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colStatus;
    public TableView tblReservation;


    private ReservationBO resBO = (ReservationBO) BOFactory.getBO (BOFactory.BOTypes.RESERVATION);
    public static ObservableList<StudentDTO> stIds = FXCollections.observableArrayList ();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setIds ();
        setData ();
        setStatus ();
        setCellValueFactory ();
        loadAllRes();
        setResID ();
    }

    private void setCellValueFactory() {
        colResId.setCellValueFactory (new PropertyValueFactory<>("resId"));
        colStId.setCellValueFactory (new PropertyValueFactory<> ("stId"));
        colStName.setCellValueFactory (new PropertyValueFactory<> ("stName"));
        colRoomId.setCellValueFactory (new PropertyValueFactory<> ("roomId"));
        colRoomType.setCellValueFactory (new PropertyValueFactory<> ("roomName"));
        colStatus.setCellValueFactory (new PropertyValueFactory<> ("status"));

    }


    public void onActionSaveRes(ActionEvent actionEvent) {
        StudentDTO studentDTO = getStudnetDetail ();
        RoomDTO roomDTO = getRoomDetail ();
        String resId = txtResId.getText ();
        String status = cmbStatus.getValue ().toString ();
        java.sql.Date sqlDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        if (checkDuplicate ()) {
            boolean isSaveReservation = resBO.saveReservation (
                    new ReservationDTO(
                            resId,
                            sqlDate,
                            studentDTO,
                            roomDTO,
                            status
                    ));
            if (isSaveReservation) {
                RoomDTO room = getRoomDetail ();

                System.out.println (room.getQty () - 1);
                room.setQty (room.getQty () - 1);
                resBO.updateRoom (room);
                // tblReservation.getItems ().clear ();
                loadAllRes ();
            }
        }
    }

    private void loadAllRes() {
        try {
            List<ReservationDTO> allRoom = resBO.loadAll ();

            ObservableList<ReservationTm> resList = FXCollections.observableArrayList ();

            for (ReservationDTO dto : allRoom) {
                resList.add (new ReservationTm (
                        dto.getResID (),
                        dto.getStudentDTO ().getStId (),
                        dto.getStudentDTO ().getStName (),
                        dto.getRoomDTO ().getRoomID (),
                        dto.getRoomDTO ().getType (),
                        dto.getStatus ()
                ));
            }

            tblReservation.setItems (resList);

            System.out.println (resList);


        } catch (Exception e) {
            System.out.println (e);
        }
    }

    private RoomDTO getRoomDetail() {
        try {
            String roomId = cmbROOMId.getValue ().toString ();
            System.out.println ("This IS ROOM ID"+roomId);
            return resBO.getRoom (roomId);
        } catch (Exception e) {
            System.out.println (e);
        }
        return null;
    }

    private StudentDTO getStudnetDetail() {
        String stId = cmbSTId.getValue ().toString ();
        return resBO.getStudent (stId);
    }

    private boolean checkDuplicate(){
        String resId = txtResId.getText();
        List<ReservationDTO> list = resBO.loadAll();

        for (ReservationDTO dto : list){
            if (resId.equals (dto.getResID ())){
                new Alert(Alert.AlertType.CONFIRMATION, "RESERVATION ADDED SUCCUSS").show ();
                return false;
            }
        }
        return true;
    }

    public void onActionUpdateRes(ActionEvent actionEvent) {
        String stId = cmbSTId.getValue ().toString ();
        String roomID = cmbROOMId.getValue ().toString ();
        String status = cmbStatus.getValue ().toString ();
        String resId = txtResId.getText ();
        StudentDTO studentDTO = getStudnetDetail ();
        RoomDTO roomDTO = getRoomDetail ();
        java.sql.Date sqlDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        try {
            boolean isUpdate = resBO.updateReservation (
                    new ReservationDTO (
                            resId,
                            sqlDate,
                            studentDTO,
                            roomDTO,
                            status
                    ));
            loadAllRes ();
        } catch (Exception e) {
            e.printStackTrace ();
        }

    }

    public void onActionDeleteRes(ActionEvent actionEvent) {
        String stId = cmbSTId.getValue ().toString ();
        String roomID = cmbROOMId.getValue ().toString ();
        String status = cmbStatus.getValue ().toString ();
        String resId = txtResId.getText ();
        StudentDTO studentDTO = getStudnetDetail ();
        RoomDTO roomDTO = getRoomDetail ();
        java.sql.Date sqlDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        try {
            boolean isDelete = resBO.deleteReservation (
                    new ReservationDTO(
                            resId,
                            sqlDate,
                            studentDTO,
                            roomDTO,
                            status
                    ));
            if (isDelete) {
                RoomDTO room = getRoomDetail ();
                room.setQty (room.getQty () + 1);
                resBO.updateRoom (room);
                loadAllRes ();

            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public void onMouseClickReservation(MouseEvent mouseEvent) {
        int index = tblReservation.getSelectionModel ().getSelectedIndex ();
        String resID = colResId.getCellData (index).toString ();//select Column value

        try {
            ReservationDTO dto = resBO.getRes (resID);
            System.out.println (dto.getResID ());
            txtResId.setText (dto.getResID ());
            txtRoomType.setText (dto.getRoomDTO ().getType ());
            txtStName.setText (dto.getStudentDTO ().getStName ());
            String stID = dto.getStudentDTO ().getStId ();
            cmbSTId.setValue (stID);
            cmbROOMId.setValue (dto.getRoomDTO ().getRoomID ());
            cmbStatus.setValue (dto.getStatus ());
            txtQty.setText (String.valueOf (dto.getRoomDTO ().getQty ()));
        } catch (Exception e) {
            System.out.println (e);
        }
    }

    private void setIds() {
        List<String> stIds = resBO.getStudentIds ();
        ObservableList student = FXCollections.observableArrayList (stIds);
        cmbSTId.setItems (student);

        List<String> roomIds = resBO.getRoomIds ();
        ObservableList room = FXCollections.observableArrayList (roomIds);
        cmbROOMId.setItems (room);
    }

    private void setStatus() {
        ObservableList<String> data = FXCollections.observableArrayList ("PAID", "UNPAID");
        cmbStatus.setItems (data);
    }

    private void setData() {
        cmbSTId.setOnAction (event -> {
            String stId = cmbSTId.getValue ().toString ();
            StudentDTO dto = resBO.getStudent (stId);
            txtStName.setText (dto.getStName ());
        });


        cmbROOMId.setOnAction (event -> {
            String roomId = cmbROOMId.getValue ().toString ();
            RoomDTO dto = resBO.getRoom (roomId);
            txtRoomType.setText (dto.getType ());
            txtQty.setText (String.valueOf (dto.getQty ()));
        });
    }

    private void setResID() {
        String resID=nextResId ();
        txtResId.setText (resID);
    }

    private String nextResId() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select resId from Reservation order by resId desc");

        String nextId = "T001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("T00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.parseInt (id);

            id = "T00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }

    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",mouseEvent);
    }
}
