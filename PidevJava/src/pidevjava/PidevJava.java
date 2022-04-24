/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import entities.Emplacement;
import java.sql.SQLException;
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
public class PidevJava extends Application{

 
 
	public void start(Stage primaryStage) {
		 Pane root = new Pane();
		 ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
				 new PieChart.Data("Nitrogen", 7809),
				 new PieChart.Data("Oxygen", 2195),
				 new PieChart.Data("Other", 93));
				 PieChart pieChart = new PieChart(valueList);
				 pieChart.setTitle("Air composition");
				 pieChart.getData().forEach(data -> {
				 String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
				 Tooltip toolTip = new Tooltip(percentage);
				 Tooltip.install(data.getNode(), toolTip);
				});

		 //adding pieChart to the root.
		 root.getChildren().addAll(pieChart);
		 Scene scene = new Scene(root, 450, 450);
		 primaryStage.setTitle("Pie Chart Demo");
		 primaryStage.setScene(scene);
		 primaryStage.show();
		}
		public static void main(String[] args) {
		 launch(args);
		 }
	}