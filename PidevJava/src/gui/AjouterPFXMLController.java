/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Emplacement;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.isEmpty;
import static javafx.beans.binding.Bindings.isEmpty;
import static javafx.beans.binding.Bindings.isEmpty;
import static javafx.beans.binding.Bindings.isEmpty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.EmplacementService;

/**
 * FXML Controller class
 *
 * @author macbook
 */
public class AjouterPFXMLController implements Initializable {

    private Label lListeP;
    @FXML
    private TextField tvue;
    @FXML
    private TextArea tdescription;
    
     
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Emplacement emplacement = null;
    private boolean update;
    int studentId;
    @FXML
    private Label lusername;

    /**
     * Initializes the controller class.
     */
    public void setType_emplacement(String vue) {
        tvue.setText(vue);
    }
    public void setEmplacement(Emplacement h){
            he=h;
    
      this.tvue.setText(he.getType_emplacement());
      this.tdescription.setText(he.getDescription());
      /* this.imageuploadm.setAccessibleText(he.getImage());*/
}
    public static Emplacement he;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void BtnAjouterP(ActionEvent event) {
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
                else {
                     getQuery();
                }
               
           

        }

             
            
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
      void setTextField( String v, String d) {

        tvue.setText(v);
        tdescription.setText(d);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

 private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `emplacement`( `type_emplacement`, `Description`) VALUES (?,?)";

        }else{
            query = "UPDATE `emplacement` SET "
                    + "`type_emplacement`=?,"
                    + "`Description`=? WHERE id_emplacement = '"+emplacement.getId_emplacement()+"'";
        }

    }



    @FXML
    private void Clean(MouseEvent event) {
         tvue.setText(null);
        tdescription.setText(null);
    }

}
