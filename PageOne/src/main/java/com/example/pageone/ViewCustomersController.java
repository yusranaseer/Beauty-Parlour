package com.example.pageone;

import com.example.pageone.mySqlConnect;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class ViewCustomersController {


    @FXML
    private Button logBtn,CPBtn;

    @FXML
    private TableView tableView;

    private ObservableList<ObservableList> data;

    @FXML
    void initialize() throws IOException {
        Connection conn = mySqlConnect.ConnectDb();

        if(conn==null){
            return;
        }

        data = FXCollections.observableArrayList();
        try{

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * FROM customers";
            //ResultSet
            ResultSet rs = conn.createStatement().executeQuery(SQL);



            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tableView.getColumns().addAll(col);

            }


            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column

                    row.add(rs.getString(i));
                }

                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableView.setItems(data);

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e.toString(),"Error",JOptionPane.ERROR_MESSAGE);

            int choice= JOptionPane.showConfirmDialog(null,"Go Back to Home","Warning",JOptionPane.YES_NO_OPTION);

            if(choice==0){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(loader.load(),900 , 600));
                stage1.setTitle("admin");
                stage1.show();

                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("viewCustomers.fxml"));
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(loader2.load(),1100 , 600));
                stage2.setTitle("admin");
                stage2.hide();



            }else{
                this.initialize();
            }


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

    @FXML
    private Button dashboardBtn;
    @FXML
    public void setDashboardBtn(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900 , 600));
        stage1.setTitle("Admin Page");
        stage1.show();

        dashboardBtn.getScene().getWindow().hide();
    }



    @FXML
    private Button viwBtn;




    public void viewWorkers(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminViewWorker.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(), 900, 600));
        stage1.setTitle("View Workers");


        stage1.show();

        viwBtn.getScene().getWindow().hide();
    }

    @FXML
    private Button addworkersBtn;
    @FXML
    public void addworkerview(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddWorkers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900, 600));
        stage1.setTitle("Add Workers");
        stage1.show();

        addworkersBtn.getScene().getWindow().hide();

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

}
