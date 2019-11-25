package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class LevelScreen extends Screen {
	private static final double LEVEL_BUTTON_SIZE = 100;
	private static final int BTNS_PER_ROW = 4;
	private static final double GRID_H_GAP = 30;
	private static final double GRID_V_GAP = 20;
	private static final double GRID_PADDING_X = (WINDOW_WIDTH
			- ((BTNS_PER_ROW * LEVEL_BUTTON_SIZE) + (BTNS_PER_ROW - 1) * (GRID_H_GAP))) / 2;
	private static final double GRID_PADDING_TOP = 100;
	
	private BorderPane root;
	private ScrollPane levels;
	private GridPane grid;

	private ArrayList<Button> levelButtons;
	private Button back;
	private UserProfile user;

	public LevelScreen(UserProfile user) {
		this.user=user;
		root = new BorderPane();
		levels = new ScrollPane();
		grid = new GridPane();
		levelButtons = new ArrayList<Button>();
		back = new Button("Back");

		for (int i = 0; i < 20; i++) {
			int levelNo = i + 1;
			
			Button b = new Button("" + (levelNo));
			b.setOnAction(event -> {
				switchScreen(new GameScreen(levelNo,user));
			});
			
			b.setMinSize(LEVEL_BUTTON_SIZE, LEVEL_BUTTON_SIZE);
			levelButtons.add(b);
			grid.add(b, i % BTNS_PER_ROW, i / BTNS_PER_ROW);
		}

		grid.setPadding(new Insets(GRID_PADDING_TOP, 0, GRID_PADDING_X, GRID_PADDING_X));
		grid.setHgap(GRID_H_GAP);
		grid.setVgap(GRID_V_GAP);
		// grid.setGridLinesVisible(true);

		levels.setContent(grid);

		root.setCenter(levels);
		root.setBottom(back);

		back.setAlignment(Pos.BASELINE_LEFT);
		grid.setAlignment(Pos.BASELINE_CENTER);

		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
}