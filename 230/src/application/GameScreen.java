package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
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
	private String levelNo;
	private Button levelSelect;
	private long time;
	private GameBoard level;
	private UserProfile user;
	private Leaderboard leaderboard;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	//int time = 0;

	/**
	 * 
	 * @param levelNo Number of the level to be loaded.
	 * @throws InterruptedException
	 */
	
	public GameScreen(String levelNo, UserProfile user)
	{
		this.levelNo = levelNo;
		this.user=user;
		
		try
		{
			level = new GameBoard("LevelFiles\\" + levelNo + ".txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Label timeLabel = new Label();
		long startTime = System.currentTimeMillis()+ level.getTime();
		 
		new AnimationTimer()
		{
			public void handle(long now)
			{
				long elapsedMillis = System.currentTimeMillis() - startTime;
				time = elapsedMillis;
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
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			try {
				keyPressed(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	//when its a game over it fades back to the title screen
	public void RestartLevel(){
		leaderboard=new Leaderboard();
		Date temp = new Date(time);
		leaderboard.addLevelTime(user.getName(), sdf.format(temp));
		FadeTransition ft = new FadeTransition(Duration.millis(3000), root);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
		switchScreen(new GameScreen(levelNo,user));
	}
	public void NextLevel(){
		switchScreen(new GameScreen(levelNo + 1,user));
	}
	

	private void keyPressed(KeyEvent event) throws IOException

	{
		boolean end;
		switch (event.getCode())
		{
		case RIGHT:
			end = level.move("right");
			if(level.playerDead())
			{
				RestartLevel();
			}
			if (end){
				 NextLevel();
			}
			break;

		case LEFT:
			end = level.move("left");
			if(level.playerDead())
			{
				RestartLevel();
			}
			if (end){
				 NextLevel();
			}
			break;

		case UP:
			end = level.move("up");
			if(level.playerDead())
			{
				RestartLevel();
			}
			if (end){
				 NextLevel();
			}
			break;

		case DOWN:
			end = level.move("down");
			if(level.playerDead())
			{
				RestartLevel();
			}
			if (end){
				 NextLevel();
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
			FileOutputer f = new FileOutputer(level,new LevelTime(user.getName(),Long.toString(time)));
			// Switch to Title Screen
			Scene s = new TitleScreen().getScene();
			scene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
			primaryStage.setScene(s);
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
