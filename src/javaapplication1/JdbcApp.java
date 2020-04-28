/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.net.URI;
import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JdbcApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
       // Pane paneTextArea = FXMLLoader.load(getClass().getResource("TextAreaPane.fxml"));
        System.out.println("PATH: " + JdbcApp.class.getResource(""));
        
        
        Pane paneTextArea = FXMLLoader.load(JdbcApp.class.getResource("TextAreaPane.fxml"));
        
       Pane paneTableView = FXMLLoader.load(getClass().getResource("TableViewPane.fxml"));
        Map<String, Pane> mapPanes = new TreeMap<>();
        mapPanes.put("textArea", paneTextArea);
        mapPanes.put("tableView", paneTableView);
        Scene scene = new Scene(mapPanes.get("tableView"));
        primaryStage.setTitle("Streams and Database App");
        primaryStage.setScene(scene);
        primaryStage.show();
          }
    public static void main(String[] args) {
                System.out.println("PATH: " + JdbcApp.class.getResource(""));

        launch(args);
    }

  
}
