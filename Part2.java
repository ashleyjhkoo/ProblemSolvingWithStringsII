
/**
 * Part 2: HowMany - Finding Multiple Occurrences
 * 
 * @author (Junghee Koo) 
 * @version (V02_2021.2.6)
 */

import edu.duke.*;
import java.io.File;

public class Part2 {
    public String findString (String stringa, String stringb, int where) {
       // find first occurrence of stringa and save as startIndex
       int startIndex = stringb.indexOf(stringa, where);
       int stringaLength = stringa.length();
       // if startIndex is -1, return ""
       if (startIndex == -1) {
          return "";    
       }
       
       if (startIndex == stringb.length()) {
          return "";
       }
       // return the stringa found
       return stringb.substring(startIndex,startIndex+stringaLength);
    }    
    
   public int howMany(String stringa, String stringb) {
       //Set starting searching point to 0, where
       int where = 0;
       //Set the number of stringa to 0, countHowMany
       int countHowMany = 0;
       String currentString = "";
       int stringaLength = stringa.length();
       int startIndex = 0;
       //Repeat the following steps
       while(true) {
           // startIndex is the index number of each stringa in stringb
           startIndex = stringb.indexOf(stringa, where);
           //Find the stringa from the startIndex, and save as currentString
           //currentString = stringb.substring(startIndex,startIndex+stringaLength);
           currentString = findString(stringa, stringb, where);
           //If no stringa was found, leave this loop
           if (currentString.isEmpty()) {
              break;
           }
           //Increase the countHowMany
           countHowMany = countHowMany + 1;
           //Update where, passing after the first stringa
           where = startIndex + stringaLength;
       }
       return countHowMany;
   }
   
   public void testHowMany() {

       String stringb = "ATGAACGAATTGAATC";
       System.out.println("stringb is " + stringb);
       String stringa = "GAA";
       System.out.println("stringa is " + stringa);  
       int howmanytimes = howMany(stringa, stringb);
       System.out.println("stringa appears " + howmanytimes+ " times");
       System.out.println("\n");

       stringb = "ATAAAA";
       System.out.println("stringb is " + stringb);
       stringa = "AA";
       System.out.println("stringa is " + stringa);  
       howmanytimes = howMany(stringa, stringb);
       System.out.println("stringa appears " + howmanytimes + " times");
       System.out.println("\n");
       
       stringb = "ATGTTGATGBCATGAAA";
       System.out.println("stringb is " + stringb);
       stringa = "ATG";
       System.out.println("stringa is " + stringa);  
       howmanytimes = howMany(stringa, stringb);
       System.out.println("stringa appears " + howmanytimes + " times");
       System.out.println("\n");
   }
}
