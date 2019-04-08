/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chartdrawer;

import chartElementsClasses.point;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;
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

    }

    @FXML
    void approximate(ActionEvent event) throws FileNotFoundException {
        //Tutaj obliczanie funkcji po dodaniu pliku
    }

    @FXML
    void clearData(ActionEvent event) {
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

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        p = new point();

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

    LineChart<Number, Number> dataChart;
    point p;
    int amountOfSeries = 0;
}
