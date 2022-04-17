/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class AjoutReservationController implements Initializable {

    @FXML
    private TextField tfvue;
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
    Reservation reservation = null;
    private boolean update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Reservation p = new Reservation(tfvue.getText(), ParseTo.valueOf(tfphone()),tfadresse.getText()
//        ,String.valueOf(tdatedebut.getValue()), String.valueOf(tdatefin.getValue()) );
//        EmplacementService ps = new EmplacementService();
//        String vue = tvue.getText();
//        String desc = tdescription.getText();
//        try {
//                if ( vue.isEmpty() || desc.isEmpty() ) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("Please Fill the data");
//                alert.show();
//                tvue.setText("");
//                tdescription.setText("");
//            }
//                else{
//                        
//                        ps.ajouterEmplacement(p);
//                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                            alert.setTitle("Succes");
//                            alert.setContentText("Reservation ajouté");
//                            alert.show();
//                            tvue.setText("");
//                            tdescription.setText("");}
//
//   
   }

    @FXML
    private void save(MouseEvent event) {
    }

    @FXML
    private void clean(MouseEvent event) {
    }
    
}
