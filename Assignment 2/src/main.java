import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

// Analysis of Algorithms - CSCI 323
// Winter session 2022
// Assignment #2
// Redwanul Haque

public class main {
	
	static long total1 = 0;  // java sort
	static long total2 = 0;  // bubble sort
	static long total3 = 0;  // selection sort
	static long total4 = 0;  // insertion sort
	static long total5 = 0;  // cocktail sort
	static long total6 = 0;  // shell sort
	static long total7 = 0;  // merge sort
	static long total8 = 0;  // quick sort
	static long total9 = 0;  // heap sort
	static long total10 = 0;  // counting sort
	static long total11 = 0;  // bucket sort
	static long total12 = 0;  // radix sort

    private JFrame frame;
	
	public static int[] randomList(int max, int size) {  // creating a random list of array data
		
		int[] list = new int[size];  // size of array
		for(int i = 0; i < size; i++) {
			list[i] = (int) (Math.random()*max);
		}
		
		System.out.print("Random array: ");
		for(int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.print("\n\n");
		return list;
	}
	
	
    // https://www.baeldung.com/java-check-sorted-array
    static boolean isSorted(int[] array) {
  	    for (int i = 0; i < array.length - 1; i++) {
  	        if (array[i] > array[i + 1])
  	            return false;
  	    }
  	    return true;
  	}
	
	
	// java sort
	public static void javaSort(int list[]) {
		Arrays.sort(list);
	} 
	
	
	// https://www.javatpoint.com/bubble-sort-in-java
	public static void bubbleSort(int[] arr) {  
		
        int n = arr.length;  
        int temp = 0;  
        
        	for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                	 if(arr[j-1] > arr[j]){  //swap elements  
                		 temp = arr[j-1];  
                         arr[j-1] = arr[j];  
                         arr[j] = temp;}           
            } 
        }
			//return -1;
    }
	
	
	// https://www.geeksforgeeks.org/selection-sort/
	public static void selectionSort(int arr[]) {
		
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
	
	
	// https://docs.google.com/document/d/1LZAgrXLkzWwlHZA4Jh5yhr3Rmoqbwh7lQP8wMDo6mwE/edit
	public static void insertionSort(int arr[]) {
		
        int n = arr.length;
        
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
	}
	
	
	// https://www.geeksforgeeks.org/cocktail-sort/
	public static void cocktailSort(int a[]) {
		
        boolean swapped = true;
        int start = 0;
        int end = a.length;
 
        while (swapped == true) {
            swapped = false;
 
            // loop from bottom to top same as
            // the bubble sort
            for (int i = start; i < end - 1; ++i) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }
 
            if (swapped == false)
                break;

            swapped = false;
            end = end - 1;
 
            // from top to bottom, doing the
            // same comparison as in the previous stage
            for (int i = end - 1; i >= start; i--) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }
            start = start + 1;
        }
    }
	
	
	// https://www.geeksforgeeks.org/shellsort/
    public static int shellSort(int arr[]) {
    	
        int n = arr.length;
        
        // Start with a big gap, then reduce the gap
        for (int gap = n/2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1) {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                int temp = arr[i];
 
                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
 
                // put temp (the original a[i]) in its correct
                // location
                arr[j] = temp;
            }
        }
        return 0;
    }
    
    // https://stackabuse.com/merge-sort-in-java/
    public static void mergeSort(int[] array, int low, int high) {
        if (high <= low) return;

        int mid = (low+high)/2;
        mergeSort(array, low, mid);
        mergeSort(array, mid+1, high);
        mergeDemo(array, low, mid, high);
    }
    
    // https://stackabuse.com/merge-sort-in-java/
    public static void mergeDemo(int[] array, int low, int mid, int high) {
        // Creating temporary subarrays
        int leftArray[] = new int[mid - low + 1];
        int rightArray[] = new int[high - mid];

        // Copying our subarrays into temporaries
        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[low + i];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];

        // Iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        // Copying from leftArray and rightArray back into array
        for (int i = low; i < high + 1; i++) {
            // If there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                   array[i] = leftArray[leftIndex];
                   leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                // If all elements have been copied from rightArray, copy rest of leftArray
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                // If all elements have been copied from leftArray, copy rest of rightArray
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
    
    
    // https://www.javatpoint.com/quick-sort
    public static int partition (int a[], int start, int end) {  
    	
        int pivot = a[end]; // pivot element  
        int i = (start - 1);  
      
        for (int j = start; j <= end - 1; j++) {  
            // If current element is smaller than the pivot  
            if (a[j] < pivot) {  
                i++; // increment index of smaller element  
                int t = a[i];  
                a[i] = a[j];  
                a[j] = t;  
            }  
        }  
        int t = a[i+1];  
        a[i+1] = a[end];  
        a[end] = t;  
        return (i + 1);  
    }  
    
    // https://www.javatpoint.com/quick-sort
    public static void quickSort(int a[], int start, int end) {  
        if (start < end) {  
            int p = partition(a, start, end);  //p is partitioning index  
            quickSort(a, start, p - 1);  
            quickSort(a, p + 1, end);  
        }  
    }  
    
    
    // https://www.javatpoint.com/heap-sort
    public static void heapify(int a[], int n, int i) {  
    	
        int largest = i; // Initialize largest as root  
        int left = 2 * i + 1; // left child  
        int right = 2 * i + 2; // right child  
        // If left child is larger than root  
        if (left < n && a[left] > a[largest])  
            largest = left;  
        // If right child is larger than root  
        if (right < n && a[right] > a[largest])  
            largest = right;  
        // If root is not largest  
        if (largest != i) {  
            // swap a[i] with a[largest]  
            int temp = a[i];  
            a[i] = a[largest];  
            a[largest] = temp;  
              
            heapify(a, n, largest);  
        }  
    }  
    
    // https://www.javatpoint.com/heap-sort
    public static void heapSort(int a[], int n) {  
    	
        for (int i = n / 2 - 1; i >= 0; i--)  
            heapify(a, n, i);  
      
        // One by one extract an element from heap  
        for (int i = n - 1; i >= 0; i--) {  
            /* Move current root element to end*/  
            // swap a[0] with a[i]  
            int temp = a[0];  
            a[0] = a[i];  
            a[i] = temp;  
              
            heapify(a, i, 0);  
        }  
    } 
    
    
    // https://www.programiz.com/dsa/counting-sort
    public static void countSort(int array[], int size) {
        int[] output = new int[size + 1];

        // Find the largest element of the array
        int max = array[0];
        for (int i = 1; i < size; i++) {
          if (array[i] > max)
            max = array[i];
        }
        int[] count = new int[max + 1];

        // Initialize count array with all zeros.
        for (int i = 0; i < max; ++i) {
          count[i] = 0;
        }

        // Store the count of each element
        for (int i = 0; i < size; i++) {
          count[array[i]]++;
        }

        // Store the cummulative count of each array
        for (int i = 1; i <= max; i++) {
          count[i] += count[i - 1];
        }

        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        for (int i = size - 1; i >= 0; i--) {
          output[count[array[i]] - 1] = array[i];
          count[array[i]]--;
        }

        // Copy the sorted elements into original array
        for (int i = 0; i < size; i++) {
          array[i] = output[i];
        }
      }
    
    
    // https://knpcode.com/java-programs/bucket-sort-java-program/
    public static void bucketSort(int[] arr, int bucketSize){
    	
        // Create bucket array for storing lists
        List<Integer>[] buckets = new List[bucketSize];
        // Linked list with each bucket array index
        // as there may be hash collision 
        for(int i = 0; i < bucketSize; i++){
          buckets[i] = new LinkedList<>();
        }
        // calculate hash and assign elements to proper bucket
        for(int num : arr){
          buckets[hash(num, bucketSize)].add(num);
        }
        // sort buckets
        for(List<Integer> bucket : buckets){
          Collections.sort(bucket);
        }

        int index = 0;
        // Merge buckets to get sorted array
        for(List<Integer> bucket : buckets){
          for(int num : bucket){
            arr[index++] = num;
          }
        }
      }
        
      // https://knpcode.com/java-programs/bucket-sort-java-program/
      public static int hash(int num, int bucketSize){
        return num/bucketSize;
      }
      
      
      // https://www.geeksforgeeks.org/radix-sort/
      public static int getMax(int arr[], int n) {
          int mx = arr[0];
          for (int i = 1; i < n; i++)
              if (arr[i] > mx)
                  mx = arr[i];
          return mx;
      }
   
      // https://www.geeksforgeeks.org/radix-sort/
      public static void counttSort(int arr[], int n, int exp) {
          int output[] = new int[n]; // output array
          int i;
          int count[] = new int[10];
          Arrays.fill(count, 0);
   
          // Store count of occurrences in count[]
          for (i = 0; i < n; i++)
              count[(arr[i] / exp) % 10]++;
   
          // Change count[i] so that count[i] now contains
          // actual position of this digit in output[]
          for (i = 1; i < 10; i++)
              count[i] += count[i - 1];
   
          // Build the output array
          for (i = n - 1; i >= 0; i--) {
              output[count[(arr[i] / exp) % 10] - 1] = arr[i];
              count[(arr[i] / exp) % 10]--;
          }
   
          // Copy the output array to arr[], so that arr[] now
          // contains sorted numbers according to current digit
          for (i = 0; i < n; i++)
              arr[i] = output[i];
      }
      
      // https://www.geeksforgeeks.org/radix-sort/
      public static void radixSort(int arr[], int n) {
          // Find the maximum number to know number of digits
          int m = getMax(arr, n);
   
          // Do counting sort for every digit. Note that
          // instead of passing digit number, exp is passed.
          // exp is 10^i where i is current digit number
          for (int exp = 1; m / exp > 0; exp *= 10)
              counttSort(arr, n, exp);
      }
      
 
      // https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
      public main() {
          frame = new JFrame("Bar Graph for Sort Algorithms");  // title of graph
          frame.setSize(1000, 770);  // size
          frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          frame.setPreferredSize(frame.getSize());
          frame.add(new DrawBars(frame.getSize()));
          frame.pack();
          frame.setLocationRelativeTo(frame);  // set location to center
          frame.setVisible(true);  // seeing the graph
      }
      
      public static void print(int max, int size, int trial) {  // this is going to print the codes
  		
  		int[] list = randomList(max,size);
  		int[] list2 = list.clone(); int[] list3 = list.clone(); int[] list4 = list.clone(); int[] list5 = list.clone(); int[] list6 = list.clone();
  		int[] list7 = list.clone(); int[] list8 = list.clone(); int[] list9 = list.clone(); int[] list10 = list.clone(); int[] list11 = list.clone();
  		int[] list12 = list.clone(); int[] list13 = list.clone();	
  		
  		for(int x=0; x<trial; x++) {
  			
        long startTime = System.nanoTime();   // start time
  		javaSort(list2);
      	long endTime1 = System.nanoTime();  
      	total1 = (total1 + (endTime1 - startTime))/trial;

  		bubbleSort(list3);
  		long endTime2 = System.nanoTime(); 
      	total2 = (total2 + (endTime2 - startTime))/trial;
  		
  		selectionSort(list4);
  		long endTime3 = System.nanoTime();
      	total3 = (total3 + (endTime3 - startTime))/trial;
  		
  		insertionSort(list5);
  		long endTime4 = System.nanoTime(); 
      	total4 = (total4 + (endTime4 - startTime))/trial;
  		
  		cocktailSort(list6);
  		long endTime5 = System.nanoTime();  
      	total5 = (total5 + (endTime5 - startTime))/trial;
  		
  		shellSort(list7);
  		long endTime6 = System.nanoTime();  
      	total6 = (total6 + (endTime6 - startTime))/trial;

  		mergeSort(list8, 0, list8.length-1);
  		long endTime7 = System.nanoTime();
      	total7 = (total7 + (endTime7 - startTime))/trial;
  		
  		quickSort(list9, 0, list9.length-1);
  		long endTime8 = System.nanoTime();
      	total8 = (total8 + (endTime8 - startTime))/trial;
  		
  		heapSort(list10, list10.length);
  		long endTime9 = System.nanoTime(); 
      	total9 = (total9 + (endTime9 - startTime))/trial;
  		
  		countSort(list11, list11.length);
  		long endTime10 = System.nanoTime(); 
      	total10 = (total10 + (endTime10 - startTime))/trial;
  		
  		bucketSort(list12, list12.length);
  		long endTime11 = System.nanoTime(); 
      	total11 = (total11 + (endTime11 - startTime))/trial;
  		
  		radixSort(list13, list13.length);
  		long endTime12 = System.nanoTime(); 
      	total12 = (total12 + (endTime12 - startTime))/trial;	
  		}
  		

  		System.out.print("              Trials #" + trial
  				+ "\n            -------------");
  		
  		System.out.print("\n     Sorts         Times of Each Search\n"
          		+ "-------------     ----------------------");
          System.out.print("\nJava sort                 0." + total1 + "s");  // java
          System.out.print("\nBubble Sort               0." + total2 + "s");  // bubble
          System.out.print("\nSelection Sort            0." + total3 + "s");  // selection
          System.out.print("\nInsertion Sort            0." + total4 + "s");  // insertion
          System.out.print("\nCocktail Sort             0." + total5 + "s");  // cocktail
          System.out.print("\nShell Sort                0." + total6 + "s");  // shell
          System.out.print("\nMerge Sort                0." + total7 + "s");  // merge
          System.out.print("\nQuick Sort                0." + total8 + "s");  // quick
          System.out.print("\nHeap Sort                 0." + total9 + "s");  // heap
          System.out.print("\nCounting Sort             0." + total10 + "s");  // counting
          System.out.print("\nBucket Sort               0." + total11 + "s");  // bucket
          System.out.print("\nRadix Sort                0." + total12 + "s\n\n");  // radix
      }
      
      
	public static void main(String... argv) throws FileNotFoundException {
		
    	PrintStream o = new PrintStream(new File("Log2.txt"));
        PrintStream console = System.out;
        System.setOut(o);
        
		print(500, 100, 1000);
        new main();  // for trial 10 graph
        
		print(100, 10, 2000);
        new main();  // for trial 100 graph

		print(100, 100, 5000);
        new main();  // for trial 1000 graph

		print(100, 100, 10000);
        new main();  // for trial 10000 graph
		
        System.setOut(console);
	}
	
	
	public static void bars(long average, int size, Graphics2D g2, Color color) {  // print bars to the graph
		
		for(int i=0; i<=12; i++) {
		for(int j = 0; j < 1; j++){  // java search bar
            g2.setColor(color);
            g2.fillRect(j*30 + 20 + size, 700-(int)(average * 2), 50 ,(int)(average * 2));
            
			}
        }
	}
	
	public static void name(Graphics2D g2, String name, Color color, int shift) {  // getting the bar to appear on the graph
		
		g2.setFont (new Font("Times New Roman", Font.BOLD, 12));
		
        g2.setColor(color);
        g2.drawString(name, shift,715);
		
	}
	
	// https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
    public static class DrawBars extends JPanel {   

        public DrawBars(Dimension dimension) { 
            setSize(dimension);
            setPreferredSize(dimension);
        }

        // https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
        @Override
        public void paintComponent(Graphics x) {
            Graphics2D g2 = (Graphics2D)x;  //g2 is the graphics object that we need to use
                                            //to draw things to the screen
            Dimension o = getSize();
            //create a background
            g2.setColor(Color.white);
            g2.fillRect(0, 0, o.width, o.height);
            

            Color a = new Color(100, 0, 102);  
            Color b = new Color(10,255, 102);  
            Color c = new Color(0, 0, 102);  
            Color d = new Color(200, 150, 102); 
            Color e = new Color(250, 100, 102);
            Color f = new Color(123, 145, 102);
            Color g = new Color(13, 179, 102);
            Color i = new Color(92, 92, 102);
            Color j = new Color(56, 89, 102);
            Color k = new Color(250, 211, 102);
            Color l = new Color(250, 21, 102);
            Color m = new Color(245, 173, 102);

            bars(total1, 0, g2, a);  // java
            bars(total2, 80, g2, b);  // bubble
            bars(total3, 160, g2, c);  // selection
            bars(total4, 240, g2, d);  // insertion
            bars(total5, 320, g2, e);  // cocktail
            bars(total6, 400, g2, f);  // shell
            bars(total7, 480, g2, g);  // merge
            bars(total8, 560, g2, i);  // quick
            bars(total9, 640, g2, j);  // heap
            bars(total10, 720, g2, k);  // heap
            bars(total11, 800, g2, l);  // heap
            bars(total12, 880, g2, m);  // heap
            
            name(g2, "Java Sort", a, 20);
            name(g2, "Bubble Sort", b, 92);
            name(g2, "Selection Sort",c, 170);
            name(g2, "Insertion Sort", d, 250);
            name(g2, "Cocktail Sort", e, 330);
            name(g2, "Shell Sort", f, 420);
            name(g2, "Merge Sort", g, 495);
            name(g2, "Quick Sort", i, 575);
            name(g2, "Heap Sort", j, 660);
            name(g2, "Counting Sort", k, 730);
            name(g2, "Bucket Sort", l, 815);
            name(g2, "Radix Sort", m, 900);
        }
    }	
}
