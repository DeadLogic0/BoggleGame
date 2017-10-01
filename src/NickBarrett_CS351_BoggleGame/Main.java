package NickBarrett_CS351_BoggleGame;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException
    {
        System.out.println("1");
        BoggleDictionary dictionary = new BoggleDictionary();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter any word to check if it is present in the dictionary.");
        System.out.println("!!input must be lowercase without any spaces!!");
        System.out.println("To quit enter 'quit0'");
        String inputword = input.nextLine();
        while(!inputword.equals("quit0"))
        {
            System.out.println(dictionary.containsWord(inputword));
            inputword = input.nextLine();
        }
        System.out.println("Program finished");
    }
}
