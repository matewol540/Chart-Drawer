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
public class Operator extends ElementRPN {

    int priority;

    public Operator(String o) throws Exception {
        super(o);
         if ("+".equals(o) || "-".equals(o) || "(".equals(o)) {
            this.priority = 1;
        } else if ("*".equals(o) || "/".equals(o)) {
            this.priority = 2;
        } else if ("^".equals(o)) {
            this.priority = 3;
        } else if(")".equals(o)){
            this.priority = 9;
        } 
        else {
            throw new Exception("Is not operator exception");
        }
    }

    @Override
    public double getPriority() {
        return priority;
    }
}
