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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class AdminViewWorkerController {

    @FXML
    public Button HOWBtn;
    public Button CPBtn;
    public Button viwBtn;
    @FXML
    private Button btnlogout;

    @FXML
    private Button addworkerbtn;

    @FXML
    private Button dashboardBtn;

    private ObservableList<ObservableList> data;

    @FXML
    private TableView tableview;




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
    void setAddworkersView(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddWorkers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900, 600));
        stage1.setTitle("Add Workers");
        stage1.show();

        addworkerbtn.getScene().getWindow().hide();


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
    public void initialize(){



        Connection conn = mySqlConnect.ConnectDb();

        if(conn==null){
            return;
        }

        data = FXCollections.observableArrayList();
        try{

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from workers";
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
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }

                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();

        }


    }

    @FXML
    public void changePasswordView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminChangePassword.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load(),900 , 600));
        stage1.setTitle("Change Password");
        stage1.show();

        CPBtn.getScene().getWindow().hide();
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
}
