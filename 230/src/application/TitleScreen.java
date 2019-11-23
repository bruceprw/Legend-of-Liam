package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
			createPopup(new NewUserScreen());
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
		leaderboards.setDisable(true);
		leaderboards.setOnAction(event -> {
			// TODO: Leaderboards not yet done
			//switchScreen(new LeaderboardsScreen());
		});
	}
}
