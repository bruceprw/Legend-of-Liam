package application;

import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OptionsScreen extends Screen{
	BorderPane root;
	
	UserProfile user;
	
	VBox optionsPane;
	Button editUserPass;
	Button resetProgress;
	Button deleteProfile;
	
	GridPane editGrid;
	Text usernameLabel;
	Text passwordLabel;
	Text checkPassLabel;
	Text oldPassPrompt;
	Text oldPassLabel;
	TextField username;
	PasswordField password;
	PasswordField checkPass;
	PasswordField oldPass;
	HBox editButtonsPane;
	Button submit;
	Button editCancel;
	
	Text resetPrompt;
	HBox resetButtonsPane;
	Button reset;
	Button resetCancel;
	
	Text deletePrompt;	
	HBox deleteButtonsPane;
	Button delete;
	Button deleteCancel;
	
	
	public OptionsScreen(UserProfile user) {
		root = new BorderPane();
		root.setPadding(new Insets(20));

		this.user = user;
		
		buildOptionsPane();
		
		buildEdit();
		
		buildReset();
		
		buildDelete();
		
		root.setCenter(optionsPane);
		
		scene = new Scene(root, POPUP_WIDTH, POPUP_HEIGHT);
	}
	
	private void buildOptionsPane() {
		optionsPane = new VBox();
		optionsPane.setAlignment(Pos.CENTER);
		
		editUserPass = new Button("Edit Username/Password");
		editUserPass.setOnAction(event ->
		{
			root.setCenter(editGrid);
			root.setBottom(editButtonsPane);
		});
		
		resetProgress = new Button("Reset Progress");
		resetProgress.setOnAction(event ->
		{
			root.setCenter(resetPrompt);
			root.setBottom(resetButtonsPane);
		});
		
		deleteProfile = new Button("Delete Profile");
		deleteProfile.setOnAction(event ->
		{
			root.setCenter(deletePrompt);
			root.setBottom(deleteButtonsPane);
		});
		
		optionsPane.getChildren().addAll(editUserPass, resetProgress, deleteProfile);
	}

	private void buildEdit() {
		editGrid = new GridPane();
		
		usernameLabel = new Text("Username:");
		passwordLabel = new Text("Password:");
		checkPassLabel = new Text("Check Password:");
		oldPassLabel = new Text("Old Password:");
		oldPassPrompt = new Text("To save changes, please enter your old password.");
		
		username = new TextField();
		password = new PasswordField();
		checkPass = new PasswordField();
		oldPass = new PasswordField();
		
		editGrid.addRow(0, usernameLabel, username);
		editGrid.addRow(1, passwordLabel, password);
		editGrid.addRow(2, checkPassLabel, checkPass);
		// Spans two columns
		editGrid.add(oldPassPrompt, 0, 4, 2, 1);
		editGrid.addRow(5, oldPassLabel, oldPass);
		
		editGrid.setVgap(10);
		editGrid.setHgap(30);
		
		editButtonsPane = new HBox();
		
		submit = new Button("Save Changes");
		submit.setOnAction(event -> {
			// TODO
		});
		
		editCancel = new Button("Cancel");
		editCancel.setOnAction(event -> {
			root.setCenter(optionsPane);
			root.setBottom(null);
		});
		
		editButtonsPane.getChildren().addAll(submit, editCancel);
		editButtonsPane.setAlignment(Pos.CENTER_RIGHT);
	}

	private void buildReset() {
		resetPrompt = new Text("Are you sure you want to reset level progress?");
		resetButtonsPane = new HBox();
		
		reset = new Button("Yes, reset.");
		reset.setOnAction(event -> {
			user.setLevelProg(0);
			root.setCenter(optionsPane);
			root.setBottom(null);
		});
		
		resetCancel = new Button("No, cancel.");
		resetCancel.setOnAction(event -> {
			root.setCenter(optionsPane);
			root.setBottom(null);
		});
		
		resetButtonsPane.getChildren().addAll(reset, resetCancel);
		resetButtonsPane.setAlignment(Pos.CENTER_RIGHT);
	}
	
	private void buildDelete() {
		deletePrompt = new Text("Are you sure you want to delete " + user.getName() + "?");
		deleteButtonsPane = new HBox();
		
		delete = new Button("Yes, delete.");
		delete.setOnAction(event -> {
			UserProfile.deleteUserProfile(user.getName(), user.getPassword(), user.getLevelProg());
			root.setCenter(optionsPane);
			root.setBottom(null);
			
			// Unsure if exception should be caught here or in titlescreen constructor
			try {
				switchScreen(new TitleScreen());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// Closes the popup
			Stage popup = (Stage) this.scene.getWindow();
			popup.close();
		});
		
		deleteCancel = new Button("No, cancel.");
		deleteCancel.setOnAction(event -> {
			root.setCenter(optionsPane);
			root.setBottom(null);
		});
		
		deleteButtonsPane.getChildren().addAll(delete, deleteCancel);
		deleteButtonsPane.setAlignment(Pos.CENTER_RIGHT);
	}
}
