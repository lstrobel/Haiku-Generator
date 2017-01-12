/**
 * Created by Lukas on 1/5/2017.
 * Create Haikus! They wont be good though...
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class HaikuGenerator {

   private static final String DIRECTORY = "C:\\Users\\Lukas\\Documents\\Programming\\Fun\\Haiku Generator\\";
   private static final String[] FORMAT = {"A", "N", "v", "V", "A", "N", "v"};
   // really makes you think eh :thinking:

   public static void main(String[] args)
           throws FileNotFoundException {

      Scanner console = new Scanner(System.in);
      System.out.print("Would you like to generate a haiku? (y/n) ");
      String ans = console.next();

      //System.out.println(checkPOS("truce", 1));
      //System.out.println(findSyllableCount("balloon"));

      while (!ans.equals("n")) {
         int[] foo = randomSum(5);
         int[] bar = randomSum(7);
         int[] baz = randomSum(5);

         System.out.println();
         System.out.println("Haiku:");
         System.out.println();
         double timeA = System.nanoTime();
         System.out.println(createHaikuLine(foo));
         System.out.println(createHaikuLine(bar));
         System.out.println(createHaikuLine(baz));
         double totalTime = (System.nanoTime() - timeA) / 1000000000.0;
         System.out.println();
         System.out.printf("Haiku took %f seconds%n", totalTime);
         System.out.println();
         System.out.print("Would you like to generate a haiku? (y/n) ");
         ans = console.next();
      }
   }

   // create the line for a haiku, with @sCount number of syllables
   private static String createHaikuLine(int[] sCount) {

      BufferedReader reader = null;

      try {
         String output = "";
         int arrayPosition = 0;
         for (int k : sCount) {
            File file = new File(DIRECTORY + "betterDict.txt");
            reader = new BufferedReader(new FileReader(file));
            String word;
            double startPos = Math.random() * 20500; // length of word file - ~100 to give the program some space
            if (k != 0) {
               for (int i = 0; i < startPos; i++) { // skip ahead so that you have new words
                  reader.readLine();
               }
               boolean breaker = true;
               while (((word = reader.readLine()) != null) && breaker) {
                  String[] array = word.split(" ");
                  if (array.length == 3) {
                     word = array[0];
                  } else if (array.length == 4) {
                     word = array[0] + " " + array[1];
                  } else {
                     word = "aaaaaaaaaaa";
                  }
                  if ((findSyllableCount(word) == k) && (checkPOS(word, arrayPosition))) {
                     output += array[0] + " ";
                     arrayPosition++;
                     breaker = false;
                  }
               }
            }
         }
         return output;
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            reader.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return null;
   }

   // create a int array specifying how many words + how many syllables they should have
   // @sum is the goal num of syllables
   private static int[] randomSum(int sum) {
      //how many words you will use
      int size = (int) Math.round(((Math.random() * (sum - 1)) + 1));

      //the array for the distribution of syllables, but between 0-1
      int[] zeroOne = new int[size + 1];
      zeroOne[0] = 0;
      zeroOne[1] = sum;
      for (int i = 2; i < zeroOne.length; i++) {
         zeroOne[i] = (int) Math.round(((Math.random() * (sum - 1)) + 1));
      }
      Arrays.sort(zeroOne);

      //the actual array with syllable count
      int[] numbers = new int[size];
      for (int i = 0; i < numbers.length; i++) {
         numbers[i] = zeroOne[i + 1] - zeroOne[i];
      }
      return numbers;
   }

   // find the syllable count of a given word
   private static int findSyllableCount(String word)
           throws FileNotFoundException {
      BufferedReader reader = null;
      try {
         File file = new File(DIRECTORY + "betterDict.txt");
         reader = new BufferedReader(new FileReader(file));

         String[] wordArray = word.split(" ");
         String line;
         while ((line = reader.readLine()) != null) { // read through the whole file
            String[] lineArray = line.split(" ");
            if (wordArray.length == 1) {
               if (wordArray[0].equals(lineArray[0])) {
                  return Integer.parseInt(lineArray[1]);
               }
            } else if (wordArray.length == 2) {
               if ((wordArray[0].equals(lineArray[0])) && (wordArray[1].equals(lineArray[1]))) {
                  return Integer.parseInt(lineArray[2]);
               }
            }
         }
         return -1;
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            reader.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return 0;
   }

   /*Noun                           N
    Plural                          p
    Noun Phrase                     h
    Verb (usu participle)           V
    Verb (transitive)               t
    Verb (intransitive)             i
    Adjective                       A
    Adverb                          v
    Conjunction                     C
    Preposition                     P
    Interjection                    !
    Pronoun                         r
    Definite Article                D
    Indefinite Article              I
    Nominative                      o */

   // Make sure that a word corresponds to the part of speech po indicated in @FORMAT
   private static boolean checkPOS(String word, int arrayPosition)
           throws FileNotFoundException {
      BufferedReader reader = null;
      try {
         File file = new File(DIRECTORY + "betterDict.txt");
         reader = new BufferedReader(new FileReader(file));
         String line;
         String[] wordArray = word.split(" ");
         String parts = "";
         while ((line = reader.readLine()) != null) { // read through the whole file
            String[] lineArray = line.split(" ");
            if (wordArray.length == 1) {
               if (wordArray[0].equals(lineArray[0])) {
                  parts += lineArray[2];
               }
            } else if (wordArray.length == 2) {
               if ((wordArray[0].equals(lineArray[0])) && (wordArray[1].equals(lineArray[1]))) {
                  parts += lineArray[3];
               }
            }
         }
         return (parts.contains(FORMAT[arrayPosition]));
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            reader.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return false;
   }

   /*public static void writeDict() {
      try {
         BufferedReader wordReader = null;

         File wordFile = new File(DIRECTORY + "betterDict.txt");
         wordReader = new BufferedReader(new FileReader(wordFile));

         String word;
         int count = 0;
         while((word = wordReader.readLine()) != null) {
            String[] array = word.split(" ");
            if(array.length > 4){
               count++;
            }
         }
         System.out.println(count);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }*/
}
