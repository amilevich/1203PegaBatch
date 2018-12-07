
import com.sun.xml.internal.ws.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ijb87
 */
public class Substring {
    public static void main(String[] args) {
        Substring("Testing", 3);
    }
    public static void Substring(String str, int idx) {
        List<String> myList = new ArrayList<String>(Arrays.asList(str.split("")));
        List input = myList.subList(0, idx);
        System.out.println(input);
        String arraylistToString = StringUtils.collectionToCommaDelimitedString(myList);
    }
}
