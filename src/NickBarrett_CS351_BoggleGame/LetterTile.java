package NickBarrett_CS351_BoggleGame;

public class LetterTile
{
  private Character letter = null;

  public  LetterTile(Character letter)
  {
    if(letter != null) this.letter = letter;
  }

  public Character getChar()
  {
    return letter;
  }
}
