package lk.ijse.hibernate.hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.hostel.bo.BOFactory;
import lk.ijse.hibernate.hostel.bo.custom.UserBO;
import lk.ijse.hibernate.hostel.dto.UserDTO;
import lk.ijse.hibernate.hostel.util.Navigation;
import lk.ijse.hibernate.hostel.util.Notification;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public JFXTextField txtUName;
    public JFXPasswordField txtPass;
    public JFXTextField txtPassShow;
    public ImageView imgOpenEye;
    public ImageView imgCloseEye;


    // public ImageView imgOpenEye;


    private UserBO userBO = (UserBO) BOFactory.getBO (BOFactory.BOTypes.USER);

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {

        if (checkUserDetail()){
            Navigation.switchNavigation("DashboardForm.fxml",actionEvent);
           Notification.notification("Login Successfully");
        }
//        Navigation.navigate(Routes.DASHBOARD, primaryPane);

    }

    private boolean checkUserDetail() {
        String userName = txtUName.getText ();
        String pass=txtPass.getText ();

        List<UserDTO> userList = userBO.loadAll ();

        for (UserDTO dto : userList) {
            if(dto.getUserName ().equals (userName) && dto.getPassword ().equals (pass)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtPass.setVisible(true);
        txtPassShow.setVisible(false);
    }


    public void openEyeOnMouseClicked(MouseEvent mouseEvent) {
        imgCloseEye.setVisible(true);
        imgOpenEye.setVisible(false);
        txtPassShow.setVisible(false);
        txtPass.setText(txtPassShow.getText());
        txtPass.setVisible(true);
        txtPass.requestFocus();
        
    }

    public void closeEyeOnMouseClicked(MouseEvent mouseEvent) {
        imgOpenEye.setVisible(true);
        imgCloseEye.setVisible(false);
        txtPass.setVisible(false);
        txtPassShow.setText(txtPass.getText());
        txtPassShow.setVisible(true);
        txtPassShow.requestFocus();
    }
}
