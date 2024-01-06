package com.example.pageone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class changePasswordController {



    @FXML
    private Button CPBtn;

    @FXML
    private TextField ConfPasTxt;

    @FXML
    private TextField CurPTxt;

    @FXML
    private Button FBBtn;

    @FXML
    private Button PofBtn;

    @FXML
    private Button ResBtn;

    @FXML
    private Button SubmBtn;

    @FXML
    private Button VWBtn;

    @FXML
    private Button homebtn;

    @FXML
    private Button logBtn;

    @FXML
    private TextField newPasTxt;


    @FXML
    private Label lblAlertMatchPw;

    private Connection conn;
    private PreparedStatement stm,query;
    private ResultSet rs;
    private  String username;


    @FXML
    void actChaP(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("changePasswordController.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
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
        stage1.setTitle("SignUp");
        stage1.show();

        homebtn.getScene().getWindow().hide();

    }

    @FXML
    void actMypr(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        PofBtn.getScene().getWindow().hide();

    }

    @FXML
    void actRes(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        ResBtn.getScene().getWindow().hide();

    }

    @FXML
    void actVW(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewWorkers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        VWBtn.getScene().getWindow().hide();

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

    String userroll = "customers";

    @FXML
    void updatePasswordBtn(ActionEvent event) {

        String currentPassword,newPassword,conformPassword,dbPassword;

        currentPassword=CurPTxt.getText().trim();
        newPassword=newPasTxt.getText().trim();
        conformPassword=ConfPasTxt.getText().trim();

        try{
            stm=conn.prepareStatement("SELECT password FROM users WHERE username=?");
            stm.setString(1,this.username);

            rs=stm.executeQuery();

            if(rs.next()){
                dbPassword=rs.getString("password");
                if(dbPassword.equals(currentPassword)){
                    if(conformPassword.equals(newPassword)){
                        query=conn.prepareStatement("UPDATE users SET password=? WHERE username=?");
                        query.setString(1,newPassword);
                        query.setString(2,this.username);

                        query.execute();

                        JOptionPane.showMessageDialog(null,"Password updated!","Info",JOptionPane.INFORMATION_MESSAGE);
                        ConfPasTxt.clear();
                        newPasTxt.clear();

                    }else{
                        JOptionPane.showMessageDialog(null,"New password and Confom password must match!","warning",JOptionPane.INFORMATION_MESSAGE);
                        ConfPasTxt.clear();
                        newPasTxt.clear();
                        CurPTxt.clear();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Password Don't match with our record!","warning",JOptionPane.INFORMATION_MESSAGE);
                    CurPTxt.clear();
                }

            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.toString(),"warning",JOptionPane.ERROR_MESSAGE);
        }









    }

    @FXML
    void initialize() {
        this.conn=mySqlConnect.ConnectDb();
        this.username=loginController.username;
    }


}
