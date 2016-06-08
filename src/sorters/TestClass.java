package sorters;

import java.util.ArrayList;

public class TestClass {

	public static void main(String[] args) 
	{
		
		final String filePath = "resources/";	// Directory path for input files


		// Sample input files in Comma-Seperated-Value (CSV) format		
		final String [] fileNames = {"numbers01.txt", "numbers02.txt", "numbers03.txt", "numbers04.txt"};

		ArrayList<Integer[]> fileChunksAsArrays = new ArrayList<Integer[]>();

		for (String fname : fileNames)
		{
			// TODO: Reads text files and stores the data into arrays of integers chunk(s).
			//       Each chunk is represented by an array of Integers of length MEM_SIZE
			//       Adds the chunk(s) to the list of chunks called fileChunksAsArrays
			// Suggestion: Use Arrays.copyOfRange(int[] original, int from, int to)
			//       to copy a chunk found into fileChunksAsArrays
			//       for more details see: 
			//       http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#copyOfRange(int[],%20int,%20int)
		 SimulateChunks.splitFileIntoArrayChunks(50, filePath + fname, fileChunksAsArrays);
		}
		
		int numOfChunks = fileChunksAsArrays.size();
		System.out.println("Number of arrays holding file input = " + numOfChunks);

		for (int i = 0; i < numOfChunks; i++)
		{
			System.out.println("file chunk[" + i + "] with size " + fileChunksAsArrays.get(i).length + " :");
			
			for(int j = 0; j < fileChunksAsArrays.get(i).length; j++)
			{
				Integer[] current = fileChunksAsArrays.get(i);
			
				System.out.println("data value : " + current[j]);
			}
		}
	}

}
