/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author macbook
 */
public class AcceuilFXMLController implements Initializable {

    @FXML
    private TextField tusername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void btnValidate(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterPFXML.fxml"));
        try {
            Parent root = loader.load();
            AjouterPFXMLController controller = loader.getController();
            controller.setType_emplacement(tusername.getText());
            tusername.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnAjoutTable(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterTableFXML.fxml"));
        try {
            Parent root = loader.load();
            AjouterTableFXMLController controller = loader.getController();
            controller.setEmp(tusername.getText());
            tusername.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
