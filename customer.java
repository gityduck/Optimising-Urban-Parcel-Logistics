/* 
 * customer.java initialises the fields, create, 
 * constructor, 
 * copy constructor, 
 * default constructor,
 * mutator method,
 * accessor method.
 * and a function that writes to the customer.csv file
 */

public class customer 
{
    //class fields
    private String id;
    private String name;
    private String address;
    private int priorityLevel;
    private String from;
    private String to;
    private String deliveryStatus;

    //parameter constructor
    public customer(String pId, String pName, String pAddress, int pPriorityLevel, String pDeliveryStatus, String pFrom, String pTo)
    {
        //assigning
        id = pId;
        name = pName;
        address = pAddress;
        priorityLevel = pPriorityLevel;
        from = pFrom;
        to = pTo;
        deliveryStatus = pDeliveryStatus;
    }

    //copy constructor
    public customer(customer pCustomer)
    {
        id = pCustomer.getId();
        name = pCustomer.getName();
        address = pCustomer.getAddress();
        priorityLevel = pCustomer.getPriorityLevel();
        deliveryStatus = pCustomer.getDeliveryStatus();
        from = pCustomer.getFrom();
        to = pCustomer.getTo();
    }

    //default constructor
    public customer()
    {
        id = "null";
        name = "null";
        address = "null";
        priorityLevel = 0; //default priority level
        deliveryStatus = "null"; //default delivery status
        from = "null";
        to = "null";
    }

    //accessor methods
    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getAddress()
    {
        return address;
    }
    public int getPriorityLevel()
    {
        return priorityLevel;
    }
    public String getDeliveryStatus()
    {
        return deliveryStatus;
    }
    public String getFrom()
    {
        return from;
    }
    public String getTo()
    {
        return to;
    }

    //mutator methods - used to modify value of a private field in an object
    public void setId(String pId)
    {
        id = pId;
    }
    public void setName(String pName)
    {
        name = pName;
    }
    public void setAddress(String pAddress)
    {
        address = pAddress;
    }
    public void setPriorityLevel(int pPriorityLevel)
    {
        priorityLevel = pPriorityLevel;
    }
    public void setDeliveryStatus(String pDeliveryStatus)
    {
        deliveryStatus = pDeliveryStatus;
    }
    public void setFrom(String pFrom)
    {
        from = pFrom;
    }
    public void setTo(String pTo)
    {
        to = pTo;
    }

    //write to the csv file
    public String toString()
    {
        return id + "," + name + "," + address + "," + priorityLevel + ","  + deliveryStatus + "," + from + "," + to;
    }
}