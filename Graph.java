/* 
 * Graph.java initialise the node list,
 * contains : 
 * addNode function with label one,
 * addVertex function with given label and value to store data,
 * addEdge function that adds the weight between two nodes,
 * hasNode function which check if the node given exist or not,
 * getNodeCount function which returns the number of nodes in the graph.
 * getEdgeCount function which returns the number of edges in the graph and divide by 2 because of it's an undirected node,
 * getAdjacent function gets the neighbouring nodes
 * displayAsList prints out the adjencency of each nodes
 * breathFirstSearch traverse through the graph and print it starting from the given label
 * detectCycleDFS checks if any nodes loop back using DFSCycleChecker
 * shortestPath, one accepts 1 parameter while the other accepts 2 which will then be use in module 3
 */
public class Graph
{
	//list of nodes in the graph
	private LinkedList nodes; 	

	public Graph()
	{
		//initialise node list
		nodes = new LinkedList();
	}

	//add a new node with only a label, no connection
	public void addNode(String label)
	{
		if (!hasNode(label))
		{
			nodes.insertLast(new Node(label));
		}
	}

	//add a new node with given label and value to store data
	public void addVertex(String label, Object value)
	{
		if (!hasNode(label))
		{
			nodes.insertLast(new Node(label,value));
		}
	}

	//add an undirected edge between two nodes
	public void addEdge(String label1, String label2, int weight)
	{
		Node node1 = getNode(label1);
		Node node2 = getNode(label2);

		if(node1 != null && node2 != null)
		{
			node1.addEdge(node2, weight);
			node2.addEdge(node1, weight);
		}
	}

	//check if the node with given label exist or not
	public boolean hasNode(String label)
	{
		return getNode(label) != null;
	}

	//return number of nodes in the graph
	public int getNodeCount()
	{
		return nodes.size();
	}
	
	//return number of edges in the graph
	public int getEdgeCount()
	{
		int count = 0 ;		
		ListNode cur = nodes.peekFirst();
		while(cur != null)
		{
			count += ((Node) cur.getValue()).getList().size();
			cur = cur.getNext();
		}
		//its undirected so divide it by 2
		return count / 2;
	}

	//retrieve a node object by its label
	public Node getNode(String label)
	{
		ListNode cur = nodes.peekFirst();
		while (cur != null)
		{
			Node node = (Node) cur.getValue();
			if (node.getLabel().equals(label) )
			{
				return ((Node) cur.getValue());
			}
			cur = cur.getNext();
		}
		return null;
	}

	//get the adjacency list of a node by label
	public LinkedList getAdjacent(String label) 
	{
		Node node = getNode(label);
		if (node != null)
		{
			return node.getList();
		}
		return null;
	}

	//display the graph as an adjacency list
	public void displayAsList() 
	{
		if (nodes != null){
			ListNode cur = (ListNode) nodes.peekFirst();

			while (cur != null) 
			{
				Node node = (Node) cur.getValue();
				System.out.print(node.getLabel() + " -> ");

				ListNode adjacent = (ListNode) node.getList().peekFirst();
				while (adjacent != null) 
				{
					Edge edge = (Edge) adjacent.getValue();
					Node adjacency = edge.getDestination();
					int weight = edge.getWeight();
					
					System.out.print(adjacency.getLabel() + "(" + weight + " minutes) ");
					adjacent = adjacent.getNext();
				}
				System.out.println();
				cur = cur.getNext();
			}
		}
	}

