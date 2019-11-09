/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

 	READ A BLOCK OF MEMORY

 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DiskReader {
	
	//ATTRIBUTES
	private final String fileName = "Disk.txt";
	
	//SERVICE METHODS
	public ArrayList<SPIMIToken> getBlock(int blockIndex){
		//VARIABLES
		File file = new File(fileName);
		Scanner scanner;
		
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
		String startBlock = "[BLOCK "+ blockIndex + "]";
		String endBlock = "[BLOCK "+ (blockIndex+1) + "]";
		String term = "$", docId = "", next;
		SPIMIToken token;
		
		try{
			scanner = new Scanner(file);
			while (scanner.hasNext()){
				//FIND DESIRED BLOCK
				if ( scanner.nextLine().contains(startBlock ) ){
					System.out.println("\nDISK READER HAS FOUND THE START BLOCK " + startBlock );
					while( !scanner.next().equals("") && !scanner.next().equals(" ") && !scanner.next().equals(endBlock)){
						//Read in DocID, Read in Term
						docId = scanner.next();
						term = scanner.next();
						System.out.println("\ndocId=" + docId + "\t\tterm= " + term);
						//Add to token
						token = new SPIMIToken(term, docId);
						tokens.add(token);	
					}//close while reading the selected block	
				}//close if reading block

			}//close while there are documents in the tex
			scanner.close();
		} catch (Exception e){
			System.out.println("\nERROR READING FROM DISK\n"+e);
		}//close try catch
		return tokens;
	}//close function get block
}//close class disk reader
