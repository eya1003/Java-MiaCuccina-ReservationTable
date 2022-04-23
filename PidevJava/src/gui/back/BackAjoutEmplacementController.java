/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Emplacement;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.EmplacementService;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class BackAjoutEmplacementController implements Initializable {

    @FXML
    private TextField tvue;
    @FXML
    private TextField tdescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btndashboardd(MouseEvent event) {
    }

    @FXML
    private void btnListeTable(MouseEvent event) {
    }

    @FXML
    private void btnListeEpmlacement(MouseEvent event) {
    }

    @FXML
    private void btnListeReservation(MouseEvent event) {
    }

    @FXML
    private void btnlListeEvent(MouseEvent event) {
    }

    @FXML
    private void btnListePanier(MouseEvent event) {
    }

    @FXML
    private void btnListeCommandes(MouseEvent event) {
    }

    @FXML
    private void btnListeProduit(MouseEvent event) {
    }

    @FXML
    private void btnListeCategorie(MouseEvent event) {
    }

    @FXML
    private void btnListeLivreur(MouseEvent event) {
    }

    @FXML
    private void btnListeLivraison(MouseEvent event) {
    }

    @FXML
    private void btnListeUser(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(MouseEvent event) {
        Emplacement p = new Emplacement(tvue.getText(), tdescription.getText());
        EmplacementService ps = new EmplacementService();
        String vue = tvue.getText();
        String desc = tdescription.getText();
        try {
                if ( vue.isEmpty() || desc.isEmpty() ||  (vue.length() < 3 || desc.length() < 3 )) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill the data");
                alert.show();
                tvue.setText("");
                tdescription.setText("");
            }
                else   if (  vue.length() < 3 || desc.length() < 3 ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Vue et Descrptiob doit contient au moins 3 caractères");
                alert.show();
            }
                else if ( vue != desc ){
                        
                        ps.ajouterEmplacement(p);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Succes");
                            alert.setContentText("Emplacement ajouté");
                            alert.show();
                            tvue.setText("");
                            tdescription.setText("");}
    }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void clean(MouseEvent event) {
        tvue.setText(null);
        tdescription.setText(null);
    }
    
}
