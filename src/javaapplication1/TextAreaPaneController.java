/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TextAreaPaneController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private Button buttonShow;
    @FXML
    private HBox h;
    @FXML
    private VBox v1;

    @FXML
    public ListView L1;
    @FXML
    private TextField sid;
    @FXML
    private Button Add;

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
    Statement statement;

    ArrayList<Course> mList = new ArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Check if class is exit 
            Class.forName("com.mysql.jdbc.Driver");
            // Create Instance of Connection by Mysql Driver
            Connection connection
                    = DriverManager.
                            getConnection("jdbc:mysql://127.0.0.1:3306/university3?serverTimezone=UTC",
                                    "root", "");
            // Make statment with Edit Option 
            this.statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // catch any exception form prevouis commands
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcDepartment.setCellValueFactory(new PropertyValueFactory("major"));
        tcSalary.setCellValueFactory(new PropertyValueFactory("grade"));
        ResultSet rs = null;
        try {
            rs = this.statement.executeQuery("Select * From course");
        } catch (SQLException ex) {
            Logger.getLogger(TextAreaPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        L1.getItems().clear();
        try {
            while (rs.next()) {
                Course s = new Course();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setRoom(rs.getInt("room"));

                mList.add(s);
                L1.getItems().add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextAreaPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void buttonShowHandle(ActionEvent event) throws Exception {
        String query = textArea.getText();

        if (query != null && !query.isEmpty()) {

            if (query.contains("update")) {
                // CHECK 
                int result = statement.executeUpdate(query);
                if (result > 0) {
                    System.out.println(result + " rows updated successfully");
                } else {
                    System.out.println("Error Update");
                }

            } else if (query.contains("select")) {

                ResultSet rs = statement.executeQuery(query);
                tableView.getItems().clear();
                while (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getInt("id"));
                    s.setName(rs.getString("name"));
                    s.setMajor(rs.getString("major"));
                    s.setGrade(rs.getDouble("grade"));
                    tableView.getItems().add(s);
                }
            }
        }

    }

    @FXML
    private void buttonAddHandle(ActionEvent event) throws Exception {
        System.out.println("CLICK:" + sid);
        int index = L1.getSelectionModel().getSelectedIndex();
        Course currentCourse = mList.get(index);

        String q = " INSERT INTO `registrations`(`studentid`, `courseid`, `semester`) VALUES (" + Integer.parseInt(sid.getText()) + ", " +  currentCourse.getId()  + "  , '2019' ) ";
        
        System.out.println("query: " + q);
      boolean isDone =   statement.execute(q);
        System.out.println("isDone: " + isDone
        );
    }
}
