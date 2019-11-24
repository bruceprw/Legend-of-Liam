package application;

import java.io.FileNotFoundException;

import cell.Cell;
import cell.Item;
import javafx.scene.canvas.GraphicsContext;

public class Empty extends Cell
{
	public Empty()
	{
		super(false, true, true, "", Item.NONE);
		// TODO Auto-generated constructor stub
	}

	public void draw(GraphicsContext gc,int x, int y)
	{
		
	}
}
