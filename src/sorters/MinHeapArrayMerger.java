package sorters;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs1c.FHbinHeap;
/**
 * Using minHeap technique to sort the various chunks with respect to each other
 * writes the output to text file 
 * @author swati
 *
 */
public class MinHeapArrayMerger {
	/**
	 * sort the various chunks with respect to each other using minHeap with the given heap size restriction
	 * divides chunks into groups based on minHeap[] size and sorts them amongst each other,
	 * stores those chunks into an arraylist and finally sorts these sorted groups among themselves
	 * @param mEM_SIZE variable of type int that specifies available memory size
	 * @param fileChunksAsArrays arraylist of chunks to be sorted amongst wach other
	 * @param minHeap array of HeapTuple objects
	 * @param string file name on which, to write the output
	 */
	public static void mergeSortedArrays(int mEM_SIZE, ArrayList<Integer[]> fileChunksAsArrays, HeapTuple[] minHeap,
			String string) {
		
		int numOfChunks = fileChunksAsArrays.size();
        int chunkSize;
        int numOfGroups = 0;
        int startingIndex = 0;
        int lastIndex;
        
        ArrayList<ArrayList<Integer[]>> subGroups = new ArrayList<ArrayList<Integer[]>>(); 
        ArrayList<Integer[]> finalChunks = new ArrayList<Integer[]> ();
		
			 while(numOfChunks > 0)
	            {
	            	if (numOfChunks <= minHeap.length)
	            	{	            		
	            		chunkSize = numOfChunks;
	            		numOfChunks = 0;            		
	            	}           	
	            	else
	            	{
	            		chunkSize = minHeap.length;
	            		numOfChunks = numOfChunks - minHeap.length; 	            		
	            	}
	            	
	            	ArrayList<Integer[]> chunk = new ArrayList<Integer[]>();
	            	
	            	lastIndex = startingIndex + chunkSize;
	            	
	            	for(int i = startingIndex; i < lastIndex ; i++)
	            	{	            	
	            		chunk.add(fileChunksAsArrays.get(i));
	            	} 	            	
	            	subGroups.add(chunk);	            	
	            	startingIndex += chunkSize; 
	            	numOfGroups++;
	            }
			 
			 for(int i = 0; i < subGroups.size(); i++)
			 {
				 Integer[] sortedArray = heapSortAndMerge(subGroups.get(i));
				 finalChunks.add(sortedArray);
			 }
			 
			 Integer[] finalArray = heapSortAndMerge(finalChunks);
	 
				BufferedWriter  filewriter;			 
				
				try 
				{
					filewriter = new BufferedWriter(new FileWriter(string));
					String newline = System.getProperty("line.separator");
					
					for(int i = 0; i < finalArray.length; i++)
					{						
							filewriter.write(" " + finalArray[i] + newline);												
					}
					
					filewriter.flush();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}			 						
		}

	/**
	 * 
	 * @param chunk list of Integer chunks to be sorted using minHeap 
	 * @return an array of sorted Integers
	 */
	public static Integer[] heapSortAndMerge(ArrayList<Integer[]> chunk)
	{
		int initialIndex = 0;
		ArrayList<Integer> sortedChunks = new ArrayList<Integer>();
		FHbinHeap<HeapTuple> currentHeap = new FHbinHeap<HeapTuple>();
		
		// storing mins of all chunks in minHeap
		for(int i = 0; i < chunk.size(); i++)
		{
			currentHeap.insert(new HeapTuple(chunk.get(i)[initialIndex],i,initialIndex));			
		}
	
			while(!currentHeap.empty())
			{			
				HeapTuple currentMin = currentHeap.remove();
				sortedChunks.add(currentMin.getData());

				int arrayIndex = currentMin.getArrayIndex();				
				Integer[] currentChunk = chunk.get(arrayIndex);

				// get the next element
				int currentIndex = currentMin.getIndexInArray()+1;

				if(currentIndex < currentChunk.length)
				{
					int number = currentChunk[currentIndex];
					currentHeap.insert(new HeapTuple(number,arrayIndex,currentIndex));						
				}
				else
					continue;																
			}
			Integer[] sortedArray = sortedChunks.toArray(new Integer[sortedChunks.size()]);
			return sortedArray;
	}

}

	

	

