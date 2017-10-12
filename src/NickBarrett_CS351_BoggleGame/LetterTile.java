package NickBarrett_CS351_BoggleGame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LetterTile
{
  private Character letter = null;
  private Rectangle chardisplay;
  private int chardisplaysize = 100;
  private int buffer = 25;

  public  LetterTile(Character letter,int row, int col)
  {
    if(letter != null) this.letter = letter;
    else return;
    int xpos = row*(chardisplaysize+buffer)+buffer;
    int ypos = col*(chardisplaysize+buffer)+buffer;
    Image image = new Image("file:../../resources/"+letter+".png",chardisplaysize,chardisplaysize,false,false);
    ImagePattern imagepattern = new ImagePattern(image);
    chardisplay = new Rectangle(xpos,ypos,image.getWidth(),image.getHeight());
    chardisplay.setFill(imagepattern);
  }

  public Character getChar()
  {
    return letter;
  }

  public Rectangle getDisplay()
  {
    return chardisplay;
  }
}
