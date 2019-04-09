/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chartdrawer;

import chartElementsClasses.fileManager;
import chartElementsClasses.functions;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label r2_label;
    
    @FXML
    private Label functionLabel;
    
    @FXML
    void addNewFile(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv", "*.csv"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("tsv", "*.tsv"));
        File data = fc.showOpenDialog(null);
        ScatterChart.Series<Number, Number> s = p.getDataFromFile(data);
        dataChart.getData().add(s);
        String temp = ".default-color" + amountOfSeries + ".chart-series-line";
        dataChart.lookup(temp).setStyle("-fx-stroke: transparent");
        amountOfSeries++;
    }
    
    @FXML
    void addNewFunction(ActionEvent event) {
        TextInputDialog tx = new TextInputDialog();
        tx.setContentText("Podaj wartość funkcji");
        tx.setTitle("Dodawanie funkcji");
        Optional<String> response = tx.showAndWait();
        if (response.isPresent()){
            functionManager.addFunction(response.get());
        }
    }
    
    @FXML
    void approximate(ActionEvent event) throws FileNotFoundException {
FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv", "*.csv"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("tsv", "*.tsv"));
        File data = fc.showOpenDialog(null);
        ScatterChart.Series<Number, Number> s = p.getDataFromFile(data);
        dataChart.getData().add(s);
        String temp = ".default-color" + amountOfSeries + ".chart-series-line";
        dataChart.lookup(temp).setStyle("-fx-stroke: red");
        
        amountOfSeries++;        
//Tutaj obliczanie funkcji po dodaniu pliku
    }
    
    @FXML
    void clearData(ActionEvent event) throws InterruptedException {
        while (!dataChart.getData().isEmpty()) {
            dataChart.getData().remove((int) (Math.random() * (dataChart.getData().size() - 1)));
        }
        amountOfSeries = 0;
        p.clearList();
    }
    
    @FXML
    void exitApplication(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    void showFunctions(ActionEvent event) {
        Stage s = new Stage();
        TextArea ta = new TextArea();
        
        String[] tempStrings = functionManager.getFunctions();
        for (String sTemp: tempStrings){
            ta.appendText(sTemp + "\n");
        }
        s.setScene(new Scene(ta));
        s.setResizable(false);
        s.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        
        p = new fileManager();
        
        try {
            functionManager = new functions();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataChart = new LineChart<>(xAxis, yAxis);
        
        Stage s = new Stage();
        Scene paneChart = new Scene(dataChart);
        
        s.setScene(paneChart);
        //paneChart.getStylesheets().add(getClass().getResource("chartCSS.css").toExternalForm());
        s.setResizable(false);
        s.setWidth(1000);
        s.setHeight(800);
        s.show();
        
    }
    
    functions functionManager;
    LineChart<Number, Number> dataChart;
    fileManager p;
    int amountOfSeries = 0;
}
