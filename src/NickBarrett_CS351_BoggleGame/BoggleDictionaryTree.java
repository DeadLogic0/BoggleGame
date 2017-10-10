package NickBarrett_CS351_BoggleGame;


/**
*Nicholas Barrett
*CS351 Boggle Project
*Boggle Dictionary
*/

import java.util.*;

/**
*custom tree structure for storing words
*
*/
public class BoggleDictionaryTree
{
  /**
  *@param CharacterMap a hashmap used to recursively call the tree function
  *for each character
  *@param WordMap an arraylist which is used to contain characters each time
  *the end of a word is present in the input deque
  */
  private Map<Character, BoggleDictionaryTree> characterMap=new HashMap<Character, BoggleDictionaryTree>();
  private ArrayList<Character> wordMap = new ArrayList<Character>();

/**
*@param word the input deque containing characters. each time the function is
*called recursively a character is removed and added to the hashmap until the
*deque is empty
*/
  public void addWord(Iterator<Character> word)
  {
    Character inputchar = Character.toUpperCase(word.next());
    if(word.hasNext() && !characterMap.containsKey(inputchar))
    {
      characterMap.put(inputchar, new BoggleDictionaryTree());
    }
    if(!word.hasNext() && !wordMap.contains(inputchar))
    {
      wordMap.add(inputchar);
      return;
    }
    else if(word.hasNext())
    {
      characterMap.get(inputchar).addWord(word);
    }
  }

  public void addWord(String word, int pos)
  {
    Character inputchar = Character.toUpperCase(word.charAt(pos));
    pos++;
    if(word.length() != pos && !characterMap.containsKey(inputchar))
    {
      characterMap.put(inputchar, new BoggleDictionaryTree());
    }
    if(word.length() == pos && !wordMap.contains(inputchar))
    {
      wordMap.add(inputchar);
      return;
    }
    else
    {
      characterMap.get(inputchar).addWord(word, pos);
    }
  }

  /**
  *@param word the input deque, each time the function is called recursively a
  *character is removed
  *@return returns true when last letter is found in the word array
  */
  public boolean containsWord(Iterator<Character> word)
  {
    Character letter = Character.toUpperCase(word.next());
    if(word.hasNext() && characterMap.containsKey(letter))
    {
      return characterMap.get(letter).containsWord(word);
    }
    else if(!word.hasNext() && wordMap.contains(letter))
    {
      return true;
    }
    return false;
  }



  public boolean containsWord(String word, int pos)
  {
    Character letter = Character.toUpperCase(word.charAt(pos));
    pos++;
    if(word.length() > pos && characterMap.containsKey(letter))
    {
      return characterMap.get(letter).containsWord(word,pos);
    }
    else if(word.length() == pos && wordMap.contains(letter))
    {
      return true;
    }
    return false;
  }

  public boolean[] containsWordContinuous(Iterator<Character> word)
  {
    Character letter = Character.toUpperCase(word.next());
    if(word.hasNext() && characterMap.containsKey(letter))
    {
      return characterMap.get(letter).containsWordContinuous(word);
    }
    else if(word.hasNext()) return new boolean[]{false,true};
    else if(!word.hasNext() && wordMap.contains(letter) && characterMap.isEmpty())
    {
      return new boolean[]{true, true};
    }
    else if(!word.hasNext() && wordMap.contains(letter))
    {
      return new boolean[]{true, false};
    }
    return new boolean[]{false, false};
  }
}
