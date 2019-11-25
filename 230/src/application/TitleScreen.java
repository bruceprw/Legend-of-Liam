package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import application.DailyMessage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TitleScreen extends Screen {
	private BorderPane root;
	
	private VBox topPane;
	private Text title;
	private Text dailyMessage;
	
	private VBox menuPane;
	private Text welcome;
	private Button editProfile;
	private Button selectLevel;
	private Button leaderboards;
	private Button logout;
	
	private GridPane loginPane;	
	private Text loginPrompt;
	private Text usernameLabel;
	private Text passwordLabel;
	private TextField username;
	private PasswordField password;
	private Button submit;
	private Button newProfile;
	
	private UserProfile currentUser = null;

	public TitleScreen() {		
		root = new BorderPane();

		buildTopPane();
		
		buildLoginPane();
		
		buildMenuPane();
		
		root.setTop(topPane);
		root.setCenter(loginPane);
		
		menuPane.setAlignment(Pos.CENTER);
		
		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void buildTopPane() {
		topPane = new VBox();
		topPane.setMaxWidth(WINDOW_WIDTH);
		topPane.setAlignment(Pos.CENTER);
		
		title = new Text("Legend of Liam");
		title.setFont(Font.font(100));
		
		dailyMessage = new Text(DailyMessage.getMessage());
		dailyMessage.setWrappingWidth(WINDOW_WIDTH);
		dailyMessage.setTextAlignment(TextAlignment.CENTER);
		
		topPane.getChildren().addAll(title, dailyMessage);
	}

	private void buildLoginPane() {
		loginPane = new GridPane();
		loginPane.setHgap(10);
		loginPane.setVgap(5);
		loginPane.setAlignment(Pos.CENTER);
		loginPrompt = new Text("Please enter your login details, or");
		usernameLabel = new Text("Username:");
		passwordLabel = new Text("Password:");
		username = new TextField();
		password = new PasswordField();
		submit = new Button("Submit");
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
				
				if (username.getText().equals(nextUser) && password.getText().equals(nextPass)) {
					currentUser = new UserProfile(nextUser, nextPass, nextProgress);
					root.setCenter(menuPane);
					found = true;
					
					loginPrompt.setText("Please enter your login details.");
					username.setText("");
					password.setText("");
					
					welcome.setText("Welcome " + currentUser.getName() + "!");
				}
			}
			
			if (!found) {
				loginPrompt.setText("User not found. Please try again.");
				username.setText("");
				password.setText("");
			}
			
		});
		newProfile = new Button("Create Profile");
		newProfile.setOnAction(event -> {
			createPopup(new NewUserScreen());
		});
		
		loginPane.addRow(0, loginPrompt, newProfile);
		loginPane.addRow(1, usernameLabel, username);
		loginPane.addRow(2, passwordLabel, password);
		loginPane.addRow(3, submit);
	}

	
	private void buildMenuPane() {
		menuPane = new VBox();
		
		welcome = new Text("Welcome!");
		
		editProfile = new Button("Edit Profile");
		editProfile.setOnAction(event -> {
			
		});
		
		selectLevel = new Button("Select Level");
		selectLevel.setOnAction(event -> {
			switchScreen(new LevelScreen());
		});
		
		leaderboards = new Button("Leaderboards");
		leaderboards.setOnAction(event -> {
			switchScreen(new LeaderboardsScreen());
		});
		
		logout = new Button ("Log Out");
		logout.setOnAction(event -> {
			root.setCenter(loginPane);
		});
		
		editProfile.setId("titleButton");
		selectLevel.setId("titleButton");
		leaderboards.setId("titleButton");
		
		menuPane.getChildren().addAll(welcome, editProfile, selectLevel, leaderboards, logout);
	}
	
}
