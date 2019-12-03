package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class HowToPlayScreen extends Screen {
	ArrayList<String> texts = new ArrayList<String>();
	private int textIndex = 0;
	
	BorderPane root;
	ImageView view;
	Text displayText;
	Text buttonPrompt;
	Button next;
	Button previous;
	
	public HowToPlayScreen() throws FileNotFoundException {
		readFile();
		root = new BorderPane();
		
		view = new ImageView();
		view.setFitWidth(600);
		view.setFitHeight(500);
		view.setImage(new Image(new FileInputStream("Images\\updateimage\\titlescreenimage.jpg")));
		
		displayText = new Text(texts.get(textIndex));
		displayText.setWrappingWidth(600);
		root.setPadding(new Insets(50, 50, 50, 50));
		
		root.setTop(view);
		root.setCenter(displayText);
		
		scene = new Scene(root, HOW_TO_PLAY_WIDTH, HOW_TO_PLAY_HEIGHT);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->
		{
			try
			{
				keyPressed(event);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
	}
	
	private void keyPressed(KeyEvent event) throws IOException

	{
		switch (event.getCode())
		{
		case LEFT:
			// TODO: Change image
			
			if (textIndex > 0) {
				textIndex--;
				displayText.setText(texts.get(textIndex));
			}
			break;

		case RIGHT:
			// TODO: Change image
			
			if (textIndex < texts.size() - 1) {
				textIndex++;
				displayText.setText(texts.get(textIndex));
			}
			break;

		default:
			break;
		}
		// Consume key press event so that arrow keys don't interact with Buttons.
		event.consume();
	}
	
	private void readFile() throws FileNotFoundException {
		File file = new File("Images\\How-To images\\text for the how to sections.txt");
		Scanner in = new Scanner(file);
		in.useDelimiter("#");
		
		while (in.hasNext()) {
			in.next();
			
			if (in.hasNext()) {
				texts.add(in.next());
			}
			
		}
		
		in.close();
	}
}
