package com.example.pageone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class myProfileController {


    @FXML
    private TextField AddTxt;

    @FXML
    private Button CPBtn;

    @FXML
    private TextField EmaTxt;

    @FXML
    private Button FBBtn;

    @FXML
    private TextField FNtxt;

    @FXML
    private TextField HomTxt;

    @FXML
    private TextField MobTxt;

    @FXML
    private Button PofBtn;

    @FXML
    private Button ResBtn;

    @FXML
    private Button UpDTxt;

    @FXML
    private Button VWBtn;

    @FXML
    private Button homebtn;

    @FXML
    private Button logBtn;

    @FXML
    private TextField usrTxt;

    private Connection conn;
    private PreparedStatement stm,query;

    @FXML
    void actChaP(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("changePasswordController.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        CPBtn.getScene().getWindow().hide();

    }

    private boolean validateData(){

        if(EmaTxt.getText().contains("@")){
            if(AddTxt.getText().length()!=0 && HomTxt.getText().length() ==10 && MobTxt.getText().length() ==10 && FNtxt.getText().length()>0){

                return true;

            }else{

                JOptionPane.showMessageDialog(null,"Please check your inputs again","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(null,"Not a valid Email","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    @FXML
    void updateData(ActionEvent event) throws IOException, SQLException {

        String fname="",lname="";

        String[] names=FNtxt.getText().split(" ");

        if(names.length>=2){
            fname=names[0];
            lname=names[1];
        }else if(names.length==1){
            fname=names[0];
            lname="";
        }

        if(this.validateData()){
            stm=conn.prepareStatement("UPDATE customers SET email=? ,firstaname=? ,lastname=? , address=?, home_number=? , personal_number=? WHERE username=? " );
            stm.setString(1,EmaTxt.getText().trim());
            stm.setString(2,fname);
            stm.setString(3,lname);
            stm.setString(4,AddTxt.getText().trim());
            stm.setString(5,HomTxt.getText().trim());
            stm.setString(6,MobTxt.getText().trim());
            stm.setString(7,usrTxt.getText().trim());

            stm.execute();

            JOptionPane.showMessageDialog(null,"Updated!","Succussful",JOptionPane.INFORMATION_MESSAGE);

            this.initialize();
        }


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
    void actLog(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 639, 400));
        stage1.setTitle("Login");
        stage1.show();

        logBtn.getScene().getWindow().hide();

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
        stage1.setTitle("View Workers");
        stage1.show();

        VWBtn.getScene().getWindow().hide();

    }


    @FXML
    void initialize() {

       conn =mySqlConnect.ConnectDb();

        if(conn==null){
            return;
        }

        try{

            PreparedStatement stm=conn.prepareStatement("select * from customers where username=?");
            stm.setString(1,loginController.username);

            ResultSet res=stm.executeQuery();


            if(res.next()){



                usrTxt.setText(loginController.username);
                FNtxt.setText(String.join(" ",res.getString("firstaname"),res.getString("lastname")));
                EmaTxt.setText(res.getString("email"));
                AddTxt.setText(res.getString("address"));
                MobTxt.setText(res.getString("personal_number"));
                HomTxt.setText(res.getString("home_number"));

            }else{

                JOptionPane.showMessageDialog(null,"error fetching data!","warning",JOptionPane.ERROR_MESSAGE);

                int choice=JOptionPane.showConfirmDialog(null,"Back to Home","warning",JOptionPane.YES_NO_OPTION);

                if(choice==0){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Stage stage1 = new Stage();
                    stage1.setScene(new Scene(loader.load(), 900, 600));
                    stage1.setTitle("Home");
                    stage1.show();

                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("myProfile.fxml"));
                    Stage stage2 = new Stage();
                    stage2.setScene(new Scene(loader2.load(), 900, 600));
                    stage2.setTitle("Home");
                    stage2.hide();
                }else{
                    this.initialize();
                }

            }

        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,e.toString(),"warning",JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null,"Error connecting to the database!","warning",JOptionPane.ERROR_MESSAGE);
        }

    }
}
