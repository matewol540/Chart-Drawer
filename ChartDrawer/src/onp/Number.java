/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onp;

/**
 *
 * @author Konrad
 */
public class Number extends ElementRPN {
    
    public Number(String value) {
        super(value);
    }
    
    public Double toDouble(){
        return Double.parseDouble(value);
    }

    @Override
    public double getPriority() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
