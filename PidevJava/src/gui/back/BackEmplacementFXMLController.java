/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Emplacement;
import gui.ListEmplacementsController;
import gui.ListReservationController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import services.EmplacementService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class BackEmplacementFXMLController implements Initializable {

  
      @FXML
    private TableView<Emplacement> emplacementTable;
    @FXML
    private TableColumn<Emplacement, String> vueColl;
    @FXML
    private TableColumn<Emplacement, String> descriptionColl;
    @FXML
    private TableColumn<Emplacement, String> actionsColl;
    
     String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    ObservableList<Emplacement> EmplacementListe = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        Connection con = MyDB.getInstance().getConnexion();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM emplacement");
            
        while(rs.next()){
        EmplacementListe.add(new Emplacement(rs.getInt("id_emplacement"),rs.getString("type_emplacement"),rs.getString("Description")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ListEmplacementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            vueColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("type_emplacement"));
            descriptionColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("Description"));  
            emplacementTable.setItems(EmplacementListe);
            
              
       
            
             //add cell of button edit 
         Callback<TableColumn<Emplacement, String>, TableCell<Emplacement, String>> cellFoctory = (TableColumn<Emplacement, String> param) -> {
            // make cell containing buttons
            final TableCell<Emplacement, String> cell = new TableCell<Emplacement, String>() {
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

                  //supprimer
                        btnsupp.setOnMouseClicked((MouseEvent event) -> {
              
                            EmplacementService T = new EmplacementService();


                             System.out.println(emplacementTable.getSelectionModel().getSelectedItem().getId_emplacement());
       try{
//                                     T.deleteEmplacement(emplacementTable.getSelectionModel().getSelectedItem().getId_emplacement());
//                                       
//                                       
//           JOptionPane.showMessageDialog(null, "Data telah terhapus");
//           Emplacement selectedItem = emplacementTable.getSelectionModel().getSelectedItem();
//            emplacementTable.getItems().remove(selectedItem);       
Emplacement e = emplacementTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `emplacement` WHERE id_emplacement  ="+e.getId_emplacement();
                                connection = MyDB.getInstance().getConnexion();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
   } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }

                        }); 
                 
                        
                        
                        
                        
                        ////modifier
                        btnModifier.setOnMouseClicked((MouseEvent event) -> {                       
                if (emplacementTable.getSelectionModel().getSelectedItem() != null) {
            EmplacementService updateSer = new EmplacementService();
                 
                    }

             });

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
         actionsColl.setCellFactory(cellFoctory);
         emplacementTable.setItems(EmplacementListe);
         
    }
    
    
    
    private void refreshTable() {
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            EmplacementListe.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM emplacement");
        while(rs.next()){
        EmplacementListe.add(new Emplacement(rs.getString("type_emplacement"),rs.getString("Description")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ListEmplacementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            vueColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("type_emplacement"));
            descriptionColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("Description"));  
            emplacementTable.setItems(EmplacementListe);
            
        
    }
        //
    

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
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackReservationFXML.fxml"));
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
    private void btnlListeEvent(MouseEvent event) {
          try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
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
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/bak/BackAjoutEmplacement.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
