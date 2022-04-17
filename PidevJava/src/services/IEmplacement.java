/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author eyaam
 */
public interface IEmplacement<E> {
    
     public void ajouterEmplacement(E e) throws SQLException;
    public List<E> afficherEmplacemennt() throws SQLException;
     public void ajouterEmplacementAutrement(E emp) throws SQLException;
    public void modifierEmplacement(E e,int id) throws SQLException;
    public void deleteEmplacement(int id_emplacement) throws SQLException;
    
}
