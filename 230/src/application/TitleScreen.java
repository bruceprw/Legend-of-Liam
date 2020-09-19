package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import application.DailyMessage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
 * The GUI for the title screen. Contains the UI elements that make up the title
 * screen, and methods to build them.
 * 
 * @author Gideon Davies.
 * @author Bruce Williams.
 *
 */
public class TitleScreen extends Screen {

	// Source files
	private static final String BACKGROUND_FILE =  "230" + File.separatorChar + "Images" + File.separatorChar + "updateimage" + File.separatorChar + "titlescreenimage.jpg";
	private static final String MUSIC_FILE = "230" + File.separatorChar + "Sound" + File.separatorChar + "BGM" + File.separatorChar + "Aurora_CurrentsINTRO.mp3";
	private static final String STYLESHEET_FILE = "titlescreen.css";
	private static final String PROFILES_FILE = "230" + File.separatorChar + "UserProfiles" + File.separatorChar + "profiles.txt";

	private static final String TITLE_STRING = "Legend Of Liam";
	private static final String LOGIN_TEXT = "Please enter your login details, or";
	private static final String LOGIN_ERROR = "User not found. Please try again.";

	private static Media music = new Media(new File(MUSIC_FILE).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	private BorderPane root = new BorderPane();

	private VBox topPane = new VBox();
	private VBox menuPane = new VBox();
	private GridPane loginPane = new GridPane();

	private Image titleImage;
	private Text welcome;
	private UserProfile currentUser = null;

	/**
	 * Constructor for the title screen. Creates a window and adds all the elements
	 * to it.
	 */
	public TitleScreen() {
		try {
			titleImage = new Image(new FileInputStream(BACKGROUND_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// mediaPlayer settings
		/*
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setVolume(0.2);
		mediaPlayer.play();
		*/

		// Build each Pane
		buildTopPane();
		buildLoginPane();
		buildMenuPane();

		// Start at log-in
		root.setTop(topPane);
		root.setCenter(loginPane);

		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stylesheet = STYLESHEET_FILE;
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

	/**
	 * Constructs the elements for the {@link #topPane}.
	 */
	private void buildTopPane() {
		topPane.setMaxWidth(WINDOW_WIDTH);
		topPane.setAlignment(Pos.CENTER);

		BackgroundImage b = new BackgroundImage(titleImage, null, null, null, null);

		Text title = new Text(TITLE_STRING);
		title.setFont(Font.font(100));
		title.setFill(Color.CHARTREUSE);

		Text dailyMessage = new Text(DailyMessage.getMessage());
		dailyMessage.setFill(Color.CHARTREUSE);
		dailyMessage.setWrappingWidth(WINDOW_WIDTH);
		dailyMessage.setTextAlignment(TextAlignment.CENTER);

		topPane.setBackground(new Background(b));
		topPane.getChildren().addAll(title, dailyMessage);
	}

	/**
	 * Construct the elements of the {@link #loginPane}.
	 */
	private void buildLoginPane() {
		BackgroundImage b = new BackgroundImage(titleImage, null, null, null, null);

		loginPane = new GridPane();
		loginPane.setBackground(new Background(b));
		// References titlescreen.css
		loginPane.setId("loginPane");

		Label loginPrompt = new Label(LOGIN_TEXT);
		Label usernameLabel = new Label("Username:");
		Label passwordLabel = new Label("Password:");

		TextField username = new TextField();
		PasswordField password = new PasswordField();

		Button submit = new Button("Submit");
		Button newProfile = new Button("Create Profile");

		submit.setOnAction(event -> {
			UserProfile u = checkLogin(username.getText(), password.getText());

			username.setText("");
			password.setText("");

			if (u != null) {
				root.setCenter(menuPane);
				currentUser = u;
				welcome.setText("Welcome " + currentUser.getName() + "!");
				loginPrompt.setText(LOGIN_TEXT);
			} else {
				loginPrompt.setText(LOGIN_ERROR);
			}
		});
		newProfile.setOnAction(event -> {
			createPopup(new NewUserScreen());
		});

		loginPane.addRow(0, loginPrompt, newProfile);
		loginPane.addRow(1, usernameLabel, username);
		loginPane.addRow(2, passwordLabel, password);
		loginPane.addRow(3, submit);
	}

	/**
	 * Checks that the entered login details are valid and match to an existing
	 * profile.
	 * 
	 * @param username The username of the profile to be searched.
	 * @param password The password of the profile to be searched.
	 * @return The matching user profile, or null if it can't be found.
	 */
	private UserProfile checkLogin(String username, String password) {
		File f = new File(PROFILES_FILE);
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

			line.close();

			// If there is a match, return the UserProfile with the same
			// details.
			if (username.equalsIgnoreCase(nextUser) && password.equals(nextPass)) {
				return new UserProfile(nextUser, nextPass, nextProgress);
			}
		}

		// Returned if there are no matches.
		return null;
	}

	/**
	 * Constructs the elements to add to {@link #menuPane}.
	 */
	private void buildMenuPane() {
		BackgroundImage b = new BackgroundImage(titleImage, null, null, null, null);
		menuPane.setBackground(new Background(b));
		menuPane.setAlignment(Pos.CENTER);
		welcome = new Text();
		welcome.setFill(Color.CHARTREUSE);

		// References Stylesheet
		menuPane.setId("minWidthButtons");

		buildMenuPaneButtons();
	}

	/**
	 * Constructs the buttons and corresponding event handlers to add to
	 * {@link #menuPane}.
	 */
	private void buildMenuPaneButtons() {
		Button loadGame = new Button("Load Game");
		Button selectLevel = new Button("Select Level");
		Button howToPlay = new Button("How To Play");
		Button leaderboards = new Button("Leaderboards");
		Button options = new Button("Options");
		Button logout = new Button("Log Out");

		loadGame.setOnAction(event -> {
			switchScreen(new GameScreen(currentUser.getName(), currentUser));
			mediaPlayer.stop();
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
}
