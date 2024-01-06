package com.example.pageone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackController {

    @FXML
    private Button CPBtn;

    @FXML
    private Button FBBtn;

    @FXML
    private Button PofBtn;

    @FXML
    private Button ResBtn;

    @FXML
    private Button VWBtn;

    @FXML
    private Button homebtn;

    @FXML
    private Button logBtn;

    @FXML
    private TextArea msgTf;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button submitBtn;

    private Connection conn;
    private PreparedStatement stm;

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
    void actFeed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("feedback.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        FBBtn.getScene().getWindow().hide();

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
        stage1.setTitle("My Profile");
        stage1.show();

        PofBtn.getScene().getWindow().hide();

    }

    @FXML
    void actRes(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("Reservation");
        stage1.show();

        ResBtn.getScene().getWindow().hide();

    }

    @FXML
    void actLog(ActionEvent event){

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

    @FXML
    void actVW(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewWorkers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("View Workers");
        stage1.show();

        VWBtn.getScene().getWindow().hide();

    }

    @FXML
    void initialize() {
        this.conn=mySqlConnect.ConnectDb();

    }

    @FXML
    void postFeedBack(){

        if(conn==null){
            JOptionPane.showMessageDialog(null,"Error connecting to the database","error",JOptionPane.ERROR_MESSAGE);
            this.initialize();
        }else{
            try{
                stm=this.conn.prepareStatement("INSERT INTO feedback(username,message) VALUES(?,?)");
                stm.setString(1,nameTxt.getText().trim());
                stm.setString(2,msgTf.getText().trim());

                stm.execute();

                JOptionPane.showMessageDialog(null,"Review added \n Thank You!","success!",JOptionPane.INFORMATION_MESSAGE);

                nameTxt.clear();
                msgTf.clear();

            } catch (SQLException  e){
                JOptionPane.showMessageDialog(null,e.toString(),"error",JOptionPane.ERROR_MESSAGE);
            }

        }


    }


}
