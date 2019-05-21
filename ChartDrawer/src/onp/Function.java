/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Konrad
 */
public class Function extends Number {

    public Function outerFunction;
    public Function innerFunction;
    public String expression;

    public Function(String expression) {
        super("");
        this.expression = expression;
    }

    public String calculateFunctions() throws Exception {
        boolean allFunctionsCalculated = false;
        int expressionLength = this.expression.length();
        int i = 0;
        DecimalFormat df = new DecimalFormat("#.#########");
        while ((!allFunctionsCalculated) && (i != expressionLength - 3)) {
            expressionLength = this.expression.length();
            if (this.expression.substring(i, i + 3).equals("sin")) {
                int expressionEnd = findClosingBracketIndex(this.expression, i + 3);
                String expressionBetweenBrackets = this.expression.substring(i + 3, expressionEnd + 1);
                try {
                    Double value = this.evaluate(expressionBetweenBrackets);
                    Double result = Math.sin(value);
                    String valueToReplace = "";
                    if (result < 0) {
                        valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                    } else {
                        valueToReplace = df.format(result).replace(",", ".");
                    }

                    this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                    i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.sin(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;
                    }
                }
            } else if (this.expression.substring(i, i + 3).equals("cos")) {
                int expressionEnd = findClosingBracketIndex(this.expression, i + 3);
                String expressionBetweenBrackets = this.expression.substring(i + 3, expressionEnd + 1);
                try {
                    Double value = this.evaluate(expressionBetweenBrackets);
                    Double result = Math.cos(value);
                    String valueToReplace = "";
                    if (result < 0) {
                        valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                    } else {
                        valueToReplace = df.format(result).replace(",", ".");
                    }
                    this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                    i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.cos(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;

                    }
                }
            } else if (this.expression.substring(i, i + 3).equals("tan")) {
                int expressionEnd = findClosingBracketIndex(this.expression, i + 3);
                String expressionBetweenBrackets = this.expression.substring(i + 3, expressionEnd + 1);
                try {
                    Double value = this.evaluate(expressionBetweenBrackets);
                    Double result = Math.tan(value);
                    String valueToReplace = "";
                    if (result < 0) {
                        valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                    } else {
                        valueToReplace = df.format(result).replace(",", ".");
                    }
                    this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                    i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.tan(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;

                    }
                }
            } else if (this.expression.substring(i, i + 4).equals("atan")) {
                int expressionEnd = findClosingBracketIndex(this.expression, i + 4);
                String expressionBetweenBrackets = this.expression.substring(i + 4, expressionEnd + 1);
                try {
                    Double value = this.evaluate(expressionBetweenBrackets);
                    Double result = Math.atan(value);
                    String valueToReplace = "";
                    if (result < 0) {
                        valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                    } else {
                        valueToReplace = df.format(result).replace(",", ".");
                    }
                    this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                    i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.atan(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;

                    }
                }
            } else if (this.expression.substring(i, i + 4).equals("acos")) {
                int expressionEnd = findClosingBracketIndex(this.expression, i + 4);
                String expressionBetweenBrackets = this.expression.substring(i + 4, expressionEnd + 1);
                try {
                    Double value = this.evaluate(expressionBetweenBrackets);
                    Double result = Math.acos(value);
                    String valueToReplace = "";
                    if (result < 0) {
                        valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                    } else {
                        valueToReplace = df.format(result).replace(",", ".");
                    }
                    this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                    i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.acos(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;

                    }
                }
            } else if (this.expression.substring(i, i + 4).equals("asin")) {
                int expressionEnd = findClosingBracketIndex(this.expression, i + 4);
                String expressionBetweenBrackets = this.expression.substring(i + 4, expressionEnd + 1);
                try {
                    Double value = this.evaluate(expressionBetweenBrackets);
                    Double result = Math.asin(value);
                    String valueToReplace = "";
                    if (result < 0) {
                        valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                    } else {
                        valueToReplace = df.format(result).replace(",", ".");
                    }
                    this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                    i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.asin(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;

                    }
                }
            } else if (this.expression.substring(i, i + 3).equals("ctg")) {
                int expressionEnd = findClosingBracketIndex(this.expression, 3);
                String expressionBetweenBrackets = this.expression.substring(i + 3, expressionEnd + 1);
                try {
                    Double value = this.evaluate(expressionBetweenBrackets);
                    Double result = Math.sin(value);
                    String valueToReplace = "";
                    if (result < 0) {
                        valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                    } else {
                        valueToReplace = df.format(result).replace(",", ".");
                    }
                    this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                    i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.sin(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;

                    }
                }
            } else if (this.expression.substring(i, i + 3).equals("log")) {
                int expressionEnd = findClosingBracketIndex(this.expression, 3);
                String expressionBetweenBrackets = this.expression.substring(i + 3, expressionEnd + 1);
                try {
                        Double value = this.evaluate(expressionBetweenBrackets);
                        Double result = Math.log10(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.log10(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;

                    }
                }
            } else if (this.expression.substring(i, i + 2).equals("ln")) {
                int expressionEnd = findClosingBracketIndex(this.expression, 2);
                String expressionBetweenBrackets = this.expression.substring(i + 2, expressionEnd + 1);
                try {
                    Double value = this.evaluate(expressionBetweenBrackets);
                    Double result = Math.log(value);
                    String valueToReplace = "";
                    if (result < 0) {
                        valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                    } else {
                        valueToReplace = df.format(result).replace(",", ".");
                    }
                    this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                    i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.log(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;

                    }
                }
            } else if (this.expression.substring(i, i + 4).equals("sqrt")) {
                int expressionEnd = findClosingBracketIndex(this.expression, 4);
                String expressionBetweenBrackets = this.expression.substring(i + 4, expressionEnd + 1);
                try {
                    Double value = this.evaluate(expressionBetweenBrackets);
                    Double result = Math.sqrt(value);
                    String valueToReplace = "";
                    if (result < 0) {
                        valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                    } else {
                        valueToReplace = df.format(result).replace(",", ".");
                    }
                    this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                    i = 0;
                } catch (Exception e) {
                    try {
                        double value = Double.parseDouble(expressionBetweenBrackets);
                        Double result = Math.sqrt(value);
                        String valueToReplace = "";
                        if (result < 0) {
                            valueToReplace = "(" + df.format(result).replace(",", ".") + ")";
                        } else {
                            valueToReplace = df.format(result).replace(",", ".");
                        }
                        this.expression = this.expression.replace(this.expression.substring(i, expressionEnd + 1), valueToReplace);
                        i = 0;
                    } catch (Exception e2) {
                        i++;

                    }
                }
            } else {
                i++;
            }

            if ((!this.expression.contains("sin")) && (!this.expression.contains("cos"))
                    && (!this.expression.contains("tan")) && (!this.expression.contains("atan"))
                    && (!this.expression.contains("acos")) && (!this.expression.contains("asin"))
                    && (!this.expression.contains("ctg")) && (!this.expression.contains("log")) && (!this.expression.contains("ln")) && (!this.expression.contains("sqrt"))) {
                allFunctionsCalculated = true;
                return this.expression;
            }
        }
        return "ERROR";

    }

    public Double evaluate(String expressionParam) throws Exception {
        Queue<ElementRPN> expression = this.convertToRPN(expressionParam);
        Stack<ElementRPN> stack = new Stack<>();

        for (ElementRPN e : expression) {
            if (isNumber(e.value)) {
                stack.push(e);
            } else if (isOperator(e.value)) {
                Double result = null;
                Number b = (Number) stack.pop();
                Number a = (Number) stack.pop();
                if ("^".equals(e.value)) {
                    result = Math.pow(Double.parseDouble(a.value), Double.parseDouble(b.value));
                } else if ("+".equals(e.value)) {
                    result = (Double.parseDouble(a.value)) + (Double.parseDouble(b.value));
                } else if ("-".equals(e.value)) {
                    result = (Double.parseDouble(a.value)) - (Double.parseDouble(b.value));
                } else if ("*".equals(e.value)) {
                    result = (Double.parseDouble(a.value)) * (Double.parseDouble(b.value));
                } else if ("/".equals(e.value)) {
                    result = (Double.parseDouble(a.value)) / (Double.parseDouble(b.value));
                }
                Number resultToPush = new Number(result.toString());
                stack.push(resultToPush);
            }
        }
        Number numberToReturn = (Number) stack.pop();
//        this.expression = numberToReturn.value;
        return Double.parseDouble(numberToReturn.value);
    }

    public Queue<ElementRPN> convertToRPN(String expression) throws Exception {
        Stack<ElementRPN> stack = new Stack<>();
        Queue<ElementRPN> output = new LinkedList<>();
        try {
            List<String> expressionSymbols = listOfSymbols(expression);
            Iterator iterator = expressionSymbols.iterator();

            while (iterator.hasNext()) {
                String token = (String) iterator.next();
                if (isNumber(token)) {
                    output.add(parseNumber(token));
                } else if (isOpeningBracket(token)) {
                    stack.add(new Operator(token));
                } else if (isClosingBracket(token)) {
                    Operator tmp = (Operator) stack.peek();
                    while (!isOpeningBracket(tmp.value)) {
                        tmp = (Operator) stack.pop();
                        if (!isOpeningBracket(tmp.value)) {
                            output.add(tmp);
                        }
                    }
                } else if (isOperator(token) && (!(isBracket(token)))) {
                    Operator lastOperator = (Operator) stack.peek();
                    Operator newOperator = parseOperator(token);
                    if ((lastOperator.priority >= newOperator.priority)) {
                        while (((lastOperator.priority >= newOperator.priority))) {
                            lastOperator = (Operator) stack.pop();
                            if (!isBracket(lastOperator.value)) {
                                output.add(lastOperator);
                            } else {
                                stack.add(lastOperator);
                                break;
                            }
                        }
                        stack.add(newOperator);
                    } else {
                        stack.add(newOperator);
                    }
                }
            }
            return output;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isNumber(String input) {
        try {
            Double toParse = Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String input) {
        if (input.equals("+")
                || input.equals("-") || input.equals("*")
                || input.equals("/") || input.equals("^")) {
            return true;
        } else {
            return false;
        }
    }

    private Number parseNumber(String input) {
        try {
            Double toParse = Double.parseDouble(input);
            return new Number(Double.toString(toParse));
        } catch (Exception e) {
            return null;
        }
    }

    private Operator parseOperator(String input) {
        try {
            return new Operator(input);
        } catch (Exception ex) {
            return null;
        }
    }

    private boolean isBracket(String input) {
        if (input.equals("(") || (input.equals(")"))) {
            return true;
        }
        return false;
    }

    private boolean isOpeningBracket(String input) {
        if (input.equals("(")) {
            return true;
        }
        return false;
    }

    private boolean isClosingBracket(String input) {
        if (input.equals(")")) {
            return true;
        }
        return false;
    }

    public List<String> listOfSymbols(String inputExpression) throws Exception {
        int lastCheckedIndex = 0;
        int expressionLength = inputExpression.length();

        List<String> symbols = new LinkedList<>();

        while (lastCheckedIndex < expressionLength) {
            for (int i = lastCheckedIndex; i < expressionLength; i = lastCheckedIndex) {
                if (inputExpression.charAt(i) == ('(') && inputExpression.charAt(i + 1) == ('-')) {
                    int numberEnd = i;
                    int numberStart = i + 1;

                    for (int j = numberStart; j < expressionLength; j++) {
                        if (inputExpression.charAt(j) == ')') {
                            numberEnd = j;
                            lastCheckedIndex = j + 1;
                            break;
                        }
                    }

                    symbols.add(inputExpression.substring(numberStart, numberEnd));
                } else if (inputExpression.charAt(i) == ('(') && ((inputExpression.charAt(i + 1) == ('(')) || ((Character.isDigit(inputExpression.charAt(i + 1)))))
                        || inputExpression.charAt(i) == '/'
                        || inputExpression.charAt(i) == '*'
                        || inputExpression.charAt(i) == '^'
                        || inputExpression.charAt(i) == '+'
                        || inputExpression.charAt(i) == '-') {
                    symbols.add(String.valueOf(inputExpression.charAt(i)));
                    lastCheckedIndex = i + 1;
                    break;
                } else if (inputExpression.charAt(i) == ')') {

                    boolean isNegativeNumber = false;

                    for (int j = i; j > 1; j--) {
                        if (inputExpression.charAt(j - 1) == '/'
                                || inputExpression.charAt(j - 1) == '*'
                                || inputExpression.charAt(j - 1) == '^'
                                || inputExpression.charAt(j - 1) == '+'
                                || inputExpression.charAt(j - 1) == '-'
                                || inputExpression.charAt(j - 1) == '('
                                || inputExpression.charAt(j - 1) == ')') {
                            isNegativeNumber = false;

                        } else if (inputExpression.charAt(j - 1) == '-' && inputExpression.charAt(j - 2) == '(') {
                            isNegativeNumber = true;
                            lastCheckedIndex = i + 1;
                            break;
                        }
                    }

                    if (!isNegativeNumber) {
                        symbols.add(String.valueOf(inputExpression.charAt(i)));
                        lastCheckedIndex = i + 1;
                    }
                } else if (Character.isDigit(inputExpression.charAt(i))) {
                    int numberStart = i;
                    int numberEnd = i;

                    for (int j = numberStart; j < expressionLength; j++) {
                        if (!isPartOfNumber(inputExpression.charAt(j))) {
                            numberEnd = j;
                            lastCheckedIndex = j;
                            break;
                        }
                    }
                    if (numberStart == numberEnd) {
                        symbols.add(String.valueOf(inputExpression.charAt(numberStart)));
                        lastCheckedIndex = numberEnd + 1;
                        break;
                    }
                    symbols.add(inputExpression.substring(numberStart, numberEnd));

                } else {
                    throw new Exception("Bad symbol in expression");
                }

            }
        }
//        for(String s : symbols ){
//            System.out.println("[" + s + "]");
//        }
        return symbols;
    }

    private boolean isPartOfNumber(char input) {
        if (Character.isDigit(input) || input == '.') {
            return true;
        }
        return false;
    }

    public String findSimpliestExpression(String inputExpression) {
        List<Integer> bracketsIndexes = findBracketsIndexes(inputExpression);
        int expressionLength = inputExpression.length();

        String foundFunction = "";
        for (Integer bracketIndex : bracketsIndexes) {
            for (int i = bracketIndex + 1; i < expressionLength; i++) {
                if ((inputExpression.indexOf("sin", i) > -1)) {
                    foundFunction = "sin";
                    break;
                } else if (inputExpression.indexOf("cos", i) > -1) {
                    foundFunction = "cos";
                    break;
                } else if (inputExpression.indexOf("tan", i) > -1) {
                    foundFunction = "tan";
                    break;
                } else if (inputExpression.indexOf("atan", i) > -1) {
                    foundFunction = "atan";
                    break;
                } else if (inputExpression.indexOf("asin", i) > -1) {
                    foundFunction = "asin";
                    break;
                } else if (inputExpression.indexOf("acos", i) > -1) {
                    foundFunction = "acos";
                    break;
                } else {
                    int begin = bracketIndex;
                    int end = inputExpression.length() - bracketsIndexes.indexOf(bracketIndex);
                    return inputExpression.substring(begin, end);
                }
            }
        }
        return "ERROR";
    }

    private List<Integer> findBracketsIndexes(String inputExpression) {
        int expressionLength = inputExpression.length();
        List<Integer> bracketsIndexes = new ArrayList<>();
        for (int i = 0; i < expressionLength; i++) {
            if (inputExpression.charAt(i) == '(') {
                bracketsIndexes.add(i);
            }
        }
        return bracketsIndexes;
    }

    public int findClosingBracketIndex(String inputEexpression, int openingBracketIndex) {
        int openingBracketsCounter = 0;
        int closingBracketsCounter = 0;
        int expressionLength = inputEexpression.length();

        for (int i = openingBracketIndex; i < expressionLength; i++) {
            if (inputEexpression.charAt(i) == '(') {
                openingBracketsCounter++;
            } else if (inputEexpression.charAt(i) == ')') {
                closingBracketsCounter++;
                if ((openingBracketsCounter == closingBracketsCounter)) {
                    return i;
                }
            }
        }
        return -1;
    }

}
