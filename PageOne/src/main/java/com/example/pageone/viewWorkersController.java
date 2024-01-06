package com.example.pageone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class viewWorkersController {

    @FXML
    private Button CPBtn;


    @FXML
    private Button MPBtn;

    @FXML
    private Button homebtn;

    @FXML
    private Button MRBtn;

    @FXML
    private Button logBtn;

    @FXML
    void actChaP(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("changePasswordController.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("Change Password");
        stage1.show();

        CPBtn.getScene().getWindow().hide();

    }



    @FXML
    void actHome(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("Home");
        stage1.show();

        homebtn.getScene().getWindow().hide();

    }

    @FXML
    void actMypr(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("Profile");
        stage1.show();

        MPBtn.getScene().getWindow().hide();

    }

    @FXML
    void actRes(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("Reservation");
        stage1.show();

        MRBtn.getScene().getWindow().hide();

    }

    @FXML
    void actLog(ActionEvent event) {


        int choice= JOptionPane.showConfirmDialog(null,"Are you Sure?","Warning",JOptionPane.YES_NO_OPTION);

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




    @FXML
    void initialize() {


    }

}
