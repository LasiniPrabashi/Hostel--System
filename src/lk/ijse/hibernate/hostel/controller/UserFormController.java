package lk.ijse.hibernate.hostel.controller;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodTextRun;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.hibernate.hostel.bo.BOFactory;
import lk.ijse.hibernate.hostel.bo.custom.UserBO;
import lk.ijse.hibernate.hostel.dto.UserDTO;
import lk.ijse.hibernate.hostel.util.Navigation;

import java.io.IOException;


public class UserFormController {


    public TextField txtNewUsername;
    public TextField txtNewPassword;
    public TextField txtReNewPassWord;
    public Label lblnewUserName;
    public Label lblnewPassWord;
    public Label lblrenewPassword;

    UserDTO userDTO = new UserDTO();
   UserBO userBO = (UserBO) BOFactory.getBO(BOFactory.BOTypes.USER);

    public void initialize() {
    }

  public void btnSaveChangesOnAction(ActionEvent actionEvent) {


    String userName = txtNewUsername.getText();
    String password = txtNewPassword.getText();
    String rePassword = txtReNewPassWord.getText();

        lblnewUserName.setText(null);
        lblnewPassWord.setText(null);
        lblrenewPassword.setText(null);
        if (password.equals(rePassword)) {
        boolean isUpdated = userBO.update(new UserDTO(userName, password));
//            userService.save(userDTO);
        Alert alert;
        if (isUpdated) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Password and UserName has been successfully Update");
            txtNewUsername.setText(null);
            txtNewPassword.setText(null);
            txtReNewPassWord.setText(null);
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
        }
        alert.show();
    } else {
        lblrenewPassword.setText("Is not matched");
        txtNewPassword.requestFocus();
        txtReNewPassWord.setText(null);
    }
}

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        Navigation.switchNavigation("DashboardForm.fxml",mouseEvent);
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) throws Exception {

        userDTO = userBO.get(txtNewUsername.getText());
        txtNewUsername.setText(userDTO.getUserName());
        txtNewPassword.setText(userDTO.getPassWord());
    }
}

