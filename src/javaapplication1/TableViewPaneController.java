/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewPaneController implements Initializable {

    @FXML
    private TextField txtFieldID;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldDepartment;
    @FXML
    private TextField txtFieldSalary;
    
      @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> tcID;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcDepartment;
    @FXML
    private TableColumn<Student, Double> tcSalary;
    @FXML
    private Button buttonShow;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonReset;
  
    
    Statement statement;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection =
               DriverManager.
                getConnection("jdbc:mysql://127.0.0.1:3306/university3?serverTimezone=UTC",
                        "root", "");
            this.statement = (Statement) connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcDepartment.setCellValueFactory(new PropertyValueFactory("major"));
        tcSalary.setCellValueFactory(new PropertyValueFactory("grade"));
        tableView.getSelectionModel().selectedItemProperty().addListener(
                event-> showSelectedStudent() );
    }    

    @FXML
    private void buttonShowHandle(ActionEvent event) throws Exception{
        ResultSet rs = this.statement.executeQuery("Select * From Student");
        tableView.getItems().clear();
        while(rs.next()){
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setMajor(rs.getString("major"));
            s.setGrade(rs.getDouble("grade"));
            tableView.getItems().add(s);
        }
    }

    @FXML
    private void buttonAddHandle(ActionEvent event) throws Exception{
        Integer id = Integer.parseInt(txtFieldID.getText());
        String name = txtFieldName.getText();
        String major = txtFieldDepartment.getText();
        Double grade = Double.parseDouble(txtFieldSalary.getText());
        String sql = "Insert Into Student values(" + id + ",'" +name + "','" 
                + major + "'," + grade + ")";
      this.statement.executeUpdate(sql);
       buttonShowHandle(null);
    }

    @FXML
    private void buttonDeleteHandle(ActionEvent event) throws SQLException, Exception {
        
        int id = tableView.getSelectionModel().getSelectedItem().getId();
         int rs = this.statement.executeUpdate("delete from Student where id =  " + id);

         System.out.println("RES : " + rs);
        buttonShowHandle(null);
        resetControls();
        
        
    }

    @FXML
    private void buttonResetHandle(ActionEvent event) {
        resetControls();
    }
    private void resetControls(){
        txtFieldID.setText("");
        txtFieldName.setText("");
        txtFieldDepartment.setText("");
        txtFieldSalary.setText("");
        tableView.getItems().clear();
    }

    @FXML
    private void buttonUpdateHandle(ActionEvent event) throws Exception{
        Integer id = Integer.parseInt(txtFieldID.getText());
        String name = txtFieldName.getText();
        String major = txtFieldDepartment.getText();
        Double grade = Double.parseDouble(txtFieldSalary.getText());
        String sql = "Update Student Set name='" + name + "', major='" + 
                major + "', grade=" + grade + " Where id=" +id;
        this.statement.executeUpdate(sql);
          buttonShowHandle(null);
    }
    private void showSelectedStudent(){
        Student s1 = tableView.getSelectionModel().getSelectedItem();
        if(s1 != null){
        txtFieldID.setText(String.valueOf(s1.getId()));
        txtFieldName.setText(s1.getName());
        txtFieldDepartment.setText(s1.getMajor());
        txtFieldSalary.setText(String.valueOf(s1.getGrade()));
        }

    }

    
}
