package lk.ijse.hibernate.hostel.controller;

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
import lk.ijse.hibernate.hostel.bo.custom.RoomBO;
import lk.ijse.hibernate.hostel.dto.RoomDTO;
import lk.ijse.hibernate.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {
    public JFXTextField txtRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeymoney;
    public JFXTextField txtQty;
    public TableView tblRoom;
    public TableColumn colRoomId;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public TableColumn colRoomType;

    private RoomBO roomBO = (RoomBO) BOFactory.getBO (BOFactory.BOTypes.ROOM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableRoom ();
        loadAllRoom ();
        setRoomID ();
    }

    public boolean checkduplicate() {
        String roomId = txtRoomId.getText ();
        List<RoomDTO> allRooms = roomBO.loadAll ();
        for (RoomDTO r : allRooms) {
            if (roomId.equals (r.getRoomID ())) {
                new Alert (Alert.AlertType.ERROR, "This ID Already Have").show ();
                return false;
            }
        }
        return true;
    }

    public void onActionSave(ActionEvent actionEvent) {


        if(checkValidation ()){
            int qty = Integer.parseInt (txtQty.getText ());
            String roomId = txtRoomId.getText ();
            String roomType = txtRoomType.getText ();
            String keyMoney = txtKeymoney.getText ();

            RoomDTO roomDTO = new RoomDTO (
                    roomId,
                    roomType,
                    keyMoney,
                    qty
            );

            try {

                List<RoomDTO> allRooms = roomBO.loadAll ();

                if (checkduplicate ()) {
                    if (checkValidation ()){
                        roomBO.saveRoom (roomDTO);
                        loadAllRoom ();
                        setRoomID ();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace ();
            }
        }

    }

    public void onActionDelete(ActionEvent actionEvent) {
        int qty = Integer.parseInt (txtQty.getText ());
        RoomDTO roomDTO = new RoomDTO (
                txtRoomId.getText (),
                txtRoomType.getText (),
                txtKeymoney.getText (),
                qty
        );

        boolean isDeleted = roomBO.deleteRoom (roomDTO);

        if (isDeleted) {
            new Alert (Alert.AlertType.INFORMATION, "Room Delete Succuss").show ();
            tblRoom.getItems ().clear ();
            clearData ();
            loadAllRoom ();
            setRoomID ();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }

    }

    public void onActionUpdate(ActionEvent actionEvent) {
        int qty = Integer.parseInt (txtQty.getText ());
        RoomDTO roomDTO = new RoomDTO (
                txtRoomId.getText (),
                txtRoomType.getText (),
                txtKeymoney.getText (),
                qty
        );

        boolean isUpdate = roomBO.updateRoom (roomDTO);

        if (isUpdate) {
            new Alert (Alert.AlertType.INFORMATION, "Student Update Succuss").show ();
            tblRoom.getItems ().clear ();
            clearData ();
            loadAllRoom ();
            setRoomID ();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    public void clearData() {
        txtRoomId.clear ();
        txtRoomType.clear ();
        txtKeymoney.clear ();
        txtQty.clear ();
    }

    public void setTable() {
        colRoomId.setCellValueFactory (new PropertyValueFactory<> ("roomId"));
        colRoomType.setCellValueFactory (new PropertyValueFactory<> ("type"));
        colKeyMoney.setCellValueFactory (new PropertyValueFactory<> ("keyMoney"));
        colQty.setCellValueFactory (new PropertyValueFactory<> ("qty"));

    }

    public void loadAllRoom() {

        try {
            List allRoom = roomBO.loadAll ();
            ObservableList observableList = FXCollections.observableArrayList (allRoom);
            tblRoom.setItems (observableList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }

    public void setTableRoom() {
        colRoomId.setCellValueFactory (new PropertyValueFactory<> ("roomID"));
        colRoomType.setCellValueFactory (new PropertyValueFactory<> ("type"));
        colKeyMoney.setCellValueFactory (new PropertyValueFactory<> ("keyMoney"));
        colQty.setCellValueFactory (new PropertyValueFactory<> ("qty"));
    }

    public void OnActionMiuseClickedRoom(MouseEvent mouseEvent) {
        int index = tblRoom.getSelectionModel ().getSelectedIndex ();
        String roomId = colRoomId.getCellData (index).toString ();//select Column value

        try {
            RoomDTO dto = roomBO.getRoom (roomId);
            txtRoomId.setText (dto.getRoomID ());
            txtRoomType.setText (dto.getType ());
            txtKeymoney.setText (dto.getKeyMoney ());
            txtQty.setText (String.valueOf (dto.getQty ()));
        } catch (Exception e) {
            System.out.println (e);
        }
    }

    private boolean checkValidation() {
        String typeText = txtRoomType.getText ();
        String moneyText = txtKeymoney.getText ();
        String qtyText = txtQty.getText ();


        if (!moneyText.matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert (Alert.AlertType.ERROR, "invalid key money").show ();
            txtKeymoney.requestFocus ();
            return false;
        } else if (!qtyText.matches("^\\d+$")) {
            //} else if (!qtyText.matches("[0-9]")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            return false;
        }else {
            return true;
        }
    }

    public String nextRoomId() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select roomTypeId from Room order by  roomTypeId desc");

        String nextId = "R001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("R00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.valueOf (id);

            id = "R00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }
    }

    public void setRoomID(){
        String roomID=nextRoomId ();
        txtRoomId.setText (roomID);
    }


}

