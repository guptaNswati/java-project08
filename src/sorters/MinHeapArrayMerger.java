package sorters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs1c.FHbinHeap;

public class MinHeapArrayMerger {

	public static void mergeSortedArrays(int mEM_SIZE, ArrayList<Integer[]> fileChunksAsArrays, HeapTuple[] minHeap,
			String string) {
		
		int fileDataSize = fileChunksAsArrays.size();
        int chunkSize;
        int numOfGroups = 0;
        int startingIndex = 0;
        
        
        ArrayList<Integer[]> subGroups = new  ArrayList<Integer[]>(); 
		
			 while(fileDataSize > 0)
	            {
	            	if (fileDataSize <= minHeap.length)
	            	{
	            		chunkSize = fileDataSize;
	            		fileDataSize = 0;            		
	            	}           	
	            	else
	            	{
	            		chunkSize = minHeap.length;
	            		fileDataSize = fileDataSize - minHeap.length;  
	            	}
	            	
	            	ArrayList<Integer[]> chunk = new ArrayList<Integer[]> ();
	            	
	            	for(int i = startingIndex; i < startingIndex + chunkSize; i++)
	            	{
	            	
	            		chunk.add(fileChunksAsArrays.get(i));
	            	}            	
	            	subGroups.add(heapSortAndMerge(chunk, minHeap));	            	
	            	startingIndex += chunkSize;                       	
	            	numOfGroups++;
	            }
			 
			    Integer[] sortedArray = heapSortAndMerge(subGroups, minHeap);			 
				BufferedWriter  filewriter;			 
				
				try 
				{
					filewriter = new BufferedWriter(new FileWriter(string));
					String newline = System.getProperty("line.separator");
					
					for(int i = 0; i < sortedArray.length; i++)
					{						
							filewriter.write(" " + sortedArray[i] + newline);												
					}
					
					filewriter.flush();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			 			
			
		}

	public static Integer[] heapSortAndMerge(ArrayList<Integer[]> chunk, HeapTuple[] minHeap )
	{
		int initialIndex = 0;
		ArrayList<Integer> sortedChunks = new ArrayList<Integer>();

		// storing mins of all chunks in minHeap
		for(int i = 0; i < chunk.size(); i++)
		{
			minHeap[i] = new HeapTuple(chunk.get(i)[initialIndex],i,initialIndex);			
		}

		FHbinHeap<HeapTuple> currentHeap = new FHbinHeap<HeapTuple>(minHeap);		
			while(!currentHeap.empty())
			{			
				HeapTuple currentMin = currentHeap.remove();
//				filewriter.write(" " + currentMin.getData() + newline);
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

	

	

