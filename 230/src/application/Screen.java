package application;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Screen is the abstract superclass of all screens in the game. It contains
 * window dimension constants, and methods required for the transition between
 * screens.
 * 
 * @author Gideon Davies.
 * @version 2.0
 */
public abstract class Screen {
	/**
	 * The default window width.
	 */
	protected final static int WINDOW_WIDTH = 900;
	/**
	 * The default window height.
	 */
	protected final static int WINDOW_HEIGHT = 730;
	/**
	 * The default popup width.
	 */
	protected final static int POPUP_WIDTH = 400;
	/**
	 * The default popup height.
	 */
	protected final static int POPUP_HEIGHT = 300;
	/**
	 * Default stylesheet applied to all screens.
	 */
	protected final static String DEFAULT_STYLESHEET = "application.css";

	/**
	 * The stage on which scenes from Screens will be set.
	 */
	protected static Stage primaryStage;

	/**
	 * The scene of this screen.
	 */
	protected Scene scene;

	/**
	 * The additional stylesheet for this screen. (Optional).
	 */
	protected String stylesheet;

	/**
	 * Gets the Scene of this Screen.
	 * 
	 * @return The Scene for this Screen.
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Checks whether the screen has an additional stylesheet.
	 * 
	 * @return True if there is a stylesheet, False otherwise.
	 */
	private boolean hasStylesheet() {
		return stylesheet != null;
	}

	/**
	 * Sets the primary stage for all Screens.
	 * 
	 * @param stage
	 *            The stage to be used.
	 */
	public static void setPrimaryStage(Stage stage) {
		primaryStage = stage;
	}

	/**
	 * Changes the Scene of the primary stage to the Scene of the Screen entered
	 * in the parameter.
	 * 
	 * @param screen
	 *            The screen containing the new Scene to be shown.
	 */
	public void switchScreen(Screen screen) {
		Scene s = screen.getScene();

		// Add default stylesheet
		s.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());

		// Add additional stylesheet (if it exists)
		if (hasStylesheet()) {
			s.getStylesheets().add(getClass().getResource(stylesheet).toExternalForm());
		}

		primaryStage.setScene(s);
	}

	/**
	 * Create a new popup window, that uses the Scene from the Screen entered as
	 * a parameter.
	 * 
	 * @param screen
	 *            The screen containing the new Scene to be shown.
	 */
	public void createPopup(Screen screen) {
		// Create additional stage for the popup.
		Stage popupStage = new Stage();

		// Can't interact with any other windows while popup is shown.
		popupStage.initModality(Modality.APPLICATION_MODAL);

		Scene s = screen.getScene();

		// Add default stylesheet
		s.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());

		// Add additional stylesheet (if it exists)
		if (hasStylesheet()) {
			s.getStylesheets().add(getClass().getResource(stylesheet).toExternalForm());
		}

		popupStage.setScene(s);
		popupStage.show();
	}
}
