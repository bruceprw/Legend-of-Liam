package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Sample
{
	private static UserProfile currentUser;
	
	
	@FXML
	public Label MOTDE;

	@FXML
	public Label usernameLabel;

	@FXML
	public Label passwordLabel;

	@FXML
	public TextField usernameText;

	@FXML
	public TextField passwordText;

	@FXML
	public Button button;

	@FXML
	public Label enterLabel;

	public void login()
	{

	}

	@FXML
	public void initialize()
	{

		FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), MOTDE);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		MOTDE.setText(DailyMessage.getMessage());
		MOTDE.setTextAlignment(TextAlignment.CENTER);
		TranslateTransition moveUP = new TranslateTransition(Duration.seconds(2), MOTDE);
		moveUP.setByY(-50);

		FadeTransition f1 = new FadeTransition(Duration.seconds(2), usernameLabel);
		f1.setFromValue(0);
		f1.setToValue(1);
		TranslateTransition m1 = new TranslateTransition(Duration.seconds(2), usernameLabel);
		m1.setByY(-50);

		FadeTransition f2 = new FadeTransition(Duration.seconds(2), passwordLabel);
		f2.setFromValue(0);
		f2.setToValue(1);
		TranslateTransition m2 = new TranslateTransition(Duration.seconds(2), passwordLabel);
		m2.setByY(-50);

		FadeTransition f3 = new FadeTransition(Duration.seconds(2), usernameText);
		f3.setFromValue(0);
		f3.setToValue(1);
		TranslateTransition m3 = new TranslateTransition(Duration.seconds(2), usernameText);
		m3.setByY(-50);

		FadeTransition f4 = new FadeTransition(Duration.seconds(2), passwordText);
		f4.setFromValue(0);
		f4.setToValue(1);
		TranslateTransition m4 = new TranslateTransition(Duration.seconds(2), passwordText);
		m4.setByY(-50);

		FadeTransition f5 = new FadeTransition(Duration.seconds(2), button);
		f5.setFromValue(0);
		f5.setToValue(1);
		TranslateTransition m5 = new TranslateTransition(Duration.seconds(2), button);
		m5.setByY(-50);

		FadeTransition f6 = new FadeTransition(Duration.seconds(2), enterLabel);
		f6.setFromValue(0);
		f6.setToValue(1);
		TranslateTransition m6 = new TranslateTransition(Duration.seconds(2), enterLabel);
		m6.setByY(-50);

		ParallelTransition sq = new ParallelTransition(fadeIn, moveUP, f1, f2, f3, f4, f5, f6, m1, m2, m3, m4, m5, m6);
		sq.play();

	}

	@FXML
	public void handleEnterAction(KeyEvent event) throws IOException
	{
		boolean found = false;
		if(event.getCode().equals(KeyCode.ENTER))
		{
			found = false;

			File f = new File("UserProfiles\\profiles.txt");
			Scanner s = null;
			try
			{
				s = new Scanner(f);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}

			while (s.hasNext())
			{
				Scanner line = new Scanner(s.nextLine());
				line.useDelimiter(",");
				String nextUser = line.next();
				String nextPass = line.next();
				int nextProgress = line.nextInt();

				if(usernameText.getText().equals(nextUser) && passwordText.getText().equals(nextPass))
				{
					currentUser = new UserProfile(nextUser, nextPass, nextProgress);
					//root.setCenter(menuPane);
					found = true;

					//loginPrompt.setText("Please enter your login details.");
					usernameText.setText("");
					passwordText.setText("");

					//welcome.setText("Welcome " + currentUser.getName() + "!");
				}
				line.close();
			}

			if(!found)
			{
				//loginPrompt.setText("User not found. Please try again.");
				usernameText.setText("");
				passwordText.setText("");
			}
		}
		
		if(found)
		{
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		}
			
	}
	
	@FXML
	public void handleButtonAction(ActionEvent event)
	{
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		dialog.setScene(new NewUserScreen().getScene());
		dialog.show();
	}
	
	public static UserProfile getCurrentUser()
	{
		return currentUser;
	}
	
}
