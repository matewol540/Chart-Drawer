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

/**
 *
 * @author User
 */
public class functions {

    List<String> listOfFunctions;
    
    public functions() throws FileNotFoundException {
        listOfFunctions = new ArrayList<>();
        Scanner sc = new Scanner(new File("ListaFunkcji.txt"));
        while (sc.hasNext()){
            listOfFunctions.add(sc.next());
        }
    }
    
    public void addFunction(String s){
        listOfFunctions.add(s);
        
    }
    
    public String[] getFunctions(){
        String[] temp = new String[listOfFunctions.size()];
        int i =0;
        for (String s:listOfFunctions){
            temp[i] = s;
            i++;
        }
        return temp;
    }
    
    
}
