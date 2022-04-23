/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import entities.Reservation;
import gui.ListReservationController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class FrontReservationFXMLController implements Initializable {

    @FXML
    private TextField tfphone;
    @FXML
    private TextField tfadresse;
    @FXML
    private DatePicker tdatedebut;
    @FXML
    private DatePicker tdatefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnmenu(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/front/MenuFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnReservationfront(MouseEvent event) {
    }

    @FXML
    private void btneventFront(MouseEvent event) {
    }

    @FXML
    private void btnpanierFront(MouseEvent event) {
    }

    @FXML
    private void btnLivraisonFront(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clean(MouseEvent event) {
         tfphone.setText(null);
        tfadresse.setText(null);
        
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
