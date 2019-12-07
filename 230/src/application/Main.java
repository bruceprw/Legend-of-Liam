package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Main class, first class ran by Java.
 * 
 * @author
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Screen.setPrimaryStage(primaryStage);
		Screen.primaryStage.setTitle("Legend of Liam");
		try {
			// Replace TitleScreen() to change the starting screen.
			Scene scene = new TitleScreen().getScene();
			scene.getStylesheets().add(getClass().getResource("titlescreen.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launches the program.
	 * 
	 * @param args argument fore the program. Launches the GUI.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
