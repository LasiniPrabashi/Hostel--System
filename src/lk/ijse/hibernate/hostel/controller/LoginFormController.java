package lk.ijse.hibernate.hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.hostel.util.Navigation;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane primaryPane;

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.switchNavigation("DashboardForm.fxml",actionEvent);
//        Navigation.navigate(Routes.DASHBOARD, primaryPane);

    }
}
