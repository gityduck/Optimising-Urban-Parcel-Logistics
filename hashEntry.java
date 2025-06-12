/* hashEntry.java initialises the fields, create, 
 * constructor (one that accepts 2 (parameters) and accessor method
 */

public class hashEntry
{
	//class fields
	private String key;
	private customer value;
	private String state;

	//constructor
	public hashEntry()
	{
		//set to null
		key = null;
		value = null;
		state = "free";
	}

	//constructor
	public hashEntry(String inKey, customer inValue)
	{
		key = inKey;
		value = inValue;
		state = "used";
	}

	//accessor
	public String getKey()
	{
		return key;
	}
	public customer getValue()
	{
		return value;
	}
	public String getState()
	{
		return state;
	}

	//mutator
	public void setValue(customer inValue)
	{
		value = inValue;	
	}
	public void setState(String inState)
	{
		state = inState;
	}
}
