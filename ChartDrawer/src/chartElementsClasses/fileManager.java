/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chartElementsClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import javafx.scene.chart.ScatterChart;

/**
 *
 * @author User
 */
public class fileManager {

    private List<ScatterChart.Series<Number, Number>> ListOfSeries;

    public fileManager() {
        this.ListOfSeries = new ArrayList<>();
    }

    public ScatterChart.Series<Number, Number> getDataFromFile(File _file) throws FileNotFoundException {
        String filename = _file.getName();
        String[] temp = filename.split("\\.");
        ScatterChart.Series<Number, Number> serie;
        if (temp[1].equals("csv")) {
            serie = addDataFromCSV(_file);
        } else {
            serie = addDataFromTSV(_file);
        }
        ListOfSeries.add(serie);
        return serie;
    }

    private ScatterChart.Series<Number, Number> addDataFromCSV(File data) throws FileNotFoundException {
        ScatterChart.Series<Number, Number> temp = new ScatterChart.Series<>();

        Scanner sc = new Scanner(data);
        List<String> dataRead = new ArrayList<>();

        while (sc.hasNext()) {
            dataRead.add(sc.next());
        }

        boolean isX = true;
        double x = 0;
        double y = 0;
        for (String s : dataRead) {
            String[] temporary = s.split(",");
            temp.getData().add(new ScatterChart.Data<>(Double.parseDouble(temporary[0]),Double.parseDouble(temporary[1])));
        }

        return temp;
    }

    private ScatterChart.Series<Number, Number> addDataFromTSV(File data) throws FileNotFoundException {
        Scanner sc = new Scanner(data);
        List<String> dataRead = new ArrayList<>();
        ScatterChart.Series<Number, Number> temp = new ScatterChart.Series<>();
        while (sc.hasNext()) {
            dataRead.add(sc.next());
        }

        for (int l = 0; l < dataRead.size() - 1; l = l + 2) {
            double x = Double.parseDouble(dataRead.get(l));
            double y = Double.parseDouble(dataRead.get(l + 1));
            temp.getData().add(new ScatterChart.Data<>(x, y));
        }
        return temp;

    }

    public void clearList(){
        ListOfSeries.clear();
    }
}
