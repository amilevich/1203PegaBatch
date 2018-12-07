/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ijb87
 */
public class FibonacciNumbers {

    public static void main(String[] args) { 

//number of elements to generate in a series 
        int limit = 25; 

        long[] series = new long[limit]; 

//create first 2 series elements 
        series[0] = 0; 
        series[1] = 1; 

//create the Fibonacci series and store it in an array 
        for(int i=2; i < limit; i++) { 
            series[i] = series[i-1] + series[i-2]; 
} 

//print the Fibonacci series numbers 

        System.out.println("Fibonacci Series Up To " + limit); 
        for(int i=0; i< limit; i++) { 
            System.out.print(series[i] + " "); 
        } 
    } 
}
