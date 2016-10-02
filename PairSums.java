/*
 * PairSums.java
 * 
 * Computer Science E-22, Harvard University
 * Caitlin Vinci
 * caitlinvinci@gmail.com
 * 9/25/2016
 */
import java.util.*; 

public class PairSums {
    
    private static long compares;     // total number of comparisons
    private static long moves;        // total number of moves
    private static int MAX_VAL = 25; 
    
     /*
     * compare - a wrapper that allows us to count comparisons.
     */
    private static boolean compare(boolean comparison) {
        compares++;
        return comparison;
    }
    
    /*
     * findPairs takes an array and finds pairs of elements
     * that will add up to the int parameter k. It is an 
     * O(n^2) algorithm because, like selection sort, it runs 
     * through nested loops to compare each element of arr with 
     * every other element of arr. 
     * The method prints the elements and the sum when it finds a 
     * match between the desiredSum and the sum of the values in 
     * the array. 
     */
    public static void findPairs (int k, int [] arr) {
        int desiredSum = k; 
        int count = 0; 
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if ( arr [i] + arr [j] == desiredSum ) {
                    System.out.println ( arr [i] + " + " + 
                                        arr [j] + " = " + (arr [i] + arr [j])); 
                }
                count++;
            }
        }
        System.out.println("Number of sum tests: " + count); 
    }
    
    
    /** 
     * randomArray - creates an array of randomly generated integers
     * with the specified number of elements
     * 
     * Taken from SortCount.java
     */
    public static int[] randomArray(int n) {
        int[] arr = new int[n];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * (MAX_VAL + 1));
        }
        
        return arr;
    }
    
     /*
     * swap - swap the values of two variables.
     * Used by several of the sorting algorithms below.
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        moves += 3;
    }
    
      /////////Quicksort methods taken from SortCount.java  
    /*
     * A helper method for qSort that takes the array that begins with
     * element arr[first] and ends with element arr[last] and
     * partitions it into two subarrays using the middle element of
     * the array for the pivot.  It returns the index of the last
     * element of the left subarray formed by the partition.  All
     * elements in the left subarray are <= the pivot, and all
     * elements in the right subarray are >= the pivot.
     */
    private static int partition(int[] arr, int first, int last) {
        int pivot = arr[(first + last)/2];
        moves++;   // for the above assignment
        int i = first - 1;  // index going left to right
        int j = last + 1;   // index going right to left
        
        while (true) {
            // Moving from left to right, find an element >= the pivot.
            do {
                i++;
            } while (compare(arr[i] < pivot));
            
            // Moving from right to left, find an element <= the pivot.
            do {
                j--;
            } while (compare(arr[j] > pivot)); 
            
            // Swap the elements so that they end up in the correct
            // subarray, or quit if the indices have overlapped or crossed.
            if (i < j)
                swap(arr, i, j);
            else
                return j;   // index of last element in the left subarray
        }                   
    }
    
    /*
     * A recursive helper method that actually implements quicksort.
     * The initial recursive call is made by quicksort() -- see below.
     */
    private static void qSort(int[] arr, int first, int last) {
        // Partition the array.  split is the index of the last
        // element of the left subarray formed by the partition.
        int split = partition(arr, first, last);
        
        //
        // Note that we only make recursive calls on subarrays that
        // have two or more elements, and thus the base case is when
        // neither subarray has two or more elements.
        //
        if (first < split)
            qSort(arr, first, split);      // left subarray
        if (last > split + 1)
            qSort(arr, split + 1, last);   // right subarray
    }
    
    /** quickSort */
    public static void quickSort(int[] arr) {
        qSort(arr, 0, arr.length - 1); 
    }
    
    public static void main (String [] args) {
        for (int i = 0; i <= 5; i++) {
            int [] a = randomArray((int) (Math.random() * (MAX_VAL + 1))); 
            System.out.println("Random Array: "); 
            System.out.println(Arrays.toString (a)); 
            int b = ((int) (Math.random() * (MAX_VAL + 1))); 
            System.out.println("Finding sums of " + b + " : "); 
            findPairs(b, a); 
            
            //testing for findPairsFaster
            System.out.println("Sorted array: "); 
            quickSort(a); 
            System.out.println(Arrays.toString(a)); 
            System.out.println(); 
        }
    }
        
        
    
    
}

