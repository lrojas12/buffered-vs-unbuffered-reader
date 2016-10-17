import java.util.*;
import java.io.*;

 public class BufCount {
	 public void run(int BUFFER_SIZE) {
		try {
			InputStream in = new FileInputStream("dict.txt");
			Reader reader = new InputStreamReader(in, "UTF8");
			BufferedReader input = new BufferedReader(reader, BUFFER_SIZE);
			
			int[] histogram = new int[26]; 
			long startTime = System.currentTimeMillis();
			
			//scan through the whole file
			String line = input.readLine();
			while(line != null) {
				line = line.toLowerCase();
				//get the last character of the line
				char lastChar = line.charAt(line.length() - 1);
				
				if (Character.isLowerCase(lastChar))
					histogram[lastChar - 'a']++;
				/* 'a' - 'a' == 0
				therefore if lastChar == a
				then we will increment histogram[0]
				*/
				
				line = input.readLine();
				
			}
			
			System.out.println("Buffer size: " + BUFFER_SIZE);

			long duration = System.currentTimeMillis() - startTime;

			//print time
			System.out.println("Took: " + duration + " ms");

			//print histogram
			printHistogram(histogram);
			
			//output to file for plots
			try {
				File file = new File("C:\\Users\\100518772\\Documents\\data.txt");
				
				if (!file.exists())
					file.createNewFile();
				
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(BUFFER_SIZE + "," + duration + "\r\n");
				bw.close();
			} catch (Exception e) {
				System.out.println("ERROR");
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch(UnsupportedEncodingException e) {
			System.out.println("Unsupported Encoding Exception");
		} catch(IOException e) {
			System.out.println("IO Exception");
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
	 
 }