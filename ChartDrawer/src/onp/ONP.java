/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onp;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ONP {

    public static void main(String[] args) throws Exception {

        System.out.println();

        String expression = "(log(x))";
        expression = expression.replace(" ", "");

        Function f = new Function(expression);

//        expression = f.calculateFunctions();
//        System.out.println(f.evaluate(expression));
        List<Double> results = new ArrayList<>();

        String tmpExpression = "";
        PrintWriter pw = new PrintWriter("results.txt");

        long startTime = System.nanoTime();
        int counter = 0;
        for (Double i = 0.0; i <= 30.0; i += 1.0) {
            counter++;
            DecimalFormat df = new DecimalFormat("#.#####");
            String argument = df.format(i);
            argument = argument.replace(",", ".");
            if (argument.equals("-0")) {
                argument = "0";
            }
            if (i < 0) {
                String negativeArgument = "(" + argument + ")";
                tmpExpression = expression.replace("x", negativeArgument);
            } else {
                tmpExpression = expression.replace("x", argument);
            }
            f.expression = tmpExpression;
            tmpExpression = f.calculateFunctions();
            f.expression = tmpExpression;
            Double result = f.evaluate(f.expression);
            String line = df.format(i) + ";" + df.format(result);
            pw.append(line + "\n");
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        pw.close();
//        for (Double x : results) {
//            System.out.println(x);
//        }
        System.out.println(" \n Execution time in seconds : " + timeElapsed / 1000000000F
                + " \n iterations:" + counter);
    }
}
