package lk.ijse.hibernate.hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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
    public JFXPasswordField txtPassShow;
    public JFXTextField txtPass;

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
}
