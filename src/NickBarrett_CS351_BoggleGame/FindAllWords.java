package NickBarrett_CS351_BoggleGame;

import java.util.ArrayList;

public class FindAllWords
{
  private int boardsize;
  private static final Integer[] ROWMODS = new Integer[]{-1,-1,-1,0,0,1,1,1};//{1, 2, 3; 4, #, 5; 6, 7, 8}
  private static final Integer[] COLMODS = new Integer[]{-1,0,1,-1,1,-1,0,1};
  private BoggleDictionaryTree foundWords = new BoggleDictionaryTree();
  private BoggleDictionary dictionary;
  private Character[][] charboard;
  private int count = 0;
  private boolean[] returnboolean = new boolean[]{false,false};
  private boolean[][] checkedCells;
  private ArrayList<Character> word = new ArrayList<Character>();

  public FindAllWords(BoggleDictionary dictionary, Character[][] board)
  {
    this.dictionary = dictionary;
    boardsize = board.length;
    charboard = new Character[boardsize][boardsize];
    checkedCells = new boolean[boardsize][boardsize];
    for(int row = 0; row < boardsize; row++)
    {
      for(int col = 0; col < boardsize; col++)
      {
        charboard[row][col] = board[row][col];
        checkedCells[row][col] = false;
      }
    }
    for(int row = 0; row < boardsize; row++)
    {
      for(int col = 0; col < boardsize; col++)
      {
        word.clear();
        findWords(row,col);
      }
    }
    System.out.println("\n count:"+count+"\n");
  }


  private void findWords(int row,int col)
  {
    word.add(charboard[row][col]);
    //System.out.print(word.toString() + "\n");
    checkedCells[row][col] = true;
    returnboolean = dictionary.containsWordCont(word);
    if(returnboolean[0] == true) {
      if(!foundWords.containsWord(word.iterator()))
      {
        count++;
        foundWords.addWord(word.iterator());
        //System.out.print(word.toString() + "\n");
      }
    }
    if(!returnboolean[1])
    {
      for(int loopone = 0; loopone < 8; loopone++)
      {
        int adjrow = row+ROWMODS[loopone];
        int adjcol = col+COLMODS[loopone];
        if(adjrow >= 0 && adjrow < boardsize && adjcol >= 0 && adjcol < boardsize && !checkedCells[adjrow][adjcol])
        {
          findWords(adjrow,adjcol);
        }//end in bounds if
      }//end loop
    }
    returnboolean[1] = false;
    checkedCells[row][col] = false;
    word.remove(word.size()-1);
  }

  public boolean containsWord(String word)
  {
    return foundWords.containsWord(word,0);
  }

}
