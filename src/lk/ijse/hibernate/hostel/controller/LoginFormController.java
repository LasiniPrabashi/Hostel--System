package lk.ijse.hibernate.hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.hostel.util.Navigation;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane primaryPane;
    public JFXTextField txtFldUsername;
    public JFXPasswordField txtPassFldPassword;
    public ImageView imgCloseEye;

    static String username;

    public void initialize(){
        txtPassFldPassword.setVisible(false);
        imgCloseEye.setVisible(false);
    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",actionEvent);
//        Navigation.navigate(Routes.DASHBOARD, primaryPane);

    }
}
