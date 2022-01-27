import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

// Analysis of Algorithms - CSCI 323
// Winter session 2022
// Assignment #1
// Redwanul Haque

public class main {
	
	static long total1 = 0;  // linear search
	static long total2 = 0;  // Binary Search
	static long total3 = 0;  // Interpolation search
	static long total4 = 0;  // Jump search
	static long total5 = 0;  // Java search
	
    private JFrame frame;
	
	public static int randomList(int max, int size, boolean sorted) {  // creating a random list of array data
		
		int list[] = new int[size];  // size of array
		int i;
		for( i = 1; i < list.length; i++) {
			list[i] = (int) (Math.random()*max);
		}
		
		System.out.print("Random list of array: ");
		for( i = 1; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
	
		sorted = sorted(list);
		System.out.print("\nIs the list sorted: " + sorted + "\n");
		System.out.print("The size of list is ");
		return size;
	}
	
	public static boolean sorted(int[] a) {  // creating a sort method to check the list is sorted or not
		
		for (int i = 0; i < a.length - 1; i++) {
	        if (a[i] > a[i + 1]) {
	            return false; // It is proven that the array is not sorted.
	        }
	    }

	    return true; // If this part has been reached, the array must be sorted.
	}
	
	

	// https://www.geeksforgeeks.org/linear-search/
	public static int linearSearch(int list[], int key) {
		
        int n = list.length;  // getting the length of the list
        
        for (int i = 0; i < n; i++) {  // seeing if the key is in the length
            if (list[i] == key)  // if the key is in the list then return the place that the key was found
                return i;
        }
        return -1;  // else return -1
        
    }  // linear search 
	
	
	
	
	public static int binarySearch(int list[], int key) {  // recursive binary search
		return binarySearchRecursive(list, 0, list.length - 1, key);
	}
	
	// https://www.geeksforgeeks.org/binary-search/
	public static int binarySearchRecursive(int list[], int l, int r, int key) {
        if (r >= l) {
        	
            int mid = l + (r - l) / 2;
 
            // If the element is present at the
            // middle itself
            if (list[mid] == key)
                return mid;
 
            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (list[mid] > key)
                return binarySearchRecursive(list, l, mid - 1, key);
 
            // Else the element can only be present
            // in right subarray
            return binarySearchRecursive(list, mid + 1, r, key);
        }
 
        // We reach here when element is not present
        // in array
        return -1;
    }  // binary search
	
	
	
	public static int interpolationSearch(int list[], int key) {  // recursive interpolationSearch
		return interpolationSearchRecursive(list, 0, list.length - 1, key);
	}
	
	// https://www.geeksforgeeks.org/interpolation-search/
    public static int interpolationSearchRecursive(int list[], int lo, int hi, int x) {
    	// Since array is sorted, an element
        // present in array must be in range
        // defined by corner
    	int pos;
    	
        if (lo <= hi && x >= list[lo] && x <= list[hi]) {
 
            // Probing the position with keeping
            // uniform distribution in mind.
            pos = lo + (((hi - lo) / (list[hi] - list[lo])) * (x - list[lo]));
 
            // Condition of target found
            if (list[pos] == x)
                return pos;
 
            // If x is larger, x is in right sub array
            if (list[pos] < x)
                return interpolationSearchRecursive(list, pos + 1, hi, x);
 
            // If x is smaller, x is in left sub array
            if (list[pos] > x)
                return interpolationSearchRecursive(list, lo, pos - 1, x);
        }
        return -1;
    }  // interpolationSearch
    
    
    
    // https://www.geeksforgeeks.org/jump-search/
    public static int jumpSearch(int list[], int key) {
        int n = list.length;
 
        // Finding block size to be jumped
        int step = (int)Math.floor(Math.sqrt(n));
 
        // Finding the block where element is
        // present (if it is present)
        int prev = 0;
        while (list[Math.min(step, n)-1] < key) {
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }
 
        // Doing a linear search for x in block
        // beginning with prev.
        while (list[prev] < key) {
            prev++;
 
            // If we reached next block or end of
            // array, element is not present.
            if (prev == Math.min(step, n))
                return -1;
        }

        // If element is found
        if (list[prev] == key)
            return prev;
 
        return -1;
    }  // jump search
    
    
    
    // https://www.delftstack.com/howto/java/java-array-indexof/
    public static int javaSearch(int[] list, int key) {
    	
    	int index = IntStream.range(0, list.length).
    			filter(i -> key == list[i]).
                findFirst().orElse(-1);
    	
    	return index;
    }
    
    
    
    // https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
    public main() {
        frame = new JFrame("Bar Graph for Search methods");  // title of graph
        frame.setSize(500, 420);  // size
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.add(new DrawBars(frame.getSize()));
        frame.pack();
        frame.setLocationRelativeTo(frame);  // set location to center
        frame.setVisible(true);  // seeing the graph
    }
    
      
    
    public static void main(String... argv) throws FileNotFoundException {
    	
    	PrintStream o = new PrintStream(new File("Log.txt"));
        PrintStream console = System.out;
        System.setOut(o);
    	
    	int max = 1000;  // max number for array
    	int size = 500;  // the number of input for array
    	int trial = 100;  // number of trials for for loop
    	
        for(int i=0; i<trial; i++) {
        	
            int key = (int)Math.floor(Math.random()*(max - 1 + 1) + 1);  // getting a random number from max input
            int list[] = {randomList(max, size, true)};
            
            for(int j=0; j<list.length; j++) {
            	System.out.print(list[j] + " ");
            }
            
            System.out.println();
            System.out.print("The key is " + key + "\n\n");
         
        	long startTime = System.nanoTime();   // start time
        	linearSearch(list, key);
        	long endTime1 = System.nanoTime();  // end time for linear search
        	total1 = (total1 + (endTime1 - startTime))/trial;
        	
        	binarySearch(list, key);
        	long endTime2 = System.nanoTime();  // end time for binary search
        	total2 = (total2 + (endTime2 - startTime))/trial;
        	
        	interpolationSearch(list, key);
        	long endTime3 = System.nanoTime();  // end time for interpolation search
        	total3 = (total3 + (endTime3 - startTime))/trial;
        	
        	jumpSearch(list, key);
        	long endTime4 = System.nanoTime();  // end time for jump search
        	total4 = (total4 + (endTime4 - startTime))/trial;
        	
        	javaSearch(list, key);
        	long endTime5 = System.nanoTime();  // end time for java search
        	total5 = (total5 + (endTime5 - startTime))/trial;	      
        }
        
        System.out.print("   Searches        Times of Each Search\n"
        		+ "-------------     ----------------------");
        System.out.print("\nLinear Search             0." + total1 + "s");  // Linear
        System.out.print("\nBinary Search             0." + total2 + "s");  // Binary
        System.out.print("\nInterpolation Search      0." + total3 + "s");  // Interpolation
        System.out.print("\nJump Search               0." + total4 + "s");  // Jump
        System.out.print("\nJava Search               0." + total5 + "s");  // java

        System.setOut(console);

        new main();
    }
    
    
    
    // https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
    public static class DrawBars extends JPanel {   

        public DrawBars(Dimension dimension) { 
            setSize(dimension);
            setPreferredSize(dimension);
        }

        // https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;  //g2 is the graphics object that we need to use
                                            //to draw things to the screen
            Dimension d = getSize();
            //create a background
            g2.setColor(Color.white);
            g2.fillRect(0, 0, d.width, d.height);
            

            Color purple = new Color(100, 0, 102);  // Linear Search 
            Color green = new Color(10,255, 102);  // Binary Search 
            Color blue = new Color(0, 0, 102);  // Interpolation Search  
            Color brown = new Color(200, 150, 102);  // Jump Search
            Color red = new Color(250, 100, 102);  // Java Search
  
            for(int i = 0; i < 1; i++){  // linear search bar
                g2.setColor(purple);
                g2.fillRect(i*30 + 40, 360-(int)(total1 * 2), 50 ,(int)(total1 * 2));
            }
            
            for(int i = 0; i < 1; i++){  // binary search bar
                g2.setColor(green);
                g2.fillRect(i*30 + 130, 360-(int)(total2 * 2), 50 ,(int)(total2 * 2));
            }
            
            for(int i = 0; i < 1; i++){  // interpolation search bar
                g2.setColor(blue);
                g2.fillRect(i*30 + 220, 360-(int)(total3 * 2), 50 ,(int)(total3 * 2));
            }
            
            for(int i = 0; i < 1; i++){  // jump search bar
                g2.setColor(brown);
                g2.fillRect(i*30 + 310, 360-(int)(total4 * 2), 50 ,(int)(total4 * 2));
            }
            
            for(int i = 0; i < 1; i++){  // java search bar
                g2.setColor(red);
                g2.fillRect(i*30 + 400, 360-(int)(total5 * 2), 50 ,(int)(total5 * 2));
            }
                    
            g2.setFont (new Font("Times New Roman", Font.BOLD, 12));

            g2.setColor(purple);
            g2.drawString("Linear Search" ,  25,375);
            
            g2.setColor(green);
            g2.drawString("Binary Search" ,  110,375);
            
            g2.setColor(blue);
            g2.drawString("Interpolation Search" ,  190,375);
            
            g2.setColor(brown);
            g2.drawString("Jump Search" ,  304,375);
            
            g2.setColor(red);
            g2.drawString("Java Search" ,  390,375);
        }
    }
}
