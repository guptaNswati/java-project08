package sorters;

import cs1c.FHsort;

public class BasicSorter {
	
	public static void sortChunk(Integer[] chunk) {
		
		if(chunk.length <= 20)
		{		
			FHsort.insertionSort(chunk);
		}
		else if(chunk.length < 20 || chunk.length <= 10000)
		{
			FHsort.shellSort1(chunk);
		}
		else if(chunk.length > 10000 || chunk.length <= 100000)
		{
			FHsort.mergeSort(chunk);
		}
		else if(chunk.length > 100000 || chunk.length <= 1000000)
		{
			FHsort.heapSort(chunk);			
		}
		else
		{
			FHsort.quickSort(chunk);			
		}
		
		
	}	
}
