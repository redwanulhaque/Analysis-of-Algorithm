import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class main {
	
	static long total1 = 0;  // native
	static long total2 = 0;  // brute force
	static long total3 = 0;  // KMP
	static long total4 = 0;  // rabin
	
    private JFrame frame;
    
	
	// https://www.codegrepper.com/code-examples/java/how+to+generate+random+letters+in+java
	public static String randomText( int n) {
		
		Random random = new Random();
		String text = "";
		System.out.print("Random Text: ");
		
    	for(int i=0; i<n; i++) {

            char randomizedCharacter = (char) (random.nextInt(26) + 'A');
            text = String.valueOf(randomizedCharacter); 
            System.out.print(text);
    	}
		return text;		
	}
	
	
	// https://stackoverflow.com/questions/46416417/split-a-string-variable-into-a-random-substring-with-a-specified-length
    public static String randomPattern (String text, int length) {
    	Random rand = new Random();
    		
    		int randomNum = rand.nextInt(text.length() - length + 1);
    		String answer = text.substring(randomNum, randomNum + length);
    		System.out.print("Random pattern: " + answer);
			return answer;	
    }
    
    
    public static void nativeSearch(String text, String pattern) {
        
        boolean result = text.contains(pattern);
        
        int index = text.indexOf(pattern);
        while (index >= 0) {
            index = text.indexOf(pattern, index + 1);
        }
    }
	
	
	// https://www.geeksforgeeks.org/naive-algorithm-for-pattern-searching/
	public static void bruteForce(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        int index;        

        /* A loop to slide pat one by one */
        for (int i = 0; i <= N - M; i++) {
 
            int j;
 
            /* For current index i, check for pattern
              match */
            for (j = 0; j < M; j++)
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
 
            if (j == M) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                index=i;
        }
    }
	
	
	// https://www.techiedelight.com/implementation-kmp-algorithm-c-cpp-java/
    public static void knuthMorrisPratt(String text, String pattern) {
        // base case 1: pattern is null or empty
    	int index;
        if (pattern == null || pattern.length() == 0) {
            System.out.println("The pattern occurs with shift 0");
            return;
        }
 
        // base case 2: text is NULL, or text's length is less than that of pattern's
        if (text == null || pattern.length() > text.length()) {
            System.out.println("Pattern not found");
            return;
        }
 
        char[] chars = pattern.toCharArray();
 
        // next[i] stores the index of the next best partial match
        int[] next = new int[pattern.length() + 1];
        for (int i = 1; i < pattern.length(); i++) {
            int j = next[i + 1];
 
            while (j > 0 && chars[j] != chars[i]) {
                j = next[j];
            }
 
            if (j > 0 || chars[j] == chars[i]) {
                next[i + 1] = j + 1;
            }
        }
 
        for (int i = 0, j = 0; i < text.length(); i++) {
            if (j < pattern.length() && text.charAt(i) == pattern.charAt(j)) {
                if (++j == pattern.length()) {
                    index= (i - j + 1);
                }
            }
            else if (j > 0) {
                j = next[j];
                i--;    // since `i` will be incremented in the next iteration
            }
        }
    }
	
	
	// https://gist.github.com/sulavtimsina/ca4659ce0674cea3f92af990c01c7198
    public static void rabinKarp(String txt, String pat) {
    	int d=256;
        int q = 101; // A prime number
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = 1;  
        int index;
     
        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M-1; i++)
            h = (h*d)%q;
     
        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < M; i++) {
            p = (d*p + pat.charAt(i))%q;
            t = (d*t + txt.charAt(i))%q;
        }
     
        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++) {
     
            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters one by one
            if ( p == t ) {
                /* Check for characters one by one */
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i+j) != pat.charAt(j))
                        break;
                }
     
                if (j == M)
                    index = i;
            }
     
            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if ( i < N-M ) {
                t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;
     
                // We might get negative value of t, converting it
                // to positive
                if (t < 0)
                t = (t + q);
            }
        }
    }
    
    
    // https://gist.github.com/landone911/4b57cbbde560ae2e7bfadacce1075a04
    public main(int trial) {
        frame = new JFrame(trial + " Trials for Search Algorithm");  // title of graph
        frame.setSize(840, 770);  // size
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.add(new DrawBars(frame.getSize()));
        frame.pack();
        frame.setLocationRelativeTo(frame);  // set location to center
        frame.setVisible(true);  // seeing the graph
    }
    
    
    public static void print(int trial, int length, String text) {
    	
    	String pattern = randomPattern(text, length);
    	
    	for(int x=0; x<trial; x++) {
  			
            long startTime = System.nanoTime();
            
      		nativeSearch(text, pattern);
          	long endTime1 = System.nanoTime();  
          	total1 = (total1 + (endTime1 - startTime))/trial;

          	bruteForce(text, pattern);
      		long endTime2 = System.nanoTime(); 
          	total2 = (total2 + (endTime2 - startTime))/trial;
      		
          	knuthMorrisPratt(text, pattern);
      		long endTime3 = System.nanoTime();
          	total3 = (total3 + (endTime3 - startTime))/trial;
      		
          	rabinKarp(text, pattern);
      		long endTime4 = System.nanoTime(); 
          	total4 = (total4 + (endTime4 - startTime))/trial;
    	}
    	
    	System.out.print("\n\n              Trials #" + trial
  				+ "\n            -------------");
  		
  		System.out.print("\n    Search         Times of Each Search\n"
          		+ "-------------     ----------------------");
          System.out.print("\nNative Search              0." + total1 + "s");  // java
          System.out.print("\nBrute Force                0." + total2 + "s");  // bubble
          System.out.print("\nKnuthMorrisPratt           0." + total3 + "s");  // selection
          System.out.print("\nRabinKarp                  0." + total4 + "s\n\n");  // insertion
          
    }
    
    
	public static void main(String... argv) throws FileNotFoundException {
		
    	PrintStream o = new PrintStream(new File("Log3.txt"));
        PrintStream console = System.out;
        System.setOut(o);

		String text = "ONCEILIKETOLIKEOMYOWNSOOSDASHAREMYSELFVALUEWITHNOONEWITHTHEPOWRERTOCHANGETHEWORLD";
		print(50, 5, text);
        new main(50);  // for trial 50 graph
        
		String text2 = "ADDKOOFKAOKFKOKOAFKOKWOKOWFKOKOFSKAOFKOKWFOKOKFOAKFOKOKOKOAKOFKOASKFOKOKFOWQKOFKOFKAOFKOKAOFOKWFOKOFKFOKAOFKOF";
		print(400, 7, text2);
        new main(400);  // for trial 400 graph
        
		String text3 = "ANHONESTMANHEHADBEENINALLTHEKNOWNACTSOFHISLIFEINHISWORDSINHISEXAMPLESHISATTITUDE"
				+ "HISBEHAVIOR,HISENTERPRISES,INTHECUTOFHISBEARDANDTHESHAPEOFHISHATS.HENEVERHADSAIDAWORDTHATDIDNOTSETANEXAMPLE"
				+ "NEVERHADGIVENANALMSWITHOUTADDINGAWORDOFADVICE,NEVERHADEXTENDEDHISHANDWITHOUTAPPEARINGTOBESTOWABENEDICTI";
		print(700, 10, text3);
        new main(700);  // for trial 700 graph
        
        System.setOut(console);
    }
	
	
	public static void bars(long average, int size, Graphics2D g2, Color color) {  // print bars to the graph
		
		for(int i=0; i<=4; i++) {
		for(int j = 0; j < 1; j++){ 
            g2.setColor(color);
            g2.fillRect(j*30 + 10 + size, 700-(int)(average * 2), 100 ,(int)(average * 2));
            
			}
        }
	}
	
	public static void name(Graphics2D g2, String name, Color color, int shift) {  // getting the bar to appear on the graph
		
		g2.setFont (new Font("Times New Roman", Font.BOLD, 16));
		
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
            Color b = new Color(10,115, 12);  
            Color c = new Color(0, 0, 102);  
            Color d = new Color(200, 150, 102); 

            bars(total1, 50, g2, a);  // native
            bars(total2, 250, g2, b);  // brute force
            bars(total3, 450, g2, c);  // KMP
            bars(total4, 650, g2, d);  // rabin
            
            name(g2, "Native Search", a, 60);
            name(g2, "Brute Force", b, 270);
            name(g2, "Knuth Morris Pratt",c, 446);
            name(g2, "Rabin-Karp", d, 675);
        }
    }
}