	//perform BFS starting from the given label
	public void breadthFirstSearch(String startLabel)
	{
		//get starting node
		Node startNode = getNode(startLabel);

		//check whether startNode exist
		if (startNode == null)
		{
			System.out.println("Node not found.");
			return;
		}

		//track visited nodes
		LinkedList visited = new LinkedList();
		//use queue for BFS
		Queue queue = new Queue();
		//stores level info
		LinkedList levels = new LinkedList();

		visited.insertLast(startNode);
		queue.enqueue(startNode);
		levels.insertLast(0); //start node is set as level 0

		while (!queue.isEmpty())
		{
			Node cur = (Node) queue.dequeue();
			//get and remove corresponding level
			int curLevel = (int) levels.removeFirst();
			System.out.println(cur.getLabel() + " - Level " + curLevel);

			//get adjacency list of current node
			LinkedList adjacents = cur.getList();
			if (adjacents != null)
			{
				//start from first adjacent in the list
				ListNode temp = (ListNode) adjacents.peekFirst();
				while (temp != null)
				{
					Edge edge = (Edge) temp.getValue();
					Node adjacent = edge.getDestination();
					if (!visited.find(adjacent))
					{
						visited.insertLast(adjacent);
						queue.enqueue(adjacent);
						levels.insertLast(curLevel + 1); //proceed to next level
					}
					temp = temp.getNext();
				}
			}
		}
		System.out.println();
	}

	//detect cycle using DFS
	public void detectCycleDFS(String startLabel) 
	{
		Node startNode = getNode(startLabel);
		if (startNode == null) 
		{
			System.out.println("Node not found");
			return;
		}

		//track visited nodes
		LinkedList visitedList = new LinkedList();
		boolean hasCycle = dfsCycleChecker(startNode, visitedList, null);

		if (hasCycle)
		{
			System.out.println("Cycle detected!");
		}
		else
		{
			System.out.println("No cycle detected.");
		}
	}

