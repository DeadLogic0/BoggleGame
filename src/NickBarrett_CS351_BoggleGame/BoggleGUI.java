package NickBarrett_CS351_BoggleGame;

/**
 * Nicholas Barrett
 * CS351
 * Boggle Project
 * Boggle User Interface
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Collection;


public class BoggleGUI extends Application implements EventHandler<MouseEvent>
{
  private int DISPLAYHEIGHT = 700;
  private int DISPLAYWIDTH = 1000;
  private int NEWGAMEBUTTONSIZE = 150;
  private int FONT_SIZE = 15;
  private int GAMETIME = 180;//time in seconds
  private int boardsize = 4;
  private Animation animation;
  private Rectangle[][] boardbuttons;
  private Rectangle[] newgamebuttons;
  private Text[] guessedWordsText;
  private ArrayList<String> guessedWords;
  private Group root;
  private BoggleDictionary dictionary;
  private FindAllWords findAll;
  private BoggleBoard board;
  private BoggleWordAssembler wordbuilder;
  private BoggleDictionaryTree foundWords;
  private DropShadow shadow;
  private Text numFoundText;
  private Text totalRemainingText;
  private Text scoreText;
  private long elapsedTime;
  private int totalWords;
  private int numFound;
  private int score;
  private Text gameovertext;
  private Rectangle timerdisp;
  private long startTime;
  private boolean gameover;

  @Override
  /**
   * start class which initializes every part of the game
   */
  public void start(Stage stage) throws Exception
  {
    guessedWords = new ArrayList<>();
    guessedWordsText = new Text[31];
    stage.setTitle("Boggle Game");
    shadow = new DropShadow();
    shadow.setRadius(40);
    shadow.setColor(Color.SILVER);
    root = new Group();
    dictionary = new BoggleDictionary();
    newGame(boardsize);
    Scene scene = new Scene(root, DISPLAYWIDTH, DISPLAYHEIGHT);
    stage.setScene(scene);
    stage.show();
    animation = new Animation();
    animation.start();
  }

  @Override
  /**
   * function for handling all the user interactions
   */
  public void handle(MouseEvent event)
  {
    Rectangle source = (Rectangle) event.getSource();
    if (source.equals(newgamebuttons[0]))
    {
      newgamebuttons[0].setEffect(shadow);
      newGame(4);
      newgamebuttons[0].setEffect(null);
    }
    else if (source.equals(newgamebuttons[1]))
    {
      newgamebuttons[1].setEffect(shadow);
      newGame(5);
      newgamebuttons[1].setEffect(null);
    }
    else
    {
      if(gameover == true) return;
      for (int row = 0; row < boardsize; row++)
      {
        for (int col = 0; col < boardsize; col++)
        {
          if (source.equals(boardbuttons[row][col]))
          {
            int id = wordbuilder.addChar(row, col);
            if (id == 1)
            {
              ArrayList<Character> word = wordbuilder.getWord();
              String wordstring = arrayToString(word);
              if(!guessedWords.contains(wordstring))guessedWords.add(wordstring);
              if (!foundWords.containsWord(word.iterator()) && dictionary.containsWord(word))
              {
                score += word.size();
                numFound++;
                foundWords.addWord(word.iterator());
                refreshText();
              }
              refreshGuessed();
              wordbuilder.clearWord();
              removeShadows();
            } else if (id == 2)
            {
              setShadow(row, col);
            }
          }
        }
      }
    }
  }

  /**
   * function for starting a new game
   * @param boardsize
   */
  private void newGame(int boardsize)
  {
    score = numFound = 0;
    guessedWords.clear();
    this.boardsize = boardsize;
    board = new BoggleBoard(boardsize);
    foundWords = new BoggleDictionaryTree();
    wordbuilder = new BoggleWordAssembler(board.getBoard(), boardsize);
    findAll = new FindAllWords(dictionary, board.getBoard());
    totalWords = findAll.getNumber();
    root.getChildren().clear();
    getBoardBackground();
    getNewGameButtons();
    getBoardDisplay();
    settext();
    setGuessed();
    timerdisp = new Rectangle(50, DISPLAYHEIGHT-60,DISPLAYWIDTH-75,10);
    timerdisp.setFill(Color.GREEN);
    root.getChildren().add(timerdisp);
    gameover = false;
    startTime = System.currentTimeMillis();
  }

  /**
   * initializes the display of the characters
   */
  private void getBoardDisplay()
  {
    boardbuttons = board.getDisplay();
    for (int row = 0; row < boardsize; row++)
    {
      for (int col = 0; col < boardsize; col++)
      {
        boardbuttons[row][col].setOnMouseClicked(this);
        root.getChildren().add(boardbuttons[row][col]);
      }
    }
  }

  /**
   * initializes the background
   */
  private void getBoardBackground()
  {
    Image image = new Image(getClass().getResource("resources/background2.jpg").toString(),
            DISPLAYWIDTH, DISPLAYHEIGHT, false, false);
    ImagePattern imagepattern = new ImagePattern(image);
    Rectangle background = new Rectangle(0, 0, image.getWidth(), image.getHeight());
    background.setFill(imagepattern);
    root.getChildren().add(background);
  }

  /**
   * initializes the new game buttons
   */
  private void getNewGameButtons()
  {
    newgamebuttons = new Rectangle[2];
    Image image = new Image(getClass().getResource("resources/New4x4.png").toString(),
            NEWGAMEBUTTONSIZE, NEWGAMEBUTTONSIZE, false, false);
    ImagePattern imagepattern = new ImagePattern(image);
    newgamebuttons[0] = new Rectangle(DISPLAYWIDTH - NEWGAMEBUTTONSIZE - 15, 30,
            image.getWidth(), image.getHeight());
    newgamebuttons[0].setFill(imagepattern);
    newgamebuttons[0].setOnMouseClicked(this);
    root.getChildren().add(newgamebuttons[0]);
    image = new Image(getClass().getResource("resources/New5x5.png").toString(),
            NEWGAMEBUTTONSIZE, NEWGAMEBUTTONSIZE, false, false);
    imagepattern = new ImagePattern(image);
    newgamebuttons[1] = new Rectangle(DISPLAYWIDTH - NEWGAMEBUTTONSIZE - 15, NEWGAMEBUTTONSIZE + 60
            , image.getWidth(), image.getHeight());
    newgamebuttons[1].setFill(imagepattern);
    newgamebuttons[1].setOnMouseClicked(this);
    root.getChildren().add(newgamebuttons[1]);
  }

  /**
   * function which adds a shadow to the character buttons
   * @param row
   * @param col
   */
  private void setShadow(int row, int col)
  {
    boardbuttons[row][col].setEffect(shadow);
  }

  /**
   * function which removes the shadows from all buttons
   */
  private void removeShadows()
  {
    for (int row = 0; row < boardsize; row++)
    {
      for (int col = 0; col < boardsize; col++)
      {
        boardbuttons[row][col].setEffect(null);
      }
    }
  }

  /**
   * main function
   * @param args
   */
  public static void main(String[] args)
  {
    launch(args);
  }

  /**
   * initializes guessed words
   */
  private void setGuessed()
  {
    guessedWordsText[0] = new Text(DISPLAYWIDTH-350,20,"GuessedWords");
    guessedWordsText[0].setFill(Color.WHITE);
    guessedWordsText[0].setFont(Font.font("Verdana", FONT_SIZE));
    root.getChildren().add(guessedWordsText[0]);
    for(int loopone = 1; loopone < 31; loopone++)
    {
      guessedWordsText[loopone] = new Text(DISPLAYWIDTH-350,20*(loopone+1),"");
      guessedWordsText[loopone].setFill(Color.GREEN);
      guessedWordsText[loopone].setFont(Font.font("Verdana", FONT_SIZE));
      root.getChildren().add(guessedWordsText[loopone]);
    }
  }

  /**
   * refreshes guessed words
   */
  private void refreshGuessed()
  {
    for(int loopone = 0; loopone < 30; loopone++)
    {
      if(guessedWords.size()>loopone)
      {
        guessedWordsText[loopone+1].setText(guessedWords.get(guessedWords.size()-loopone-1));
        if(foundWords.containsWord(guessedWords.get(guessedWords.size()-loopone-1),0))
        {
          guessedWordsText[loopone+1].setFill(Color.GREEN);
        }
        else
        {
          guessedWordsText[loopone+1].setFill(Color.RED);
        }

      }
    }
  }

  /**
   * Function which turns an array into a string
   * @param wordarray
   * @return
   */
  private String arrayToString(Collection<Character> wordarray)
  {
    String wordstring = "";
    for(Character wordchar: wordarray)
    {
      wordstring = wordstring+wordchar;
    }
    return wordstring;
  }

  /**
   * refreshes the text boxes
   */
  private void refreshText()
  {
    scoreText.setText("Score: " + score);
    totalRemainingText.setText("Words Remaining: " + (totalWords - numFound));
    numFoundText.setText("Words Found: " + numFound);
  }

  /**
   * initializes the text
   */
  private void settext()
  {
    numFoundText = new Text( DISPLAYWIDTH / 2 - 200, DISPLAYHEIGHT - 25,"Words Found: " + numFound);
    numFoundText.setFill(Color.WHITE);
    numFoundText.setFont(Font.font("Verdana", FONT_SIZE));
    totalRemainingText = new Text(DISPLAYWIDTH - 300, DISPLAYHEIGHT - 25,
            "Words Remaining: " + (totalWords - numFound));
    totalRemainingText.setFill(Color.WHITE);
    totalRemainingText.setFont(Font.font("Verdana", FONT_SIZE));
    scoreText = new Text(20, DISPLAYHEIGHT - 20,"Score: " + score);
    scoreText.setFill(Color.WHITE);
    scoreText.setFont(Font.font("Verdana", FONT_SIZE));
    gameovertext = new Text(5, DISPLAYHEIGHT-175,"");
    gameovertext.setFill(Color.RED);
    gameovertext.setFont(Font.font("Verdana", 130));
    root.getChildren().add(totalRemainingText);
    root.getChildren().add(scoreText);
    root.getChildren().add(numFoundText);
    root.getChildren().add(gameovertext);
  }

  /**
   * animation class for timer bar
   */
  class Animation extends AnimationTimer
  {
    @Override
    /**
     * handles the timer bar using the system time
     */
    public void handle(long now)
    {
      elapsedTime = (System.currentTimeMillis()-startTime)/1000;
      if(elapsedTime < GAMETIME)
      {
        //System.out.println((GAMETIME-elapsedTime));
        timerdisp.setWidth((GAMETIME-elapsedTime)*(Math.ceil((DISPLAYWIDTH-75)/GAMETIME)));
      }
      else
      {
        timerdisp.setWidth(0);
        gameover = true;
        gameovertext.setText("GAME OVER");
      }
    }
  }

}
