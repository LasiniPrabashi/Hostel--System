package lk.ijse.hibernate.hostel.controller;

import javafx.event.ActionEvent;
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
