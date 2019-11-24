package application;

import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GameScreen extends Screen
{
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 700;

	private BorderPane root;

	private Canvas game;

	private HBox buttonsPane;
	private Button save;
	private Button levelSelect;

	private GameBoard level;

	/**
	 * 
	 * @param levelNo Number of the level to be loaded.
	 * @throws InterruptedException 
	 */
	public GameScreen(int levelNo)
	{
		try
		{
			level = new GameBoard("LevelFiles\\"+levelNo+".txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		root = new BorderPane();
		game = new Canvas(GAME_WIDTH, GAME_HEIGHT);
		buttonsPane = new HBox();

		// Initial Call of drawGame() (can delete if you want)
		drawGame();

		// Create buttons and set on Actions
		buildButtons();

		// Add buttons to buttonsPane
		buttonsPane.getChildren().addAll(levelSelect, save);

		// Put canvas and buttons into root.
		root.setCenter(game);
		root.setBottom(buttonsPane);

		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> keyPressed(event));
	}

	private void keyPressed(KeyEvent event)
	{

		switch (event.getCode())
		{
		case RIGHT:
			level.move("right");
			break;

		case LEFT:
			level.move("left");
			break;

		case UP:
			level.move("up");
			break;

		case DOWN:
			level.move("down");
			break;

		default:
			break;
		}

		drawGame();

		// Consume key press event so that arrow keys don't interact with Buttons.
		event.consume();
	}

	private void drawGame()
	{
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.clearRect(0, 0, game.getWidth(), game.getHeight());

		try
		{
			level.drawGame(gc);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TEST - get rid of this
		gc.setFill(Color.BLACK);
		gc.strokeRect(0, 0, game.getWidth(), game.getHeight());
	}

	private void buildButtons()
	{
		save = new Button("Save and Quit");
		save.setOnAction(event ->
		{
			// TODO: Create Save and add to User.
			
			// Switch to Title Screen
			Scene s = new TitleScreen().getScene();
			scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
			primaryStage.setScene(s);
		});

		levelSelect = new Button("Back to Level Select");
		levelSelect.setOnAction(event ->
		{
			// Switch to Level Select Screen
			Scene s = new LevelScreen().getScene();
			scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
			primaryStage.setScene(s);
		});
	}
}
