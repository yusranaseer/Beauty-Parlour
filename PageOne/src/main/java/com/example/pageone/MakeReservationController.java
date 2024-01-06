package com.example.pageone;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MakeReservationController {


    @FXML
    public Button logBtn;

    public Button MRBtn;
    public Button MPBtn;
    public Button CPBtn;
    public Button HomeBtn;
    public ComboBox<String> title;
    public Label worker_id;
    public Label worker_name;
    public Label worker_mobile;
    public TextField nameTXT;
    public DatePicker dateTXT;
    public TextArea noteTXT;
    public Button add_reservationBTN;
    private List<String> workers;
    private Connection conn;
    private ResultSet res;
    private PreparedStatement stm;

    private int selected_worker_ID;
    private String username;


    @FXML
    void initialize() {

        workers=new ArrayList<>();
        username=loginController.username;

        conn=mySqlConnect.ConnectDb();

        if(conn==null){
            JOptionPane.showMessageDialog(null,"error connecting to the database!","error",JOptionPane.ERROR_MESSAGE);
        }

        try{
            stm=conn.prepareStatement("SELECT username FROM workers");
            res=stm.executeQuery();

            while(res.next()){
                workers.add(res.getString("username"));
            }
            title.getItems().addAll(workers);
            title.setValue(workers.get(1));
            this.setWorkerDetails(workers.get(1));

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.toString(),"error",JOptionPane.ERROR_MESSAGE);
        }

    }

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

    public void actHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        HomeBtn.getScene().getWindow().hide();
    }

    public void actRes(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        MRBtn.getScene().getWindow().hide();
    }

    public void actMypr(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
        Stage stage1 = new Stage();

        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        MPBtn.getScene().getWindow().hide();
    }

    public void actChaP(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("changePasswordController.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("SignUp");
        stage1.show();

        CPBtn.getScene().getWindow().hide();
    }

    @FXML
    void addReservation(ActionEvent event){

        /*
        reservation_id
        worker_id
        reservation_date
        reservation_start_time
        reservation_end_time
        reservation_details
        submit_dateTime
        worker_remark
        worker_reply
        customer_reply
        reservationcol

         */

        String username= title.getValue();
        String name=nameTXT.getText();
        String date=dateTXT.getValue().toString();
        String text=noteTXT.getText();
        String postDate= new Date().toString();

        if(!username.equals("") && !name.equals("") && !text.equals("")){
            try{
                stm=conn.prepareStatement("INSERT INTO reservation( worker_id,username,reservation_date,reservation_start_time,reservation_end_time,reservation_details,submit_dateTime,worker_remark" +
                        ",worker_reply,customer_reply, reservationcol) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                stm.setString(1, String.valueOf(this.selected_worker_ID));
                stm.setString(2,this.username);
                stm.setString(3,date);
                stm.setString(4,"Now");
                stm.setString(5,"Tomorrow");
                stm.setString(6,text);
                stm.setString(7,postDate);
                stm.setString(8,"worker remarks");
                stm.setString(9,"Pending...");
                stm.setString(10,"owner reply");
                stm.setString(11," reservationcol");

                stm.execute();

                JOptionPane.showMessageDialog(null,"Reservation Added!","Info",JOptionPane.INFORMATION_MESSAGE);

                nameTXT.clear();
                noteTXT.clear();



            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,e.toString(),"error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Please fill the relevant information","Warning",JOptionPane.INFORMATION_MESSAGE);
        }







    }

    @FXML
    public void itemSelected(ActionEvent event) {

        this.setWorkerDetails(title.getValue().toString());
    }

    private void setWorkerDetails(String username){

        try{
            stm=conn.prepareStatement("SELECT workerID,firstname,home_number FROM workers WHERE username=?");
            stm.setString(1,username);

            res=stm.executeQuery();
            if(res.next()){

                this.selected_worker_ID=Integer.parseInt(res.getString("workerID"));
                worker_id.setText(res.getString("workerID"));
                worker_name.setText(res.getString("firstname"));
                worker_mobile.setText(res.getString("home_number"));



            }else{
                JOptionPane.showMessageDialog(null,"No users found!","Error",JOptionPane.INFORMATION_MESSAGE);
                worker_id.setText(res.getString("Not Found!"));
                worker_name.setText(res.getString("Not Found!"));
                worker_mobile.setText(res.getString("Not Found!"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"error getting information!","error",JOptionPane.ERROR_MESSAGE);
        }


    }
}
