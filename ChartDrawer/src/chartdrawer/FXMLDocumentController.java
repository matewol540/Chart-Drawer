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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import onp.Function;

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
    void addNewFunction(ActionEvent event) throws FileNotFoundException {
        TextInputDialog tx = new TextInputDialog();
        tx.setContentText("Podaj wartość funkcji");
        tx.setTitle("Dodawanie funkcji");
        Optional<String> response = tx.showAndWait();
        if (response.isPresent()) {
            functionManager.addFunction(response.get());
        }
        functionManager.saveFunction();
    }
    
    @FXML
    void approximate(ActionEvent event) throws FileNotFoundException, Exception {
        ObservableList<Series<Number, Number>> temp = dataChart.getData();
        Function[] functionsTemp = functionManager.getFunctionClass();
        Map<Function, Double> mapFuncitons = new HashMap<Function, Double>();
        double summarryDistance = 0;
        
        for (Series<Number, Number> s : temp) {
            for (Function sF : functionsTemp) {
                summarryDistance = 0;
                for (Data<Number, Number> d : s.getData()) {
                    double x = (double) d.getXValue();
                    double y = (double) d.getYValue();
                    summarryDistance += functionManager.pointDistance(x, y, sF);
                }
                mapFuncitons.put(sF, summarryDistance);
            }
        }
        double best = Double.MAX_VALUE;
        Function bestF = new Function("");
        for (Function f : mapFuncitons.keySet()) {
            if (best > mapFuncitons.get(f)) {
                best = mapFuncitons.get(f);
                bestF = f;
            }
        }
        System.out.println(best);
        System.out.println(bestF.expression);
        drawApproximate(bestF);
        this.r2_label.setText("Odleglość punktów: " + summarryDistance);
        this.functionLabel.setText("Funkcja: " + bestF.expression);
    }
    
    void drawApproximate(Function f) throws Exception {
        ScatterChart.Series<Number, Number> s = new Series<>();
        System.out.println("Wyswietlam dodawane x i y");
        
        String temporaryFunction = f.expression;
        
        for (Data<Number, Number> d : dataChart.getData().get(0).getData()) {
            double x = (double) d.getXValue();
            double y;
            String tempExp;
            f.expression = temporaryFunction;
            if (x < 0) {
                tempExp = f.expression.replace("x", "(" + Double.toString(x) + ")");
            } else {
                tempExp = f.expression.replace("x", Double.toString(x));
            }
            f.expression = tempExp;
            f.expression = f.calculateFunctions();
            y = f.evaluate(f.expression);
            System.out.println("X: " + x);
            System.out.println("Y: " + y);
            s.getData().add(new Data<>(x, y));
        }
        f.expression = temporaryFunction;
        dataChart.getData().add(s);
        String temp = ".default-color" + amountOfSeries + ".chart-series-line";
        dataChart.lookup(temp).setStyle("-fx-stroke: red");
        
        amountOfSeries++;
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
        for (String sTemp : tempStrings) {
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
