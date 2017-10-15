package NickBarrett_CS351_BoggleGame;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.HashMap;

public class TileImages
{
  private final Character[] LETTERSET = new Character[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P'
          ,'Q','R','S','T','U','V','W','X','Y','Z'};
  private HashMap<Character,ImagePattern> images = new HashMap<>();
  private int chardisplaysize = 100;

  public TileImages()
  {
    Image loopimage;
    for(int loopone = 0; loopone < LETTERSET.length; loopone++)
    {
      loopimage = new Image(getClass().getResource("resources/"+LETTERSET[loopone]+".png"
                  ).toString(),chardisplaysize,chardisplaysize,false,false);
      images.put(LETTERSET[loopone], new ImagePattern(loopimage));
    }
  }

  public ImagePattern getImage(Character letter)
  {
    return images.get(letter);
  }
}
