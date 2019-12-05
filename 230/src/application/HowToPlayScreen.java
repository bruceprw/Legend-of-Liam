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
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
/**
 * Generates a screen that allows the user to navigate
 * through images that are informative about the controls/gameplay
 * @author User
 *
 */
public class HowToPlayScreen extends Screen {
	/**
	 * Width of the HowToPlayScreen.
	 */
	protected final static int HOW_TO_PLAY_WIDTH = 700;
	/**
	 * Height of the HowToPlayScreen.
	 */
	protected final static int HOW_TO_PLAY_HEIGHT = 700;
	
	ArrayList<String> texts = new ArrayList<String>();
	private int pageIndex = 0;
	
	BorderPane root;
	ImageView view;
	Text displayText;
	Text buttonPrompt;
	Button next;
	Button previous;
	
	/**
	 * Creates the information screen and sets the default image.
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("static-access")
	public HowToPlayScreen() throws FileNotFoundException {
		readFile();
		root = new BorderPane();
		
		view = new ImageView();
		view.setFitWidth(600);
		view.setFitHeight(500);
		view.setImage(new Image(new FileInputStream("Images\\How-To images\\" + pageIndex + ".png")));
		
		displayText = new Text(texts.get(pageIndex));
		displayText.setWrappingWidth(600);
		root.setPadding(new Insets(50, 50, 50, 50));
		
		root.setTop(view);
		root.setCenter(displayText);
		root.setAlignment(displayText, Pos.TOP_LEFT);
		
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
	
	/**
	 * This function is called whenever a key is pressed and 
	 * decides the appropriate action to take. In this case
	 * in which way to navigate images.
	 * @param event This is the constant of the
	 * given key the user has pressed.
	 * @throws IOException Ensures that the system
	 * expects a potential error of this type such as
	 * a file not existing or file access being blocked.
	 */
	private void keyPressed(KeyEvent event) throws IOException

	{
		switch (event.getCode())
		{
		case LEFT:
			if (pageIndex > 0) {
				pageIndex--;
				displayText.setText(texts.get(pageIndex));
				view.setImage(new Image(new FileInputStream("Images\\How-To images\\" + pageIndex + ".png")));
			}
			break;

		case RIGHT:
			if (pageIndex < texts.size() - 1) {
				pageIndex++;
				displayText.setText(texts.get(pageIndex));
				view.setImage(new Image(new FileInputStream("Images\\How-To images\\" + pageIndex + ".png")));
			}
			break;

		default:
			break;
		}
		// Consume key press event so that arrow keys don't interact with Buttons.
		event.consume();
	}
	
	/**
	 * Gets the descriptions that correspond to the how to images.
	 * @throws FileNotFoundException
	 */
	private void readFile() throws FileNotFoundException {
		File file = new File("Images\\How-To images\\text for the how to sections.txt");
		Scanner in = new Scanner(file);
		in.useDelimiter("#");
		
		while (in.hasNext()) {
			in.next();
		
			if (in.hasNext()) {
				//texts.add(in.next());
				texts.add(readPage(new Scanner(in.next())));
			}
			
		}
		
		in.close();
	}
	/**
	 * Splits up the descriptions into discrete sub headings.
	 * @param page
	 * @return
	 */
	private String readPage(Scanner page) {
		page.useDelimiter("//");
		
		String s = "";
		
		while (page.hasNext()) {
			s += page.next();
			
			if (page.hasNextLine()) {
				page.nextLine();
				
			}
		}
		
		return s;
	}
}
