package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import cell.LevelDoor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class LevelScreen extends Screen {
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 700;

	private BorderPane root;

	private Canvas game;

	private GameBoard level;
	private UserProfile user;

	/**
	 * 
	 * @param levelNo Number of the level to be loaded.
	 * @throws InterruptedException
	 */
	
	public LevelScreen(UserProfile user)
	{
		this.user=user;

		try
		{
			level = new GameBoard("LevelFiles\\levelSelect.txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		root = new BorderPane();
		game = new Canvas(GAME_WIDTH, GAME_HEIGHT);

		// Initial Call of drawGame() (can delete if you want)
		drawGame();

		root.setCenter(game);
		
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

	private void keyPressed(KeyEvent event) throws IOException

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
			
		case SPACE:
			Element space = level.getBackground()[level.getPlayerY()][level.getPlayerX()];
			if (space.getClass() == LevelDoor.class) {
				LevelDoor door = (LevelDoor) space;
				int levelNo = door.getLevelNo();
				
				if (levelNo == 0) {
					TitleScreen t = new TitleScreen();
					switchScreen(t);
					t.switchToMenu(user);
				} else {
					switchScreen(new GameScreen(levelNo + "", user));
				}
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
	
	
}