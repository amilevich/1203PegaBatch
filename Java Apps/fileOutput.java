import java.io.*;  
//Author: Steven Jean-Paul
//Q20 - File I/O
class fileOutput extends FilterReader {   //Use this file to output text
    fileOutput(Reader in) {  
        super(in);  
    }  
    public int read() throws IOException {  
        int x = super.read();  
        if ((char) x == ' ')  
            return ((int) '?');  
        else  
            return x;  
    }  
}  