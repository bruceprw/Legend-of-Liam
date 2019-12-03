package application;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Screen {
	protected final static int WINDOW_WIDTH = 900;
	protected final static int WINDOW_HEIGHT = 730;
	protected final static int POPUP_WIDTH = 400;
	protected final static int POPUP_HEIGHT = 300;
	protected final static int HOW_TO_PLAY_WIDTH = 700;
	protected final static int HOW_TO_PLAY_HEIGHT = 700;
	protected final static String STYLESHEET = "application.css";

	protected static Stage primaryStage;

	protected Scene scene;

	public Scene getScene() {
		return scene;
	}

	public static void setPrimaryStage(Stage stage) {
		primaryStage = stage;
	}

	public void switchScreen(Screen screen) {
		Scene s = screen.getScene();
		s.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
		primaryStage.setScene(s);
	}

	public void createPopup(Screen screen) {
		Stage popupStage = new Stage();
		popupStage.initModality(Modality.APPLICATION_MODAL);
		Scene s = screen.getScene();
		s.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());

		popupStage.setScene(s);
		popupStage.show();
	}
}
