/* hashTable.java
 * has the put (to insert), get (to retrive), remove (to delete) function for the hash table,
 * hash function is to change String key into array index,
 * resize is to change the size of the hash table to fits it current needs
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class hashTable
{
	private hashEntry[] hashArray;
	private int count;

	//constructor method
	public hashTable(int n)
	{
		int size = nextPrime(n); 
		hashArray = new hashEntry[size];
		count = 0 ;
		for (int i = 0 ; i < size; i++)
		{
			hashArray[i] = new hashEntry();
		}
	}

	public hashEntry[] getHashArray() 
	{
		return hashArray;
	}

	//insert
	public void put(String inKey , customer inValue)
	{
		if (getLoadFactor() > 0.7)
		{
			resize((hashArray.length*2));
			
		}
		int index = findEmpty(inKey);
		hashArray[index] = new hashEntry(inKey,inValue);
		count++;
	}

	//search
	public Object get(String inKey)
	{
		int index=  findKey(inKey);
		if (index == -1)
		{
			return null; 
		}
		return hashArray[index].getValue();

	}

	//delete
	public void remove(String inKey)
	{
		int index = findKey(inKey);
		if (index == -1)
		{
			return; 
		}
		hashArray[index] = new hashEntry();
		hashArray[index].setState("previously-used");
		count--;
		if (getLoadFactor() < 0.3)
		{
			resize(hashArray.length /2);
		}
	}

	private int hash(String key)
	{
		int hashVal = 0 ;
		//change key string into array index
		for (int i = 0 ; i < key.length();i++)
		{
			hashVal = (hashVal * 31 + key.charAt(i)) % hashArray.length;
		}
		return hashVal;
	}

	private int findEmpty(String inKey)
	{
		int index = hash(inKey);
		int originalIndex = index;
		while(hashArray[index] != null && hashArray[index].getState() == "used" && !hashArray[index].getKey().equals(inKey))
		{
			index = (index + 1 ) % hashArray.length;
		}
		if (index != originalIndex)
		{
			System.out.println("Collision detected for key " + inKey + " at index " + originalIndex + " resolved to index " + index);
		}
		return index;
	}

	private int findKey(String inKey)
	{
		int index = hash(inKey);
		int oriIndex = index;;
		while (hashArray[index].getState() != "free")
		{
			if(hashArray[index].getState() == "used" && hashArray[index].getKey().equals(inKey))
			{
				return index;
			}
			index = (index + 1) % hashArray.length;
			if (index == oriIndex) 
			{
				break;
			}
		}
		return -1;
	}

	public void resize(int newSize)
	{
		int newPrimeSize = nextPrime(newSize);
		hashEntry[] oldArray = hashArray;
		hashArray = new hashEntry[newPrimeSize];
		count = 0 ;
		for (int i = 0 ; i < newPrimeSize; i ++)
		{
			hashArray[i] = new hashEntry();
		}
		for (hashEntry entry : oldArray)
		{
			if (entry.getState() == "used")
			{
				put(entry.getKey(),entry.getValue());
			}
		}
	}

	//find the next prime number
	private int nextPrime(int num)
	{
		while(true)
		{
			if(isPrime(num))
			{
				return num;
			}
			num++;
		}
	}

	private boolean isPrime(int num)
	{
		if (num < 2)
		{
			return false;

		}
		for (int i = 2; i <= Math.sqrt(num); i++)
		{
			if(num % i == 0 )
			{
				return false;
			}
		}
		return true;
	}

	private double getLoadFactor()
	{
		return (double) count / hashArray.length;
	}

	//read the customer csv
	public void readFile(String fileName)
	{
		//initialise
		FileInputStream fileStream = null;
		InputStreamReader isr;
		BufferedReader bufRdr;
		String line;
		
		try
		{
			//import the csv file
			fileStream = new FileInputStream(fileName);
			isr = new InputStreamReader(fileStream);
			bufRdr = new BufferedReader(isr);
			bufRdr.readLine(); //to skip first line in csv file

			while ((line = bufRdr.readLine()) != null)
			{
				String[] data;
				//Split the line based on ','
				data = line.split(",");

				//assigning each info into their variable
				String id = data[0];
				String name = data[1];
				String address = data[2];
				int priorityLevel = Integer.parseInt(data[3]);
				String deliveryStatus = data[4];
				String from = data[5];
				String to = data[6];

				//creating customer object using values from the CSV file
				customer Customer = new customer(id, name, address, priorityLevel, deliveryStatus, from, to);

				//store the customer object using its ID as the key
				put(id,Customer);
			}
			fileStream.close();
		} 
		catch(IOException e)
		{
			System.out.println("Error opening this file");
		}
	}

	//add lines to the CSV file
	public void writeToCSV(String fileName)
	{
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		try
		{
			fileStrm = new FileOutputStream("customer.csv");
			pw = new PrintWriter(fileStrm);
			
			//writes line to file
			pw.println("customerID,name,address,priorityLevel,deliveryStatus,From,To");

			//loop through the hash table
			for (int i = 0; i < hashArray.length; i++)
			{
				hashEntry entry = hashArray[i];
				if (entry != null && entry.getState().equals("used"))
				{
					//cast value to customer to access fields
					customer cust = (customer) entry.getValue();

					//writing customer details
					pw.println(cust.getId() + "," + cust.getName() + "," + cust.getAddress() + "," + cust.getPriorityLevel() + "," + cust.getDeliveryStatus()  + "," + cust.getFrom() + "," + cust.getTo());
				}
			}
			pw.close();
		}
		catch (IOException e)
		{
			System.out.println("Error in writing to file: " + e.getMessage());
		}
	}

	public void printCustomer()
	{
		for (int i = 0; i < hashArray.length; i++)
		{
			if (hashArray[i].getState().equals("used"))
			{
				//get customer info at index i in hash table and cast to customer
				customer c = (customer) hashArray[i].getValue();

				System.out.println("-------------------------------");
				System.out.println("Customer ID: " + c.getId());
				System.out.println("Name: " + c.getName());
				System.out.println("Address: " + c.getAddress());
				System.out.println("Priority Level: " + c.getPriorityLevel());
				System.out.println("Delivery Status: " + c.getDeliveryStatus());
				System.out.println("From: " + c.getFrom());
				System.out.println("To: " + c.getTo());
				System.out.println("-------------------------------");
			}
		}
	}
}