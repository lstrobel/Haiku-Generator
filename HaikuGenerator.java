/**
 * Created by Lukas on 1/5/2017.
 * Create Haikus! They wont be good though...
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class HaikuGenerator {
   public static void main(String[] args)
           throws FileNotFoundException {

      Scanner console = new Scanner(System.in);
      System.out.print("Would you like to generate a haiku? (y/n)");
      String ans = console.next();

      while (!ans.equals("n")) {
         int[] foo = randomSum(5);
         int[] bar = randomSum(7);
         int[] baz = randomSum(5);

         System.out.println("Haiku:");
         System.out.println(createHaikuLine(foo));
         System.out.println(createHaikuLine(bar));
         System.out.println(createHaikuLine(baz));

         System.out.print("Would you like to generate a haiku? (y/n)");
         ans = console.next();
      }
   }

   // create the line for a haiku, with @sCount number of syllables
   public static String createHaikuLine(int[] sCount) {

      BufferedReader reader = null;

      try {
         String output = "";
         for (int k : sCount) {
            File file = new File("C:\\Users\\Lukas\\Documents\\Programming\\Fun\\Haiku Generator\\words.txt");
            reader = new BufferedReader(new FileReader(file));
            String word;
            double startPos = Math.random() * 21700; // length of word file - ~100 to give the program some space
            if (k != 0) {
               for (int i = 0; i < startPos; i++) { // skip ahead so that you have new words
                  word = reader.readLine();
               }
               while ((word = reader.readLine()) != null) {
                  if (findSyllableCount(word) == k) {
                     output += word + " ";
                     break;
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
   public static int[] randomSum(int sum) {
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
   public static int findSyllableCount(String word)
           throws FileNotFoundException {
      BufferedReader reader = null;
      try {
         File file = new File("C:\\Users\\Lukas\\Documents\\Programming\\Fun\\Haiku Generator\\pronunciation.txt");
         reader = new BufferedReader(new FileReader(file));

         word = word.toUpperCase();
         String line;
         int count = 0;
         while ((line = reader.readLine()) != null) { // read through the whole file
            String[] lineArray = line.split(" ");
            if (lineArray[0].equals(word)) {
               line = "";
               for (int i = 1; i < lineArray.length; i++) { // replace line with line minus the actual word
                  line += lineArray[i];
               }
               char[] charArray = line.toCharArray(); // count how many stressed vowel sounds there are for sCount
               for (char c : charArray) {
                  if (Character.isDigit(c)) {
                     count++;
                  }
               }
               return count;
            }
         }
         return 0;
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

   // TEMPORARY
   public static String parseWord(String line) {
      String[] array = line.split(" ");
      return array[0];
   }
}
