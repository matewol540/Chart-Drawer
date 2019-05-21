/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chartdrawer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */

//Klasa uruchomieniowa
public class ChartDrawer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Pobranie pliku z widokiem
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        //Utworzenie sceny na podstawie pobranego widoku
        Scene scene = new Scene(root);
        //Ustawanie sceny w oknie
        stage.setScene(scene);
        stage.setTitle("Kontroler");
        //Wywołanie okna
        stage.show();
        stage.setResizable(false);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
