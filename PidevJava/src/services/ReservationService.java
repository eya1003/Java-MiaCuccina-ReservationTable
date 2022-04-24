/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

  
 public ArrayList<Reservation> TrierParDateReservation() {

        ArrayList<Reservation> List = new ArrayList<>();
        try {

            String req = "select * from reservation ORDER BY date_resv";
          PreparedStatement pre = connexion.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);

            while (rs.next()) {
                Reservation m = new Reservation();

               
                m.setId_resv(rs.getInt("id_resv"));
                m.setDate_resv(rs.getDate("date_resv"));
                m.setEnd_resv(rs.getDate("end_resv"));
                m.setPhone_resv(rs.getInt("phone_resv"));
                 m.setEmail_resv(rs.getString("email_resv"));
             
               
         

                List.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List;
    }
   
     
     public List<Reservation> RechercherReservation(String x) {
        ArrayList<Reservation> List = new ArrayList<>();
        try {
            String req = "Select * from reservation where  date_resv like '%" + x + "%'  ";
            System.out.println("aa: "+x);
       PreparedStatement pre = connexion.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);
                while (rs.next()) {
                 Reservation m = new Reservation();

                m.setId_resv(rs.getInt("id_resv"));
                m.setDate_resv(rs.getDate("date_resv"));
                m.setEnd_resv(rs.getDate("end_resv"));
                m.setPhone_resv(rs.getInt("phone_resv"));
                 m.setEmail_resv(rs.getString("email_resv"));
             
                    List.add(m);
                }

           

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (List.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        return List;
    }
   
    
}
