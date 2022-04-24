/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Emplacement;
import entities.Table;
import gui.ListReservationController;
import gui.ListTablesController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import services.TableService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class BackTableFXMLController implements Initializable {

    @FXML
    private TableView<Table>  tabletable;
    @FXML
    private TableColumn<Table, Integer> idColl;
    @FXML
    private TableColumn<Emplacement, String> vueColl1;
    @FXML
    private TableColumn<Table, Integer>nbChaiseColl;
    @FXML
    private TableColumn<Table, String> actionsColl1;
    @FXML
    private TableColumn<Table, Integer> stockcoll;
    ObservableList<Table> table = FXCollections.observableArrayList();
    @FXML
    private TextField tfsearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
         Connection con = MyDB.getInstance().getConnexion();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `table`");
        while(rs.next()){
        table.add(new Table(rs.getInt("id_tab"),rs.getString("emp"),rs.getInt("nb_chaise_tab"),rs.getInt("stock_tab")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ListTablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
            idColl.setCellValueFactory(new PropertyValueFactory<Table,Integer>("id_tab"));
            vueColl1.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("emp"));
            nbChaiseColl.setCellValueFactory(new PropertyValueFactory<Table,Integer>("nb_chaise_tab"));   
            stockcoll.setCellValueFactory(new PropertyValueFactory<Table,Integer>("stock_tab"));
            tabletable.setItems(table);
            
           String a= new  Emplacement().getType_emplacement();
            
            
            //add cell of button edit 
         Callback<TableColumn<Table, String>, TableCell<Table, String>> cellFoctory = (TableColumn<Table, String> param) -> {
            // make cell containing buttons
            final TableCell<Table, String> cell = new TableCell<Table, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button btnModifierTab = new Button("Modifier");
                         Button btnsupp = new Button("Supprimer");

                  
                     btnsupp.setOnMouseClicked((MouseEvent event) -> {
                            TableService T = new TableService();
                           try{
                          T.deleteTable(tabletable.getSelectionModel().getSelectedItem().getId_tab());
                                       
                                       
           JOptionPane.showMessageDialog(null, "Table supprimée");
           Table selectedItem = tabletable.getSelectionModel().getSelectedItem();
            tabletable.getItems().remove(selectedItem);       
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }
                           tabletable.refresh();
                            
               
                        });
                        
                        
                        btnModifierTab.setOnMouseClicked((MouseEvent event) -> {
                         
           
                        });

                        HBox managebtn = new HBox(btnModifierTab,btnsupp);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnModifierTab, new Insets(2, 2, 0, 3));
                         HBox.setMargin(btnsupp, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         actionsColl1.setCellFactory(cellFoctory);
         tabletable.setItems(table);
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
    private void getadd(MouseEvent event) {
         try {
             Parent parent = FXMLLoader.load(getClass().getResource("/gui/back/BackAjoutTableFXML.fxml"));
             Scene scene = new Scene(parent);
             Stage stage = new  Stage();
             stage.setScene(scene);
             stage.initStyle(StageStyle.UTILITY);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(BackAjoutTableFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void btnrefresh(MouseEvent event) {
         try{
         Connection con = MyDB.getInstance().getConnexion();
         table.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `table`");
        while(rs.next()){
        table.add(new Table(rs.getInt("id_tab"),rs.getString("emp"),rs.getInt("nb_chaise_tab"),rs.getInt("stock_tab")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ListTablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
               idColl.setCellValueFactory(new PropertyValueFactory<Table,Integer>("id_tab"));
            vueColl1.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("emp"));
            nbChaiseColl.setCellValueFactory(new PropertyValueFactory<Table,Integer>("nb_chaise_tab"));   
            stockcoll.setCellValueFactory(new PropertyValueFactory<Table,Integer>("stock_tab"));
            tabletable.setItems(table);
    }

    @FXML
    private void search(MouseEvent event) {
      
    }

    

    @FXML
    private void triii(MouseEvent event) {
        TableService ms = new TableService();
        ObservableList<Table> liste = FXCollections.observableArrayList(ms.TrierParStockTable());

             idColl.setCellValueFactory(new PropertyValueFactory<Table,Integer>("id_tab"));
            vueColl1.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("emp"));
            nbChaiseColl.setCellValueFactory(new PropertyValueFactory<Table,Integer>("nb_chaise_tab"));   
            stockcoll.setCellValueFactory(new PropertyValueFactory<Table,Integer>("stock_tab"));
            tabletable.setItems(liste);
    }
    
}
