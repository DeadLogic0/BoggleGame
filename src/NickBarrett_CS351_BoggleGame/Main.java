package NickBarrett_CS351_BoggleGame;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BoggleDictionary dictionary = new BoggleDictionary();
        BoggleBoard board = new BoggleBoard(5);
        System.out.println("dictionary finished");
        board.displayBoard();
        //System.out.println("board generated");
        FindAllWords findwords = new FindAllWords(dictionary,board.getBoard());
        Scanner input = new Scanner(System.in);
        System.out.println("Enter any word you find on the board and if it is a word and exists it will be true.");
        System.out.println("!!input must not contain any spaces!!");
        System.out.println("To quit enter 'quit0'");
        board.displayBoard();
        System.out.println("\n\n");
        String inputword = input.nextLine();
        board.displayBoard();
        System.out.println("\n\n");
        while(!inputword.equals("quit0"))
        {
            System.out.println(findwords.containsWord(inputword));
            board.displayBoard();
            System.out.println("\n\n");
            inputword = input.nextLine();
        }
        System.out.println("Program finished");
    }
}
