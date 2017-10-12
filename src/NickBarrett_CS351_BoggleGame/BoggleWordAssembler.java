package NickBarrett_CS351_BoggleGame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BoggleWordAssembler
{
  private Character[][] board;
  private static final Integer[] ROWMODS = new Integer[]{-1,-1,-1,0,0,1,1,1};//{1, 2, 3; 4, #, 5; 6, 7, 8}
  private static final Integer[] COLMODS = new Integer[]{-1,0,1,-1,1,-1,0,1};
  private int boardsize;
  private ArrayList<Character> word = new ArrayList<Character>();
  private ArrayDeque<Integer[]> charLocs = new ArrayDeque<>();

  public BoggleWordAssembler(Character[][] board, int boardsize)
  {
    this.board = board;
    this.boardsize = boardsize;
  }

  public boolean addChar(int row, int col)
  {
    int adjcol, adjrow;
    for(int loopone = 0; loopone < 8; loopone++)
    {
      adjrow = charLocs.peek()[0] + ROWMODS[loopone];
      adjcol = charLocs.peek()[1] + COLMODS[loopone];
      if ((adjrow == row && adjcol == col) || charLocs.isEmpty())
      {
        charLocs.addFirst(new Integer[]{row,col});
        word.add(board[row][col]);
        return true;
      }
    }
    return false;
  }

  public boolean removeChar(int row, int col)
  {
    if(charLocs.peek()[0] == row && charLocs.peek()[1] == col)
    {
      charLocs.pop();
      word.remove(word.size()-1);
      return true;
    }
    return false;
  }

  public ArrayList<Character> getWord()
  {
    return word;
  }

  public void clearWord()
  {
    charLocs.clear();
    word.clear();
  }
}
