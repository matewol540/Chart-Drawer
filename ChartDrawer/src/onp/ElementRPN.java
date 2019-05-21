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
public abstract class ElementRPN {

    public String value;
    public ElementRPN(String value) {
        this.value = value;
    }
    @Override
    public String toString(){
        return this.value;
    }
    public abstract double getPriority();

}
