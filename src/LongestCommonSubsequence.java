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

/** 
 * 
 * @author Julie Garcia
 * 
 * @version 1.0.0 20 November 2016
 * 
 * This class is used to find the Longest Common Subsequence
 * in two strings. The strings are initialized in the constructor.
 * The class implements two different strategies for finding the 
 * LCS. 
 * 
 * The naive strategy, finds only the length of the LCS, 
 * and uses a recursive algorithm to compare all possible combinations
 * of substrings. This strategy repeats comparisons and is not
 * the ideal strategy.
 * 
 * The dynamic programming strategy, finds the lenght of the LCS
 * by using a bottom up approach and creating a table that
 * keeps track of the common subsequence while it loops
 * through each character of each string exactly once. This
 * table can then be used to construct the string of the LCS
 * which traces the path backwards through the table.
 * 
 * For a more detailed analysis of the two strategies, please
 * see the analysis document in the root directory of the application.
 */
public class LongestCommonSubsequence {
	
	/* Two strings to be compared */
	private char[] firstStr;
	private char[] secondStr;
	
	/***
	 * The LongestCommonSubsequence constructor initializes the two strings
	 * 
	 * @param str1 The first string to be compared
	 * @param str2 The second string to be compared
	 */
	public LongestCommonSubsequence(String str1, String str2) {
		firstStr = str1.toCharArray();
		secondStr = str2.toCharArray();
	}
	
	/***
	 * This function uses a dynamic programming approach.
	 * It takes the two strings, iterates
	 * through each character of each string, constructing
	 * a table and counting the common characters. At the end
	 * a table will be constructed that contains a path that
	 * can be followed to find the string of the LCS.
	 * 
	 * @return The table constructed that can be used
	 * to find the length of the LCS LCS[m][n]. This table
	 * can also be sent to FindLCSStringDynamic in order
	 * to find the actual common string of the LCS
	 */
	public int[][] FindLCSLengthDynamic() {
		
		int m = firstStr.length;
		int n = secondStr.length;
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
	
		return LCS;
	}
	
	/***
	 * This function uses a dynamic programming approach
	 * and take a table constructed from FindLCSStringDynamic
	 * to trace the path of the LCS String backwards.
	 * 
	 * @param LCSTable The table used to retrace the path of 
	 * the LCS and find the LCS string.
	 * @return The longest common subsequence between the two strings.
	 */
	public String FindLCSStringDynamic(int[][] LCSTable) {
		
		int i = firstStr.length;
		int j = secondStr.length;
		int k = LCSTable[i][j];
		char [] lcsString = new char[k];
		while(i > 0 && j> 0) {
			if(firstStr[i-1] == secondStr[j-1]) {
				lcsString[--k] = firstStr[i-1];
				i--;
				j--;
			}
			else if(LCSTable[i-1][j] > LCSTable[i][j-1]) 	 
				i = i -1;
			else 
				j = j - 1;
		}
		
		return new String(lcsString);
	}
	
	/***
	 * This function finds the lenght of the Longest Common
	 * Subsequence between the two strings. This function is not
	 * recommended for strings over length 20 and was implemented
	 * to demonstrate speed gains when using dynamic programming. 
	 * For a faster solution, use FindLCSLengthDynamic and
	 * FindLCSStringDynamic.
	 * 
	 * @return The length of the longest common subsequence of firstStr and secondStr
	 */
	public int FindLCSLengthNaive()
	{
		return FindLengthNaive(firstStr, secondStr, firstStr.length, secondStr.length);
	}
	
	/***
	 * This function implements the naive strategy to solve
	 * the longest common subsequence lenght. This solution uses
	 * recursion and compares all possible combinations of substrings.
	 * 
	 * @return The current length of the LCS>
	 */
	private int FindLengthNaive(char[] first, char[] second, int m, int n) {
		
		   if (m == 0 || n == 0)
		     return 0;
		   
		   if (first[m-1] == second[n-1])
		     return 1 + FindLengthNaive(first, second, m-1, n-1);
		   else
		     return Math.max(FindLengthNaive(first, second, m, n-1), 
		    		 FindLengthNaive(first, second, m-1, n));
	}
}
