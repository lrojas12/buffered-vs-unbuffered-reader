import java.util.*;
import java.io.*;
 
 public class BufCount2 {
	 
	 /*
	 public static void main(String[] args) {
		 int buffer_size = 1000;
		 if (args.length > 0)
			buffer_size = Integer.parseInt(args[0]);
		
		//to run program 1000 times
		for(int i = 1; i <= 1000; i++) {
			BufCount bufCount = new BufCount();
			bufCount.run(buffer_size);
		}	 
	 }*/
	 	 
	 public static void main(String[] args) {
		 for (int buffer_size = 1; buffer_size <= 1000; buffer_size++) {
			BufCount bufCount = new BufCount();
			bufCount.run(buffer_size);
		 }
	 }
 }