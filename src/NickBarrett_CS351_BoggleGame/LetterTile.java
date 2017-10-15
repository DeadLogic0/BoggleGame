package NickBarrett_CS351_BoggleGame;

/**
 * Nicholas Barrett
 * CS351
 * Boggle Project
 * Letter tile class
 */

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


/**
 * class for storing the letter tiles
 */
public class LetterTile
{
  private Character letter = null;
  private Rectangle chardisplay;
  private int chardisplaysize = 100;
  private int buffer = 25;

  /**
   * constructor for letter tile
   * @param letter character value of the tile
   * @param row row position of the tile
   * @param col col position of the tile
   * function initializes the character value and retrieves the characters image creating a rectangle for it
   */
  public  LetterTile(Character letter,int row, int col)
  {
    if(letter != null) this.letter = letter;
    else return;
    int xpos = row*(chardisplaysize+buffer)+buffer;
    int ypos = col*(chardisplaysize+buffer)+buffer;
    chardisplay = new Rectangle(xpos,ypos,chardisplaysize,chardisplaysize);
  }

  /**
   * returns the tile character value
   * @return
   */
  public Character getChar()
  {
    return letter;
  }


  /**
   * returns the rectangle to be displayed
   * @return
   */
  public Rectangle getDisplay()
  {
    return chardisplay;
  }
}