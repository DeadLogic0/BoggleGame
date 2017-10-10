package NickBarrett_CS351_BoggleGame;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BoggleDictionary dictionary = new BoggleDictionary();
//        ArrayList<Character> word = new ArrayList<Character>();
//        word.add('A');
//      word.add('X');
//      word.add('B');
//      word.add('Z');
//      boolean[] returnbool = dictionary.containsWordCont(word);
//      System.out.println("booleans:"+returnbool[0]+","+returnbool[1]);
//        FindAllWords findwords = new FindAllWords(dictionary,new Character[][]{
//                {'A','X','B','L'},
//                {'N','T','R','N'},
//                {'W','U','S','E'},
//                {'C','Q','S','N'}});
      System.out.println("dictionary finished");
      FindAllWords findwords = new FindAllWords(dictionary,new Character[][]{
              {'H','E','L','L','O'},
              {'P','E','W','N','D'},
              {'E','E','A','O','Y'},
              {'N','S','L','T','E'},
              {'T','A','T','S','O'}

      });
//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter any word to check if it is present in the dictionary.");
//        System.out.println("!!input must not contain any spaces!!");
//        System.out.println("To quit enter 'quit0'");
//        String inputword = input.nextLine();
//        while(!inputword.equals("quit0"))
//        {
//            System.out.println(dictionary.containsWord(inputword));
//            inputword = input.nextLine();
//        }
        System.out.println("Program finished");
    }
}
