/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Table;
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
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class ListTablesController implements Initializable {

    @FXML
    private TableView<Table> TableList;
    @FXML
    private TableColumn<Table, Integer> idColl;
    @FXML
    private TableColumn<Table, String> vueColl;
    @FXML
    private TableColumn<Table, String> nbChaiseColl;
    @FXML
    private TableColumn<Table, String> stockcoll;
    @FXML
    private TableColumn<Table, String> actionColl;
    
       String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Table tab = null;
    
    ObservableList<Table> table = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            vueColl.setCellValueFactory(new PropertyValueFactory<Table,String>("emp"));
            nbChaiseColl.setCellValueFactory(new PropertyValueFactory<Table,String>("nb_chaise_tab"));   
            stockcoll.setCellValueFactory(new PropertyValueFactory<Table,String>("stock_tab"));
            TableList.setItems(table);
            
            
            
            
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

                        Button btnModifier = new Button("Modifier");
                         Button btnsupp = new Button("Supprimer");

                  /*
                        btnsupp.setOnMouseClicked((MouseEvent event) -> {
                            try{
                               emplacement = emplacementTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `emplacement` WHERE id_emplacement="+emplacement.getId_emplacement();
                              Connection con = MyDB.getInstance().getConnexion();
                                  preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                               
                       
                                
                           } catch (SQLException ex) {
                                Logger.getLogger(ListEmplacementsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               
                        });
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
         TableList.setItems(table);
    }    

    @FXML
    private void refresh(MouseEvent event) {
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
            vueColl.setCellValueFactory(new PropertyValueFactory<Table,String>("emp"));
            nbChaiseColl.setCellValueFactory(new PropertyValueFactory<Table,String>("nb_chaise_tab"));   
            stockcoll.setCellValueFactory(new PropertyValueFactory<Table,String>("stock_tab"));
            TableList.setItems(table);
    }

    @FXML
    private void getadd(MouseEvent event) {
        try {
             Parent parent = FXMLLoader.load(getClass().getResource("/gui/AjouterTableFXML.fxml"));
             Scene scene = new Scene(parent);
             Stage stage = new  Stage();
             stage.setScene(scene);
             stage.initStyle(StageStyle.UTILITY);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(AjouterTableFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
