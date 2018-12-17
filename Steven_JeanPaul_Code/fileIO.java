import java.io.*;  
//Author: Steven Jean-Paul
//Q20 - File I/O
class fileIO extends FilterWriter {  //Use this class to write to a text file.
    fileIO(Writer out) {  
        super(out);  
    }  
    public void write(String str) throws IOException {  
        super.write(str);  
    }  
}  
