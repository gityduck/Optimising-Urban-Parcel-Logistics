/* Queue.java initialises the fields, create, 
 * constructor
 * check if queue is empty with isEmpty function
 * peek >> return the front value of the queue
 * enqueue/dequeue >> adds/removes first OR last value in the queue
 */

public class Queue
{
	private LinkedList queue; 

	//constructor - empty queue
	public Queue()
	{
		queue = new LinkedList();
	}
	
	//check if queue is empty
	public boolean isEmpty()
	{
		return queue.isEmpty();
	}

	//return value at front of queue
	public Object peek()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("Queue is empty.");
			
		}
		return queue.peekFirst();
	}

	//adds new element to end of queue
	public void enqueue(Object value)
	{
		queue.insertLast(value);
	}

	//removes value from front of queue
	public Object dequeue()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("Queue is empty.");
		
		}
		return queue.removeFirst();
	}
}