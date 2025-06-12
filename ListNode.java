/* ListNode.java initialises the fields, create, 
 * constructor, mutator and accessor method
 */

public class ListNode
{
	public Object value; //stores value in the node
	public ListNode next; //ref to next node
	public ListNode prev; //ref to previous node

	//constructor
	public ListNode(Object inValue)
	{
		value = inValue;
		next = null;
		prev = null;
	}

	//mutator
	public void setNext(ListNode newNext)
	{
		next = newNext;
	}
	public void setPrev(ListNode newPrev)
	{
		prev = newPrev;
	}
	public void setValue(Object inValue)
	{
		value = inValue; 
	}

	//accessor
	public ListNode getNext()
	{
		return next;
	}
	public ListNode getPrev()
	{
		return prev; 
	}
	public Object getValue()
	{
		return value;
	}
}
