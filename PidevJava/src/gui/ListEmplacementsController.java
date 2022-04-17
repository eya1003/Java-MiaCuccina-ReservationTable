/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entities.Emplacement;
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
import javafx.scene.control.Alert;
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
import services.EmplacementService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class ListEmplacementsController implements Initializable {

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
    Emplacement emplacement = null;
    
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
        EmplacementListe.add(new Emplacement(rs.getString("type_emplacement"),rs.getString("Description")));
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
//                            try{
//                               emplacement = emplacementTable.getSelectionModel().getSelectedItem();
//                                query = "DELETE FROM `emplacement` WHERE id_emplacement="+emplacement.getId_emplacement();
//                              Connection con = MyDB.getInstance().getConnexion();
//                                  preparedStatement = connection.prepareStatement(query);
//                                preparedStatement.execute();
//                  
//                           } catch (SQLException ex) {
//                                Logger.getLogger(ListEmplacementsController.class.getName()).log(Level.SEVERE, null, ex);
//                           }
//                        });

                if (emplacementTable.getSelectionModel().getSelectedItem() != null) {
            try {
                emplacement = emplacementTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `emplacement` WHERE id_emplacement  ="+emplacement.getId_emplacement();
                                 Connection connection = MyDB.getInstance().getConnexion();
                               PreparedStatement preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                       
                                refreshTable();
            } catch (SQLException ex) {
                Logger.getLogger(ListEmplacementsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner un Emplacement");
            alert.show();
        }
                        }); 
                        
                        
                        
                        
                        ////modifier
                        btnModifier.setOnMouseClicked((MouseEvent event) -> {
                            
//                            emplacement = emplacementTable.getSelectionModel().getSelectedItem();
//                            FXMLLoader loader = new FXMLLoader ();
//                            loader.setLocation(getClass().getResource("/gui/AjouterPFXML.fxml"));
//                            try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                Logger.getLogger(ListEmplacementsController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            
//                            AjouterPFXMLController addStudentController = loader.getController();
//                            addStudentController.setUpdate(true);
//                            addStudentController.setTextField(emplacement.getType_emplacement(), emplacement.getDescription());
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();

if (emplacementTable.getSelectionModel().getSelectedItem() != null) {

            
               try {
          Emplacement a = emplacementTable.getSelectionModel().getSelectedItem();
        emplacement = a;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterPFXML.fxml"));
        Parent root = loader.load();
        AjouterPFXMLController hc = loader.getController();
       hc.setEmplacement(a);
        
        Stage stage= new Stage();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
       refreshTable();
        } catch (IOException ex) {
            Logger.getLogger(ListEmplacementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                   } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner un emplacement");
            alert.show();
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
    
    
    
    @FXML
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

    @FXML
    private void getAdd(MouseEvent event) {
         try {
             Parent parent = FXMLLoader.load(getClass().getResource("/gui/AjouterPFXML.fxml"));
             Scene scene = new Scene(parent);
             Stage stage = new  Stage();
             stage.setScene(scene);
             stage.initStyle(StageStyle.UTILITY);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ListEmplacementsController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
    }

  
    
}
