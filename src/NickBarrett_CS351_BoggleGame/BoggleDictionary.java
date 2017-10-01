package NickBarrett_CS351_BoggleGame;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;

public class BoggleDictionary
{
  private BoggleDictionaryTree dictionary = new BoggleDictionaryTree();
  private String DICTIONARY = "OpenEnglishWordList.txt";

  public BoggleDictionary() throws IOException
  {
    BufferedReader in = null ;
    try {
      String line ;
      in = new BufferedReader ( new FileReader (DICTIONARY));
      while (( line = in. readLine ()) != null )
      {
        //System.out.println(line);
        if(line.length() > 2)dictionary.addWord(line, 0);
      }
    } finally{
      if (in != null ) { in. close (); }
    }
  }

  public boolean containsWord(String word)
  {
    return dictionary.containsWord(word, 0);
  }

  public boolean containsWord(Collection<Character> word)
  {
    Iterator wordIterator = word.iterator();
    return dictionary.containsWord(wordIterator);
  }
}
