/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import entities.Reservation;
import gui.ListReservationController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidevjava.PidevJava;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class FrontReservationFXMLController implements Initializable {

    @FXML
    private TextField tfphone;
    @FXML
    private TextField tfadresse;
    @FXML
    private DatePicker tdatedebut;
    @FXML
    private DatePicker tdatefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public long diff(java.util.Date one, java.util.Date two) {
        long diifff = (one.getTime() - two.getTime()) / 86400000;
        return Math.abs(diifff);
    }

    @FXML
    private void btnmenu(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/front/MenuFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnReservationfront(MouseEvent event) {
    }

    @FXML
    private void btneventFront(MouseEvent event) {
    }

    @FXML
    private void btnpanierFront(MouseEvent event) {
    }

    @FXML
    private void btnLivraisonFront(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clean(MouseEvent event) {
        tfphone.setText(null);
        tfadresse.setText(null);

    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, timeUnit);
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    @FXML
    private void save(MouseEvent event) {
        String addr = tfadresse.getText();
        LocalDate value1 = tdatedebut.getValue();
        LocalDate value2 = tdatefin.getValue();
        String tlf = tfphone.getText();

        try {
            if (tlf.toString().compareTo("") == 0 || tlf.toString().length() != 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Remplir votre numero du TELEPHONE : 8 numéros!");
                alert.show();
                Notifications notificationBuilder = Notifications.create()
                        .title("ERREUR RESERVATION NOTIFICATION ")
                        .text("Réservation non approuvée !! ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                System.out.println("Clicked on notif");

                            }
                        });
                notificationBuilder.showError();
            } else if (addr.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Ecrivez votre adresse!");
                alert.show();
                Notifications notificationBuilder = Notifications.create()
                        .title("Erreur RESERVATION NOTIFICATION  ")
                        .text("Réservation non approuvée !! ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                System.out.println("Clicked on notif");

                            }
                        });
                notificationBuilder.showError();
            } else if (value1 == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Remplir la date DEBUT!");
                alert.show();
                Notifications notificationBuilder = Notifications.create()
                        .title("ERREUR RESERVATION NOTIFICATION ")
                        .text("Réservation non approuvée !! ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                System.out.println("Clicked on notif");

                            }
                        });
                notificationBuilder.showError();
            } else if (value2 == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Remplir la date DEBUT!");
                alert.show();
                Notifications notificationBuilder = Notifications.create()
                        .title("ERREUR RESERVATION NOTIFICATION ")
                        .text("Réservation non approuvée !! ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                System.out.println("Clicked on notif");

                            }
                        });
                notificationBuilder.showError();
            } else {
                System.out.println(Date.valueOf(tdatedebut.getValue()).toString());
                System.out.println(Date.valueOf(tdatefin.getValue()).toString());
                Reservation p = new Reservation(Integer.parseInt(tfphone.getText()), tfadresse.getText(), Date.valueOf(tdatedebut.getValue()), Date.valueOf(tdatefin.getValue()));
                ReservationService ps = new ReservationService();
                
                
            Date resv = p.getDate_resv();
            
              ps.ajouterReservation(p);

            //converting date resv to calendar 
            toCalendar(resv);
            ////
            java.util.Date today = new java.util.Date();

            PidevJava myobject = new PidevJava();

            long days = myobject.diff(today, resv);

            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,YYYY");
            String todaysDate = sdf.format(today);

            String newYearsDay = sdf.format(resv);
            System.out.println(days + "     days from today s date of    " + todaysDate + "  until   " + newYearsDay);

/////// check

             
            

/////
          
            Notifications notificationBuilder = Notifications.create()
                    .title("RESERVATION NOTIFICATION ")
                    .text(days + "  nombre des jours restants de la date   " + todaysDate
                            + "  jusqu'à votre date du réservation   " + newYearsDay)
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("Clicked on notif");
                        }
                    });
            notificationBuilder.showConfirm();

        }

    }
    catch (SQLException ex

    
        ) {
            System.out.println(ex.getMessage());
    }
}

/* 
  // Create function for finding difference   
    static void find(String join_date, String leave_date)   
    {   
        // Create an instance of the SimpleDateFormat class  
        SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy ");
         String eya =  "24-12-2022";
                     String var =   "25-12-2022";
        // In the try block, we will try to find the difference  
        try {   
            // Use parse method to get date object of both dates  
            Date date1 = (Date) obj.parse(eya);   
            Date date2 = (Date) obj.parse(var);   
            // Calucalte time difference in milliseconds   
            long time_difference = date2.getTime() - date1.getTime();  
            // Calucalte time difference in days  
            long days_difference = (time_difference / (1000*60*60*24)) % 365;   
            // Calucalte time difference in years  
            long years_difference = (time_difference / (1000l*60*60*24*365));   
            // Calucalte time difference in seconds  
            long seconds_difference = (time_difference / 1000)% 60;   
            // Calucalte time difference in minutes  
            long minutes_difference = (time_difference / (1000*60)) % 60;   
              
            // Calucalte time difference in hours  
            long hours_difference = (time_difference / (1000*60*60)) % 24;   
            // Show difference in years, in days, hours, minutes, and seconds   
            System.out.print(   
                "Difference "  
                + "between two dates is: ");   
            System.out.println(   
                hours_difference   
                + " hours, "  
                + minutes_difference   
                + " minutes, "  
                + seconds_difference   
                + " seconds, "  
                + years_difference   
                + " years, "  
                + days_difference   
                + " days"  
                );   
        }   
        // Catch parse exception   
        catch (ParseException excep) {   
            excep.printStackTrace();   
        }   
    
}

 */
}
