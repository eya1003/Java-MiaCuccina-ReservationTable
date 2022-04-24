/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import entities.Emplacement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.EmplacementService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author macbook
 */
public class PidevJava {
public long diff(Date one, Date two){
    long diifff = (one.getTime()-two.getTime())/86400000;
    return Math.abs(diifff);
}
public static void main(String args[]){
    Date today = new Date();
    Calendar mynextcalendar = Calendar.getInstance();
    Date nyd = mynextcalendar.getTime();
    PidevJava myobject = new PidevJava();
    long days = myobject.diff(today,nyd);
    
    SimpleDateFormat sdf= new SimpleDateFormat("MMM dd,YYYY");
    String todaysDate = sdf.format(today);
    String newYearsDay = sdf.format(nyd);
    System.out.println(days+"     days from today s date of    "+todaysDate + "  until   "+newYearsDay);
    
}

 
}