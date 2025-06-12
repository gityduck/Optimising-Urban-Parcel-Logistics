/* Heap.java
 * has the add (to insert -> trickle up), remove (to delete -> to remove) into and from the heap
 */

public class Heap
{
	private HeapEntry[] heap;
	private int count;

	public Heap(int size)
	{
		heap = new HeapEntry[size];
		count = 0; 
	}

	//add into heap
	public void add(int priority, Object value, String custId)
	{
		if (count == heap.length)
		{
			System.out.println("Heap full.");
			return;
		}
		HeapEntry entry = new HeapEntry(priority, value, custId);
		heap[count] = entry;
		trickleUp(count);
		count++;
	}

	//delete from heap
	public HeapEntry remove()
	{
		if (count == 0)
		{
			return null;
		}
		HeapEntry root = heap[0];
		heap[0] = heap[count -1];
		count--; 
		trickleDown(0);
		return root;
	}

	//print the heap priorities
	public void display()
	{
		System.out.print("Heap priorities: [");
		System.out.print(heap[0].getPriority());
		for (int i = 1 ; i < count ; i ++)
		{
			System.out.print(" , " + heap[i].getPriority());
		}
		System.out.print("]");
		System.out.println();
	}

	//to add into heap
	private void trickleUp(int index)
	{
		int parentIndex = (index - 1)/2;
		while (index > 0 && heap[index].getPriority() > heap[parentIndex].getPriority())
		{
			swap(index,parentIndex);
			index = parentIndex;
			parentIndex = (index - 1) /2;
		}
	}

	//to remove from heap
	private void trickleDown(int index)
	{
		int leftChild = (index * 2 ) + 1;
		int rightChild = leftChild + 1; 
		boolean keepGoing = true;
		while (keepGoing && leftChild < count)
		{
			keepGoing = false;
			int largeIndex = leftChild;
			if (rightChild < count)
			{
				if(heap[rightChild].getPriority() > heap[leftChild].getPriority())
				
				{
					largeIndex = rightChild;
				}
			}

			if (heap[largeIndex].getPriority() > heap[index].getPriority())
			{
				swap(index,largeIndex);
				keepGoing = true;
				index = largeIndex;
				leftChild = (index * 2) + 1;
				rightChild = leftChild + 1; 
			}
		}
	}

	private void swap(int i , int j)
	{
		HeapEntry temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
}