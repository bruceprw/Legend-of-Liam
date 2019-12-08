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
 * Generates GUI for the options screen. Builds UI element that allows the user
 * to edit their profile details.
 * 
 * @author Gideon Davies.
 *
 */
public class OptionsScreen extends Screen {
	private BorderPane root;

	private UserProfile user;

	private VBox optionsPane;

	private GridPane editGrid;
	private HBox editButtonsPane;
	private TextField username = new TextField();
	private PasswordField password = new PasswordField();
	private PasswordField checkPass = new PasswordField();
	private PasswordField oldPass = new PasswordField();

	private Text resetPrompt;
	private HBox resetButtonsPane;

	private Text deletePrompt;
	private HBox deleteButtonsPane;

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

		Button editUserPass = new Button("Edit Username/Password");
		editUserPass.setOnAction(event -> {
			root.setCenter(editGrid);
			root.setBottom(editButtonsPane);
		});

		Button resetProgress = new Button("Reset Progress");
		resetProgress.setOnAction(event -> {
			root.setCenter(resetPrompt);
			root.setBottom(resetButtonsPane);
		});

		Button deleteProfile = new Button("Delete Profile");
		deleteProfile.setOnAction(event -> {
			root.setCenter(deletePrompt);
			root.setBottom(deleteButtonsPane);
		});

		optionsPane.getChildren().addAll(editUserPass, resetProgress, deleteProfile);
	}

	/**
	 * Used to update the screen with fields for the user to alter or change their
	 * password and save those changes.
	 */
	private void buildEdit() {
		Text usernameLabel = new Text("Username:");
		Text passwordLabel = new Text("Password:");
		Text checkPassLabel = new Text("Check Password:");
		Text oldPassLabel = new Text("Old Password:");
		Text oldPassPrompt = new Text("To save changes, please enter your old password.");

		editGrid = new GridPane();
		editGrid.addRow(0, usernameLabel, username);
		editGrid.addRow(1, passwordLabel, password);
		editGrid.addRow(2, checkPassLabel, checkPass);
		editGrid.add(oldPassPrompt, 0, 4, 2, 1);
		editGrid.addRow(5, oldPassLabel, oldPass);
		editGrid.setVgap(10);
		editGrid.setHgap(30);

		Button submit = new Button("Save Changes");
		submit.setOnAction(event -> {
			if (fieldsFilled() && uniqueUsername() && passwordsMatch() && oldPassCorrect()) {
				// Update UserProfile
				UserProfile.updateUserProfile(user.getName(), username.getText(), password.getText(),
						user.getLevelProg());
				user = new UserProfile(username.getText(), password.getText(), user.getLevelProg());

				// Transition back to optionPane
				clearTextBoxes();
				root.setCenter(optionsPane);
				root.setBottom(null);
			}
		});

		Button editCancel = new Button("Cancel");
		editCancel.setOnAction(event -> {
			clearTextBoxes();
			root.setCenter(optionsPane);
			root.setBottom(null);
		});

		editButtonsPane = new HBox();
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

		Button reset = new Button("Yes, reset.");
		reset.setOnAction(event -> {
			user.setLevelProg(0);
			UserProfile.updateUserProfile(user.getName(), user.getName(), user.getPassword(), 0);
			root.setCenter(optionsPane);
			root.setBottom(null);
		});

		Button resetCancel = new Button("No, cancel.");
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
		deletePrompt = new Text("Are you sure you want to delete " + user.getName() + "?");

		Button delete = new Button("Yes, delete.");
		delete.setOnAction(event -> {
			UserProfile.deleteUserProfile(user.getName(), user.getPassword(), user.getLevelProg());
			root.setCenter(optionsPane);
			root.setBottom(null);

			try {
				switchScreen(new TitleScreen());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Closes the popup
			Stage popup = (Stage) this.scene.getWindow();
			popup.close();
		});

		Button deleteCancel = new Button("No, cancel.");
		deleteCancel.setOnAction(event -> {
			root.setCenter(optionsPane);
			root.setBottom(null);
		});

		deleteButtonsPane = new HBox();
		deleteButtonsPane.getChildren().addAll(delete, deleteCancel);
		deleteButtonsPane.setAlignment(Pos.CENTER_RIGHT);
	}

	/**
	 * Checks whether all text boxes have something entered into them.
	 * 
	 * @return True, if all fields have input. False otherwise.
	 */
	private boolean fieldsFilled() {
		boolean userEmpty = username.getText().isEmpty();
		boolean newPassEmpty = password.getText().isEmpty();
		boolean checkPassEmpty = checkPass.getText().isEmpty();
		boolean oldPassEmpty = oldPass.getText().isEmpty();

		return !(userEmpty || newPassEmpty || checkPassEmpty || oldPassEmpty);
	}

	/**
	 * Checks whether the username entered is used by another profile (or if the
	 * username entered is the same as their current username).
	 * 
	 * @return True, if the name is unique or is their old username. False
	 *         otherwise.
	 */
	private boolean uniqueUsername() {
		UserProfile.readList();

		boolean unique = !(UserProfile.exists(username.getText()));
		boolean sameName = username.getText().equals(user.getName());

		UserProfile.outputList();

		return unique || sameName;
	}

	/**
	 * Checks whether the passwords entered are matching.
	 * 
	 * @return True, if the text in those boxes are the same. False otherwise.
	 */
	private boolean passwordsMatch() {
		return password.getText().equals(checkPass.getText());
	}

	/**
	 * Checks whether they have entered their old password correctly.
	 * 
	 * @return True, if the password is correct. False otherwise.
	 */
	private boolean oldPassCorrect() {
		return oldPass.getText().equals(user.getPassword());
	}

	/**
	 * Clear all the text boxes in the 'Edit Profile' section.
	 */
	private void clearTextBoxes() {
		username.setText("");
		password.setText("");
		checkPass.setText("");
		oldPass.setText("");
	}
}
