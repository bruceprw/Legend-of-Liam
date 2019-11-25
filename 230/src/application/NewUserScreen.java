package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewUserScreen extends Screen{
	BorderPane root;
	
	GridPane top;
	//VBox topBorder;
	Text prompt;
	Text usernameLabel;
	Text passwordLabel;
	Text checkPassLabel;
	TextField username;
	TextField password;
	TextField checkPassword;
	Text passwordEnter = new Text("Please enter your password: ");
	Text passwordConfirm = new Text("Please confirm your password: ");
	
	HBox bottomBorder;
	Button submit;
	Button cancel;
	
	public NewUserScreen() {
		root = new BorderPane();
		
		top = new GridPane();
		top.setVgap(20);
		top.setHgap(30);
		
		//topBorder = new VBox();
		//topBorder.setAlignment(Pos.BASELINE_LEFT);
		
		bottomBorder = new HBox();
		bottomBorder.setAlignment(Pos.BASELINE_RIGHT);
		
		prompt = new Text("Please enter a new username.");
		usernameLabel = new Text("Username:");
		passwordLabel = new Text("Password:");
		checkPassLabel = new Text("Check Password:");
		
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
		

		top.addRow(0, prompt);
		top.addRow(1, usernameLabel, username);
		top.addRow(2, passwordLabel, password);
		top.addRow(3, checkPassLabel, checkPassword);

		bottomBorder.getChildren().addAll(submit, cancel);
		
		//root.setTop(topBorder);
		root.setCenter(top);
		root.setBottom(bottomBorder);
		root.setPadding(new Insets(20, 20, 20, 20));

		scene = new Scene(root, POPUP_WIDTH, POPUP_HEIGHT);
	}
}
