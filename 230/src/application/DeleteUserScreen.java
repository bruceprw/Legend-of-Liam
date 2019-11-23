package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeleteUserScreen extends Screen{
	BorderPane root;
	
	Text prompt;
	
	HBox bottomBorder;
	Button delete;
	Button cancel;
	
	public DeleteUserScreen(User user) {
		root = new BorderPane();
		
		bottomBorder = new HBox();
		bottomBorder.setAlignment(Pos.BASELINE_RIGHT);
		
		prompt = new Text("Are you sure you want to delete " + user.getUsername() + "?");
		delete = new Button("Submit");
		delete.setOnAction(event -> {
			// TODO: DELETE USER FUNCTION GOES HERE
			
			// Closes the popup
			Stage popup = (Stage) this.scene.getWindow();
			popup.close();
		});
		
		cancel = new Button("Cancel");
		cancel.setOnAction(event -> {
			// Closes the popup
			Stage popup = (Stage) this.scene.getWindow();
			popup.close();
		});
		
		bottomBorder.getChildren().addAll(delete, cancel);
		
		root.setTop(prompt);
		root.setBottom(bottomBorder);
		root.setPadding(new Insets(20, 20, 20, 20));

		scene = new Scene(root, POPUP_WIDTH, POPUP_HEIGHT);
	}
}
