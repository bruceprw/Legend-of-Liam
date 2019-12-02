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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TitleScreen extends Screen
{
	private BorderPane root;

	private VBox topPane;
	private Text title;
	private Text dailyMessage;
	private Image titleImage;
	private VBox menuPane;
	private Text welcome;
	private Button loadGame;
	private Button selectLevel;
	private Button leaderboards;
	private Button options;
	private Button logout;
	private ImageView a ;
	private GridPane loginPane;
	private Text loginPrompt;
	private Text usernameLabel;
	private Text passwordLabel;
	private TextField username;
	private PasswordField password;
	private Button submit;
	private Button newProfile;
	private static Media music = new Media(new File("Sound\\BGM\\Aurora_CurrentsINTRO.mp3").toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	private UserProfile currentUser = null;


	public TitleScreen() throws FileNotFoundException
	{
		root = new BorderPane();
		titleImage = new Image(new FileInputStream("Images\\updateimage\\titlescreenimage.jpg"));
		
		a = new ImageView(titleImage);
		a.setFitHeight(730);
		a.setFitWidth(900);
		buildTopPane();

		buildLoginPane();

		buildMenuPane();
		mediaPlayer = new MediaPlayer(music);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setVolume(0.5);
		mediaPlayer.play();
		
		
		root.setTop(topPane);
		root.setCenter(loginPane);

		menuPane.setAlignment(Pos.CENTER);

		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void buildTopPane()
	{
		topPane = new VBox();
		topPane.setMaxWidth(WINDOW_WIDTH);
		topPane.setAlignment(Pos.CENTER);

		title = new Text("Legend of Liam");
		title.setFont(Font.font(100));

		dailyMessage = new Text(DailyMessage.getMessage());
		dailyMessage.setWrappingWidth(WINDOW_WIDTH);
		dailyMessage.setTextAlignment(TextAlignment.CENTER);
		BackgroundImage b = new BackgroundImage(titleImage, null, null, null, null); 
		topPane.setBackground(new Background(b));
		topPane.getChildren().addAll(title, dailyMessage);
	}

	private void buildLoginPane()
	{
		BackgroundImage b = new BackgroundImage(titleImage, null, null, null, null); 
		loginPane = new GridPane();
		loginPane.setHgap(10);
		loginPane.setVgap(5);
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setBackground(new Background(b));
		loginPrompt = new Text("Please enter your login details, or");
		usernameLabel = new Text("Username:");
		passwordLabel = new Text("Password:");
		username = new TextField();
		password = new PasswordField();
		submit = new Button("Submit");
		//let's let the user allowed to press "Enter key" to login
		submit.setOnAction(event ->
		{
			boolean found = false;
			
			File f = new File("UserProfiles\\profiles.txt");
			Scanner s = null;
			try
			{
				s = new Scanner(f);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}

			while (s.hasNext())
			{
				Scanner line = new Scanner(s.nextLine());
				line.useDelimiter(",");
				String nextUser = line.next();
				String nextPass = line.next();
				int nextProgress = line.nextInt();

				if(username.getText().equals(nextUser) && password.getText().equals(nextPass))
				{
					currentUser = new UserProfile(nextUser, nextPass, nextProgress);
					root.setCenter(menuPane);
					found = true;

					loginPrompt.setText("Please enter your login details.");
					username.setText("");
					password.setText("");

					welcome.setText("Welcome " + currentUser.getName() + "!");
				}
			}

			if(!found)
			{
				loginPrompt.setText("User not found. Please try again.");
				username.setText("");
				password.setText("");
			}

		});
		newProfile = new Button("Create Profile");
		newProfile.setOnAction(event ->
		{
			createPopup(new NewUserScreen());
		});

		loginPane.addRow(0, loginPrompt, newProfile);
		loginPane.addRow(1, usernameLabel, username);
		loginPane.addRow(2, passwordLabel, password);
		loginPane.addRow(3, submit);
	}

	private void buildMenuPane()
	{
		BackgroundImage b = new BackgroundImage(titleImage, null, null, null, null); 
		menuPane = new VBox();
		menuPane.setBackground(new Background(b));
		welcome = new Text("Welcome!");

		loadGame = new Button("Load Game");
		loadGame.setOnAction(event ->
		{
			switchScreen(new GameScreen(currentUser.getName(), currentUser));
		});

		selectLevel = new Button("Select Level");
		selectLevel.setOnAction(event ->
		{
			switchScreen(new LevelScreen(currentUser));
			mediaPlayer.stop();
		});

		leaderboards = new Button("Leaderboards");
		leaderboards.setOnAction(event ->
		{
			switchScreen(new LeaderboardsScreen("1"));
		});
		
		options = new Button("Options");
		options.setOnAction(event -> 
		{
			createPopup(new OptionsScreen(currentUser));
		});

		logout = new Button("Log Out");
		logout.setOnAction(event ->
		{
			root.setCenter(loginPane);
		});

		menuPane.getChildren().addAll(welcome, loadGame, selectLevel, leaderboards, options, logout);
	}
	
	public void switchToMenu(UserProfile currentUser) {
		this.currentUser = currentUser;
		root.setCenter(menuPane);
		welcome.setText("Welcome " + currentUser.getName() + "!");
	}

}
