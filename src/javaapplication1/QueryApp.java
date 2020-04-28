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
import java.sql.Statement;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class QueryApp  extends Application{
    




    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
        Pane paneTextArea = FXMLLoader.load(JdbcApp.class.getResource("TextAreaPane.fxml"));
        
//       Pane paneTableView = FXMLLoader.load(getClass().getResource("TableViewPane.fxml"));
        Map<String, Pane> mapPanes = new TreeMap<>();
        mapPanes.put("textArea", paneTextArea);
//        mapPanes.put("tableView", paneTableView);
        Scene scene = new Scene(mapPanes.get("textArea"));
        primaryStage.setTitle("Streams and Database App");
        primaryStage.setScene(scene);
        primaryStage.show();
          }
    public static void main(String[] args) {
                System.out.println("PATH: " + JdbcApp.class.getResource(""));

        launch(args);
    }
}


/*

select student.* from student, registrations ,course where 
student.id = registrations.studentid and 
course.id = registrations.courseid 
and 
course.name = "Software Engineering"




select student.* from student where grade  >= 90


select student.* from student where grade  >= 50 order by name desc

*/