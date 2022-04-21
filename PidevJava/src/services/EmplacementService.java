/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Emplacement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author eyaam
 */
public class EmplacementService implements IEmplacement<Emplacement> {

    Connection connexion;
    Statement stm;

    public EmplacementService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    

    @Override
    public List<Emplacement> afficherEmplacemennt() throws SQLException {
        List<Emplacement> emplac = new ArrayList<>();
        String req = "select * from emplacement";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Emplacement p = new Emplacement(rst.getInt("id_emplacement"),//or rst.getInt(1)
                    rst.getString("type_emplacement"),
                    rst.getString("Description"));
            emplac.add(p);
        }
        return emplac;
    }

    @Override
    public void ajouterEmplacement(Emplacement em) throws SQLException {
 String req = "INSERT INTO `emplacement` (`type_emplacement`, `description`) VALUES ( '"
                + em.getType_emplacement() + "', '" + em.getDescription() + "') ";
        try {
            stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     //***********************update**********

    @Override
    public void modifierEmplacement(Emplacement e, int id) throws SQLException {
       
        String req = "UPDATE emplacement SET   type_emplacement = ?, description = ?  where id_emplacement=" + id;
        PreparedStatement pre = connexion.prepareStatement(req);
      
        pre.setString(1, e.getType_emplacement());
        pre.setString(2, e.getDescription());
        
        pre.executeUpdate();
    }
    
    
    //***************sup**************
    @Override
    public void deleteEmplacement(int id) throws SQLException {
         String req = "DELETE FROM emplacement WHERE id_emplacement = "+ id;
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    public void deleteEmplacementParType(String type) throws SQLException {
         String req = "DELETE FROM emplacement WHERE type_emplacement = "+ type;
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
     public void ajouterEmplacementAutrement(Emplacement emp) throws SQLException {
        String req = "INSERT INTO `emplacement` (`type_emplacement`, `description` ) "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, emp.getType_emplacement());
        ps.setString(2, emp.getDescription());
        ps.executeUpdate();
    }

}
