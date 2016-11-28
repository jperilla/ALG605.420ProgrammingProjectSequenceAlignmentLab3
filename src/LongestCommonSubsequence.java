import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.time.StopWatch;

public class LongestCommonSubsequence {
	public static void main(String[] args) {

		// Validate input
        if (args.length < 1) {
            throw new IllegalArgumentException(
            			"Path to input file must be entered as argument.");
        }
    
        // Read in input file name
        String inputFileName = args[0];
        BufferedReader br = FileIO.readInputFile(inputFileName);
        
        // if option -n run naive and dynamic solution, default runs dynaic only
        boolean runNaive = false;
        if(args.length > 1 && args[1].equalsIgnoreCase("-n")) {
        	runNaive = true;
        }

        // Setup output file
        File outputFile = FileIO.setupOutputFile(inputFileName, "Julie Garcia Longest Common Subsequence Lab #3");
        
        // ProcessInput
        processInput(br, outputFile, runNaive);
        
        System.out.println("Success!");
        
	}

	private static void processInput(BufferedReader br, File outputFile, boolean runNaive) {
		
		String strLine;
		ArrayList<String> arrayOfStrings = new ArrayList<String>();
		StopWatch timer = new StopWatch();
		
        try {
        	
        	// Read strings into an array
			while ((strLine = br.readLine()) != null) {
				if(strLine != null && !strLine.isEmpty()) {
					String strToAdd = strLine.substring(strLine.indexOf('=') + 1, strLine.length());
					arrayOfStrings.add(strToAdd.trim());
				}
			}
			
			// Loop through each string
			for (int i=0; i < arrayOfStrings.size(); i++) {
				
				// Loop through each string again, comparing
				for(int j=0; j < arrayOfStrings.size(); j++) {
					
					// Don't compare the string with itself
					if(i != j) {    
						String str1 = arrayOfStrings.get(i);
						String str2 = arrayOfStrings.get(j);

						// Write strings to output file
						FileIO.writeCarriageReturnToFile(outputFile);
						FileIO.writeLineToOutputFile(outputFile, "Compare S" + Integer.toString(i+1) 
								+ " = " + str1 + " and S" + Integer.toString(j+1) + " = " + str2);
						
						// Run the Naive Solution, this runs slow for longer
						// strings, so don't run unless you have a lot of time :)
						// I added this to show comparison to the dynamic solution
						if(runNaive)
							processStringsNaiveSolution(outputFile, timer, str1, str2);
						
						processStringsDynamicSolution(outputFile, timer, str1, str2);
					}
				}
			}
				
        } catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not read the file: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	private static void processStringsDynamicSolution(File outputFile, StopWatch timer, String str1, String str2) {
		String lcs;
		double percentMatchStr1 = 0, percentMatchStr2 = 0;
		timer.reset();
		timer.start();
		lcs = longestCommonSubsequenceDynamic(str1.toCharArray(), str2.toCharArray(), 
							str1.length(), str2.length());
		timer.stop();
		// print longest common subsequence and statistics
		FileIO.writeLineToOutputFile(outputFile, "LCS = " + lcs);
		FileIO.writeLineToOutputFile(outputFile, "LCS length = " + lcs.length());
		if(str1.length() > 0)  // prevent divide by zero
			percentMatchStr1 = (double)lcs.length()/(double)str1.length() * 100.0;
		FileIO.writeLineToOutputFile(outputFile, String.format("Percent Match String 1 = %02f Percent", percentMatchStr1));
		if(str2.length() > 0) // prevent divide by zero
			percentMatchStr2 = (double)lcs.length()/(double)str2.length() * 100;
		FileIO.writeLineToOutputFile(outputFile, String.format("Percent Match String 2 = %02f Percent", percentMatchStr2));
		FileIO.writeLineToOutputFile(outputFile, "Dynamic Running time: " + timer);
	}
	
	private static String longestCommonSubsequenceDynamic(char[] firstStr, char[] secondStr, int m, int n) {
	   int[][] LCS = new int[m+1][n+1];
	   
	   // Construct the matrix, bottom up
	   for (int i=0; i<=m; i++)
	   {
	     for (int j=0; j<=n; j++)
	     {
	       if (i == 0 || j == 0)
	         LCS[i][j] = 0;
	       else if (firstStr[i-1] == secondStr[j-1]) 
	         LCS[i][j] = LCS[i-1][j-1] + 1;
	       else
	         LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
	     }
	   }
	    
	   // Reconstruct the path, by running through it backwards
	   int k = LCS[m][n];
	   char [] lcsString = new char[k];
	   int i = m;
	   int j = n;
	   while(i > 0 && j> 0) {
		   if(firstStr[i-1] == secondStr[j-1]) {
			   lcsString[--k] = firstStr[i-1];
			   i--;
			   j--;
		   }
		   else if(LCS[i-1][j] > LCS[i][j-1]) 	 
			   i = i -1;
		   else 
			   j = j - 1;
	   }
	   
	   return new String(lcsString);
	}

	private static void processStringsNaiveSolution(File outputFile, StopWatch timer, String str1, String str2) {
		timer.reset();
		timer.start();
		int lengthOfLcs = longestCommonSubsequenceNaive(str1.toCharArray(), str2.toCharArray(), 
													str1.length(), str2.length());
		timer.stop();
		// print string numbers, strings, and longest common subsequence
		FileIO.writeLineToOutputFile(outputFile, "LCS = " + lengthOfLcs);
		FileIO.writeLineToOutputFile(outputFile, "Naive Running time: " + timer);
	}
	
	// Recursive solution that compares all possible combinations
	private static int longestCommonSubsequenceNaive(char[] firstStr, char[] secondStr, int m, int n) {
		   if (m == 0 || n == 0)
		     return 0;
		   if (firstStr[m-1] == secondStr[n-1])
		     return 1 + longestCommonSubsequenceNaive(firstStr, secondStr, m-1, n-1);
		   else
		     return Math.max(longestCommonSubsequenceNaive(firstStr, secondStr, m, n-1), 
		    		 longestCommonSubsequenceNaive(firstStr, secondStr, m-1, n));
	}
}
