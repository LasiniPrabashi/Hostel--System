package lk.ijse.hibernate.hostel.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigation {

        private static Stage stage;
        private static Scene scene;
        private static Parent parent;


        public static void switchNavigation(String link, ActionEvent event) throws IOException {
        parent = FXMLLoader.load(Navigation.class.getResource("/view/" + link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

        public static void switchNavigation(String link, javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        parent = FXMLLoader.load(Navigation.class.getResource("/view/" + link));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
        public static void popupNavigation(String link) throws IOException {
        URL resource = Navigation.class.getResource("/view/" + link);
        Parent parent = FXMLLoader.load(resource);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

        public static void close(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

        public static void close(javafx.scene.input.MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
    }







//    private static AnchorPane pane;
//    public static void navigate(Routes routes,AnchorPane pane) throws IOException{
//        Navigation.pane = pane;
//        Navigation.pane.getChildren().clear();
//        Stage window = (Stage) Navigation.pane.getScene().getWindow();
//
//        switch (routes){
//            case LOGIN:
//                initUI("LoginForm.fxml");
//                window.setTitle("");
//                break;
//
//            case DASHBOARD:
//                initUI("DashboardForm.fxml");
//                window.setTitle("");
//                break;
//
////            case STUDENTFORM:
////                initUI("DashboardStudentForm.fxml");
////                window.setTitle("");
////                break;
////
////            case ROOMFORM:
////                initUI("DashboardRoomForm.fxml");
////                window.setTitle("");
////                break;
////
////            case RESERVATIONFORM:
////                initUI("DashboardReservationForm.fxml");
////                window.setTitle("");
////                break;
////
////            case VIEWALLSTUDENTS:
////                initUI("DashboardStudentViewAllForm.fxml");
////                window.setTitle("");
////                break;
////
////            case VIEWALLROOMS:
////                initUI("DashboardRoomViewAllForm.fxml");
////                window.setTitle("");
////                break;
////
////            case VIEWALLRESERVATIONS:
////                initUI("DashboardReservationViewAllForm.fxml");
////                window.setTitle("");
////                break;
////
////            case USER:
////                initUI("UserForm.fxml");
////                window.setTitle("");
////                break;
////
////            case NOTPAY:
////                initUI("DashboardNotPayStudentViewAllForm.fxml");
////                window.setTitle("");
////                break;
//
//            default:
//                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
//        }
//    }
//
//
//

}