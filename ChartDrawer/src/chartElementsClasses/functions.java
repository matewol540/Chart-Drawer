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

import onp.*;

/**
 *
 * @author User
 */
public class functions {

    List<Function> listOfFunctions;
    List<String> listUncalculatedFunctions;

    public functions() throws FileNotFoundException {
        listOfFunctions = new ArrayList<>();
        Scanner sc = new Scanner(new File("ListaFunkcji.txt"));
        while (sc.hasNext()) {
            String s = sc.next();
            listOfFunctions.add(new Function(s));

        }
        sc.close();
    }

    public void addFunction(String s) {
        listOfFunctions.add(new Function(s));
    }

    public void saveFunction() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("ListaFunkcji.txt"));
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
        return temp;
    }

    public Function[] getFunctionClass() {
        Function[] temp = new Function[listOfFunctions.size()];
        int i = 0;
        for (Function f : listOfFunctions) {
            temp[i] = f;
            i++;
        }
        return temp;
    }

    public double pointDistance(double x, double y, Function function) throws Exception {
        double result;
        String temporaryFunction = function.expression;
//        if (x < 0) {
//            function.expression = function.expression.replace("x", "(" + Double.toString(x) + ")");
//        } else {
//            function.expression = function.expression.replace("x", Double.toString(x));
//        }
//        String tempExpression = function.calculateFunctions();
//        function.expression = tempExpression;
//        result = function.evaluate(function.expression);
//        function.expression = temporaryFunction;
//        
        String tempExpression = "";

        if (x < 0) {
            String negativeArgument = "(" + Double.toString(x) + ")";
            tempExpression = function.expression.replace("x", negativeArgument);
        } else {
            tempExpression = function.expression.replace("x", Double.toString(x));
        }
        function.expression = tempExpression;
        tempExpression = function.calculateFunctions();
        function.expression = tempExpression;
        result = function.evaluate(function.expression);

        function.expression = temporaryFunction;
        return Math.pow(result - y, 2);
    }
    
    
}
