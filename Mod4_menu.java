/* runs the Sorting Delivery Records
 * interactiveMod4 allows the user to input the datasets (n) 
 * runModule4 basically does the sorting by calling the function name
 */

import java.util.*;

public class Mod4_menu
{
    /** No of times to run sorts to get mean time */
    private static final int REPEATS = 4;

    /** number of items moved (% of n) in nearly sorted array */
    private static final double NEARLY_PERCENT = 0.10;

    /** No of times (*n) to randomly swap elements to get random array */
    private static final int    RANDOM_TIMES   = 100;

    private static void usage() 
    {
        System.out.println(" Usage: java TestHarness n xy [xy ...]");
        System.out.println("        where");
        System.out.println("        n is number of integers to sort");
        System.out.println("        x is one of");
	    System.out.println("           q - quicksort median of three");
        System.out.println("           m - mergesort");
        System.out.println("        y is one of");
        System.out.println("           v - 1..n reversed");
        System.out.println("           r - 1..n in random order");
        System.out.println("           n - 1..n nearly sorted (10% moved)");
    }

    public static void interativeMod4()
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of integers to sort (n): ");
        String n = sc.nextLine();

        System.out.print("Enter sort type (eg. qv, mn, qr....): ");
        String line = sc.nextLine();

        //manually split user input line into individual array type
        String[] input = line.trim().split("\\s+");

        //create a new string array run command-line arguments
        String[] args = new String[input.length + 1];

        //set first argument
        args[0] = n;

        //copy each input into the args array
        for (int i = 0; i < input.length; i++)
        {
            args[i+1] = input[i];
        }

        System.out.println();
        runModule4(args);

        System.out.println("\nReturning to main menu...");
    }

    // Process command line arguments, generate array and call sort
    public static void runModule4(String[] args)
    {
        int n;
        char sortType, arrayType;
        if (args.length < 2)
        {
            usage();
        }
        else
        {
            n = Integer.parseInt(args[0]);
            int[] A = new int[n];
            for(int numSorts = 1 ; numSorts < args.length ; numSorts++)
            {
                sortType  = args[numSorts].charAt(0);
                arrayType = args[numSorts].charAt(1);
               
                double runningTotal = 0;
                for (int repeat = 0 ; repeat < REPEATS ; repeat++)
                {
                    // Create initial ascending-order array
                    for(int i = 0 ; i < n ; i++) 
                    {
                        A[i] = i+1;
                    }

                    switch (arrayType)
                    {
                        case 'v' :    // Convert to reversed
                            for(int i = 0 ; i < n/2 ; i++)
                            {
                                swap(A, i, n - i - 1);
                            }
                            break;
                        case 'r' :  // Randomly reorder (overdo it to make sure)
                            for(int i = 0 ; i < RANDOM_TIMES*n ; i++)
                            {
                                int x = (int)Math.floor(Math.random()*(n-1));
                                int y = (int)Math.floor(Math.random()*(n-1));
                                swap(A, x, y);
                            }
                            break;
                        case 'n' : // Randomly re-order *part* of the array
                            for(int i = 0 ; i < n*NEARLY_PERCENT/2.0 ; i++)
                            {
                                int x = (int)Math.round(Math.random()*(n-1));
                                int y = (int)Math.round(Math.random()*(n-1));
                                swap(A, x, y);
                            }
                            break;
                        default :
                            System.err.println("Unsupported array type " + arrayType);
                    }

                    // Do the sorting
                    long startTime = System.nanoTime();
                    switch (sortType)
                    {
                        case 'm' : Sorts.mergeSort(A); break;
			            case 'q' : 
			                //for median 3 sort
                            Sorts.quickSortMedian3(A);
                            break;
                        default :
                            throw new IllegalArgumentException("Unsupported sort type " + sortType);
                    }
                    long endTime = System.nanoTime();

                    if (repeat == 0) {
                        // Check that it actually sorted correctly!
                        for(int i = 1 ; i < A.length; i++) 
                        {
                            if (A[i] < A[i-1])
                            throw new IllegalStateException("Array is not in sorted order! At element: " + i);                        
                        }
                    }
                    else 
                    {
                        runningTotal += (int)((double)(endTime - startTime) / 1000.0);	// Convert to microsecs
                    }
                }
                System.out.print(args[numSorts]+ " " + n);
                System.out.println(" " + (runningTotal/(REPEATS-1)));
            }
         }
     }

    private static void swap(int[] A, int idx1, int idx2)
    {
        int temp = A[idx1];
        A[idx1] = A[idx2];
        A[idx2] = temp;
    }
}