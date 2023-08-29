package lk.ijse.hibernate.hostel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.hibernate.hostel.util.Navigation;

import java.io.IOException;

public class DashboardFormController {


    public void UserOnAction(ActionEvent actionEvent) {

    }

    public void RoomOnAction(ActionEvent actionEvent) throws IOException {
         Navigation.switchNavigation("RoomForm.fxml",actionEvent);
    }

    public void StudentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchNavigation("StudentForm.fxml",actionEvent);

    }

    public void ReservationOnAction(ActionEvent actionEvent) throws IOException {
      Navigation.switchNavigation("ReservationForm.fxml",actionEvent);
    }

}
