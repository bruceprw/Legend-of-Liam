package application;

import application.DailyMessage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TitleScreen extends Screen{	
	private BorderPane root;
	
	private VBox topPane;
	private Text title;
	private Text dailyMessage;
	
	private VBox buttonsPane;
	private Button newProfile;
	private Button loadProfile;
	private Button leaderboards;
	

	public TitleScreen() {		
		root = new BorderPane();
		topPane = new VBox();
		buttonsPane = new VBox();
		title = new Text("Legend of Liam");
		title.setFont(Font.font(100));

		
		topPane.setMaxWidth(WINDOW_WIDTH);
		dailyMessage = new Text(DailyMessage.getMessage());
		dailyMessage.setWrappingWidth(WINDOW_WIDTH);
		dailyMessage.setTextAlignment(TextAlignment.CENTER);
		
		topPane.setAlignment(Pos.CENTER);
		
		buildButtons();
		
		root.setTop(topPane);
		root.setCenter(buttonsPane);
		
		topPane.getChildren().addAll(title, dailyMessage);
		buttonsPane.getChildren().addAll(newProfile, loadProfile, leaderboards);
		
		buttonsPane.setAlignment(Pos.CENTER);
		
		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	private void buildButtons() {
		newProfile = new Button("Create Profile");
		newProfile.setOnAction(event -> {
			//createPopup(new NewUserScreen());
		});
		
		// TODO: Change from "Select Level" to "Load Profile"
		loadProfile = new Button("Select Level");
		loadProfile.setOnAction(event -> {
			//TODO: Implement when user classes imported.
			//switchScreen(new SelectUserScreen());
			
			// To be removed
			switchScreen(new LevelScreen());
		});
		
		leaderboards = new Button("Leaderboards");
		leaderboards.setOnAction(event -> {
			switchScreen(new LeaderboardsScreen());
		});
		
		newProfile.setId("titleButton");
		loadProfile.setId("titleButton");
		leaderboards.setId("titleButton");
	}
}
