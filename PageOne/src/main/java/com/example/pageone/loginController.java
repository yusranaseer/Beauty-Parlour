package com.example.pageone;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginController {

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField pwdTxt;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField unameTxt;


    Connection conn;
    PreparedStatement pst,pst2;
    ResultSet rs;

    static String userroll;
    public static String username;

    @FXML
    void login(ActionEvent event) {

        String username = unameTxt.getText();
        String password = pwdTxt.getText();

        if (username.equals("") || password.equals(""))
        {
            JOptionPane.showMessageDialog(null, "please Enter Username and Password");
        }
        else
        {
            try {

                pst = conn.prepareStatement("select * from users where username=? and password=?");
                pst.setString(1, username);
                pst.setString(2,password);

                rs = pst.executeQuery();
                if (rs.next())
                {
                    // JOptionPane.showMessageDialog(null, "Login Successfully");

                    userroll=rs.getString("userroll");
                    loginController.username =rs.getString("username");

                    if(userroll.equals("customer")) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(loader.load(), 900, 600));
                        stage1.setTitle("Home");


                        stage1.show();

                        loginBtn.getScene().getWindow().hide();
                    }else if(userroll.equals("admin")){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(loader.load(), 900, 600));
                        stage1.setTitle("Admin");


                        stage1.show();

                        loginBtn.getScene().getWindow().hide();
                    }

                    loginBtn.getScene().getWindow().hide();

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Check your  Username and password");
                    unameTxt.setText("");
                    pwdTxt.setText("");
                    unameTxt.requestFocus();
                }

            }  catch (IOException | SQLException ex)
            {
                Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 800, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        signUpBtn.getScene().getWindow().hide();

    }

    @FXML
    void initialize() {
        this.conn =mySqlConnect.ConnectDb();
    }


}
