/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw;

/**
 *
 * @author ijb87
 */
public class IsOdd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n = 101; 
        if(isEven(n) == false)
            System.out.print( "Odd" ); 
        else
            System.out.print( "Even" ); 
    }
    
    public static boolean isEven(int n) {
        return ((n / 2) * 2 == n); 
    }
    
}
