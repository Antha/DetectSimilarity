/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestMain {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
    String wFilt="kapanpun";
    
    String potongan=wFilt.substring(wFilt.length()-3);
    String potongan2=wFilt.substring(0,wFilt.length()-3);
   
    System.out.println("Potongan : " + potongan);
    System.out.println("Potongan2 : " + potongan2);
    
    }    
}
