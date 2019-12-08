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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The window for creating a new user. This class builds the javafx that makes
 * up this screen.
 * 
 * @author Gideon Davies.
 * @author Andy Kuo
 *
 */
public class NewUserScreen extends Screen {
	private static final String PASSWORD_WARNING = "Password not the same...";
	private static final String ERROR_MESSAGE = "Duplicate username!!!";
	private static final String PROMPT = "Please enter a new username.";

	private BorderPane root = new BorderPane();

	private GridPane top;
	private TextField username = new TextField();
	private PasswordField password = new PasswordField();
	private PasswordField checkPassword = new PasswordField();
	private Text passwordWarning = new Text(PASSWORD_WARNING);

	private HBox bottomBorder;
	private Button submit = new Button("Submit");
	private Button cancel = new Button("Cancel");
	private Text error = new Text(ERROR_MESSAGE);

	private UserProfile user;

	/**
	 * Creates a new window and adds the elements for adding a new user.
	 */
	public NewUserScreen() {
		// Build Top Border
		buildTopPane();

		// Build Bottom Border
		error.setVisible(false);

		buildButtons();

		bottomBorder = new HBox();
		bottomBorder.setAlignment(Pos.BASELINE_RIGHT);
		bottomBorder.getChildren().addAll(submit, cancel, error);

		// Put Panes into Root
		root.setCenter(top);
		root.setBottom(bottomBorder);
		root.setPadding(new Insets(20, 20, 20, 20));

		scene = new Scene(root, POPUP_WIDTH, POPUP_HEIGHT);
	}

	/**
	 * Confirms the password is correct.
	 * 
	 * @return True if its correct else false.
	 */
	public boolean passwordConfirm() {
		return this.checkPassword.getText().equals(password.getText());
	}

	/**
	 * Builds the javafx elements for {{@link #top}. Which contains
	 * UI elements for entering a username and password.
	 */
	private void buildTopPane() {
		top = new GridPane();
		top.setVgap(20);
		top.setHgap(30);

		Text prompt = new Text(PROMPT);
		Text usernameLabel = new Text("Username:");
		Text passwordLabel = new Text("Password:");
		Text checkPassLabel = new Text("Check Password:");

		passwordWarning.setFill(Color.RED);
		passwordWarning.setVisible(false);
		username.setMaxWidth(POPUP_WIDTH);
		password.setMaxWidth(POPUP_WIDTH);
		checkPassword.setMaxWidth(POPUP_WIDTH);

		// Adding the elements to the GridPane
		top.addRow(0, prompt);
		top.addRow(1, usernameLabel, username);
		top.addRow(2, passwordLabel, password);
		top.addRow(3, checkPassLabel, checkPassword);
		top.addRow(4, passwordWarning);
	}

	/**
	 * Builds the event handlers for this buttons in this class.
	 */
	private void buildButtons() {
		submit.setOnAction(event -> {
			boolean exists = false;
			if (!passwordConfirm())
				passwordWarning.setVisible(true);
			else {
				try {
					exists = UserProfile.createUserProfile(username.getText(), password.getText(), 0);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				if (exists)
					error.setVisible(true);
				else {
					user = new UserProfile(username.getText(), password.getText(), 0);

					// Closes the popup
					switchScreen(new LevelScreen(user));
					Stage popup = (Stage) this.scene.getWindow();
					popup.close();
				}

			}

		});

		cancel.setOnAction(event -> {
			// Closes the popup
			Stage popup = (Stage) this.scene.getWindow();
			popup.close();
		});
	}
}
