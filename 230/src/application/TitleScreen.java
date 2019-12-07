package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import application.DailyMessage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The GUI for the title screen.
 * 
 * @author
 *
 */
public class TitleScreen extends Screen {
	private BorderPane root;
	private VBox topPane;
	private Image titleImage;
	private VBox menuPane;
	private Text welcome;
	private GridPane loginPane;
	private static Media music = new Media(new File("Sound\\BGM\\Aurora_CurrentsINTRO.mp3").toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	private UserProfile currentUser = null;

	/**
	 * Constructor for the title screen. Creates a window and adds all the elements
	 * to it.
	 */
	public TitleScreen() {
		root = new BorderPane();
		try {
			titleImage = new Image(new FileInputStream("Images\\updateimage\\titlescreenimage.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageView a = new ImageView(titleImage);
		a.setFitHeight(730);
		a.setFitWidth(900);

		buildTopPane();

		buildLoginPane();

		buildMenuPane();
		mediaPlayer = new MediaPlayer(music);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setVolume(0.2);
		mediaPlayer.play();

		root.setTop(topPane);
		root.setCenter(loginPane);

		menuPane.setAlignment(Pos.CENTER);

		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stylesheet = "titlescreen.css";
	}

	/**
	 * Constructs the elements for the {@link #topPane}.
	 */
	private void buildTopPane() {
		topPane = new VBox();
		topPane.setMaxWidth(WINDOW_WIDTH);
		topPane.setAlignment(Pos.CENTER);

		Text title = new Text("Legend of Liam");
		title.setFont(Font.font(100));
		title.setFill(Color.CHARTREUSE);

		Text dailyMessage = new Text(DailyMessage.getMessage());
		dailyMessage.setFill(Color.CHARTREUSE);
		dailyMessage.setWrappingWidth(WINDOW_WIDTH);
		dailyMessage.setTextAlignment(TextAlignment.CENTER);
		BackgroundImage b = new BackgroundImage(titleImage, null, null, null, null);
		topPane.setBackground(new Background(b));
		topPane.getChildren().addAll(title, dailyMessage);
	}

	/**
	 * Construct the elements of the {@link #loginPane}.
	 */
	private void buildLoginPane() {
		BackgroundImage b = new BackgroundImage(titleImage, null, null, null, null);
		loginPane = new GridPane();
		loginPane.setHgap(10);
		loginPane.setVgap(5);
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setBackground(new Background(b));
		Text loginPrompt = new Text("Please enter your login details, or");
		loginPrompt.setFill(Color.CHARTREUSE);
		Text usernameLabel = new Text("Username:");
		usernameLabel.setFill(Color.CHARTREUSE);
		Text passwordLabel = new Text("Password:");
		passwordLabel.setFill(Color.CHARTREUSE);
		TextField username = new TextField();
		PasswordField password = new PasswordField();
		Button submit = new Button("Submit");
		// let's let the user allowed to press "Enter key" to login
		submit.setOnAction(event -> {
			boolean found = false;

			File f = new File("UserProfiles\\profiles.txt");
			Scanner s = null;
			try {
				s = new Scanner(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			while (s.hasNext()) {
				Scanner line = new Scanner(s.nextLine());
				line.useDelimiter(",");
				String nextUser = line.next();
				String nextPass = line.next();
				int nextProgress = line.nextInt();

				if (username.getText().equalsIgnoreCase(nextUser) && password.getText().equals(nextPass)) {
					currentUser = new UserProfile(nextUser, nextPass, nextProgress);
					root.setCenter(menuPane);
					found = true;

					loginPrompt.setText("Please enter your login details.");
					username.setText("");
					password.setText("");

					welcome.setText("Welcome " + currentUser.getName() + "!");
				}
				line.close();
			}

			if (!found) {
				loginPrompt.setText("User not found. Please try again.");
				username.setText("");
				password.setText("");
			}

		});
		Button newProfile = new Button("Create Profile");
		newProfile.setOnAction(event -> {
			createPopup(new NewUserScreen());
		});

		loginPane.addRow(0, loginPrompt, newProfile);
		loginPane.addRow(1, usernameLabel, username);
		loginPane.addRow(2, passwordLabel, password);
		loginPane.addRow(3, submit);
	}

	/**
	 * Constructs the elements to add to {@link #menuPane}.
	 */
	private void buildMenuPane() {
		BackgroundImage b = new BackgroundImage(titleImage, null, null, null, null);
		menuPane = new VBox();
		menuPane.setId("minWidthButtons");
		menuPane.setBackground(new Background(b));
		welcome = new Text("Welcome!");
		welcome.setFill(Color.CHARTREUSE);

		Button loadGame = new Button("Load Game");
		Button selectLevel = new Button("Select Level");
		Button howToPlay = new Button("How To Play");
		Button leaderboards = new Button("Leaderboards");
		Button options = new Button("Options");
		Button logout = new Button("Log Out");

		loadGame.setOnAction(event -> {
			switchScreen(new GameScreen(currentUser.getName(), currentUser));
		});

		selectLevel.setOnAction(event -> {
			switchScreen(new LevelScreen(currentUser));
			mediaPlayer.stop();
		});

		howToPlay.setOnAction(event -> {
			try {
				createPopup(new HowToPlayScreen());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});

		leaderboards.setOnAction(event -> {
			switchScreen(new LeaderboardsScreen("1", currentUser));
		});

		options.setOnAction(event -> {
			createPopup(new OptionsScreen(currentUser));
		});

		logout.setOnAction(event -> {
			root.setCenter(loginPane);
		});

		menuPane.getChildren().addAll(welcome, loadGame, selectLevel, howToPlay, leaderboards, options, logout);
	}

	/**
	 * Show the {@link menuPane} on the title screen.
	 * 
	 * @param currentUser The user who is logged in.
	 */
	public void switchToMenu(UserProfile currentUser) {
		this.currentUser = currentUser;
		root.setCenter(menuPane);
		welcome.setText("Welcome " + currentUser.getName() + "!");
	}

}
