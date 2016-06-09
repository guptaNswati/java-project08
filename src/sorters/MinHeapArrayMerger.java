package sorters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import cs1c.FHbinHeap;

public class MinHeapArrayMerger {

	public static void mergeSortedArrays(int mEM_SIZE, ArrayList<Integer[]> fileChunksAsArrays, HeapTuple[] minHeap,
			String string) {
		
		int initialIndex = 0;
		
		// storing mins of all chunks in minHeap
		for(int i = 0; i < fileChunksAsArrays.size(); i++)
		{
			minHeap[i] = new HeapTuple(fileChunksAsArrays.get(i)[initialIndex],i,initialIndex);			
		}
		
		FHbinHeap<HeapTuple> currentHeap = new FHbinHeap<HeapTuple>(minHeap);		
		BufferedWriter  filewriter;
		
		try 
		{
			filewriter = new BufferedWriter(new FileWriter(string));
			String newline = System.getProperty("line.separator");
		
			 while(!currentHeap.empty())
			{			
				HeapTuple currentMin = currentHeap.remove();
				filewriter.write(" " + currentMin.getData() + newline);
				
				int arrayIndex = currentMin.getArrayIndex();
				Integer[] currentChunk = fileChunksAsArrays.get(arrayIndex);
				
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
			filewriter.flush();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
}
