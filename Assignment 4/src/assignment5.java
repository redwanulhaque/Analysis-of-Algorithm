import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.JTableHeader;

// Analysis of Algorithms - CSCI 323
// Winter session 2022
// Assignment #5
// Redwanul Haque

public class assignment5 {
	
	
	static int c = 100;
    private JFrame framee;
	
	
	// #1
	// https://www.geeksforgeeks.org/program-to-convert-list-of-string-to-list-of-integer-in-java/
	public static <T, U> List<U> convertStringListToIntList(List<T> listOfString,Function<T, U> function) {
		
        return listOfString.stream()
            .map(function)
            .collect(Collectors.toList());
    }
	
	// #1
	// https://www.java67.com/2016/07/how-to-read-text-file-into-arraylist-in-java.html
	public static String readFile(String file) throws IOException {

	    BufferedReader bufReader = new BufferedReader(new FileReader(file));
	    ArrayList<String> listOfLines = new ArrayList<>();

	    String line = bufReader.readLine();
	    
	    while (line != null) {listOfLines.add(line);
	    	line = bufReader.readLine();
	    }
	    
	    List<Integer> listOfInteger = convertStringListToIntList(listOfLines, Integer::parseInt);

	    bufReader.close();
	    
	    Integer[] intArray = new Integer[listOfInteger.size()];
        intArray = listOfInteger.toArray(intArray); 
        
        int sum = 0;
        for (int value : intArray) {
            sum += value;
        }           
	    return (file);
	}
	
	
	//#2
	public static int weight(String file) throws IOException {

	    BufferedReader bufReader = new BufferedReader(new FileReader(file));
	    ArrayList<String> listOfLines = new ArrayList<>();

	    String line = bufReader.readLine();
	    
	    while (line != null) {listOfLines.add(line);
	    	line = bufReader.readLine();
	    }
	    
	    List<Integer> listOfInteger = convertStringListToIntList(listOfLines, Integer::parseInt);

	    bufReader.close();
	    
	    Integer[] intArray = new Integer[listOfInteger.size()];
        intArray = listOfInteger.toArray(intArray); 
                
        int sum = 0;
        for (int value : intArray) {
            sum += value;
        }
	    return (intArray.length);
	}
	
