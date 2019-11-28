package application;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import application.LevelTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class LeaderboardsScreen extends Screen {
	private static final double LEVEL_BUTTON_SIZE = 50;
	private static final int BTNS_PER_ROW = 5;
	private static final double GRID_H_GAP = 30;
	private static final double GRID_V_GAP = 20;
	private static final double GRID_PADDING_X = 50;
	private static final double GRID_PADDING_TOP = 50;

	private BorderPane root;
	private HBox centerBox;
	private VBox scrollBox;
	private ScrollPane levels;
	private GridPane levelGrid;
	private TableView<LevelTime> leaderboard;
	private ArrayList<LevelTime> list;
	private ArrayList<Button> levelButtons;
	private Button back;
	private static Leaderboard ld;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

	public LeaderboardsScreen(String level){
		screenInit(level);
	}
	private void screenInit(String level) {
		System.out.println(level);
		ld=new Leaderboard(level);
		list=ld.getList();
		root = new BorderPane();
		centerBox = new HBox();
		scrollBox = new VBox();
		levels = new ScrollPane();
		levelGrid = new GridPane();
		levelButtons = new ArrayList<Button>();

		back = new Button("Back");

		for (int i = 0; i < 10; i++) {
			Button b = new Button("" + (i + 1));
			b.setMinSize(LEVEL_BUTTON_SIZE, LEVEL_BUTTON_SIZE);
			b.setOnAction(event ->
			{
				System.out.println(b.getText());
				switchScreen(new LeaderboardsScreen(b.getText()));
				System.out.println(ld.getList());
			});			
			levelButtons.add(b);
			levelGrid.add(b, i % BTNS_PER_ROW, i / BTNS_PER_ROW);
			
			// TODO
		}
		
		levelGrid.setHgap(GRID_H_GAP);
		levelGrid.setVgap(GRID_V_GAP);
		levelGrid.setGridLinesVisible(true);
		levelGrid.setPadding(new Insets(10));
		
		centerBox.setStyle("-fx-box-border: transparent");

		buildAdd();

		scrollBox.getChildren().add(levels);
		levels.setContent(levelGrid);
		centerBox.getChildren().addAll(scrollBox, leaderboard);
		centerBox.setAlignment(Pos.CENTER);
		root.setCenter(centerBox);
		root.setBottom(back);

		scrollBox.setAlignment(Pos.CENTER);
		levelGrid.setAlignment(Pos.BASELINE_CENTER);

		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}


	private void buildAdd() {
		buildTable();
		addItems();
	}
	private void buildTable() {
		leaderboard = new TableView<LevelTime>();
		leaderboard.setMinWidth(WINDOW_WIDTH/2);
		leaderboard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		leaderboard.setId("leaderboard");

		TableColumn<LevelTime, Integer> rank = new TableColumn<LevelTime, Integer>("Rank");
		rank.setCellValueFactory(new PropertyValueFactory<LevelTime, Integer>("Rank"));
		rank.setMinWidth(40);
		rank.setMaxWidth(40);
		
		TableColumn<LevelTime, String> username = new TableColumn<LevelTime, String>("Username");
		username.setCellValueFactory(new PropertyValueFactory<LevelTime, String>("Username"));

		TableColumn<LevelTime, String> time = new TableColumn<LevelTime, String>("Time");
		time.setCellValueFactory(new PropertyValueFactory<LevelTime, String>("StringTime"));
		

		//let's not use the rank. the list is sorted by insertion sort. so there is no reason to show an extra"rank" column...
		
		leaderboard.getColumns().addAll(rank, username, time);
	}
	
	private void addItems() {
		// TODO: Load list of users function.

		// E.g., Delete when you need to
		
		for(int i=0;i<ld.getList().size();i++)
		{
			leaderboard.getItems().add(ld.getList().get(i));
		}
	}
}