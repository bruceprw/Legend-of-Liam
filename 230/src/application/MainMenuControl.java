package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuControl
{
	@FXML
	private Button loadG;
	@FXML
	private Button select;
	@FXML
	private Button leader;
	@FXML
	private Button logout;
	private UserProfile currentUser;
	
	
	
	@FXML
	public void initialize()
	{
		currentUser=Sample.getCurrentUser();
	}
	
	@FXML
	public void handleLoad(ActionEvent event)
	{
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new GameScreen(currentUser.getName(),currentUser).getScene());
	}
	
	@FXML
	public void handleSelect(ActionEvent event)
	{
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new LevelScreen(currentUser).getScene());
	}
	
	@FXML 
	public void handleLeader(ActionEvent event)
	{
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new LeaderboardsScreen("1").getScene());
	}
	
	@FXML
	public void handleLogout(ActionEvent event)
	{
		
	}
	
}
