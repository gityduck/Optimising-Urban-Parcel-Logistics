/* Node.java initialises the fields, create, 
 * constructor one with label, and one with both label and value
 * accessor
 * addEdge function to another node with weight
 * setVisited >> mark as visited - used in traversing the graph
 * getVisited >> check whether the node has been visited
 */

public class Node
{
	private String label; //label for nodes
	private Object value; //data the node may carry
	private LinkedList adjacent; //store Edge objects
	private boolean visited; //flag for traversal algorithm - BFS/DFS


	//constructor with label
	public Node(String inLabel)
	{
		label = inLabel;
		value = null;
		adjacent = new LinkedList();
		visited = false;
	}

	//constructor with label and value
	public Node(String inLabel, Object inValue)
	{
		label = inLabel;
		value = inValue;
		adjacent = new LinkedList();
		visited = false;
	}
	
	//accessor
	public String getLabel()
	{
		return label;
	}
	public Object getValue()
	{
		return value;
	}
	public LinkedList getList()
	{
		return adjacent;
	}

	//add edge to another node with weight
	public void addEdge(Node node, int weight)
	{
		Edge edge = new Edge (node, weight);
		adjacent.insertLast(edge);
	}

	//mark as visited - used in traversing the graph
	public void setVisited()
	{
		visited = true;
	}

	//clear visited flag
	public void clearVisited()
	{
		visited = false;
	}

	//check whether the node has been visited
	public boolean getVisited()
	{
		return visited;
	}
}