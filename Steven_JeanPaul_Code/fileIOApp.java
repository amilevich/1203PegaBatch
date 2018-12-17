import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  
import java.io.*;  
import java.util.ArrayList;
//Author: Steven Jean-Paul
//Q20 - File I/O
//This main method creates a string to write into a txt file. It then accesses the output stream to read each line and parse out the colons. 
public class fileIOApp {  
    public static void main(String[] args) {  
        try {  
            FileWriter fw = new FileWriter("Data.txt");   
            fileIO filterWriter = new fileIO(fw);             
            filterWriter.write("Mickey:Mouse:35:Arizona\nHulk:Hogan:50:Virginia\nRoger:Robert:22:California\nWonder:Woman:18:Montana");  
            filterWriter.close();  
            FileReader fr = new FileReader("Data.txt");  
            BufferedReader bufferedReader = new BufferedReader(fr);  
            int k;  
            while ((k = bufferedReader.read()) != -1) {  
                System.out.print((char) k);  
            }  
            bufferedReader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
      
        char[] name = new char[100];
        char[] state = new char[50];
        int[] age = new int[10];
        int count = 0;
        System.out.println("\n");
        try  {  
            Reader reader = new FileReader("Data.txt");  
            fileOutput fr = new fileOutput(reader);  
            int i;  
            while ((i = fr.read()) != -1) {  
                //System.out.print((char) i); 
            	
                if(((char)i) != ':') {
                	System.out.print((char)i);
                	name[count] = (char)i;
                	System.out.println(name[count]);
                }
                else if (((char)i) == ':') {
                	
                }
            }  
            
            
            fr.close();  
            reader.close();  
        } catch (Exception e) {  
            e.getMessage();  
        }  
    }  
}  