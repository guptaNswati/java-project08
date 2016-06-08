package sorters;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import cs1c.FHbinHeap;

public class MinHeapArrayMerger {

	public static void mergeSortedArrays(int mEM_SIZE, ArrayList<Integer[]> fileChunksAsArrays, HeapTuple[] minHeap,
			String string) {
		
		int indexInArray = 0;
		ArrayList<Integer> sortedData = new ArrayList<Integer>();
		
		// storing mins of all chunks in minHeap
		for(int i = 0; i < fileChunksAsArrays.size(); i++)
		{
			minHeap[i] = new HeapTuple(fileChunksAsArrays.get(i)[indexInArray],i,indexInArray);			
		}
		
		FHbinHeap<HeapTuple> currentHeap = new FHbinHeap<HeapTuple>(minHeap);		
		FileWriter filewriter;
		
//		try 
//		{
//			filewriter = new FileWriter(string, true);
			 int i = 0;
			
			 while(!currentHeap.empty())
			{			
				HeapTuple currentMin = currentHeap.remove();
				sortedData.add(currentMin.getData());
				System.out.println(sortedData.get(i));
//				System.out.println(currentMin);
				
				int arrayIndex = currentMin.getArrayIndex();
				System.out.println("array index " + arrayIndex);
				Integer[] currentChunk = fileChunksAsArrays.get(arrayIndex);
				System.out.println("current chunk size " + currentChunk.length);
				
				if(indexInArray < currentChunk.length)
				{
					int number = currentChunk[indexInArray];
					currentHeap.insert(new HeapTuple(number,arrayIndex,indexInArray));				
				}
//				
//				if(indexInArray > fileChunksAsArrays.get(arrayIndex).length)
//				{
//					fileChunksAsArrays.set(arrayIndex, null);		
//				}
				
				indexInArray++;
				
				i++;
			
				
			}
			 System.out.println(" sorted list size " + sortedData.size());
				
//				sortedData.add(currentMin.getData());
//				System.out.println(sortedData.get(i));
//				filewriter.write(" " + currentMin.getData());							

//				int arrayIndex = currentMin.getArrayIndex();
//				
//				Integer[] currentChunk = remove(fileChunksAsArrays.get(arrayIndex));
//				int number = currentChunk[indexInArray];
//				
//				if(currentChunk.length == 1)
//				{
//					fileChunksAsArrays.remove(arrayIndex);
//				}
//			
//				// adding the next big num from array which had the first min
//				HeapTuple newMin = new HeapTuple(number,arrayIndex,indexInArray);
//				currentHeap.insert(newMin);	
//				i++;
			}
			
//			for(int i = 0; i < sortedData.size(); i++)
//			{
				
//			}

//			filewriter.flush();
//		} 
//		catch (IOException e1) 
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
	
	private static Integer[] remove(Integer[] array)
	{
		if(array.length == 1)
		{
			return array;
		}
		
		ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(array));
		a.remove(0);
		return array = a.toArray(new Integer[a.size()]);		
	}
	
}
