/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chartElementsClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.Alert;

import onp.*;

/**
 *
 * @author User
 */
public class functions {

    //Lista z funkcjami wprowadzonymi przez uzytkownika
    List<Function> listOfFunctions;

    public functions() throws FileNotFoundException {
        listOfFunctions = new ArrayList<>();
        Scanner sc = new Scanner(new File("ListaFunkcji.txt"));
        //Pobranie w petli zawartosci pliku z funkcjami
        while (sc.hasNext()) {
            String s = sc.next();
            listOfFunctions.add(new Function(s));
        }
        sc.close();
    }

    public void addFunction(String s) {
        //Dodanie funkcji do listy
        listOfFunctions.add(new Function(s));
    }

    public void saveFunction() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("ListaFunkcji.txt"));
        //Zapis funckji do pliku
        for (Function f : listOfFunctions) {
            pw.write(f.expression + "\n");
        }
        pw.close();
    }

    public String[] getFunctions() {
        String[] temp = new String[listOfFunctions.size()];
        int i = 0;
        for (Function f : listOfFunctions) {
            temp[i] = f.expression;
            i++;
        }
        //Zwrocenie tablicy zawierajacej funkcje jako lancuch znakow
        return temp;
    }

    public Function[] getFunctionClass() {
        Function[] temp = new Function[listOfFunctions.size()];
        int i = 0;
        for (Function f : listOfFunctions) {
            temp[i] = f;
            i++;
        }
        //Zwrocenie tablicy zawierajacej funkcje jako klase Function
        return temp;
    }

    public double pointDistance(double x, double y, Function function) {
        double result = 0.0;
        String temporaryFunction = function.expression;
        String tempExpression = "";
        //Porównywanie czy argument jest ujemny
        if (x < 0) {
            String negativeArgument = "(" + Double.toString(x) + ")";
            tempExpression = function.expression.replace("x", negativeArgument);
        } else {
            tempExpression = function.expression.replace("x", Double.toString(x));
        }
        //Zapis wyrazenia jako tymczasowe
        function.expression = tempExpression;
        //Blok try catch wylapujacej bledy 
        try {
            tempExpression = function.calculateFunctions();
            function.expression = tempExpression;
            result = function.evaluate(function.expression);
        } catch (Exception e) {
            //Wyswietlenie bledu w nowym oknie
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Błąd");
            a.setContentText(e.getMessage());
        } finally {
            //Przywrocenie pierwotnego wzoru funkcji
            function.expression = temporaryFunction;
        }
        //Zwrocenie odlegosci miedzy punktami
        return Math.pow(result - y, 2);
    }

}
