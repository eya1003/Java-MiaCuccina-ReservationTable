/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    
 public ArrayList<Table> TrierParStockTable() {

        ArrayList<Table> List = new ArrayList<>();
        try {

            String req = "select * from `table` ORDER BY stock_tab";
          PreparedStatement pre = connexion.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);

            while (rs.next()) {
                Table m = new Table();

               
                m.setId_tab(rs.getInt("id_tab"));
                m.setNb_chaise_tab(rs.getInt("nb_chaise_tab"));
                m.setStock_tab(rs.getInt("stock_tab"));
                m.setEmp(rs.getString("emp"));
             
               
         

                List.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List;
    }
}
