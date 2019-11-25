package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewUserScreen extends Screen{
	BorderPane root;
	
	VBox topBorder;
	Text prompt;
	TextField username;
	TextField password;
	TextField checkPassword;
	
	HBox bottomBorder;
	Button submit;
	Button cancel;
	
	public NewUserScreen() {
		root = new BorderPane();
		
		topBorder = new VBox();
		topBorder.setAlignment(Pos.BASELINE_LEFT);
		bottomBorder = new HBox();
		bottomBorder.setAlignment(Pos.BASELINE_RIGHT);
		
		prompt = new Text("Please enter a new username.");
		
		username = new TextField();
		username.setMaxWidth(POPUP_WIDTH);
		
		password = new TextField();
		password.setMaxWidth(POPUP_WIDTH);
		
		checkPassword = new TextField();
		checkPassword.setMaxWidth(POPUP_WIDTH);
		
		submit = new Button("Submit");
		cancel = new Button("Cancel");
		//UserProfile user = new UserProfile(input,);
		
		submit.setOnAction(event -> {
			// TODO: CREATE USER FUNCTION GOES HERE
			switchScreen(new LevelScreen());
			
			// Closes the popup
			Stage popup = (Stage) this.scene.getWindow();
			popup.close();
		});
		
		cancel.setOnAction(event -> {
			// Closes the popup
			Stage popup = (Stage) this.scene.getWindow();
			popup.close();
		});
		
		topBorder.getChildren().addAll(prompt, username, password, checkPassword);
		bottomBorder.getChildren().addAll(submit, cancel);
		
		root.setTop(topBorder);
		root.setBottom(bottomBorder);
		root.setPadding(new Insets(20, 20, 20, 20));

		scene = new Scene(root, POPUP_WIDTH, POPUP_HEIGHT);
	}
}
