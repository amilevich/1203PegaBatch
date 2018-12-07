/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ijb87
 */
public class FindFactorial {
   static int factorial(int n){    
  if (n == 0)    
    return 1;    
  else    
    return(n * factorial(n-1));    
 }    
    public static void main(String args[]){  
        int i, fact = 1;  
        int number = 7;//It is the number to calculate factorial    
        fact = factorial(number);   
        System.out.println("Factorial of " + number + " is: " + fact);    
 }  
}  
