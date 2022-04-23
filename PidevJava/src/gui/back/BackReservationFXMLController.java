/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Reservation;
import gui.ListReservationController;
import gui.ListTablesController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import services.ReservationService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class BackReservationFXMLController implements Initializable {

     @FXML
    private TableView<Reservation> ReservationTable;
    @FXML
    private TableColumn<Reservation, Integer> phoneColl;
    @FXML
    private TableColumn<Reservation, String> mailColl;
    @FXML
    private TableColumn<Reservation, Date> debutColl;
    @FXML
    private TableColumn<Reservation, Date> finColl;
    @FXML
    private TableColumn<Reservation, String> actionColl;
    
    
     ObservableList<Reservation> listereservtion = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
         Connection con = MyDB.getInstance().getConnexion();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `reservation`");
        while(rs.next()){
        listereservtion.add(new Reservation(rs.getInt("id_resv"),rs.getInt("phone_resv"),rs.getString("email_resv"),rs.getString("tab_resv")
                         ,rs.getDate("end_resv"),rs.getDate("date_resv")
        ));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
            phoneColl.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("phone_resv"));   
            mailColl.setCellValueFactory(new PropertyValueFactory<Reservation,String>("email_resv"));
             debutColl.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("date_resv"));
              finColl.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("end_resv"));
            
            ReservationTable.setItems(listereservtion);
            
              //add cell of button edit 
         Callback<TableColumn<Reservation, String>, TableCell<Reservation, String>> cellFoctory = (TableColumn<Reservation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reservation, String> cell = new TableCell<Reservation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button btnModifier = new Button("Modifier");
                         Button btnsupp = new Button("Supprimer");

                  
                        btnsupp.setOnMouseClicked((MouseEvent event) -> {
                                        ReservationService T = new ReservationService();
                            System.out.println(ReservationTable.getSelectionModel().getSelectedItem().getId_resv());
       try{
                                     T.deleteResv(ReservationTable.getSelectionModel().getSelectedItem().getId_resv());
                                       
                                       
           JOptionPane.showMessageDialog(null, "Reservation supprimée");
           Reservation selectedItem = ReservationTable.getSelectionModel().getSelectedItem();
            ReservationTable.getItems().remove(selectedItem);       
   } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }
                            
               
                        });
                        /*
                        btnModifier.setOnMouseClicked((MouseEvent event) -> {
                            
                            emplacement = emplacementTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui/AjouterPFXML.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ListEmplacementsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AjouterPFXMLController addStudentController = loader.getController();
                            addStudentController.setUpdate(true);
                            addStudentController.setTextField(emplacement.getType_emplacement(), emplacement.getDescription());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });*/

                        HBox managebtn = new HBox(btnModifier,btnsupp);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnModifier, new Insets(2, 2, 0, 3));
                         HBox.setMargin(btnsupp, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         actionColl.setCellFactory(cellFoctory);
         ReservationTable.setItems(listereservtion);
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
            Logger.getLogger(AllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void btnListeCategorie(MouseEvent event) {
    }

    @FXML
    private void btnAjouter(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/front/FrontReservationFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnrefresh(MouseEvent event) {
      try{
         Connection con = MyDB.getInstance().getConnexion();
          listereservtion.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `reservation`");
        while(rs.next()){
        listereservtion.add(new Reservation(rs.getString("tab_resv"),rs.getInt("phone_resv"),rs.getString("email_resv")
                        ,rs.getDate("date_resv") ,rs.getDate("end_resv")
        ));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ListTablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
            phoneColl.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("phone_resv"));   
            mailColl.setCellValueFactory(new PropertyValueFactory<Reservation,String>("email_resv"));
             debutColl.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("date_resv"));
              finColl.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("end_resv"));
            
            ReservationTable.setItems(listereservtion);
            
    }
    
}
