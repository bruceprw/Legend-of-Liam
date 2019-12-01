package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SampleScreen extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		
		Scene scene = new Scene(root,900,730);
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Legend of Liam");
		primaryStage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
