package application;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
	int time=0;

	/**
	 * 
	 * @param levelNo Number of the level to be loaded.
	 * @throws InterruptedException
	 */
	public GameScreen(int levelNo)
	{
		Label timeLabel = new Label();
		long startTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		new AnimationTimer() {
			public void handle(long now) {
				long elapsedMillis = System.currentTimeMillis()-startTime;
				//System.out.println(elapsedMillis);
				Date d = new Date(elapsedMillis);
				timeLabel.setText(sdf.format(d));
			}
		}.start();

		try
		{
			level = new GameBoard("LevelFiles\\" + levelNo + ".txt");
		} catch (FileNotFoundException e)
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
		buttonsPane.getChildren().addAll(levelSelect, save, timeLabel);

		// Put canvas and buttons into root.
		root.setCenter(game);
		root.setBottom(buttonsPane);

		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> keyPressed(event));
	}

	private void keyPressed(KeyEvent event)
	{
		boolean end;
		switch (event.getCode())
		{
		case RIGHT:
			end =level.move("right");
			
			if (end)
			{
				Scene s = new LevelScreen().getScene();
				scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
				primaryStage.setScene(s);
				
			}
			break;

		case LEFT:
			end=level.move("left");
			if (end)
			{
				Scene s = new LevelScreen().getScene();
				scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
				primaryStage.setScene(s);
				
			}
			break;

		case UP:
			end=level.move("up");
			if (end)
			{
				Scene s = new LevelScreen().getScene();
				scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
				primaryStage.setScene(s);
				
			}
			break;

		case DOWN:
			end = level.move("down");
			if (end)
			{
				Scene s = new LevelScreen().getScene();
				scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
				primaryStage.setScene(s);
				
			}
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
		} catch (FileNotFoundException e)
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
			// FileOutputer f = new FileOutputer(level);
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
