/* runModule1 
 * runs the Graph-Based route planning 
 * load_graph basically initialise it and read the nodes.csv and edges.csv
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Mod1_menu
{
	public static Graph load_graph()
    {
		Graph g = new Graph();
        String filename1 = "nodes.csv";
		String filename2 = "edges.csv";
		readFile(filename1, filename2, g);

        return g;
    }

	public static void runModule1()
	{
		Scanner sc = new Scanner(System.in);
		Graph graph = load_graph();

		//declare variable
		int option;
		String label;
		boolean running = true;

		while(running)
		{
			try
			{
				System.out.println();
				System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈・୨ ✦ ୧・┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
				System.out.println("\tWhat would you like to do?");
				System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
				System.out.println("1. View Structure of graph");
				System.out.println("2. Breadth First Search");
				System.out.println("3. Check for cycles using Depth First Search");
				System.out.println("4. Find Shortest Path");
				System.out.println("5. Exit");;
				System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
				System.out.print("Your choice: ");
				option = sc.nextInt();	
				sc.nextLine();
				System.out.println();

				switch(option)
				{
					case 1:
						System.out.println("Structure of graph: ");
						graph.displayAsList();
						break;

					case 2:
						System.out.println("Please insert the node you would like to start from: ");
						System.out.print("BFS from node: ");
						label = sc.nextLine();
						graph.breadthFirstSearch(label);
						break;

					case 3:
						System.out.println("Please insert the node you would like to start from: ");
						System.out.print("DFS from node: ");
						label = sc.nextLine();
						graph.detectCycleDFS(label);
						break;

					case 4:
						System.out.println("Please insert the node you would like to start from: ");
						System.out.print("Shortest Path from node: ");
						label = sc.nextLine();
						// use dummy_var here and will be unused in Mod 1
						int dummy_var = graph.shortestPath(label);
						break;

					case 5:
						System.out.println("Returning to main menu...");
						running = false;
						break;

					default:
						System.out.println("Invalid input. Please enter a number from 1, 2, 3, 4 or 5 only.");
						break;
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("Invalid input. Please enter a number from 1, 2, 3, 4, 5 or 6 only.");
				sc.nextLine();
			}
		}
	}

	//read the nodes.csv
	public static void readFile(String fileName1, String fileName2, Graph graph)
	{
		//initialise
		FileInputStream fileStream1 = null, fileStream2 = null;
		InputStreamReader isr1, isr2;
		BufferedReader bufRdr1, bufRdr2;
		String line1, line2;
		
		try
		{
			//read first file
			//import the csv file
			fileStream1 = new FileInputStream(fileName1);
			isr1 = new InputStreamReader(fileStream1);
			bufRdr1 = new BufferedReader(isr1);
			bufRdr1.readLine(); //to skip first line in csv file

			while ((line1 = bufRdr1.readLine()) != null)
			{
				String[] node;
				//Split the line based on ','
				node = line1.split(",");

				//assigning each info into their variable
				String label = node[0];

				//add to graph
				graph.addNode(label);
			}
			fileStream1.close();

			//read second file
			//import the csv file
			fileStream2 = new FileInputStream(fileName2);
			isr2 = new InputStreamReader(fileStream2);
			bufRdr2 = new BufferedReader(isr2);
			bufRdr2.readLine(); //to skip first line in csv file

			while ((line2 = bufRdr2.readLine()) != null)
			{
				String[] edge;
				//Split the line based on ','
				edge = line2.split(",");

				//assigning each info into their variable
				String label1 = edge[0];
				String label2 = edge[1];
				int weight = Integer.parseInt(edge[2]);

				//add to graph
				graph.addEdge(label1, label2, weight);
			}
			fileStream2.close();
		} 
		catch(IOException e)
		{
			System.out.println("Error opening this file");
		}
	}
}