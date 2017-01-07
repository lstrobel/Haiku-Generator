/**
 * Created by Lukas on 1/5/2017.
 * Create Haikus! They wont be good though...
 */

import java.io.*;
import java.util.*;
import java.lang.*;

public class HaikuGenerator {
      public static void main(String[] args){

         Scanner console = new Scanner(System.in);
         String ans = "y";
         System.out.print("Would you like to generate a haiku? (y/n)");
         ans = console.next();

         while(!ans.equals("n")){
            int[] foo = randomSum(5);
            int[] bar = randomSum(7);
            int[] baz = randomSum(5);

            String line1 = createHaikuLine(foo);
            String line2 = createHaikuLine(bar);
            String line3 = createHaikuLine(baz);

            System.out.println("Haiku:");
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            System.out.print("Would you like to generate a haiku? (y/n)");
            ans = console.next();
         }
      }

      // create the line for a haiku, with @sCount number of syllables
      public static String createHaikuLine(int[] sCount){

         BufferedReader reader = null;

         try {
            String output = "";
            for(int k : sCount){
               File file = new File("C:\\Users\\Lukas\\Documents\\Programming\\Fun\\Haiku Generator\\Syllables.txt");
               reader = new BufferedReader(new FileReader(file));
               String line;
               double startPos = Math.random()*42300;
               if(k != 0){
                  for(int i = 0; i < startPos; i++) {
                     line = reader.readLine();
                  }
                  while((line = reader.readLine()) != null) {
                     if(findSCount(line) == k){
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

      // create a int array specifying how many words + how many syllables they should have
      // @sum is the goal num of syllables
      public static int[] randomSum(int sum){
         //how many words you will use
         int size = (int) Math.round( ((Math.random() * (sum-1)) + 1) );

         //the array for the distribution of syllables, but between 0-1
         int[] zeroOne = new int[size+1];
         zeroOne[0] = 0; zeroOne[1] = sum;
         for(int i = 2; i < zeroOne.length; i++){
            zeroOne[i] = (int) Math.round( ((Math.random() * (sum-1)) + 1) );
         }
         Arrays.sort(zeroOne);

         //the actual array with syllable count
         int[] numbers = new int[size];
         for(int i = 0; i < numbers.length; i++){
            numbers[i] = zeroOne[i+1]-zeroOne[i];
         }
         return numbers;
      }

      public static int findSCount(String line){
         String[] array = line.split(" ");
         int num = array.length-2;
         return num;
      }

      public static String parseWord(String line){
         String[] array = line.split(" ");
         return array[0];
      }
}
