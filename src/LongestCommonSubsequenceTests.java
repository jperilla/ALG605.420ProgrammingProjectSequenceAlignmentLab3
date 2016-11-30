/**
 * Julie Garcia
 * 
 * Copyright (c) Julie Garcia 2016
 * 
 * ALG 605.420.81.FA16
 * 
 * Programming Project #3 - Longest Common Subsequence Lab
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang3.time.StopWatch;

/** 
 * 
 * @author Julie Garcia
 * 
 * @version 1.0.0 20 November 2016
 * 
 * This class LongestCommonSubsequenceTests reads in a list of string
 * into an array and finds the longest common subsequence between
 * each pair of strings using the LongestCommonSubsequence class.
 * This class runs two different strategies
 * for comparing the strings, and times each of them in order to 
 * compare the difference. The first strategy is a naive strategy
 * that compares the strings by using recursion to find every
 * single possible combination (see analysis doc for more explanation).
 * The other strategy uses dynamic programming in a bottom up fashion
 * to cut out redundancies and speed up the comparison.
 * 
 * The main function takes as arguments the input file name
 * or path (if it's in a different directory) and an optional
 * argument "-n" if the naive option is to be run. The naive 
 * option should only be run if you have a lot of time, or if 
 * your strings are very small (less than 20 characters).
 * 
 * The strings are outputted to a file of the same name
 * as the input file, but with the postfix "Output.txt" and
 * is saved in the same directory as the input file. Also in 
 * the output file is the LCS - the longest common subsequence between
 * each string, it's length, and the percent match that the lcs has
 * with each original string.
 * 
 * For information about how inputfile should be formatted,
 * and detailed instructions on how to run this program,
 * please see the README.md in the application's root directory.
 */
public class LongestCommonSubsequenceTests {
	
	/***
	 * This main function takes two parameters, reads in the data
	 * and processes the longest common subsequence. By default,
	 * it runs the dynamic programming solution (which is much faster).
	 * The naive option should only be run if you have a lot of time, or if 
	 * your strings are very small. If your strings are longer than
	 * 15, you will need more than 10 minutes to run the program.
	 * 
	 * @param arg[0] (required) the input file name or path (if it's 
	 * in a different directory) 
	 * @param arg[1] (optional) argument "-n" if the naive option is 
	 * to be run. 
	 */
	public static void main(String[] args) {

		// Validate input
        if (args.length < 1) {
            throw new IllegalArgumentException(
            			"Path to input file must be entered as argument.");
        }
    
        try {
	        // Read in input file name
	        String inputFileName = args[0];
	        BufferedReader br = FileIO.readInputFile(inputFileName);
	        
	        // If option -n run naive and dynamic solution, default runs dynaic only
	        boolean runNaive = false;
	        if(args.length > 1 && args[1].equalsIgnoreCase("-n")) {
	        	runNaive = true;
	        }
	
	        // Setup output file
	        File outputFile = FileIO.setupOutputFile(inputFileName, "Julie Garcia Longest Common Subsequence Lab #3");
	        
	        // The main work is done in here
	        processInput(br, outputFile, runNaive);
	        
	        System.out.println("Success!");
	        
        } catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not read the file: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
        
	}

	/***
	 * This function takes the buffered reader of the inputfile, 
	 * reads the list of strings into an array, and compares each
	 * pair of strings to find the longest common subsequence using 
	 * a dynamic programming solution by default, and optionally runs
	 * a naive strategy for comparison.
	 * 
	 * @param br The buffered reader of the input file to read from
	 * @param outputFile The output file to write to
	 * @param runNaive Run naive solution (true), or not (false)
	 * @throws IOException
	 */
	private static void processInput(BufferedReader br, File outputFile, boolean runNaive) throws IOException {
		
		String strLine;
		ArrayList<String> arrayOfStrings = new ArrayList<String>();
		StopWatch timer = new StopWatch();
		
    	// Read strings into an array
		while ((strLine = br.readLine()) != null) {
			if(strLine != null && !strLine.isEmpty()) {
				String strToAdd = strLine.substring(strLine.indexOf('=') + 1, strLine.length());
				arrayOfStrings.add(strToAdd.toUpperCase().trim());
			}
		}
		
		// Loop through each string
		for (int i=0; i < arrayOfStrings.size(); i++) {
			
			// Loop through each string again, comparing
			for(int j=i; j < arrayOfStrings.size(); j++) {
				
				if(i != j) {  // Don't compare the string with itself
					String str1 = arrayOfStrings.get(i);
					String str2 = arrayOfStrings.get(j);

					// Write strings to output file "Compare S1 = ... to S2 = ..."
					FileIO.writeCarriageReturnToFile(outputFile);
					FileIO.writeLineToOutputFile(outputFile, "Compare S" 
												+ Integer.toString(i+1) 
												+ " = " + str1);
					FileIO.writeLineToOutputFile(outputFile, "to S" 
												+ Integer.toString(j+1) 
												+ " = " + str2);
					
					// Run the Naive Solution, if requested
					if(runNaive)
						compareStringsNaiveSolution(outputFile, timer, str1, str2);
					
					// Run the dynamic solution
					compareStringsDynamicSolution(outputFile, timer, str1, str2);
					
					FileIO.writeLineToOutputFile(outputFile, 
							"----------------------------------------------------");
					
				}
			}
		}
	}
	
