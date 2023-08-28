package lk.ijse.hibernate.hostel.controller;

import javafx.event.ActionEvent;
import lk.ijse.hibernate.hostel.util.Navigation;

import java.io.IOException;

public class LoginFormController {
    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",actionEvent);
    }
}
