package NickBarrett_CS351_BoggleGame;

/**
 * Nicholas Barrett
 * CS351
 * Boggle Project
 * Boggle Game Board
 */

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class BoggleBoard
{

  private final Character[] LETTERSET = new Character[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P'
                                                       ,'Q','R','S','T','U','V','W','X','Y','Z'};
  private static final Integer[] ROWMODS = new Integer[]{-1,-1,-1,0,0,1,1,1};//{1, 2, 3; 4, #, 5; 6, 7, 8}
  private static final Integer[] COLMODS = new Integer[]{-1,0,1,-1,1,-1,0,1};
  private final int LETTERSETSIZE = LETTERSET.length;
  private final int MAXCOUNT = 4;
  private int boardsize;
  private int[] lettercounts = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  private LetterTile[][] board;
  private Random rand = new Random();
  private ArrayList<Integer[]> qpositions = new ArrayList<>();

  /**
   * Boggle Constructor
   * @param boardsize
   */
  public BoggleBoard(int boardsize)
  {
   newGame(boardsize);
  }

  /**
   * creates a new board
   * @param boardsize
   */
  public void newGame(int boardsize)
  {
    this.boardsize = boardsize;
    board = new LetterTile[boardsize][boardsize];
    Character tempchar;
    for(int row = 0; row < boardsize; row++)
    {
      for(int col = 0; col < boardsize; col++)
      {
        tempchar = LETTERSET[rand.nextInt(LETTERSETSIZE)];
        while(lettercounts[tempchar-65] == MAXCOUNT)
        {
          tempchar = LETTERSET[rand.nextInt(LETTERSETSIZE)];
        }//end while
        lettercounts[tempchar-65]++;
        board[row][col] = new LetterTile(tempchar,row,col);
        if(tempchar.equals('Q'))
        {
          qpositions.add(new Integer[]{row,col});
        }//end if
      }//end col loop
    }//end row loop
    if(!qpositions.isEmpty())
    {
      if(rand.nextInt(10) < 5)
      {
        Integer[] qpos = qpositions.get(rand.nextInt(qpositions.size()));
        int row = qpos[0];
        int col = qpos[1];
        int randmod = rand.nextInt(8);//8 is the size of the ROWMODS array and the number of adjacent cells
        int randomizedloc,adjrow,adjcol;
        for(int loopone = 0; loopone < 8; loopone++)
        {
          randomizedloc = (loopone+randmod)%8;
          adjrow = row+ROWMODS[randomizedloc];
          adjcol = col+COLMODS[randomizedloc];
          if(adjrow >= 0 && adjrow < boardsize && adjcol >= 0 && adjcol < boardsize)
          {
            board[adjrow][adjcol] = new LetterTile('U',adjrow,adjcol);
            return;
          }//end in bounds if
        }//end loop
      }//end chance if
    }//end check empty if
  }//end new game method

  /**
   * returns a representation of the board as a 2d character array
   * @return
   */
  public Character[][] getBoard()
  {
    Character[][] returnboard = new Character[boardsize][boardsize];
    for(int row = 0; row < boardsize; row++)
    {
      for(int col = 0; col < boardsize; col++)
      {
        returnboard[row][col] = board[row][col].getChar();
      }
    }
    return returnboard;
  }

  /**
   * class for displaying the board in the command window
   */
  public void displayBoard()
  {
    String rowstr = "";
    for(int row = 0; row < boardsize; row++)
    {
      for(int col = 0; col < boardsize; col++)
      {
        rowstr = rowstr+" "+board[row][col].getChar();
      }
      System.out.println(rowstr);
      rowstr = "";
    }
  }

  /**
   * returns a 2d array of rectangles representing the game board
   * @return
   */
  public Rectangle[][] getDisplay()
  {
    Rectangle[][] returnboard = new Rectangle[boardsize][boardsize];
    for(int row = 0; row < boardsize; row++)
    {
      for(int col = 0; col < boardsize; col++)
      {
        returnboard[row][col] = board[row][col].getDisplay();
      }
    }
    return returnboard;
  }
}



