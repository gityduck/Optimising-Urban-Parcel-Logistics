/* LinkedList.java
 * insertFirst function which add the new node at the front of the list
 * insertLast adds at the end of the list
 * delete -> first node found with given value
 * peekFirst/peekLast -> look at the first or last node in the list
 * removeFirst/removeLast -> remove the first or last node in the list

 */

import java.util.*;

public class LinkedList
{
	//points to first and last node in the list
	private ListNode head;
	private ListNode tail; 

	//Constructor
	public LinkedList()
	{
		head = null;
		tail = null; 
	}

	//inserts a new node at beginning of list
	public void insertFirst(Object newValue)
	{
		ListNode newNd = new ListNode(newValue);
		if (isEmpty())
		{
			head = tail = newNd;
		}
		else 
		{
			newNd.setNext(head); //new head points to old head
			head.setPrev(newNd); //old head points to new node
			head = newNd; //head is updated to new node
		}
		newNd.setPrev(null); //make sure the new head has no previous node
	}

	//inserts a new node at the end of list
	public void insertLast(Object newValue)
	{
		//weight w = new weight(node, weight);
		ListNode newNode = new ListNode(newValue);

		if(isEmpty())
		{
			head = tail = newNode;
		}
		else
		{
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode; 
		}
	}

	//delete first node found with given value
	public void delete(Object value)
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("List is empty");
		}	
		ListNode current = head;

		while(current != null)
		{
			if(current.getValue().equals(value))
			{

				if (current == head && current == tail) 
				{
					//only one node in list
					head = tail = null;
				} 
				//node to delete is head
				else if (current == head) 
				{
					head = head.getNext();
					if (head != null) 
					{
						head.setPrev(null);
					}
				} 
				//node to delete is tail
				else if (current == tail) 
				{
					tail = tail.getPrev();
					if (tail != null) 
					{
						tail.setNext(null);
					}
				}
				//node to delete is in the middle
				else 
				{
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());
				}
				break;
			}
			current = current.getNext();
		}
	}

	//look at first node
	public ListNode peekFirst()
	{
		if (isEmpty())
		{
			return null;
		}
		else 
		{
			return head;
		}
	}

	//look at last node
	public ListNode peekLast()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException("List is empty");
		}
		else 
		{
			return tail;
		}
	}

	//removes value of first node
	public Object removeFirst()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("List is empty");
		}
		else 
		{
			Object nodeValue = head.getValue();
			if (head == tail)
			{
				head = tail = null;
			}
			else 
			{
				head = head.getNext();
				head.setPrev(null);
			}
			return nodeValue;
		}
	}

	//removes value of last node
	public Object removeLast()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("List is Empty");
		}
		else 
		{
			Object nodeValue = tail.getValue();
			if (head == tail)
			{
				tail = head = null;
			}
			else 
			{
				tail = tail.getPrev();
				tail.setNext(null);
			}
			return nodeValue;
		}
	}

	//check if the list is empty
	public boolean isEmpty()
	{
		return head == null;	
	}

	//traverse the list from head to tail and print values
	public void traverseFoward()
	{
		ListNode current = head;
		while (current != null)
		{
			System.out.println(current.value + " ");
			current = current.next;
		}
	}

	//find given value in the list
	public boolean find(Object valueToFind)
	{
		ListNode currNd = head;
		while (currNd != null)
		{
			if (currNd.getValue().equals(valueToFind))
			{
				return true;
			}
			currNd = currNd.getNext();
		}	
		return false;
	}

	//returns number of nodes in list
	public int size()
	{
		ListNode cur = head;		
		int count = 0; 
		while (cur != null)
		{
			cur = cur.next;
			count++;
		}
		return count ;
	}
}