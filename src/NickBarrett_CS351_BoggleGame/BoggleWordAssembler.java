package NickBarrett_CS351_BoggleGame;

/**
 * Nicholas Barrett
 * CS351
 * Boggle Project
 * boggle word builder class
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class BoggleWordAssembler
{
  private Character[][] board;
  private static final Integer[] ROWMODS = new Integer[]{-1,-1,-1,0,0,1,1,1};//{1, 2, 3; 4, #, 5; 6, 7, 8}
  private static final Integer[] COLMODS = new Integer[]{-1,0,1,-1,1,-1,0,1};
  private int boardsize;
  private ArrayList<Character> word = new ArrayList<Character>();
  private ArrayDeque<Integer[]> charLocs = new ArrayDeque<>();

  /**
   * constructor which takes in the current board and its size
   * @param board
   * @param boardsize
   */
  public BoggleWordAssembler(Character[][] board, int boardsize)
  {
    this.board = board;
    this.boardsize = boardsize;
  }

  /**
   * adding the character at row,col if valid
   * @param row
   * @param col
   * @return
   */
  public int addChar(int row, int col)
  {
    int adjcol, adjrow;
    Integer[] tempcharloc = new Integer[]{row,col};
    if(Arrays.equals(tempcharloc,charLocs.peekFirst())) return 1;
    else if(charLocs.isEmpty() && !charLocs.stream().anyMatch(x -> Arrays.equals(x,tempcharloc)))
    {
      charLocs.push(tempcharloc);
      word.add(board[row][col]);
      return 2;
    }
    for(int loopone = 0; loopone < 8; loopone++)
    {
      adjrow = charLocs.peek()[0] + ROWMODS[loopone];
      adjcol = charLocs.peek()[1] + COLMODS[loopone];
      if ((adjrow == row && adjcol == col && !charLocs.stream().anyMatch(x -> Arrays.equals(x,tempcharloc))))
      {
        charLocs.push(tempcharloc);
        word.add(board[row][col]);
        return 2;
      }
    }
    return 3;
  }

  /**
   * returns the built word
   * @return
   */
  public ArrayList<Character> getWord()
  {
    return word;
  }

  /**
   * clears the word
   */
  public void clearWord()
  {
    charLocs.clear();
    word.clear();
  }

}
