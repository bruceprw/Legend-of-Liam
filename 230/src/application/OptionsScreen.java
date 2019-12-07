package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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

/**
 * Generates GUI for the options screen.
 * 
 * @author Gideon Davies.
 *
 */
public class OptionsScreen extends Screen {
	BorderPane root;

	UserProfile user;

	VBox optionsPane;
	Button editUserPass;
	Button resetProgress;
	Button deleteProfile;

	GridPane editGrid;
	HBox editButtonsPane;
	Button submit;
	Button editCancel;
	TextField username = new TextField();
	PasswordField password = new PasswordField();
	PasswordField checkPass = new PasswordField();
	PasswordField oldPass = new PasswordField();

	Text resetPrompt;
	HBox resetButtonsPane;
	Button reset;
	Button resetCancel;

	Text deletePrompt;
	HBox deleteButtonsPane;
	Button delete;
	Button deleteCancel;

	/**
	 * Create an instance of the options screen for a given user, mainly calling
	 * other methods within the class.
	 * 
	 * @param user allows the screen to determine which users' options to change or
	 *             display.
	 */
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

	/**
	 * Creates the creates the 'background' for the screen and all the
	 * buttons/interactive features.
	 */
	private void buildOptionsPane() {
		optionsPane = new VBox();
		optionsPane.setAlignment(Pos.CENTER);

		editUserPass = new Button("Edit Username/Password");
		editUserPass.setOnAction(event -> {
			root.setCenter(editGrid);
			root.setBottom(editButtonsPane);
		});

		resetProgress = new Button("Reset Progress");
		resetProgress.setOnAction(event -> {
			root.setCenter(resetPrompt);
			root.setBottom(resetButtonsPane);
		});

		deleteProfile = new Button("Delete Profile");
		deleteProfile.setOnAction(event -> {
			root.setCenter(deletePrompt);
			root.setBottom(deleteButtonsPane);
		});

		optionsPane.getChildren().addAll
			(editUserPass, resetProgress, deleteProfile);
	}

	/**
	 * Used to update the screen with fields for the user to alter or change their
	 * password and save those changes.
	 */
	private void buildEdit() {
		editGrid = new GridPane();

		Text usernameLabel = new Text("Username:");
		Text passwordLabel = new Text("Password:");
		Text checkPassLabel = new Text("Check Password:");
		Text oldPassLabel = new Text("Old Password:");
		Text oldPassPrompt = new Text("To save changes, please enter your old password.");

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
			if (fieldsFilled() && uniqueUsername() && passwordsMatch() && oldPassCorrect()) {
				UserProfile.updateUserProfile(user.getName(),
					username.getText(), password.getText(), user.getLevelProg());
				user = new UserProfile(username.getText(), 
					password.getText(), user.getLevelProg());
				clearTextBoxes();
				root.setCenter(optionsPane);
				root.setBottom(null);
			}
		});

		editCancel = new Button("Cancel");
		editCancel.setOnAction(event -> {
			clearTextBoxes();
			root.setCenter(optionsPane);
			root.setBottom(null);
		});

		editButtonsPane.getChildren().addAll(submit, editCancel);
		editButtonsPane.setAlignment(Pos.CENTER_RIGHT);
	}

	/**
	 * Allows the user to reset their level progress. Displays a yes/no popup screen
	 * to user.
	 */
	private void buildReset() {
		resetPrompt = new Text("Are you sure you want to reset level progress?");
		resetButtonsPane = new HBox();

		reset = new Button("Yes, reset.");
		reset.setOnAction(event -> {
			user.setLevelProg(0);
			UserProfile.updateUserProfile(user.getName(), user.getName(), 
				user.getPassword(), 0);
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

	/**
	 * Allows the user to delete their profile. Displays a yes/no popup screen to
	 * user.
	 */
	private void buildDelete() {
		deletePrompt = new Text("Are you sure you want to delete " + 
			user.getName() + "?");
		deleteButtonsPane = new HBox();

		delete = new Button("Yes, delete.");
		delete.setOnAction(event -> {
			UserProfile.deleteUserProfile(user.getName(), 
				user.getPassword(), user.getLevelProg());
			root.setCenter(optionsPane);
			root.setBottom(null);

			// Unsure if exception should be caught here or in titlescreen constructor
			try {
				switchScreen(new TitleScreen());
			} catch (Exception e) {
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

	private boolean fieldsFilled() {
		boolean userEmpty = username.getText().isEmpty();
		boolean newPassEmpty = password.getText().isEmpty();
		boolean checkPassEmpty = checkPass.getText().isEmpty();
		boolean oldPassEmpty = oldPass.getText().isEmpty();

		return !(userEmpty || newPassEmpty || checkPassEmpty || oldPassEmpty);
	}

	private boolean uniqueUsername() {
		UserProfile.readList();

		boolean unique = !(UserProfile.exists(username.getText()));
		boolean sameName = username.getText().equals(user.getName());

		UserProfile.outputList();

		return unique || sameName;
	}

	private boolean passwordsMatch() {
		return password.getText().equals(checkPass.getText());
	}

	private boolean oldPassCorrect() {
		return oldPass.getText().equals(user.getPassword());
	}

	private void clearTextBoxes() {
		username.setText("");
		password.setText("");
		checkPass.setText("");
		oldPass.setText("");
	}
}
