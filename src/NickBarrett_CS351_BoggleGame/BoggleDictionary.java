package NickBarrett_CS351_BoggleGame;

/**
 * Nicholas Barrett
 * CS351
 * Boggle Project
 * Dictionary Class
 */

import java.io.*;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

public class BoggleDictionary
{
  private BoggleDictionaryTree dictionary = new BoggleDictionaryTree();
  private InputStream DICTIONARY = getClass().getResourceAsStream("/OpenEnglishWordList.txt");

  /**
   * constructor which reads the dictionary file into the custom data structure
   * @throws IOException
   */
  public BoggleDictionary() throws IOException
  {
    BufferedReader in = null ;
    try {
      String line ;
      in = new BufferedReader ( new InputStreamReader (DICTIONARY,"UTF-8"));
      while (( line = in. readLine ()) != null )
      {
        //System.out.println(line);
        if(line.length() > 2)dictionary.addWord(line, 0);
      }
    } finally{
      if (in != null ) { in. close (); }
    }
  }

  /**
   * function for checking if a string exists in the dictionary
   * @param word
   * @return
   */
  public boolean containsWord(String word)
  {
    return dictionary.containsWord(word, 0);
  }

  public boolean[] containsWordCont(Collection<Character> word)
  {
    return dictionary.containsWordContinuous(word.iterator());
  }

  /**
   * function for checking if a collection exists in the dictionary
   * @param word
   * @return
   */
  public boolean containsWord(Collection<Character> word)
  {
    Iterator<Character> wordIterator = word.iterator();
    return dictionary.containsWord(wordIterator);
  }

}
