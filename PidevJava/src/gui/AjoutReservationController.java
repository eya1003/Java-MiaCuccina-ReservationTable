/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class AjoutReservationController implements Initializable {

    @FXML
    private DatePicker tdatedebut;
    @FXML
    private DatePicker tdatefin;
        @FXML
    private TextField tfphone;
    @FXML
    private TextField tfadresse;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    private boolean update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
       

    @FXML
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

        
    }

    

