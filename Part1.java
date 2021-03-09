
/**
 * Part 1: Finding many Genes
 * 
 * @author (Junghee Koo) 
 * @version (V01_2021.2.6)
 */

import edu.duke.*;
import java.io.File;

public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
       // find stopCodon starting from (startIndex + 3), currIndex
       int currIndex = dna.indexOf(stopCodon, startIndex + 3);
       // as long as currIndex is not equal to -1
       while (currIndex != -1) {
          // check if currIndex - startIndex is a multiple of 3 
          int diff = currIndex - startIndex;
          if (diff % 3 == 0) {
             // if so, currIndex is answer, return it 
             return currIndex; 
          }
          // if not, update currIndex, looking for stopCodon again
          // starting from currIndex + 1
          else {
             currIndex = dna.indexOf(stopCodon, currIndex + 1);
          }
       // if we exit loop, we didn't find stopCodon             
       }
       // so return dna.length()
       return dna.length();
    }
    
    public String findGene (String dna, int where) {
       // find first occurrence of "ATG", startIndex
       int startIndex = dna.indexOf("ATG", where);
       // if startIndex is -1, return ""
       if (startIndex == -1) {
          return "";    
       }
       // use taaIndex to store findStopCodon(dna, startIndex, "TAA")
       int taaIndex = findStopCodon(dna, startIndex, "TAA");
       // use tagIndex to store findStopCodon(dna, startIndex, "TAG")
       int tagIndex = findStopCodon(dna, startIndex, "TAG");
       // use tgaIndex to store findStopCodon(dna, startIndex, "TGA")
       int tgaIndex = findStopCodon(dna, startIndex, "TGA");
       // store in minIndex the smallest of these three values
       int temp = Math.min(taaIndex, tagIndex);
       int minIndex = Math.min(temp, tgaIndex);
       // int minIndex = Math.min(taaIndex,Math.min(tagIndex,tgaIndex));
       // if minIndex is dna.length()? nothing found, return ""
       if (minIndex == dna.length()) {
          return "";
       }
       // otherwise answer is text from startIndex to minIndex
       return dna.substring(startIndex,minIndex + 3);
    }
    
    public void testFindStopCodon() {
       //            01234567890123456789012345
       String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
       System.out.println("dna is" + dna);
       int stopCodon = findStopCodon(dna,0,"TAA");
       System.out.println("stop codon is " + stopCodon);
       
       //     01234567890123456789012345       
       dna = "xxxyyyzzzTAGxxxyyyzzzTAGxx";
       System.out.println("dna is" + dna);
       stopCodon = findStopCodon(dna,9,"TAG");
       System.out.println("stop codon is " + stopCodon);
       
       //     01234567890123456789012345       
       dna = "xxxyyyzzTAGxxxyyyzzzTAGxxx";
       System.out.println("dna is" + dna);
       stopCodon = findStopCodon(dna,0,"TAG");
       System.out.println("stop codon is " + stopCodon);
    }
    
    public void testFindGene() {
       // DNA with no "ATG"
       String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
       System.out.println("dna is " + dna);
       String gene = findGene(dna,0);
       System.out.println("gene is " + gene);
       
       // DNA with "ATG" and one valid stop codon
       dna = "ATGyyyzzzTAAxxxyyyzzzTTTxx";
       System.out.println("dna is " + dna);
       gene = findGene(dna,0);
       System.out.println("gene is " + gene);       
       
       // DNA with "ATG" and multiple valid stop codons
       dna = "xxxATGzzzTAAxxxyyyzzzTAGxx";
       System.out.println("dna is " + dna);
       gene = findGene(dna,0);
       System.out.println("gene is " + gene);        
       
       // DNA with "ATG" and no valid stop codons
       dna = "xxxyyyATGTTTxxxyyyzzTAAxx";
       System.out.println("dna is " + dna);
       gene = findGene(dna,0);
       System.out.println("gene is " + gene);        
      
    }
    
    public void printAllGenes(String dna) {
       //Set startIndex to 0
       int startIndex = 0;
       //Repeat the following steps
       while(true) {
           //Find the next gene after startIndex
           String currentGene = findGene(dna, startIndex);
           //If no gene was found, leave this loop
           if (currentGene.isEmpty()) {
              break;
           }
           //Print that gene out
           System.out.println(currentGene);
           //Set startIndex to just past the end of the gene
           startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
       }
    }
    
    public void printFormat(String dna) {
       System.out.println("Testing printAllGenes on " + dna);
       printAllGenes(dna);
       System.out.println("\n");
    }
    
    public void callPrintFormat() {
       printFormat("ATGATCTAATTTATGCTGCAACGGTGAAGA");
       printFormat("");
       printFormat("ATGATCATAAGAAGATAA");
       printFormat("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
