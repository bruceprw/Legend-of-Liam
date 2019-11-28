package Character;

public class Node
{
	// (x, y) represents coordinates of a cell in matrix
	int x, y;

	// maintain a parent node for printing final path
	Node parent;

	Node(int x, int y, Node parent)
	{
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	/**
	 * @return the x
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY()
	{
		return y;
	}

	private Node getParent()
	{
		return this.parent;
	}

	private void setParent(Node node)
	{
		this.parent = node;
	}

	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ')';
	}

	public Node getGoodNode(Node leafNode)
	{
		while (true)
		{
			if(leafNode.getParent().getParent() == null)
			{
				leafNode.setParent(null);
				return leafNode;
			}
			else
			{
				leafNode = leafNode.getParent();
			}
		}
	}

}
