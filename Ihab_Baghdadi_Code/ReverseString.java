/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ijb87
 */
public class ReverseString {
    public static void main(String[] args) {
    String reverseMe = "example string";
    for (int i = 0; i < reverseMe.length(); i++) {
    reverseMe = reverseMe.substring(1, reverseMe.length() - i)
        + reverseMe.substring(0, 1)
        + reverseMe.substring(reverseMe.length() - i, reverseMe.length());
 }
    System.out.println(reverseMe);
}
}
