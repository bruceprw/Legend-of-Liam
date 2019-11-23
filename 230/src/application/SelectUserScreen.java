package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class SelectUserScreen extends Screen {
	private static final double TABLE_PADDING = 50;

	private BorderPane root;

	private TableView<User> userList;

	private HBox buttonsPane;
	private Button toTitleScreen;
	private Button loadUser;
	private Button deleteUser;

	public SelectUserScreen() {
		root = new BorderPane();

		buildTable();
		addItems();

		buttonsPane = new HBox();
		buttonsPane.setAlignment(Pos.BASELINE_LEFT);

		toTitleScreen = new Button("Back");
		toTitleScreen.setOnAction(event -> {
			switchScreen(new TitleScreen());
		});
		
		loadUser = new Button("Load Profile");
		loadUser.setOnAction(event -> {
			User user = userList.getSelectionModel().getSelectedItem();
			switchScreen(new LevelScreen(user));
		});
		
		deleteUser = new Button("Delete Profile");
		deleteUser.setOnAction(event -> {
			User user = userList.getSelectionModel().getSelectedItem();
			createPopup(new DeleteUserScreen(user));
		});
		
		buttonsPane.getChildren().addAll(toTitleScreen, loadUser, deleteUser);

		root.setCenter(userList);
		root.setBottom(buttonsPane);

		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	private void buildTable() {
		userList = new TableView<User>();

		TableColumn<User, String> username = new TableColumn<User, String>("Username");
		username.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));

		TableColumn<User, Integer> progress = new TableColumn<User, Integer>("Progress");
		progress.setCellValueFactory(new PropertyValueFactory<User, Integer>("LevelProgress"));

		TableColumn<User, String> save = new TableColumn<User, String>("Saved Level?");
		save.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<User, String> p) {
				boolean saveExists = p.getValue().getSave();
				if (saveExists) {
					return new SimpleStringProperty("Yes");
				} else {
					return new SimpleStringProperty("No");
				}
			}
		});
		
		userList.getColumns().addAll(username, progress, save);
		userList.setPadding(new Insets(TABLE_PADDING));
	}
	
	private void addItems() {
		// TODO: Load list of users function.
		
		// E.g., Delete when you need to
		userList.getItems().add(new User("username", 5, true));
		userList.getItems().add(new User("example", 10, false));
		userList.getItems().add(new User("aaron", 1, false));
	}
}
