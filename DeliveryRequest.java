/* 
 * DeliveryRequest.java initialises the fields, create, 
 * constructor and accessor methods
 */

public class DeliveryRequest
{
	//class fields
	private String customerID;
	private int deliveryTime;
	private int priorityLevel;

	//constructor
	public DeliveryRequest(String inCustomerID , int inDeliveryTime, int inPriorityLevel)
	{
		customerID = inCustomerID;	
		deliveryTime = inDeliveryTime;
		priorityLevel = inPriorityLevel;
	}

	//accessor
	public String getCustomerID()
	{
		return customerID;	
	}
	public int getDeliveryTime()
	{
		return deliveryTime;
	}
	public int getPriorityLevel()
	{
		return priorityLevel;
	}
}