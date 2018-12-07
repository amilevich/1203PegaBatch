/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ijb87
 */
import java.util.Arrays;
public class BubbleSort {
    public static void main(String args[]) {
        //one more testing of our bubble sort code logic in Java
        int[] test = {1,0,5,6,3,2,3,7,9,8,4};
        bubbleSort(test);
      
    }   
    public static void bubbleSort(int[] unsorted){
        System.out.println("unsorted integer array before sorting: " + Arrays.toString(unsorted));
      
        for(int i=0; i<unsorted.length -1; i++){
          

            for(int j= 1; j<unsorted.length -i; j++){
              
                if(unsorted[j-1] > unsorted[j]){
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[j-1];
                    unsorted[j-1] = temp;
                }
            }
            System.out.printf("Unsorted array after %d pass %s: %n", i+1, Arrays.toString(unsorted));
        }
    }
}
