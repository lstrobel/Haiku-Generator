// Created by Lukas Strobel, December 2016

import java.util.*; // scanners
import java.io.*; // file input
import java.lang.*; //math

public class HaikuGenerator {
   public static void main(String[] args){
      
      int[] bar = {2,2,1};
      int[] sCount = {7};
      int[] foo = {4,1};
      
         
      System.out.println(createHaikuLine(bar));
      System.out.println(createHaikuLine(sCount));
      System.out.println(createHaikuLine(foo));
   
   }

   // creates the line for a haiku. @sCount is the Array that defines word syllable length
   public static String createHaikuLine(int[] sCount) {
   
      BufferedReader reader = null;  
          
      try {
         File file = new File("dictionary.dat");
         reader = new BufferedReader(new FileReader(file));
         String line = "";
         double startPos = Math.random()*187000;
         String output = "";
         
         for(int k = 0; k < sCount.length; k++){
            for(int i = 0; i < startPos; i++) {
               line = reader.readLine();
            }
            if(sCount[k] != 0){
               while((line = reader.readLine()) != null) {
                  if(findSCount(line) == sCount[k]){
                     line = (parseWord(line) + " ");
                     output += line;
                     break;
                  }
               }
            }
         }
         
         return output;
      } 
      
      catch (IOException e) {
         e.printStackTrace();
      } 
      finally {
         try {
            reader.close();
         } 
         catch (IOException e) {
            e.printStackTrace();
         }
      }
      return null;
   }
   
   public static int findSCount(String line){
      Scanner lineScan = new Scanner(line);
      String[] array = line.split(" ");
      return array.length;
   }
   
   public static String parseWord(String line){
      String[] array = line.split(" ");
      String word = "";
      for(String i : array){
         word += i;
      }
      return word;
   }
}