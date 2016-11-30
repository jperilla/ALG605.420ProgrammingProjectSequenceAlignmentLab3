/**
 * Julie Garcia
 * 
 * Copyright (c) Julie Garcia 2016
 * 
 * ALG 605.420.81.FA16
 * 
 * Programming Project #3 - Longest Common Subsequence Lab, Code reused from Lab #2
 * 
 */

import java.io.*;

/** 
 * 
 * @author Julie Garcia
 * 
 * @version 1.0.0 24 October 2016
 * 
 * This function does the I/O for reading from an input file
 * and writing to an output file.
 * 
 **/

public class FileIO {
	
	/**
	 * This function reads the file with inputFileName into a BufferedReader
	 * 
	 * @param inputFileName The name of the input file to read, 
	 * should include the path if it's not in the same directory
	 * @return The BufferedReader
	 */
	public static BufferedReader readInputFile(String inputFileName) {
        
        BufferedReader br = null;
        
        try {
        	br = new BufferedReader(new FileReader(inputFileName));
        } catch (FileNotFoundException e) {
			e.printStackTrace();
        }
		
        return br;
	}
	
	/**
	 * This function creates a new output file (if it doesn't exist)
	 * and sets up the output file with a title.
	 * 
	 * @param inputFileName The name of the input file, should include the path if it's
	 * not in the same directory
	 * @param title The title of the output file, written to the first line of the file
	 * @return The output file
	 */
	public static File setupOutputFile(String inputFileName, String title) {
		
		String outputFileName = inputFileName.substring(0, 
				inputFileName.lastIndexOf("Input.")) + "Output.txt";
		
        File outputFile = new File(outputFileName);
		
        // Initialize output file
        try (Writer writer = new BufferedWriter(
        		new FileWriter(outputFile.getAbsoluteFile()))) {
        	
        	// Check if file exists, if not create it
        	if (!outputFile.exists()) {
        		outputFile.createNewFile();
    		}
        	
        	// Title of Output file
		    writer.write(title);
		    writer.write("\r\n");
		    writer.write("\r\n");
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return outputFile;
	}	
	
	/**
     * This function writes a line to the output file
     * 
     * @param outputFile The output file to be written to
     * @param strToWrite string to write
     */
	public static void writeLineToOutputFile(File outputFile, String strToWrite) {
		
		try (Writer writer = new BufferedWriter(
				new FileWriter(outputFile.getAbsoluteFile(), true))) {
			
		    writer.write(strToWrite);
		    writer.write("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This function writes a string to the output file
     * 
     * @param outputFile The output file to be written to
     * @param strToWrite string to write
     */
	public static void writeToOutputFile(File outputFile, String strToWrite) {
		
		try (Writer writer = new BufferedWriter(
				new FileWriter(outputFile.getAbsoluteFile(), true))) {
			
		    writer.write(strToWrite + " ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This function writes an integer to the output file
     * 
     * @param outputFile The output file to be written to
     * @param intToWrite integer to write
     */
	public static void writeToOutputFile(File outputFile, int intToWrite) {
		
		try (Writer writer = new BufferedWriter(
				new FileWriter(outputFile.getAbsoluteFile(), true))) {
			
		    writer.write(Integer.toString(intToWrite));
		    writer.write(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This function writes a carriage return to the file
     * 
     * @param outputFile The output file to be written to
     * @param intToWrite integer to write
     */
	public static void writeCarriageReturnToFile(File outputFile) {
		
		try (Writer writer = new BufferedWriter(
				new FileWriter(outputFile.getAbsoluteFile(), true))) {

		    writer.write("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
