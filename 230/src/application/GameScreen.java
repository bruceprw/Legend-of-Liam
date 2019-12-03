package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class GameScreen extends Screen
{
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 700;

	private BorderPane root;

	private Canvas game;

	private HBox buttonsPane;
	private Button save;
	private String levelNo;
	private Button levelSelect;
	private long time;
	private GameBoard level;
	private UserProfile user;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

	/**
	 * 
	 * @param levelNo Number of the level to be loaded.
	 * @throws InterruptedException
	 */

	public GameScreen(String levelNo, UserProfile user)
	{

		try
		{
			Image ggImage = new Image(new FileInputStream("Images\\game_over.png"));
			Image ggBGImage = new Image(new FileInputStream("Images\\gameoverbg.png"));
		}
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.levelNo = levelNo;
		this.user = user;

		try
		{
			level = new GameBoard("LevelFiles\\" + levelNo + ".txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		Label timeLabel = new Label();
		long startTime = System.currentTimeMillis() - level.getTime();

		new AnimationTimer()
		{
			public void handle(long now)
			{
				long elapsedMillis = System.currentTimeMillis() - startTime;
				time = elapsedMillis;
				// System.out.println(time);
				// System.out.println(elapsedMillis);
				Date d = new Date(elapsedMillis);
				timeLabel.setText(sdf.format(d));
			}
		}.start();

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
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->
		{
			try
			{
				keyPressed(event);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	// when its a game over it fades back to the title screen
	public void RestartLevel()
	{

		FadeTransition ft = new FadeTransition(Duration.millis(3000), root);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
		switchScreen(new GameScreen(levelNo, user));
	}

	public void NextLevel()
	{
		switchScreen(new LevelScreen(user));
		Leaderboard ld = new Leaderboard(levelNo);

		ld.addLevelTime(user.getName(), time);
	}

	private void keyPressed(KeyEvent event) throws IOException

	{
		int a = 0;
		switch (event.getCode())
		{
		case RIGHT:
			a = level.move("right");
			if(a == 2)
			{
				// RestartLevel();
			}
			if(a == 1)
			{

				NextLevel();
			}
			break;

		case LEFT:
			a = level.move("left");
			if(a == 2)
			{
				// RestartLevel();
			}
			if(a == 1)
			{
				NextLevel();
			}
			break;

		case UP:
			a = level.move("up");
			if(a == 2)
			{

				// RestartLevel();
			}
			if(a == 1)
			{
				NextLevel();
			}
			break;

		case DOWN:
			a = level.move("down");
			if(a == 2)
			{

				// RestartLevel();
			}
			if(a == 1)
			{
				NextLevel();
			}
			break;
		case ESCAPE:
			Scene s = new LevelScreen(user).getScene();
			scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
			primaryStage.setScene(s);
			break;

		default:
			break;
		}
		if(a == 2)
		{
			RestartLevel();
		}
		else
		{
			drawGame();
		}

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
	}

	private void buildButtons()
	{
		save = new Button("Save and Quit");
		save.setOnAction(event ->
		{
			// TODO: Create Save and add to User.
			// System.out.println(Long.toString(time));
			FileOutputer f = new FileOutputer(level, new LevelTime(user.getName(), time));
			// Switch to Title Screen
			
			TitleScreen s = null;
			try {
				// TODO Not sure if exception should be caught here or in TitleScreen constructor.
				s = new TitleScreen();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			switchScreen(s);
			s.switchToMenu(user);
		});

		levelSelect = new Button("Back to Level Select");
		levelSelect.setOnAction(event ->
		{
			// Switch to Level Select Screen
			Scene s = new LevelScreen(user).getScene();
			scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
			primaryStage.setScene(s);
		});

	}
}
