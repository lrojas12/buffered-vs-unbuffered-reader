import java.util.*;
import java.io.*;

 public class UnbufCount {
	 public void run() {
		try {
			InputStream in = new FileInputStream("dict.txt");
			Reader reader = new InputStreamReader(in, "UTF8");
			
			int[] histogram = new int[26]; 
			long startTime = System.currentTimeMillis();

			
			int lastChar = getLastCharOfLine(reader);
			while(lastChar != -1) {
				
				//Convert from int to lowercase char
				Character actualLastChar = (char)lastChar;
				actualLastChar = Character.toLowerCase(actualLastChar);
				lastChar = actualLastChar;
				//get the last character of the line
				
				if (Character.isLowerCase(lastChar))
					histogram[lastChar - 'a']++;
				/* 'a' - 'a' == 0
				therefore if lastChar == a
				then we will increment histogram[0]
				*/
				
				lastChar = getLastCharOfLine(reader);
				
			}
			
			long duration = System.currentTimeMillis() - startTime;

			//print time
			System.out.println("Took: " + duration + " ms");

			//print histogram
			printHistogram(histogram);
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch(UnsupportedEncodingException e) {
			System.out.println("Unsupported Encoding Exception");
		}
	 }
	 
	 public void printHistogram(int[] h) {
		 for(char c = 'A'; c <= 'Z'; c++) {
			 System.out.println(c + ": " + h[c-'A']);
		 }
		 /* //equivalent to below
		 for(int i = 0; i < 26; i++) {
			 char current = 'A' + i;
			 System.out.println(current + h[i]);
		 }
		 */		 
	 }
	 
	 private int getLastCharOfLine(Reader r) {
		 //A line in the file is structured as:
		 // WORD\r\n (ends with CarriageReturn and NewLine)
		 try {
			 //read the next character in the file
			 int current = r.read();
			 
			//assign a sentinel value to prev so we can tell if it was never updated
			// if prev is never updated and current == -1 then we are at the end of file
			 int prev = -1;
			 
			 //Keep stepping through the file char by char until we see the END_OF_FILE, NEWLINE, or CARRIAGE_RETURN
			 while (current != -1 && current != '\n' && current != '\r') {
				 //create a sliding window of previous and current
				 prev = current;
				 current = r.read();
			 }

			 //move the location pointer (bookmark) forward 1 character to throw away the \n after \r
			 r.read();
			 
			 //if prev==-1 then the while loop never ran (we are at end of file)
			 if (prev == -1 && current == -1) {
				 //reached end of file
				 return -1;
			 }
			 
			 //if current represents the first end-of-line character, prev contains the last character of the line
			 //return the last character before the end of line
			 return prev;
		} catch (Exception e) {
			System.out.println("ERROR");
			return -1;
		}
	 }
 }