	/***
	 * This function takes two strings, compares them using the dynamic
	 * strategy to find the longest common subsequence, and writes
	 * the LCS, its length, and the percent match of each string to
	 * the output file. The time this solution takes is also outputed.
	 * 
	 * @param outputFile The file to write the output to
	 * @param timer The timer is passed in order to record the time
	 * of this strategy.
	 * @param str1 The first string to compare.
	 * @param str2 The second string to compare.
	 */
	private static void compareStringsDynamicSolution(File outputFile, StopWatch timer, String firstStr, String secondStr) {
		String lcsString;
		
		// Setup timer
		double percentMatchStr1 = 0, percentMatchStr2 = 0;
		timer.reset();
		timer.start();
		
		// Construct a table comparing strings
		LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);
		int [][] LCSTable = lcs.ConstructLCSTableDynamic();

		timer.stop();
	
		// Print longest common subsequence and statistics to output file
		int lcsLength = LCSTable[firstStr.length()][secondStr.length()];
		FileIO.writeCarriageReturnToFile(outputFile);
		FileIO.writeLineToOutputFile(outputFile, "LCS length = " + lcsLength);
		if(firstStr.length() > 0)  // prevent divide by zero
			percentMatchStr1 = (double)lcsLength/(double)firstStr.length() * 100.0;
		FileIO.writeLineToOutputFile(outputFile, String.format("Percent Match String 1 = %02f Percent", percentMatchStr1));
		if(secondStr.length() > 0) // prevent divide by zero
			percentMatchStr2 = (double)lcsLength/(double)secondStr.length() * 100;
		FileIO.writeLineToOutputFile(outputFile, String.format("Percent Match String 2 = %02f Percent", percentMatchStr2));
		FileIO.writeLineToOutputFile(outputFile, "Dynamic Compare Running time: " + timer);
		
		// Reset the timer
		timer.reset();
		timer.start();
		
		// Reconstruct the path, creating the LCS String, by running through the table backwards
		lcsString = lcs.FindLCSStringDynamic(LCSTable);

		// Output the actual LCS string to the file
		timer.stop();
		FileIO.writeCarriageReturnToFile(outputFile);
		FileIO.writeLineToOutputFile(outputFile, "LCS = " + lcsString);
		FileIO.writeLineToOutputFile(outputFile, "Dynamic String Construct Running time: " + timer);
		FileIO.writeCarriageReturnToFile(outputFile);
	}
	
	

	/***
	 * This function takes two strings, compares them using the naive
	 * strategy to find the longest common subsequence, and writes
	 * the LCS, its length, and the percent match of each string to
	 * the output file. The time this solution takes is also outputed.
	 * 
	 * @param outputFile The file to write the output to
	 * @param timer The timer is passed in order to record the time
	 * of this strategy.
	 * @param str1 The first string to compare.
	 * @param str2 The second string to compare.
	 */
	private static void compareStringsNaiveSolution(File outputFile, StopWatch timer, String str1, String str2) {
		timer.reset();
		timer.start();
		
		LongestCommonSubsequence lcs = new LongestCommonSubsequence(str1, str2);
		int lengthOfLcs = lcs.FindLCSLengthNaive();
		
		timer.stop();
		
		// print string numbers, strings, and longest common subsequence
		FileIO.writeCarriageReturnToFile(outputFile);
		FileIO.writeLineToOutputFile(outputFile, "LCS Length = " + lengthOfLcs);
		FileIO.writeLineToOutputFile(outputFile, "Naive String Compare Running time: " + timer);
		FileIO.writeCarriageReturnToFile(outputFile);
	}
}
