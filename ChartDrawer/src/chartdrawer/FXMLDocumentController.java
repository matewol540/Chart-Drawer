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
        //Utworzenie nowego obiektu wyboru pliku
        FileChooser fc = new FileChooser();
        //Dodanie odpowiednich filtrów dla pożądanych rozszerzeń
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv", "*.csv"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("tsv", "*.tsv"));
        //Pobranie pliku z utworzonego wcześniej obiektu
        File data = fc.showOpenDialog(null);
        //Wywowałanie metody klasy fileManager zwracającej serie danych na podstawie pliku jako argumentu
        ScatterChart.Series<Number, Number> s = p.getDataFromFile(data);
        //Dodanie serii do wykresu
        dataChart.getData().add(s);
        //Stylowanie niezbedne do poprawnego wyswietlania pliku
        String temp = ".default-color" + amountOfSeries + ".chart-series-line";
        dataChart.lookup(temp).setStyle("-fx-stroke: transparent");
        //Zwiekszenie ilosci dodanych serii danych
        amountOfSeries++;
    }

    @FXML
    void addNewFunction(ActionEvent event) throws FileNotFoundException {
        //Obiekt do wprowadzania nowych funkcji
        TextInputDialog tx = new TextInputDialog();
        tx.setContentText("Podaj wartość funkcji");
        tx.setTitle("Dodawanie funkcji");
        //Oczekiwanie na odpowiedz 
        Optional<String> response = tx.showAndWait();
        if (response.isPresent()) {
            //Dodanie funkcji do listy 
            functionManager.addFunction(response.get());
        }
        //Zapis funkcji do pliku w celu zapewnienia reużywalnosci
        functionManager.saveFunction();
    }

    @FXML
    void approximate(ActionEvent event) throws FileNotFoundException, Exception {
        //Utworzenie listy obserwowalnej
        ObservableList<Series<Number, Number>> temp = dataChart.getData();
        //Pobranie funkcji jako listy klas
        Function[] functionsTemp = functionManager.getFunctionClass();
        //Mapa z funkcja oraz z jej dopasowaniem do wykresu
        Map<Function, Double> mapFuncitons = new HashMap<Function, Double>();
        double summarryDistance = 0;
        //Petla wykonujaca niezbedne obliczenia
        for (Function sF : functionsTemp) {
            for (Series<Number, Number> s : temp) {
                summarryDistance = 0;
                for (Data<Number, Number> d : s.getData()) {
                    double x = (double) d.getXValue();
                    double y = (double) d.getYValue();
                    summarryDistance += functionManager.pointDistance(x, y, sF);
                }
                //Umieszczenie funkcji jako klucz i dopasowania jako wartosc
                mapFuncitons.put(sF, summarryDistance);
            }
        }
        //Utworzenie zmiennej do iteracji po mapie
        double best = Double.MAX_VALUE;
        Function bestF = new Function("");
        //Pobranie najlepszej funkcji
        for (Function f : mapFuncitons.keySet()) {
            if (best > mapFuncitons.get(f)) {
                best = mapFuncitons.get(f);
                bestF = f;
            }
        }
        //Wyrysowanie najlepszej funkcji na wykresie
        drawApproximate(bestF);
        //Zmiana odpowiednich labeli 
        this.r2_label.setText("Odleglość punktów: " + summarryDistance);
        this.functionLabel.setText("Funkcja: " + bestF.expression);
    }

    void drawApproximate(Function f) throws Exception {
        //Utworzenie noweje serii danych
        ScatterChart.Series<Number, Number> s = new Series<>();
        // Zapis wyrazenia w formie stringa
        String temporaryFunction = f.expression;
        //W petli dodanie punktow do serii
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
            s.getData().add(new Data<>(x, y));
        }
        f.expression = temporaryFunction;
        //Dodanie serii do wykresu
        dataChart.getData().add(s);
        //Style niezbedne do poprawnego wyswietlenia
        String temp = ".default-color" + amountOfSeries + ".chart-series-line";
        dataChart.lookup(temp).setStyle("-fx-stroke: red");
        //Zwiekszenie ilosci serii
        amountOfSeries++;
    }

    @FXML
    void clearData(ActionEvent event) throws InterruptedException {
        //Usuniecie serii danych z wykresu
        while (!dataChart.getData().isEmpty()) {
            dataChart.getData().remove((int) (Math.random() * (dataChart.getData().size() - 1)));
        }
        amountOfSeries = 0;
        p.clearList();
    }

    @FXML
    void exitApplication(ActionEvent event) {
        //zamkniecie aplikacji
        Platform.exit();
    }

    @FXML
    void showFunctions(ActionEvent event) {
        //Utworzenie nowego okna
        Stage s = new Stage();
        //Utworzenie pola tekstowego
        TextArea ta = new TextArea();
        //Ppobranie listy funkcji
        String[] tempStrings = functionManager.getFunctions();
        //Zapis z tablicy do pola tekstowego
        for (String sTemp : tempStrings) {
            ta.appendText(sTemp + "\n");
        }
        //Dodanie do okna nowej sceny z polem tekstowym
        s.setScene(new Scene(ta));
        s.setResizable(false);
        s.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Utworzenie osi wykresu
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        //Obiekt do zarzadania plikiem
        p = new fileManager();
        //Utworzenie zarzadcy funkcjami
        try {
            functionManager = new functions();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Utworzenie nowego wykresu
        dataChart = new LineChart<>(xAxis, yAxis);
        //Utworzenie nowego okna
        Stage s = new Stage();
        //Utworzenie nowej sceny
        Scene paneChart = new Scene(dataChart);
        //Wyswietlenie nowego okna
        s.setScene(paneChart);
        s.setTitle("Wykres");
        s.setResizable(false);
        s.setWidth(1000);
        s.setHeight(800);
        s.show();

    }

    public functions functionManager;
    LineChart<Number, Number> dataChart;
    fileManager p;
    int amountOfSeries = 0;
}
