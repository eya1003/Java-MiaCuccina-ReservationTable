/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Table;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.time.Duration.between;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.EmplacementService;
import services.TableService;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class AjouterTableFXMLController implements Initializable {

    @FXML
    private TextField tfStock;
    @FXML
    private TextField tfnbChaises;
    @FXML
    private TextField tfEmplacement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
 public void setEmp(String vue) {
        tfEmplacement.setText(vue);
    }
    @FXML
    private void btnValiderTable(ActionEvent event) {
        Table t = new Table(tfEmplacement.getText(),
                Integer.parseInt(tfnbChaises.getText()), 
                Integer.parseInt(tfStock.getText())
        );
        TableService ts = new TableService();
         String Emp = tfEmplacement.getText();
         String chaise= tfnbChaises.getText().toString();
        try {
            if (  Emp.isEmpty()  ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs doit etre repmlir");
                alert.show();
            }
            else if (  chaise.toString().length()   >10 ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("La valeur du stock doit etre entre 1 et 10");
                alert.show();
            }
           
//           else if (  tfnbChaises.toString().isEmpty() ||  tfStock.toString().isEmpty() ||  tfEmplacement.toString().isEmpty() ) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("Tous les champs doit etre repmlir");
//                alert.show();
//            }
                else {
            ts.ajouterTable(t);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Table  ajoutée");
            alert.show();
            tfEmplacement.setText("");
            tfnbChaises.setText("");
            tfStock.setText("");
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnListe(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ListEmplacements.fxml"));
        try {
            Parent root = loader.load();
            ListEmplacementsController controller = loader.getController();
          //  controller.setEmp(tfEmplacement.getText());
           // tfEmplacement.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnValiderTable(MouseEvent event) {
    }

    @FXML
    private void cleanTable(MouseEvent event) {
    }
    
}
