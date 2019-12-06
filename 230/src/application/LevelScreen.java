package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import cell.LevelDoor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

/**
 * Class used to create and display the level screen.
 * 
 * @author
 *
 */
public class LevelScreen extends Screen {
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 700;
	private Image bg;
	private BorderPane root;
	private Media music;
	private MediaPlayer mediaPlayer;
	private Canvas game;

	private GameBoard level;
	private UserProfile user;

	/**
	 * Creates the canvas, music and other elements for the LevelScreen.
	 * 
	 * @param levelNo Number of the level to be loaded.
	 */
	public LevelScreen(UserProfile user) {
		this.user = user;

		music = new Media(new File("Sound\\BGM\\bg.mp3").toURI().toString());

		mediaPlayer = new MediaPlayer(music);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
		try {
			bg = new Image(new FileInputStream("Images\\updateimage\\titlescreenimage.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			level = new GameBoard("LevelFiles\\levelSelect.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		root = new BorderPane();
		root.setBackground(new Background(new BackgroundImage(bg, null, null, null, null)));
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

	/**
	 * Processes key presses that interact with the level.
	 * 
	 * @param event The key press action by the user.
	 * @throws IOException Tells the system that this
	 * method could cause an error such as unable to access a file.
	 */
	private void keyPressed(KeyEvent event) throws IOException {
		boolean locked = false;
		
		// Check which key was pressed.
		switch (event.getCode()) {
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
			// Space bar used to select a level
			Element space = level.getBackground()[level.getPlayerY()][level.getPlayerX()];

			// If pressed on a LevelDoor, enter it if the user can access it.
			if (space.getClass() == LevelDoor.class) {
				LevelDoor door = (LevelDoor) space;
				int levelNo = door.getLevelNo();

				if (levelNo == 0) {
					TitleScreen t = new TitleScreen();
					switchScreen(t);
					t.switchToMenu(user);
				} else {
					if (levelNo <= user.getLevelProg() + 1) {
						switchScreen(new GameScreen(levelNo + "", user));
					} else {
						locked = true;
					}
				}
			}
			mediaPlayer.stop();
			break;

		default:
			break;
		}

		drawGame();
		if (locked) {
			GraphicsContext gc = game.getGraphicsContext2D();
			gc.fillText("Locked", 5, 50);
		}
		
		// Consume key press event so that arrow keys don't interact with
		// Buttons.
		event.consume();
	}

	/**
	 * Draws the game state onto the canvas.
	 */
	private void drawGame() {
		// Clear the canvas
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.clearRect(0, 0, game.getWidth(), game.getHeight());

		try {
			level.drawGame(gc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gc.setFill(Color.BLACK);
		gc.strokeRect(0, 0, game.getWidth(), game.getHeight());
	}

}