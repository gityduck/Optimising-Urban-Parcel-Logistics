/* Stack.java initialises the fields, create, 
 * constructor with default size and another with a custom capacity
 * getCount >> get current number of elements in the stack
 * isEmpty/isFull >> check if the stack is empty/full
 * top >> return top element of the stack
 * push/pop >> insert or remove element into/from the stack
 */

import java.util.*;

class Stack
{
	private Object[] stack; //array to store stack elements
	private static int count; //tracks current number of elements
	private static final int DEFAULT_VALUE = 10; //default capacity
	
	//constructor with default size
	public Stack()
	{
		stack = new Object[DEFAULT_VALUE];
		count = 0 ;
	}

	//consructor with custom capacity
	public Stack(int maxCapacity)
	{
		stack = new Object[maxCapacity];
		count = 0;
	}

	//returns current number of elements in the stack
	public int getCount()
	{
		return count;
	}

	//check if stack is empty
	public boolean isEmpty()
	{
		return count == 0; 
	}

	//check if stack is full
	public boolean isFull()
	{
		return count >= DEFAULT_VALUE;
	}

	//return top element
	public Object top()
	{
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		return stack[count-1];
	}

	//insert new element
	public void push (Object x)
	{
		if (isFull())
		{
			System.out.print("Stack is full.");
			return;
		}
		else 
		{
			stack[count] = x; 
			count ++;
		}
	}

	//removes top element
	public Object pop()
	{
		if (isEmpty())
		{
			throw new EmptyStackException();
		}
		Object value = stack[count - 1];
		stack[count-1] = null;	
		count -- ;		
		return value;
	}
}
