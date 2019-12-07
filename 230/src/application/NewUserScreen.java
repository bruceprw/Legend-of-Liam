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
 * The window for creating a new user.
 * 
 * @author Gideon Davies.
 * @author Andy Kuo
 *
 */
public class NewUserScreen extends Screen {
	BorderPane root;

	GridPane top;

	// VBox topBorder;
	Text prompt;
	Text usernameLabel;
	Text passwordLabel;
	Text checkPassLabel;
	TextField username;

	// TextField password;
	// TextField checkPassword;
	Text passwordWarning = new Text("Password not the same...");

	PasswordField password;
	PasswordField checkPassword;
	Text passwordEnter = new Text("Please enter your password: ");
	Text passwordConfirm = new Text("Please confirm your password: ");
	Text error = new Text("Duplicate usernames!!!");
	private UserProfile user;

	HBox bottomBorder;
	Button submit;
	Button cancel;

	/**
	 * Creates a new window and adds the elements for adding a new user.
	 */
	public NewUserScreen() {
		root = new BorderPane();

		top = new GridPane();
		top.setVgap(20);
		top.setHgap(30);
		error.setVisible(false);

		// topBorder = new VBox();
		// topBorder.setAlignment(Pos.BASELINE_LEFT);

		bottomBorder = new HBox();
		bottomBorder.setAlignment(Pos.BASELINE_RIGHT);

		prompt = new Text("Please enter a new username.");
		usernameLabel = new Text("Username:");
		passwordLabel = new Text("Password:");
		checkPassLabel = new Text("Check Password:");

		passwordWarning.setFill(Color.RED);
		passwordWarning.setVisible(false);
		username = new TextField();
		username.setMaxWidth(POPUP_WIDTH);

		password = new PasswordField();

		password = new PasswordField();

		password.setMaxWidth(POPUP_WIDTH);

		checkPassword = new PasswordField();

		checkPassword = new PasswordField();

		checkPassword.setMaxWidth(POPUP_WIDTH);

		submit = new Button("Submit");
		cancel = new Button("Cancel");

		// UserProfile user = new UserProfile(input,);

		submit.setOnAction(event -> {
			boolean exists = false;
			// TODO: CREATE USER FUNCTION GOES HERE
			if (!passwordConfirm())
				passwordWarning.setVisible(true);
			else {
				try {
					exists = UserProfile.createUserProfile
						(username.getText(), password.getText(), 0);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (exists)
					error.setVisible(true);
				else {
					user = new UserProfile(username.getText(), 
						password.getText(), 0);
					switchScreen(new LevelScreen(user));
					Stage popup = (Stage) this.scene.getWindow();
					popup.close();// Closes the popup
				}

			}

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
		top.addRow(4, passwordWarning);

		bottomBorder.getChildren().addAll(submit, cancel, error);

		// root.setTop(topBorder);
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
}
