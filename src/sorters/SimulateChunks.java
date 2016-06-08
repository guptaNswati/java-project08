package sorters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SimulateChunks {

	public static void splitFileIntoArrayChunks(int mEM_SIZE, String file, ArrayList<Integer[]> fileChunksAsArrays) {
		// TODO Auto-generated method stub
		
		try 
        {
            Scanner input = new Scanner (new File(file)); 
            
            ArrayList<Integer> listOfData = new ArrayList<Integer>();
            
            while(input.hasNextLine())
            {
                String[] token = input.nextLine().split(",");
                
                for(int i = 0; i < token.length; i++)
                {
                	int fileValue = Integer.parseInt(token[i]);
                	listOfData.add(fileValue);
                }
            }
                      
            Integer[] fileData = listOfData.toArray(new Integer[listOfData.size()]);
            
            // the total size of all chunks should be the same as the total number
    		// of values in each file divided by the memory size.
            int fileDataSize = fileData.length;
            int chunkSize;
            int numOfChunks = 0;
            int startingIndex = 0;
            
            while(fileDataSize > 0)
            {
            	if (fileDataSize <= mEM_SIZE)
            	{
            		chunkSize = fileDataSize;
            		fileDataSize = 0;            		
            	}           	
            	else
            	{
            		chunkSize = mEM_SIZE;
            		fileDataSize = fileDataSize - mEM_SIZE;  
            	}
           	
            	Integer[] chunk = Arrays.copyOfRange(fileData , startingIndex, startingIndex + chunkSize);
            	startingIndex += chunkSize;                       	
            	fileChunksAsArrays.add(chunk);
            	numOfChunks++;           	
            }
            input.close();
           
        }
		catch (FileNotFoundException e) 
		{	e.printStackTrace(); } 
	}

}
