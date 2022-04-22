/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import entities.Emplacement;
import entities.Table;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.EmplacementService;
import services.TableService;

/**
 *
 * @author macbook
 */

//Main Class
public class PersonnesFXMain extends Application {
    // singleton yaaml instance mara wa7da w ken t3awed izid aleha 
    Stage stage;
    Parent parent;
    
    @Override
    public void start(Stage primaryStage) {
       this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/gui/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
          
            stage.setTitle("Acceuil");
            stage.setScene(scene);
              primaryStage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
           
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