	//DFS checker with parent tracking
	private boolean dfsCycleChecker(Node current, LinkedList visited, Node parent)
	{
		visited.insertLast(current);

		LinkedList adjacents = current.getList();
		ListNode temp = adjacents.peekFirst();

		while (temp != null)
		{
			Edge edge = (Edge) temp.getValue();
			Node adjacent = edge.getDestination();

			if (!visited.find(adjacent))
			{
				//not visited
				if (dfsCycleChecker(adjacent, visited, current))
				{
					return true;
				}
			}
			else if (adjacent != parent)
			{
				//visited and not parent
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	// find the shortest path
	//
	// This function has two purposes:
	// Purpose 1: for printing all possible paths - required by Mod 1
	// Purpose 2: returns the edge weight (delivery time) - if there is an edge
	//			  that exists, between the startLabel and endLabel
	//
	// Method: Use function overloading to cater for single parameter, and two parameters
	//
	public int shortestPath(String startLabel) 
	{
		// call the other overloaded function with endLabel=0 
		// for the purpose of Mod 1 - printing only
		return shortestPath(startLabel, null);
	}
	public int shortestPath(String startLabel, String endLabel)
	{
		boolean flagPrint;
		int deliveryTime;

		if (endLabel==null) 
		{
			// Mod 1 use: just print 
			// System.out.println("\n** calling shortestPath() by Mod 1 with startLabel=" + startLabel + " only **\n");
			flagPrint = true;
		}
		else 
		{
			// System.out.println("\n## calling shortPath() by Mod 3 with startLabel=" + startLabel + " and endLabel=" + endLabel + " ## \n");
			flagPrint = false;
		}

		int size = getNodeCount();
		String[] labels = new String[size]; //array to store node labels
		int[] distances = new int[size]; //array to store shortest distances from the start
		boolean[] visited = new boolean[size];
		String[] previous = new String[size]; //store prev nodes for path recording

		//initialise
		ListNode curr = nodes.peekFirst(); //start from first node
		int index = 0;
		while (curr != null)
		{
			Node node = (Node) curr.getValue();
			labels[index] = node.getLabel();

			//set start node as 0, and any other node as maximum
			distances[index] = (node.getLabel().equals(startLabel)) ? 0 : Integer.MAX_VALUE;
			visited[index] = false; //mark all nodes as unvisited
			curr = curr.getNext();
			index++;
		}

		//using Dijkstra's algorithm
		for (int i = 0; i < size; i++)
		{
			int minIndex = getMinDistance(distances, visited); //get the unvisited node with the smallest distance
			//break if remaining nodes are unreachable
			if (minIndex == -1)
			{
				break;
			}

			visited[minIndex] = true; 
			Node node = getNode(labels[minIndex]); //get the actual node object

			//traverse all adjacent nodes
			ListNode adjacentNode = node.getList().peekFirst();
			while (adjacentNode != null)
			{
				Edge edge = (Edge) adjacentNode.getValue();
				Node adjacent = (Node) edge.getDestination();
				int adjacentIdx = getIndexOfNode(adjacent.getLabel(), labels);

				//update distance if a shorter path is found
				if (!visited[adjacentIdx] && distances[minIndex] != Integer.MAX_VALUE && distances[minIndex] + edge.getWeight() < distances[adjacentIdx])
				{
					distances[adjacentIdx] = distances[minIndex] + edge.getWeight();
					previous[adjacentIdx] = labels[minIndex];
				}
				adjacentNode = adjacentNode.getNext(); //move to next adjacent node
			}
		}

		// These arrays would have all the necessary information at this point
		// labels : contain all the possible end destinations
		// distances: contain respective "delivery time" (edge weight) associated with the labels
		//
		int endLabelIndex = findArrayIndex(labels, endLabel);
		if (endLabelIndex == -1) 
		{
			// endLabel does not exist in graph!!
			// return an invalid delivery time
			deliveryTime = -1;
		}
		else 
		{
			deliveryTime = distances[endLabelIndex];
		}


		// Code below is to:
		// construct display string used by Mod 1 to display all possible paths
		// from startLabel to all labels for display purpose
		//
		if (flagPrint) 
		{
			// only print when null, in the case of calls from Mod 1
			System.out.println("Shortest distance from " + startLabel + ":");
		}
		
		for (int i = 0; i < size; i++)
		{
			if (distances[i] == Integer.MAX_VALUE)
			{
				// max value denotes there is no path from the startLabel to the label[i]
				if (flagPrint)
				{
					// only print when null, in the case of calls from Mod 1
					System.out.println(startLabel + " -> " + labels[i] + " = No path");
				}
			}
			else
			{
				// there is path from startLabel to label[i]
				// reconstruct the path and print the distance
				String path = pathRecord(previous, labels, i);
				if (flagPrint) 
				{
					// only print when null, in the case of calls from Mod 1
					System.out.println(path + " = " + distances[i]);
				}
			}
		}
		/// returns delivery time for calls made from Mod 3
		/// Mod 1 will disregard this returned value
		return deliveryTime;
	}

	// reverse lookup on array to return the index to the target_value that is to
	// be looked up
	// returns -1 if the target value does not exist in the array
	private static int findArrayIndex(String array[], String target_value) 
	{
		for (int i=0; i<array.length; i++) 
		{
			if (array[i].equals(target_value)) 
			{
				// found, return the index
				return i;
			}
		}
		// target_value not found 
		return -1;
	}

	//find index of unvisited nodes with smallest distance
	private int getMinDistance(int[] distances, boolean[] visited)
	{
		int min = Integer.MAX_VALUE;
		int minIndex = -1;

		for(int i = 0; i < distances.length; i++)
		{
			if (!visited[i] && distances[i] < min)
			{
				min = distances[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	//get index of a node based on its label
	private int getIndexOfNode(String label, String[] labels)
	{
		for (int i = 0; i < labels.length; i++)
		{
			if (labels[i].equals(label))
			{
				return i;
			}
		}
		return -1;
	}

	//reconstruct the path from start node to current node
	private String pathRecord(String[] previous, String[] labels, int index)
	{
		if (previous[index] == null)
		{
			//start node
			return labels[index];
		}
		//get the previous node index
		int previousIndex = getIndexOfNode(previous[index], labels);
		//recursive build path
		return pathRecord(previous, labels, previousIndex) + " -> " + labels[index];
	}
}