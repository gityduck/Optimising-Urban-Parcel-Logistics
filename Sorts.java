/* Sorts.java contains
 * bubbleSort, insertionSort, selectionSort, reversed
 * >> mergeSort for recurse
 * >> quickSort for Median of Three
 */

class Sorts
{
	// bubble sort
	public static void bubbleSort(int[] A)
	{
		boolean swapped;
		int temp;

		for (int pass = 0; pass < A.length-1; pass++)
		{
			swapped = false;
			for (int i = 0; i < A.length-1-pass; i++)
			{
				if (A[i] > A[i+1])
				{
					temp = A[i];
					A[i] = A[i+1];
					A[i+1] = temp;
					swapped = true;
				}
			}
			if (!swapped)
			{
				break;
			}
		}
	}

	// selection sort
	public static void selectionSort(int[] A)
	{
		for (int i = 0; i < A.length-1; i++)
		{
			int min_index = i;
			for (int j = i + 1; j < A.length; j++)
			{
				if (A[j] < A[min_index])
				{
					min_index = j;
				}
			}
			int temp = A[i];
			A[i] = A[min_index];
			A[min_index] = temp;
		}
	}

	// insertion sort
	public static void insertionSort(int[] A)
	{
		for (int i = 0; i < A.length; i++)
		{
			int value = A[i];
			int j = i-1;
			while (j >= 0 && A[j] > value)
			{
				A[j+1] = A[j];
				j--;
			}
			A[j+1] = value;
		}
	}
	
	//reversed
	public static int[] reversed(int n)
	{
		int[] result = new int[n];
		for (int i = 0; i < n; i++)
		{
			result[i] = n-1-i;
		}
		return result;
	}

	// mergeSort - front-end for kick-starting the recursive algorithm
	public static void mergeSort(int[] A)
	{
		mergeSortRecurse(A, 0, A.length-1);
	}
	
	private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
	{
		if (leftIdx < rightIdx)
		{
			int midIdx = (leftIdx + rightIdx)/2;
			//sort left half of current sub-array
			mergeSortRecurse(A, leftIdx, midIdx);
			//sort right half of current sub-array
			mergeSortRecurse(A, midIdx+1, rightIdx);
			//merge left and right sub-array
			merge(A, leftIdx, midIdx, rightIdx);
		}
	}
	
	private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
	{
		int[] tempArr = new int[rightIdx - leftIdx + 1];
		//start of left and right sub-array
		int ii = leftIdx;
		int jj = midIdx + 1;
		//index for tempArr
		int kk = 0;

		while (ii <= midIdx && jj <= rightIdx)
		{
			if (A[ii] <= A[jj])
			{
				//take from left sub-array
				tempArr[kk++] = A[ii++];
			}
			else
			{
				//take from right sub-array
				tempArr[kk++] = A[jj++];
			}
		}

		//flush remainder from left sub-array
		while (ii <= midIdx)
		{
			tempArr[kk++] = A[ii++];
		}
		//flush remainder from right sub-array
		while (jj <= rightIdx)
		{
			tempArr[kk++] = A[jj++];
		}
		//copy the sorted tempArr back to actual array
		for (int i = 0; i < tempArr.length; i++)
		{
			A[leftIdx+i] = tempArr[i];
		}
	}

	// quickSortMedian
    public static void quickSortMedian3(int[] A)
    {
            quickSortMedian3Recurse(A, 0, A.length-1);
    }

    private static void quickSortMedian3Recurse(int[] A, int leftIdx, int rightIdx)
    {
        if (rightIdx > leftIdx)
        {
            //left most pivot selection
            int pivotIdx = medianOfThree(A, leftIdx, rightIdx);
            int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            //recurse on left and right sub-array
            quickSortMedian3Recurse(A, leftIdx, newPivotIdx-1);
            quickSortMedian3Recurse(A, newPivotIdx+1, rightIdx);
        }
    }

	//helper method to choose the pivot as the meidan of the first, middle and last element
	private static int medianOfThree(int[] A, int leftIdx, int rightIdx)
	{
		int midIdx = (leftIdx + rightIdx)/2;

		int a = A[leftIdx];
		int b = A[midIdx];
		int c = A[rightIdx];

		if ((a>b) && (a<c))
		{
			return leftIdx;
		}
		else if ((b>a) && (b<c))
		{
			return midIdx;
		}
		else 
		{
			return rightIdx;
		}
	}

	private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
	{
		int pivotVal = A[pivotIdx];
		int currIdx = leftIdx;

		//move pivot to the end
		int temp = A[pivotIdx];
		A[pivotIdx] = A[rightIdx];
		A[rightIdx] = temp;

		//move all values smaller than the pivot to the left
		for (int ii = leftIdx; ii < rightIdx; ii++)
		{
			if (A[ii] < pivotVal)
			{
				int temporary = A[ii];
				A[ii] = A[currIdx];
				A[currIdx] = temporary;

				currIdx++;
			}
		}	
		//move pivot to its rightful place
		int tempp = A[currIdx];
		A[currIdx] = A[rightIdx];
		A[rightIdx] = tempp;
		
		return currIdx;
	}
}//end Sorts calss