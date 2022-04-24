/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Table;
import gui.ListReservationController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.TableService;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class BackAjoutTableFXMLController implements Initializable {

   
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
      @FXML
    private void btndashboardd(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
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
    private void btnListeTable(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackTableFXML.fxml"));
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
    private void btnListeEpmlacement(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackEmplacementFXML.fxml"));
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
    private void btnListeReservation(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackReservationFXML.fxml"));
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
    private void btnlListeEvent(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
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
    private void btnListePanier(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
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
    private void btnListeCommandes(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
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
    private void btnListeProduit(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
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
    private void btnListeLivreur(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
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
    private void btnListeLivraison(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
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
    private void btnListeUser(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
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
    private void close(MouseEvent event) {
          Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnListecategorie(MouseEvent event) {
    }

    @FXML
    private void save(MouseEvent event) {
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
             Notifications notificationBuilder = Notifications.create()
                            .title("Ajout d'une tabme NOTIFICATION ")
                            .text(" la nouvelle Table est ajoutée avec succés! ")
                            .graphic(null)
                            .hideAfter(Duration.seconds(10))
                            .position(Pos.TOP_RIGHT)
                            .onAction( new EventHandler<ActionEvent>() {
                 public void handle(ActionEvent event){
                     System.out.println("Clicked on notif");
                 }
                            });
                 notificationBuilder.showConfirm();
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void clean(MouseEvent event) {
         tfStock.setText(null);
        tfnbChaises.setText(null);
        tfEmplacement.setText(null);
    }
    
}
