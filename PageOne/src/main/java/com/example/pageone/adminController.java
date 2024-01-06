package com.example.pageone;

import com.example.pageone.*;
import com.example.pageone.loginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class adminController {

    public Button ResBTN;
    @FXML
    private Button CPBtn;

    @FXML
    private Button HOWBtn;

    @FXML
    private Button addworkersBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button logBtn;

    @FXML
    private Button viwBtn;

    String uroll,uname;

    @FXML
    private Label admin_name;


    @FXML
    void initialize() {
        this.admin_name.setText(loginController.username);


    }

    @FXML
    void logout(){

        int choice=JOptionPane.showConfirmDialog(null,"Are you Sure?","Warning",JOptionPane.YES_NO_OPTION);

        if(choice==0){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(loader.load(),639 , 400));
                stage1.setTitle("Home");
                stage1.show();

                logBtn.getScene().getWindow().hide();
            }catch (IOException |NullPointerException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }


    public void addworkersWindow(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddWorkers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("Add Workers");


        stage1.show();

        addworkersBtn.getScene().getWindow().hide();
    }


    public void viewWorkers(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminViewWorker.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("View Workers");


        stage1.show();

        viwBtn.getScene().getWindow().hide();
    }

    @FXML
    public void showCustomers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewCustomers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900 , 600));
        stage1.setTitle("Customers");
        stage1.show();

        HOWBtn.getScene().getWindow().hide();
    }

    @FXML
    public void changePasswordView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminChangePassword.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900 , 600));
        stage1.setTitle("Customers");
        stage1.show();

        CPBtn.getScene().getWindow().hide();
    }

    public void setReservationView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminViewReservations.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),1000 , 600));
        stage1.setTitle("Reservations");
        stage1.show();

        ResBTN.getScene().getWindow().hide();

    }
}
