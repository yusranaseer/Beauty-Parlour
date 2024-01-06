package com.example.pageone;

import com.example.pageone.loginController;
import com.example.pageone.mySqlConnect;
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

public class AdminChangePasswordController {

    @FXML
    public Button dashboardBtn;
    public Button addworkersBtn;
    public Button viwBtn;
    public Button HOWBtn;
    public Button CPBtn;
    public Button SubmBtn;
    public TextField CurPTxt;
    public TextField newPasTxt;
    public TextField ConfPasTxt;
    public Label lblAlertMatchPw;

    @FXML
    private Button logBtn;


    private Connection conn;
    private PreparedStatement stm,query;
    private ResultSet rs;

    private  String username;

    @FXML
    public void logout(ActionEvent actionEvent) {
        int choice= JOptionPane.showConfirmDialog(null,"Are you Sure?","Warning",JOptionPane.YES_NO_OPTION);

        if(choice==0){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(loader.load(),639 , 400));
                stage1.setTitle("Home");
                stage1.show();

                logBtn.getScene().getWindow().hide();
            }catch (IOException | NullPointerException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    public void addworkersWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddWorkers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900, 600));
        stage1.setTitle("Add Workers");
        stage1.show();

        addworkersBtn.getScene().getWindow().hide();
    }



    @FXML
    public void viewWorkers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminViewWorker.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("View Workers");


        stage1.show();

        viwBtn.getScene().getWindow().hide();
    }

    @FXML
    void setDashboardView(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900 , 600));
        stage1.setTitle("Admin Page");
        stage1.show();

        dashboardBtn.getScene().getWindow().hide();
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
    void initialize() {
        this.conn= mySqlConnect.ConnectDb();
        this.username= loginController.username;
    }

    @FXML
    public void updatePasswordBtn(ActionEvent actionEvent) {

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
}
