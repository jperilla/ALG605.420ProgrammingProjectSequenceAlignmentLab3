# ALG605.420ProgrammingProjectSequenceAlignmentLab3
Performs Sequence alignment in pairs of strings

Java and IDE Info:
java version "1.8.0_111"
Java(TM) SE Runtime Environment (build 1.8.0_111-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.111-b14, mixed mode)
IDE is Eclipse Neon



TO RUN:
Run JulieGarciaSequenceAlignmentLab3.jar with argument of the input file name. See INPUT section for input formatting instructions.

"java -jar JulieGarciaSequenceAlignmentLab3.jar DynamicLab3Input.txt"

or if you put your input file in a different location

"java -jar JulieGarciaSequenceAlignmentLab3.jar /path/to/file/DynamicLab3Input.txt"



OPTIONS:
-n : You can optionally run the program with -n, which needs to be placed after the input file name. This will run the
naive solution in addition to the dynamic programming solution. Only run this if you (a) have a lot of time or (b) your
input strings are short (20 characters or less).



INPUT:
Input file must be named xxxxxInput.txt, where xxxxx can be any length text. The input file should be placed in the same directory as the .jar file or you should include the path in the argument sent on the command line (see TO RUN section above).

I have included five sample input files: DynamicLab3Input.txt, DynamicLab3ShortInput.txt, 
										 HepatitisDInput.txt, HumanDiffInput.txt and HumanSimilarInput.txt

New Input files should be formatted as follows: A list of strings (any length)
				 			with "S" and the number of the line, for example:

S1 = ACCGGTCGACTG
S2 = GTCGTTCGGCAA
S3 = ATTGCA
S4 = CTTGCTTAAATGTGCA

Input can be lower or upper case, it will be converted to uppercase and trimmed upon reading into the program.



OUTPUT:
The output file will be placed in the same directory as the input file and will be named using the name of the input file. xxxxxOutput.txt. CAUTION: be aware that every time you run the application with the same input file, it will overwrite the previous output file. The output file will contain the input strings, the LCS length, the LCS string and percent match of
each string. It also contains running times of each solution.


ANALYSIS:
See analysis of algorithm in "JulieGarciaSequenceAlignmentLab3Analysis.pdf"


