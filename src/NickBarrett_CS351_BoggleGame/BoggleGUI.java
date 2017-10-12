package NickBarrett_CS351_BoggleGame;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Scanner;


public class BoggleGUI extends Application implements EventHandler<MouseEvent>
{
  private int DISPLAYHEIGHT = 700;
  private int DISPLAYWIDTH = 800;
  private int boardsize = 5;
  private Rectangle[][] boardbuttons;
  private Group root;
  private Canvas canvas;
  private BoggleDictionary dictionary;
  private FindAllWords findAll;
  private BoggleBoard board;
  private BoggleWordAssembler wordbuilder;
  private GraphicsContext gtx;

  @Override
  public void start(Stage stage) throws Exception
  {
    stage.setTitle("Boggle Game");
    canvas = new Canvas(DISPLAYWIDTH,DISPLAYHEIGHT);
    root = new Group();
    gtx = canvas.getGraphicsContext2D();
    gtx.setFill(Color.BLACK);
    gtx.fillRect(0,0,DISPLAYWIDTH,DISPLAYHEIGHT);
    dictionary = new BoggleDictionary();
    newGame(boardsize);
    Scene scene = new Scene(root,DISPLAYWIDTH,DISPLAYHEIGHT);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void handle(MouseEvent event)
  {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter any word you find on the board and if it is a word and exists it will be true.");
    System.out.println("!!input must not contain any spaces!!");
    System.out.println("To quit enter 'quit0'");
    String inputword = input.nextLine();
    while(!inputword.equals("quit0"))
    {
      System.out.println(findAll.containsWord(inputword));
      inputword = input.nextLine();
    }
    System.out.println("Program finished");
  }

  private void newGame(int boardsize)
  {
    board = new BoggleBoard(boardsize);
    wordbuilder = new BoggleWordAssembler(board.getBoard(),boardsize);
    findAll =  new FindAllWords(dictionary,board.getBoard());
    getBoardBackground();
    getBoardDisplay();

  }

  private void getBoardDisplay()
  {
    boardbuttons = board.getDisplay();
    for(int row = 0; row < boardsize; row++)
    {
      for(int col = 0; col < boardsize; col++)
      {
        boardbuttons[row][col].setOnMouseClicked(this);
        root.getChildren().add(boardbuttons[row][col]);
      }
    }
  }

  private void getBoardBackground()
  {
    Image image = new Image("file:../../resources/background2.jpg",
            DISPLAYWIDTH,DISPLAYHEIGHT,false,false);
    ImagePattern imagepattern = new ImagePattern(image);
    Rectangle background = new Rectangle(0,0,image.getWidth(),image.getHeight());
    background.setFill(imagepattern);
    root.getChildren().add(background);
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
