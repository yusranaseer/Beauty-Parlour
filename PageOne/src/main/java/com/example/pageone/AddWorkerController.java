package com.example.pageone;

import com.example.pageone.mySqlConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddWorkerController {

    @FXML
    public Button CPBtn;
    public Button HOWBtn;
    @FXML
    private ComboBox title;

    @FXML
    private ComboBox roll;

    @FXML
    private Button dashboardBtn;


    @FXML
    private Button logBtn;
    @FXML
    private Label admin_name;


    @FXML
    private  Button addBtn;

    private Connection conn;
    private PreparedStatement stm,query;
    private ResultSet res;

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
    void initialize() {

        title.getItems().addAll("Mr","Miss","Mrs");
        roll.getItems().addAll("Worker","Admin","Others");

        this.conn= mySqlConnect.ConnectDb();

    }

    @FXML
    void setDashboardBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900 , 600));
        stage1.setTitle("Home");
        stage1.show();

        dashboardBtn.getScene().getWindow().hide();
    }


    @FXML
    private TextField firstNameTxt,usernameTXT,homeNumberTxt;

    @FXML
    private TextArea InfoTxt;

    @FXML
    void addWorker(ActionEvent event){

        String firstname,username,mobile,info;

        firstname=firstNameTxt.getText().trim();
        username=usernameTXT.getText().trim();
        mobile=homeNumberTxt.getText().trim();
        info=InfoTxt.getText().trim();

        if(this.validate()){

            try{
                stm=conn.prepareStatement("SELECT workerID FROM workers WHERE username=?");
                stm.setString(1,username);

                res=stm.executeQuery();

                if(!res.next()){
                    query=conn.prepareStatement("INSERT INTO workers(title,firstname,personalInfo,username,workerroll,home_number) VALUES(?,?,?,?,?,?)");

                    query.setString(1,title.getValue().toString());
                    query.setString(2,firstname);
                    query.setString(3,info);
                    query.setString(4,username);
                    query.setString(5,roll.getValue().toString());
                    query.setString(6,mobile);


                    query.execute();

                    JOptionPane.showMessageDialog(null,"Worker Added!","Info",JOptionPane.INFORMATION_MESSAGE);

                    firstNameTxt.clear();
                    InfoTxt.clear();
                    usernameTXT.clear();
                    homeNumberTxt.clear();


                }
            }catch (SQLException e){

                JOptionPane.showMessageDialog(null,e.toString(),"Warning",JOptionPane.ERROR_MESSAGE);
            }


        }



    }

    private boolean validate(){

        if(firstNameTxt.getText().length()>0 && usernameTXT.getText().length()>0 && homeNumberTxt.getText().length()==10 ){

            return true;

        }else{
            JOptionPane.showMessageDialog(null,"Please check your inputs!","warning",JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    @FXML
    private Button viwBtn;

    @FXML
    public void viewWorkers(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminViewWorker.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("View Workers");


        stage1.show();

        viwBtn.getScene().getWindow().hide();
    }

    @FXML
    private Button clearnBtn;

    @FXML
    void clear(){

        firstNameTxt.clear();
        usernameTXT.clear();
        homeNumberTxt.clear();
        InfoTxt.clear();
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

    @FXML
    public void showCustomers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewCustomers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900 , 600));
        stage1.setTitle("Customers");
        stage1.show();

        HOWBtn.getScene().getWindow().hide();
    }



}
