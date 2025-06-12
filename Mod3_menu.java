/* runModule3
 * runs the Heap-based Parcel Scheduling
 * extract_priority prints the extracted priority and updates the heap status
 * insert basically insert the customer into the heap
 */

import java.util.*;

public class Mod3_menu
{
	static Heap heap = new Heap(1000);

	public static void runModule3()
	{
		// load graph with sample inputs from edges.csv and nodes.csv
		Graph g = Mod1_menu.load_graph();

		// load hash table with sample inputs from customer.csv
		hashTable h = Mod2_menu.load_hashTable();

		Scanner sc = new Scanner(System.in);
		boolean running = true;

		// Insert all customers in sample customer.csv file
		hashEntry[] hashArray = h.getHashArray();

		for (int i = 0; i < hashArray.length; i++)
		{
			if (hashArray[i].getState().equals("used"))
			{
				//get customer info at index i in hash table and cast to customer
				customer c = (customer) hashArray[i].getValue();

				String deliverStatus = c.getDeliveryStatus();
				System.out.println("\nDelivery Status: " + deliverStatus);

				if (!deliverStatus.equals("Delivered")) 
				{
					// This entry has Status != Delivered
					// Insert active customers (In transit / Delayed)
					String customerID = c.getId();
					String from = c.getFrom();
					String to = c.getTo();
					System.out.println("Trying to insert to heap: Customer ID: " + customerID + " | " + from + " >> " + to);	

					// check if there exist an edge between "from" and "to"
					int deliveryTime = g.shortestPath(from,to);
					if (deliveryTime == -1)
					{
						// There is ho path between "from" and "to"
						System.out.println("There is no path between " + from +  " and " + to);
						System.err.println("Cannot insert to heap -- SKIP this customer's transaction");
 					}
					else 
					{
						// there is connection between these 2 nodes
						System.out.println("Delivery time: " + deliveryTime);

						int priority = c.getPriorityLevel();

						DeliveryRequest delivery = new DeliveryRequest(customerID, deliveryTime, priority);
						insert(priority,deliveryTime,delivery,customerID);
					} 
				} 
				else 
				{
					// Skip - only insert active customers (Intransit / Delayed)
					System.out.println("Skip -- Customer ID: " + c.getId());
				}
			}
		}

		while (running)
		{
			try
			{
				System.out.println();
                System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈・୨ ✦ ୧・┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
				System.out.println("\tWhat would you like to do ");
				System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
				System.out.println("1. Insert new delivery request");
				System.out.println("2. Extract Priority");
				System.out.println("3. Exit");
				System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
				System.out.print("Your choice: ");
				int input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				switch(input)
				{
					case 1:
						System.out.print("Please insert customer ID: ");
						String customerID = sc.nextLine();

						System.out.print("Please insert delivery time: ");
						int deliveryTime = sc.nextInt();

						System.out.print("Please insert priority level [1(highest) -5(lowest)] of the delivery: ");
						int priority = sc.nextInt();

						DeliveryRequest delivery = new DeliveryRequest(customerID,deliveryTime,priority);
						insert(priority, deliveryTime, delivery, customerID);
						break;

					case 2:
						extractPriority();
						break;

					case 3:
						System.out.println("Returning to main menu...");
						running = false;
						break; 

					default:
						System.out.println("Invalid input. Please enter a number from 1, 2 or 3 only.");
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Invalid input. Please enter a number from 1, 2 or 3 only.");
				sc.nextLine();
			}
		}
	}

	public static DeliveryRequest extractPriority()
	{
        HeapEntry value = heap.remove();

		if (value == null)
		{
			System.out.println("Heap is empty! No delivery to extract.");
			return null;
		}

        DeliveryRequest delivery = (DeliveryRequest) value.getValue();
        System.out.println("Prioriy extracted: ");
        System.out.println("Customer ID : " + delivery.getCustomerID());
        System.out.println("Delivery Time: " + delivery.getDeliveryTime());
        System.out.println("Priority Level: " + delivery.getPriorityLevel());
        System.out.println();
        System.out.println("State of current heap: ");
        heap.display();
        System.out.println();
        return delivery;
    }

	// insert customer to heap
	// delivery priority will be derived in this function 
	public static void insert(int custPriorityLevel, int deliveryTime, DeliveryRequest delivery, String custId)
	{
		// derive delivery priority
        int deliverPriority = (6-custPriorityLevel) + (1000/deliveryTime);

		System.out.println();
        System.out.println("Priority level for " + custId + ": " + deliverPriority);

		// insert into heap - delivery priority, delivery object, custID
        heap.add(deliverPriority, delivery, custId);

		System.out.println();
        System.out.println("State of current heap: ");

		heap.display();
        System.out.println();
    }
}
