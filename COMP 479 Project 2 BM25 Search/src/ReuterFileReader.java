/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

 	READS IN THE REUTERS FILE IN ORDER TO CONVERT ARTICLE TO OBJECT THAN TOKENIZE ALL WORDS

 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReuterFileReader {

	//READ FILES 
	public ArrayList<ArrayList<String>> readFiles(){

		//VARIABLES
		ArrayList<ArrayList<String>> reutersDocuments = new ArrayList<ArrayList<String>>(22);
		for (int i = 0 ; i < 22 ; i++){
			ArrayList<String> strings = new ArrayList<String>();
			reutersDocuments.add(strings);
		}//close for i each reuters document
		
		//FILE READING
		String fileName[] = new String[22];
		File file[] = new File[22];
		Scanner fscan[] = new Scanner[22];

		//NAME ALL FILES
		for (int i = 0 ; i < fileName.length ; i++){
			fileName[i] = "reut2-0";
			if (i < 10)
				fileName[i] += "0"+i+".txt";
			else
				fileName[i] += i+".txt";
			file[i] = new File(fileName[i]);
		}//close for i each filename

		//READ IN ALL OF FILE
		//Message user
		System.out.println("\nWorking with Reuters Collection, files: reuters000-021.sgm\n");			
		//Create file using the given filename
		//Scan in all words in to array.
		try {
			for (int i = 0 ; i < fileName.length ; i++){
				fscan[i] = new Scanner(file[i]);	
				//Save each line to an array
				while (fscan[i].hasNextLine()){ 
					reutersDocuments.get(i).add( fscan[i].nextLine() );
				}//close while reading file
				fscan[i].close();
			}//close for i each reuter file
		}//close try custom file path
		catch (Exception e){
			System.out.println(e);
			System.out.println("There was an error opening one of the files reuters.txt");
		}//close catch for try opening file and extracting data

		return reutersDocuments;
	}//close constructor

}//close class reuter file reader
