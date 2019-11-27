package Character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.zip.DataFormatException;

import application.Element;
import cell.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;

/**
 * Smart targeting enemies uses BFS to find player and chase
 * 
 * @author Miles Singleton
 * @version 0.0
 */
public class SmartTargettingEnemy extends Enemy {
	private String path = "Images\\smart.jpg";
	private Image image;

	public SmartTargettingEnemy(int currentX, int currentY, String movDirection) throws FileNotFoundException {
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;
		this.movDirection = UP;

		setImage();
	}

	public SmartTargettingEnemy(int currentX, int currentY) throws FileNotFoundException {
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;

		setImage();
	}

	public Node findPath(Element[][] matrix, int x, int y, int playerX, int playerY) {
		// make queue
		// visited set
		// root add to queue/set

		// while queue is not empty
		// is the current x,y the player
		// yes return

		// no get adjacent (child) nodes
		// have they been visited
		// are they movable
		// yes - add to queue

		// with the shortest path return pair [1]
		// move to pair[1]

		Queue<Node> nodeQueue = new ArrayDeque<>();
		Node root = new Node(x, y, null);
		nodeQueue.add(root);

		int[] newXPositions = { 1, -1, 0, 0};
		int[] newYPositions = { 0, 0, 1, -1};

		// set of visited nodes, each value in a set is unique, more efficient then
		// boolean
		Set<String> visited = new HashSet<>();

		String key = root.x + "," + root.y;
		visited.add(key);

		while (!nodeQueue.isEmpty()) {
			// pop front node from queue and process it
			Node currentNode = nodeQueue.poll();
			// current node X and Y
			int cNX = currentNode.x;
			int cNY = currentNode.y;

			// return if player has been found
			if (cNX == playerX && cNY == playerY) {
				//refactor this
				return currentNode.getGoodNode(currentNode);
			}

			// find next node
			for (int k = 0; k < 4; k++) {
				x = cNX + newXPositions[k];
				y = cNY + newYPositions[k];

				if (this.isMovable((Cell)matrix[x][y])) {
					Node nextNode = new Node(x, y, currentNode);

					key = nextNode.x + "," + nextNode.y;

					// if it not visited yet
					if (!visited.contains(key)) {
						// add it to queue and mark as visited
						nodeQueue.add(nextNode);
						visited.add(key);
					}
				}

			}

		}
		// return null if path is impossible
		return null;

	}

	// Utility function to print path from source to destination
	public static int printPath(Node node) {
		if (node == null) {
			return 0;
		}
		int len = printPath(node.parent);
		System.out.print(node + " ");
		return len + 1;
	}

	private void RandomDirection() throws DataFormatException {
		int rand = (int) Math.random();
		switch (rand) {
		case (0):
			this.setMovDirection(UP);
			break;
		case (1):
			this.setMovDirection(DOWN);
			break;
		case (2):
			this.setMovDirection(LEFT);
			break;
		case (3):
			this.setMovDirection(RIGHT);
			break;
		default:
			throw new DataFormatException("Method is broken, find a beter exception subclass as this ones shit");
		}
	}

	public String getString() {
		return "SMART";
	}

	public void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}

	public void draw(GraphicsContext gc, int x, int y) {
		gc.drawImage(image, x, y, 100, 100);
	}
}

public class Node {
	// (x, y) represents coordinates of a cell in matrix
	int x, y;

	// maintain a parent node for printing final path
	Node parent;

	Node(int x, int y, Node parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	private Node getParent() {
		return this.parent;
	}
	
	private void setParent(Node node)
	{
		this.parent = node;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ')';
	}

	public Node getGoodNode(Node leafNode)
	{
		while(true) {
			if(leafNode.getParent().getParent() == null)
			{
				leafNode.setParent(null);
				return leafNode;
			}else {
				leafNode = leafNode.getParent();
			}
		}
	}
};
