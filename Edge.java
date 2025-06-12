/* Edge.java initialises the fields, create, 
 * constructor and accessor method
 */

public class Edge
{
    private Node dest; //destination node of the edge
    public int w; //weight of the edge

    //constructor
    public Edge(Node destination, int weight)
    {
        dest = destination;
        w = weight;
    }

    //accessor
    public Node getDestination()
    {
        return dest;
    }
    public int getWeight()
    {
        return w;
    }
}