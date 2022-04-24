/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Emplacement;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.EmplacementService;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class BackModifierEmplacementFXMLController implements Initializable {

    @FXML
    private TextField tvuemodif;
    @FXML
    private TextField tdescriptionmodif;
    
    int id ;
    Emplacement c;
    
        ObservableList<Emplacement> ListRV=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       public void inflateUI(Emplacement rv) {
         id=rv.getId_emplacement();
        tvuemodif.setText(rv.getType_emplacement());
//        //profField.setText(m.getNomProf());
        tdescriptionmodif.setText(rv.getDescription());
        
    }
        public void setEmp(Emplacement c) {
        System.out.println(c) ;
        tvuemodif.setText(c.getType_emplacement());
        tdescriptionmodif.setText(c.getDescription());
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
    private void save(MouseEvent event) {
          
         

            EmplacementService cs = new EmplacementService();
            String vuue = tvuemodif.getText();
            String description = tdescriptionmodif.getText();

            Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
            a1.setTitle("Modification d'une catégorie");
            a1.setContentText("vous voulez vraiment Mdofier cette catégorie ?");
            Optional<ButtonType> result = a1.showAndWait();
            if (result.get() == ButtonType.OK) {
           //     cs.modifierEmplacement(c, id);

            } else if (result.get() == ButtonType.CANCEL) {

            }

        }
    

    @FXML
    private void clean(MouseEvent event) {
    }
    
}
