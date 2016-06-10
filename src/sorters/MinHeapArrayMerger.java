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
        int lastIndex;
        
        ArrayList<ArrayList<Integer[]>> subGroups = new ArrayList<ArrayList<Integer[]>>(); 
        ArrayList<Integer[]> finalChunks = new ArrayList<Integer[]> ();
		
			 while(fileDataSize > 0)
	            {
	            	if (fileDataSize <= minHeap.length)
	            	{
	            		
	            		chunkSize = fileDataSize;
	            		System.out.println("chunk size inside if  " + chunkSize );
	            		fileDataSize = 0;            		
	            	}           	
	            	else
	            	{
	            		chunkSize = minHeap.length;
	            		System.out.println("chunk size inside else  " + chunkSize);
	            		fileDataSize = fileDataSize - minHeap.length; 
	            		System.out.println(" file size inside else " + fileDataSize);
	            	}
	            	
	            	ArrayList<Integer[]> chunk = new ArrayList<Integer[]>();
	            	
	            	lastIndex = startingIndex + chunkSize;
	            	System.out.println("last index " + lastIndex);
	            	for(int i = startingIndex; i < lastIndex ; i++)
	            	{	            	
	            		chunk.add(fileChunksAsArrays.get(i));
	            	} 
	            	
	            	System.out.println("chunk size  " + chunkSize );
	            	subGroups.add(chunk);
//	            	subGroups.add(heapSortAndMerge(chunk, minHeap));	            	
	            	startingIndex += chunkSize; 
	            	
	            	System.out.println("strating index " + startingIndex);
	            	
	            	numOfGroups++;
	            }
			 
			 System.out.println("sub groups size " + subGroups.size());
			 
			 for(int i = 0; i < subGroups.size(); i++)
			 {
				 Integer[] sortedArray = heapSortAndMerge(subGroups.get(i));
				 System.out.println("sorted array size " + sortedArray.length);
				 finalChunks.add(sortedArray);
			 }
			 
			 Integer[] finalArray = heapSortAndMerge(finalChunks);
			 
//			 for(int i = 0; i < finalArray.length; i++)
//					{
//						System.out.println(finalArray[i]);
//						
//					}
	 
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

	public static Integer[] heapSortAndMerge(ArrayList<Integer[]> chunk)
	{
		int initialIndex = 0;
		ArrayList<Integer> sortedChunks = new ArrayList<Integer>();
		FHbinHeap<HeapTuple> currentHeap = new FHbinHeap<HeapTuple>();

//		if(chunk.size() < minHeap.length)
//		{
//			for(int i = 0; i < chunk.size(); i++)
//			{			
//				currentHeap.insert(new HeapTuple(chunk.get(i)[initialIndex],i,initialIndex));
//			}			
//			
//		}
//		else
		// storing mins of all chunks in minHeap
		for(int i = 0; i < chunk.size(); i++)
		{
			currentHeap.insert(new HeapTuple(chunk.get(i)[initialIndex],i,initialIndex));			
		}

//		currentHeap = (minHeap);		
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
			
//			for(int i = 0; i < sortedArray.length; i++)
//			{
//				System.out.println(sortedArray[i]);
//				
//			}
			return sortedArray;
	}


}

	

	

