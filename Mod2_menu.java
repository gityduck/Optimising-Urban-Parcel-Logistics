/* runModule2
 * runs the Heap-Based Customer Lookup 
 * load_hashTable basically initialise it and read the customer.csv
 */
import java.util.*;

public class Mod2_menu 
{
    public static hashTable load_hashTable()
    {
        hashTable table = new hashTable(20);
        table.readFile("customer.csv");

        return table;
    }

    //main
	public static void runModule2()
    {
        hashTable table = load_hashTable();

		Scanner sc = new Scanner(System.in);
		boolean running = true;
		int option;

		while (running)
		{
            try
            {
                System.out.println();
                System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈・୨ ✦ ୧・┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
                System.out.println("\tLookup Customer Menu");
                System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
                System.out.println("1. View All Customers");
                System.out.println("2. Add New Customer");
                System.out.println("3. Search Customer by ID");
                System.out.println("4. Delete Customer");
                System.out.println("5. Exit");
                System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
                System.out.print("Your option: ");
                option = sc.nextInt();
                sc.nextLine();
                System.out.println();

                switch (option)
                {
                    case 1:
                        System.out.println("Customers information: ");
                        table.printCustomer();
                        break;

                    case 2:
                        System.out.print("Enter ID (Cxxx): ");
                        String id = sc.nextLine();

                        System.out.print("Enter name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter address: ");
                        String address = sc.nextLine();

                        System.out.print("Enter priority level (1-5): ");
                        int priorityLevel = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter delivery status (Delivered, In Transit, Delayed): ");
                        String deliveryStatus = sc.nextLine();

                        System.out.print("Enter From: ");
                        String from = sc.nextLine();

                        System.out.print("Enter To: ");
                        String to = sc.nextLine();

                        System.out.println();
                        
                        customer newCustomer = new customer(id, name, address, priorityLevel, deliveryStatus, from, to);
                        if (table.get(id) != null)
                        {
                            System.out.println("customer ID " + id + " already existed. Please use another ID");
                        }
                        else
                        {
                            table.put(id, newCustomer);
                            table.writeToCSV("customer.csv");
                            System.out.println("Customer added.");
                        }
                        break;			

                    case 3:
                        System.out.print("Enter the customer's ID you want to search (Cxxx): ");
                        id = sc.nextLine();
                        //retrive customer from the table using given ID
                        customer found = (customer) table.get(id);
                        if (found != null)
                        {
                            System.out.println("-------------------------------");
                            System.out.println("Customer ID: " + found.getId());
                            System.out.println("Name: " + found.getName());
                            System.out.println("Address: " + found.getAddress());
                            System.out.println("Priority Level: " + found.getPriorityLevel());
                            System.out.println("Delivering From: " + found.getFrom());
                            System.out.println("Delivering To: " + found.getTo());
                            System.out.println("Delivery Status: " + found.getDeliveryStatus());
                            System.out.println("-------------------------------");
                        }
                        else
                        {
                            System.out.println("Customer not found. Invalid ID.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter the customer's ID you want to delete (Cxxx): ");
                        id = sc.nextLine();

                        //find if the customer id exist or not
                        customer customerToDelete = (customer) table.get(id);
                        if (customerToDelete != null)
                        {
                            table.remove(id);
                            table.writeToCSV("customer.csv");
                            System.out.println("Customer deleted");
                        }
                        else
                        {
                            System.out.println("Customer not found. Cannot delete.");
                        }
                        break;

                    case 5:
                        System.out.println("Returning to main menu...");
                        running = false;
                        break;
                    
                    default:
                        System.out.println("Please enter 1, 2, 3, 4 or 5 only.");
                }
            }
            catch (Exception e) 
            {
                System.out.println("Invalid input. Please enter a number from 1, 2, 3, 4 or 5 only.");
				sc.nextLine();
            }
		}
	}
}