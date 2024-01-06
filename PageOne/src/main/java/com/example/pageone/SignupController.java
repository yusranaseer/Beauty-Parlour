package com.example.pageone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignupController {

    @FXML
    private TextField AddTxt;

    @FXML
    private TextField EAdTxt;

    @FXML
    private TextField HNumTxt;

    @FXML
    private TextField UnamTxt;

    @FXML
    private TextField fNameTxt;

    @FXML
    private TextField lNameTxt;

    @FXML
    private TextField mNumTxt;

    @FXML
    private PasswordField pwdTxt;

    @FXML
    private Button backBtn;

    @FXML
    private Button clrBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    private ComboBox<String> title;

    @FXML
    private AnchorPane ancSignin;

    @FXML
    private AnchorPane ancSignup;

    Connection conn;
    PreparedStatement pst,pst2;
    ResultSet rs;

    String userroll,username;


    void clear(){
        fNameTxt.clear();
        lNameTxt.clear();
        mNumTxt.clear();
        HNumTxt.clear();
        AddTxt.clear();
        EAdTxt.clear();
        UnamTxt.clear();
        pwdTxt.clear();
        title.getItems().clear();
        title.getItems().addAll("Mr","Miss","Mrs");
        title.requestFocus();
    }

    @FXML
    void actClear(ActionEvent event) {
        clear();
    }

    @FXML
    void initialize() {
        title.getItems().addAll("Mr","Miss","Mrs");

    }

    private boolean validate(){

        if(EAdTxt.getText().contains("@")){

            if (HNumTxt.getText().length() ==10 && mNumTxt.getText().length()==10){
                return true;
            }else{
                JOptionPane.showMessageDialog(null,"Numbers can contains 10 digits  only","Error",JOptionPane.INFORMATION_MESSAGE);
                HNumTxt.clear();
                mNumTxt.clear();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Email Address invalid1","Error",JOptionPane.INFORMATION_MESSAGE);
            EAdTxt.clear();
        }


        return false;
    }

    @FXML
    void signUp(ActionEvent event) {

        conn =mySqlConnect.ConnectDb();
        if(conn==null){
            JOptionPane.showMessageDialog(null,"Error connecting to the database","Warning",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(this.validate()){
            String sql="insert into customers(title,firstaname,lastname,personal_number,home_number,username,email,address)values(?,?,?,?,?,?,?,?)";
            try{
                pst =conn.prepareStatement(sql);
                pst.setString(1,title.getValue().toString());
                pst.setString(2,fNameTxt.getText());
                pst.setString(3,lNameTxt.getText());
                pst.setString(4,mNumTxt.getText());
                pst.setString(5,HNumTxt.getText());
                pst.setString(6,UnamTxt.getText().trim());
                pst.setString(7,EAdTxt.getText().trim());
                pst.setString(8,AddTxt.getText().trim());


                pst.execute();

                String sql2="insert into users(username,password,userroll)values(?,?,?)";

                pst2 =conn.prepareStatement(sql2);
                pst2.setString(1,UnamTxt.getText());
                pst2.setString(2,pwdTxt.getText());
                pst2.setString(3,"customer");

                pst2.execute();

                JOptionPane.showMessageDialog(null,"Your account has been Successfully Created.You can login for further information");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(loader.load(),639 , 400));
                stage1.setTitle("Home");
                stage1.show();

                signUpBtn.getScene().getWindow().hide();



                // ancSignup.setVisible(false);
                // ancSignin.setVisible(true);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);

            }
        }


    }

    @FXML
    void bacBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 639, 400));
        stage1.setTitle("SignUp");
        stage1.show();

        backBtn.getScene().getWindow().hide();

    }




}
