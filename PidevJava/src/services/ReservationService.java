/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import utils.MyDB;

/**
 *
 * @author eyaam
 */
public class ReservationService implements IReservation<Reservation> {

    Connection connexion;
    Statement stm;

    public ReservationService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterReservation(Reservation t) throws SQLException {
        String req = "INSERT INTO `reservation` (`phone_resv`, `email_resv`,`date_resv`, `end_resv`) "
                + "VALUES ( '"
                + t.getPhone_resv()+ "', '" + t.getEmail_resv()+ "', '" + t.getDate_resv()
                + "', '" + t.getEnd_resv() + "') ";
        try {
            stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public void deleteResv(int id) throws SQLException {
         String req = "DELETE FROM `reservation` WHERE id_resv = "+ id;
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

  

   
    
}
