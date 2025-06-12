/* HeapEntry.java initialises the fields, create, 
 * constructorm mutator and accessor method
 */

public class HeapEntry
{
	//class fields
	private int priority;
	private Object value;
	private String custId;

	//constructor
	public HeapEntry(int inPriority, Object inValue, String customerId)
	{
		priority = inPriority;	
		value = inValue;
		custId = customerId;
	}

	//accessor
	public int getPriority()
	{
		return priority;
	}
	public Object getValue()
	{
		return value;
	}

	public String getCustId()
	{
		return custId;
	}

	//mutator
	public void setPriority(int inPriority)
	{
		priority = inPriority;
	}
 	public void setValue(Object inValue)
	{
		value = inValue;	
	}
 
	public void setCustId(String customerId)
	{
		custId = customerId;
	}
}
