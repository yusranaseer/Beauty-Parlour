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

public class AdminViewReservations {
    @FXML
    public Button btnlogout;
    public Label admin_name;
    public Button dashboardBtn;
    public Button addworkerbtn;
    public Button viwBtn;
    public Button HOWBtn;
    public Button CPBtn;
    @FXML
    private ComboBox<Integer> reservations;
    @FXML
    private TextField replyTXT;
    @FXML
    private Button replyBTN;

    private List<Integer> reservationIDS;
    public TableView tableview;

    private Connection conn;
    private PreparedStatement stm;

    private ObservableList<ObservableList> data;

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

                btnlogout.getScene().getWindow().hide();
            }catch (IOException | NullPointerException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    public void setDashboardView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900 , 600));
        stage1.setTitle("Home");
        stage1.show();

        dashboardBtn.getScene().getWindow().hide();
    }


    @FXML
    public void setAddworkersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminViewWorker.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("View Workers");


        stage1.show();

        viwBtn.getScene().getWindow().hide();
    }

    @FXML
    public void viewCustomer(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewCustomers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900 , 600));
        stage1.setTitle("Customers");
        stage1.show();

        HOWBtn.getScene().getWindow().hide();
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
    void initialize() {

        conn = mySqlConnect.ConnectDb();
        reservationIDS=new ArrayList<>();


        if(conn==null){
            return;
        }

        data = FXCollections.observableArrayList();
        try{

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

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT reservation_id AS ID,username AS customer, reservation_date AS Date," +
                    "worker_reply AS MyReply,customer_reply AS Message from reservation";
            //ResultSet
            ResultSet rs = conn.createStatement().executeQuery(SQL);



            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));

                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tableview.getColumns().addAll(col);

            }


            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();

                reservationIDS.add(Integer.parseInt(rs.getString("ID")));
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));

                }

                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
            reservations.getItems().addAll(reservationIDS);



        }catch(Exception e){
            e.printStackTrace();

        }


    }

    @FXML
    public void AddReply(ActionEvent actionEvent) {

        String reply=replyTXT.getText();
        String ID;
        try{
            ID=String.valueOf(reservations.getValue());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.toString(),"Warning",JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(!reply.equals("")){
            try{
                stm=conn.prepareStatement("UPDATE reservation SET worker_reply=? WHERE reservation_id=?");
                stm.setString(1,reply);
                stm.setString(2,ID);

                stm.execute();
                JOptionPane.showMessageDialog(null,"Reply has been sent","Info",JOptionPane.INFORMATION_MESSAGE);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(loader.load(),900 , 600));
                stage1.setTitle("Admin Home");
                stage1.show();

                replyBTN.getScene().getWindow().hide();


            }catch (SQLException | IOException e){
                JOptionPane.showMessageDialog(null,e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private Button clearBTN;

    @FXML
    public void clear(ActionEvent actionEvent) {

        replyTXT.clear();
    }
}
