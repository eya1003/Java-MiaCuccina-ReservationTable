/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class StatistiqueTableFXMLController implements Initializable {

    @FXML
    private BarChart<String, Integer> BarChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         String query = "SELECT id_tab, emp FROM table ";
        System.out.println(query);
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            Connection con = MyDB.getInstance().getConnexion();
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString("emp"), rs.getInt("id_tab")));
            }
            BarChart.getData().add(series);
        } catch (Exception e) {
        }
    }    
    
}