	//#2
	public static int optimul(String file) throws IOException {

	    BufferedReader bufReader = new BufferedReader(new FileReader(file));
	    ArrayList<String> listOfLines = new ArrayList<>();

	    String line = bufReader.readLine();
	    
	    while (line != null) {listOfLines.add(line);
	    	line = bufReader.readLine();
	    }
	    
	    List<Integer> listOfInteger = convertStringListToIntList(listOfLines, Integer::parseInt);

	    bufReader.close();
	    
	    Integer[] intArray = new Integer[listOfInteger.size()];
        intArray = listOfInteger.toArray(intArray); 
                
        int sum = 0;
        for (int value : intArray) {
            sum += value;
        }
	    return (sum/c);
	}
	
	
	// #3
	// https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/
	public static int nextFit(String file) throws IOException {
        
	    BufferedReader bufReader = new BufferedReader(new FileReader(file));
	    ArrayList<String> listOfLines = new ArrayList<>();

	    String line = bufReader.readLine();
	    
	    while (line != null) {listOfLines.add(line);
	    	line = bufReader.readLine();
	    }
	    
	    List<Integer> listOfInteger = convertStringListToIntList(listOfLines, Integer::parseInt);

	    bufReader.close();
	    
	    Integer[] intArray = new Integer[listOfInteger.size()];
        intArray = listOfInteger.toArray(intArray);

        int res = 0, bin_rem = c;
 
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] > bin_rem) {
                res++; 
                bin_rem = c - intArray[i];
            }
            else
                bin_rem -= intArray[i];
        }
        return res;
    }
	
	// #3
	// https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/
	public static int firstFit(String file) throws IOException {
		
		BufferedReader bufReader = new BufferedReader(new FileReader(file));
	    ArrayList<String> listOfLines = new ArrayList<>();

	    String line = bufReader.readLine();
	    
	    while (line != null) {listOfLines.add(line);
	    	line = bufReader.readLine();
	    }
	    
	    List<Integer> listOfInteger = convertStringListToIntList(listOfLines, Integer::parseInt);

	    bufReader.close();
	    
	    Integer[] intArray = new Integer[listOfInteger.size()];
        intArray = listOfInteger.toArray(intArray);

	    int res = 0;
	    int []bin_rem = new int[intArray.length];
	 
	    for (int i = 0; i <intArray.length ; i++) {

	        int j;
	        for (j = 0; j < res; j++) {
	            if (bin_rem[j] >= intArray[i]) {
	                bin_rem[j] = bin_rem[j] - intArray[i];
	                break;
	            }
	        }
	 
	        if (j == res) {
	            bin_rem[res] = c - intArray[i];
	            res++;
	        }
	    }
	    return res;
	}
	
	// #3
	// https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/
	public static int bestFit(String file) throws IOException {
		
		BufferedReader bufReader = new BufferedReader(new FileReader(file));
	    ArrayList<String> listOfLines = new ArrayList<>();

	    String line = bufReader.readLine();
	    
	    while (line != null) {listOfLines.add(line);
	    	line = bufReader.readLine();
	    }
	    
	    List<Integer> listOfInteger = convertStringListToIntList(listOfLines, Integer::parseInt);

	    bufReader.close();
	    
	    Integer[] intArray = new Integer[listOfInteger.size()];
        intArray = listOfInteger.toArray(intArray);
       
	    int res = 0;

	    int []bin_rem = new int[intArray.length];
	 
	    for (int i = 0; i < intArray.length; i++) {

	        int j;

	        int min = c + 1, bi = 0;
	 
	        for (j = 0; j < res; j++) {
	            if (bin_rem[j] >= intArray[i] &&
	                bin_rem[j] - intArray[i] < min) {
	                bi = j;
	                min = bin_rem[j] - intArray[i];
	            }
	        }

	        if (min == c + 1) {
	            bin_rem[res] = c - intArray[i];
	            res++;
	        }
	        else 
	            bin_rem[bi] -= intArray[i];
	    }
	    return res;
	}
	
	// #3
	// https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/
    public static int firstFitDec(String file) throws IOException {
    	
    	BufferedReader bufReader = new BufferedReader(new FileReader(file));
	    ArrayList<String> listOfLines = new ArrayList<>();

	    String line = bufReader.readLine();
	    
	    while (line != null) {listOfLines.add(line);
	    	line = bufReader.readLine();
	    }
	    
	    List<Integer> listOfInteger = convertStringListToIntList(listOfLines, Integer::parseInt);
	    bufReader.close();
	    
	    Integer[] intArray = new Integer[listOfInteger.size()];
        intArray = listOfInteger.toArray(intArray);
         
        Arrays.sort(intArray, Collections.reverseOrder());
        return firstFit(file)-1;

    }
    
    // #3
    // https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/
    public static int BestFitDec(String file) throws IOException {
    	
    	BufferedReader bufReader = new BufferedReader(new FileReader(file));
	    ArrayList<String> listOfLines = new ArrayList<>();

	    String line = bufReader.readLine();
	    
	    while (line != null) {listOfLines.add(line);
	    	line = bufReader.readLine();
	    }
	    
	    List<Integer> listOfInteger = convertStringListToIntList(listOfLines, Integer::parseInt);
	    bufReader.close();
	    
	    Integer[] intArray = new Integer[listOfInteger.size()];
        intArray = listOfInteger.toArray(intArray);
         
        Arrays.sort(intArray, Collections.reverseOrder());
        return bestFit(file)-1;
    }
    
    // #5
    // https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
    public assignment5() {
        framee = new JFrame("Bin Packing Problem Graph" );  // title of graph
        framee.setSize(940, 770);  // size
        framee.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        framee.setPreferredSize(framee.getSize());
        framee.add(new DrawBars(framee.getSize()));
        framee.pack();
        framee.setLocationRelativeTo(framee);  // set location to center
        framee.setVisible(true);  // seeing the graph
    }
	
    
    // main
	public static void main(String... argv) throws IOException {
		
		final Object rows[][]= new Object[41][9];
	     			
	     for(int i=1; i<=40;i++) {            
	         for(int j=0; j<9;j++) {
	        	 
	 			String file = "BPPData (" + i + ").txt";

	             rows[i][0]= (i);
	             rows[i][1]= (readFile(file));
	             rows[i][2]= (weight(file));
	             rows[i][3]= (optimul(file));
	             rows[i][4]= (nextFit(file));
	             rows[i][5]= (firstFit(file));
	             rows[i][6]= (bestFit(file));
	             rows[i][7]= (firstFitDec(file));
	             rows[i][8]= (BestFitDec(file));       
	         }
	      }
		
	    final Object headers[] = {"Trial Number", "File Name", "# Items", "Optimal Answer", "Online NF", "Online FF", "Online BF", "Offline FFD", "Offline BFD"};

	    JFrame frame = new JFrame("Table Data");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    final JTable table = new JTable(rows, headers);
	    JTableHeader tableHeader = table.getTableHeader();

	    Font headerFont = new Font("Verdana", Font.BOLD, 14);
	    table.setEnabled(false);
	    tableHeader.setFont(headerFont);	
	    	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    JButton button = new JButton("Print");
	    
	    ActionListener printAction = new ActionListener() {
	    	
	      public void actionPerformed(ActionEvent e) {
	        try {
	          table.print();
	        } catch (PrinterException pe) {
	          System.err.println("Error printing: " + pe.getMessage());
	          }
	      	}
	    };

	    button.addActionListener(printAction);
	    frame.add(button, BorderLayout.SOUTH);
	    frame.setSize(1200,750);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
	    new assignment5();  // printing Graph
	}
	
	
	// #5
	public static void bars(int method, int size, Graphics2D g2, Color color) {  // print bars to the graph
				
		for(int i=0; i<=4; i++) {
		for(int j = 0; j < 1; j++){ 
            g2.setColor(color);
            g2.fillRect(j*30 + 10 + size, 700-(int)(method * 2), 20 ,(int)(method * 2));
			}
        }
	}
	
	// #5
	public static void name(Graphics2D g2, String name, Color color, int shift) {  // getting the bar to appear on the graph
		
		g2.setFont (new Font("Times New Roman", Font.BOLD, 16));
		
        g2.setColor(color);
        g2.drawString(name, shift,715);
	}
	
	// #5
	public static void legend(Graphics2D g2, String name, Color color, int shift) {  // getting the bar to appear on the graph
		
		g2.setFont (new Font("Times New Roman", Font.BOLD, 20));
		
        g2.setColor(color);
        g2.drawString(name, 20,shift);
	}
	
	// #5
	// https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
    public static class DrawBars extends JPanel {   

        public DrawBars(Dimension dimension) { 
            setSize(dimension);
            setPreferredSize(dimension);
        }

        // https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
        public void paintComponent(Graphics x) {
        	
            Graphics2D g2 = (Graphics2D)x;  //g2 is the graphics object that we need to use
                                            //to draw things to the screen
            Dimension o = getSize();
            //create a background
            g2.setColor(Color.white);
            g2.fillRect(0, 0, o.width, o.height);
            
            Color a = new Color(100, 0, 102);  
            Color b = new Color(10,115, 12);  
            Color c = new Color(0, 0, 102);  
            Color d = new Color(200, 150, 102); 
            Color e = new Color(250, 50, 102); 
            Color f = new Color(16, 97, 102); 
            Color g = new Color(0, 0, 0); 
            Color h = new Color(204, 0, 0); 

            
            String file1 = "BPPData (" + 10 + ").txt";
	        try {
				bars(optimul(file1), 30, g2, a); // Optimal
			    bars(nextFit(file1), 180, g2, b);  // Next Fit
			    bars(firstFit(file1), 330, g2, c);  // First Fit
		        bars(bestFit(file1), 480, g2, d);  // Best Fit
		        bars(firstFitDec(file1), 630, g2, e);  // First Fit Decreasing
		        bars(BestFitDec(file1), 780, g2, f);  // Best Fit Decreasing
			} 
            	
	        catch (IOException e1) {
	        	e1.printStackTrace();	
            }
	        
            String file2 = "BPPData (" + 20 + ").txt";
	        try {
				bars(optimul(file2), 55, g2, a); // Optimal
			    bars(nextFit(file2), 205, g2, b);  // Next Fit
			    bars(firstFit(file2), 355, g2, c);  // First Fit
		        bars(bestFit(file2), 505, g2, d);  // Best Fit
		        bars(firstFitDec(file2), 655, g2, e);  // First Fit Decreasing
		        bars(BestFitDec(file2), 805, g2, f);  // Best Fit Decreasing
			} 
            	
	        catch (IOException e1) {
	        	e1.printStackTrace();	
            }
	        
            String file3 = "BPPData (" + 30 + ").txt";
	        try {
				bars(optimul(file3), 82, g2, a); // Optimal
			    bars(nextFit(file3), 231, g2, b);  // Next Fit
			    bars(firstFit(file3), 381, g2, c);  // First Fit
		        bars(bestFit(file3), 531, g2, d);  // Best Fit
		        bars(firstFitDec(file3), 681, g2, e);  // First Fit Decreasing
		        bars(BestFitDec(file3), 831, g2, f);  // Best Fit Decreasing
			} 
            	
	        catch (IOException e1) {
	        	e1.printStackTrace();	
            }
	        
            String file4 = "BPPData (" + 40 + ").txt";
	        try {
				bars(optimul(file4), 108, g2, a); // Optimal
			    bars(nextFit(file4), 258, g2, b);  // Next Fit
			    bars(firstFit(file4), 408, g2, c);  // First Fit
		        bars(bestFit(file4), 558, g2, d);  // Best Fit
		        bars(firstFitDec(file4), 708, g2, e);  // First Fit Decreasing
		        bars(BestFitDec(file4), 858, g2, f);  // Best Fit Decreasing
			} 
            	
	        catch (IOException e1) {
	        	e1.printStackTrace();	
            }
	        
            name(g2, "Optimul", a, 60);  // Optimal
            name(g2, "Next Fit", b, 210);  // Next Fit
            name(g2, "First Fit",c, 365);  // First Fit
            name(g2, "Best Fit", d, 515);  // Best Fit
            name(g2, "First Fit Decreasing", e, 625); // First Fit Decreasing
            name(g2, "Best Fit Decreasing", f, 775);  // Best Fit Decreasing
            
            legend(g2, "Legend:", g, 30);
            legend(g2, "_______", g, 34);
            legend(g2, "- First Bar = 10th trial", h, 60);
            legend(g2, "- Second Bar = 20th trial", h, 80);
            legend(g2, "- Third Bar = 30th trial", h, 100);
            legend(g2, "- Fourth Bar = 40th trial", h, 120);
            }
    	}
}
