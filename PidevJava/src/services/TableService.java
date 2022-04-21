/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import utils.MyDB;

/**
 *
 * @author eyaam
 */
public class TableService implements ITable<Table>{

     Connection connexion;
    Statement stm;

    public TableService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterTable(Table t) throws SQLException {
            String req = "INSERT INTO `table` (`emp`, `nb_chaise_tab`, `stock_tab`) VALUES ( '"
                + t.getEmp()+ "', '" + t.getNb_chaise_tab() +  "', '" + t.getStock_tab() +"') ";
        try {
            stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @Override
    public void deleteTable(int id) throws SQLException {
         String req = "DELETE FROM `table` WHERE id_tab = "+ id;
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    public void modifierTab(Table t, int id) throws SQLException {
  String req = "UPDATE table SET  emp = ?, nb_chaise_tab = ?,  stock_tab = ? where id_tab= " + id;
        PreparedStatement pre = connexion.prepareStatement(req);
  
        pre.setString(1, t.getEmp());
        pre.setInt(2, t.getNb_chaise_tab());
        pre.setInt(3, t.getStock_tab());
        
       pre.executeUpdate();  
    }
}
