package com.example.pageone;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class reservationController {

    @FXML
    private Button CPBtn;

    @FXML
    private Button FBBtn;

    @FXML
    private Button PofBtn;

    @FXML
    private Button ResBtn;

    @FXML
    private Button UpDTxt;

    @FXML
    private Button UpDTxt1;

    @FXML
    private Button VWBtn;

    @FXML
    private Button homebtn;

    @FXML
    private Button logBtn;
    private String username;

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
        stage1.setTitle("Feedback");
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
        stage1.setTitle("Profile");
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
    void actVW(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewWorkers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("View Workers");
        stage1.show();

        VWBtn.getScene().getWindow().hide();

    }

    @FXML
    void actLog(ActionEvent event){


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
    private TableView myReservationsTable;
    private ObservableList<ObservableList> data;

    @FXML
    private ComboBox<Integer> replyID;
    private List<Integer> replies;

    @FXML
    void initialize() {

        conn = mySqlConnect.ConnectDb();
        this.username=loginController.username;
        replies=new ArrayList<>();


        if(conn==null){
            return;
        }

        data = FXCollections.observableArrayList();
        try{

             /*
            reservation_id
            worker_id
            reservation_date
            reservation_details
            submit_dateTime
            worker_remark
            worker_reply
            customer_reply
            reservationcol

             */

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT reservation_id AS ID,reservation_date AS Date," +
                    "worker_reply AS 'Worker Reply',customer_reply AS 'My Reply' from reservation WHERE username='"+this.username+"'";
            //ResultSet
            ResultSet rs = conn.createStatement().executeQuery(SQL);



            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));

                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                myReservationsTable.getColumns().addAll(col);

            }


            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                replies.add(Integer.parseInt(rs.getString("ID")));

                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));

                }

                data.add(row);

            }

            //FINALLY ADDED TO TableView
            myReservationsTable.setItems(data);

            replyID.getItems().addAll(replies);

        }catch(Exception e){
            e.printStackTrace();

        }


    }

    @FXML
    private Button replyBTN;
    @FXML
    private TextField replyTXT;


    @FXML
    public void replyMsg(ActionEvent actionEvent) {

        PreparedStatement stm;

        String msg=replyTXT.getText();

        String ID;

        try{
            ID=String.valueOf(replyID.getValue());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString(),"Warning",JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try{
            stm=this.conn.prepareStatement("UPDATE reservation SET customer_reply=? WHERE reservation_id=?");
            stm.setString(1,msg);
            stm.setString(2,ID);

            stm.execute();
            JOptionPane.showMessageDialog(null,"Reply sent!","Info",JOptionPane.INFORMATION_MESSAGE);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(loader.load(), 900, 600));
            stage1.setTitle("Home");
            stage1.show();


            replyBTN.getScene().getWindow().hide();


        }catch (SQLException |IOException e){
            JOptionPane.showMessageDialog(null,e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }


}
