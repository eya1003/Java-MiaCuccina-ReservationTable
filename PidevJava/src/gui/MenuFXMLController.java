/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class MenuFXMLController implements Initializable {

    private TextField tfphone;
    private TextField tfadresse;
    private DatePicker tdatedebut;
    private DatePicker tdatefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void save(MouseEvent event) {
          String addr = tfadresse.getText();
        try {
             
             if (  addr.isEmpty() ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Ecrivez votre adresse!");
                alert.show();
            }
             else {
                 System.out.println(Date.valueOf(tdatedebut.getValue()).toString());
         System.out.println(Date.valueOf(tdatefin.getValue()).toString());
        Reservation p = new Reservation(Integer.parseInt(tfphone.getText()), tfadresse.getText(),Date.valueOf(tdatedebut.getValue()),Date.valueOf(tdatefin.getValue()));
        ReservationService ps = new ReservationService();
                    ps.ajouterReservation(p);
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Succes");
                            alert.setContentText("Reservation ajouté");
                            alert.show();
           
             }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
                }
    

    private void clean(MouseEvent event) {
         tfphone.setText(null);
        tfadresse.setText(null);
        
    }

    
}
