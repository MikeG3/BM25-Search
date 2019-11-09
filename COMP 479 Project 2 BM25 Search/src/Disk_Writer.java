/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

 	SIMULATES WRITING TO A DISK DRIVE, BY WRITING TO A FILE DISK.TXT

 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Disk_Writer {

	//ATTRIBUTES
	private final String fileName = "Disk.txt";
	private File file = new File(fileName);
	private Scanner fscan;
	private int blockCounter = 0;

	//CONSTRUCTOR
	public Disk_Writer(){

	}//close constructor 

	//DISPLAY
	public void print(){
		ArrayList<String> strings = readDisk();
		System.out.println("\nDISPLAYING DISK CONTENTS:\n");
		if (strings.size() < 1)
			System.out.println("The Disk is empty\n");
		else
			for (int i = 0 ; i < strings.size() ; i++ )
				System.out.println(i+": "+strings.get(i));
	}//close function display
	public void display(){
		print();
	}//close function display

	//SERVICE METHODS
	//READ FROM DISK
	public ArrayList<String> readDisk(){
		//Temp memory for strings
		ArrayList<String> strings = new ArrayList<String>();
		//Scan in all words in to array.
		try{
			fscan = new Scanner(file);
			while (fscan.hasNextLine()){ strings.add( fscan.next() );}
			fscan.close();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("There was an error opening the Disk (Disk.txt");
		}//close try catch for opening file and extracting data
		return strings;
	}//close function read disk

	//WRITE TO DISK
	public void write(BM25Dictionary input){
		System.out.println("\nWRITING TO DISK (Disk.txt)\n");
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			for (int i = 0 ; i < input.getSize() ; i++ )
				bfr.write(input.getToken(i).toString() + "\n");
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n");
		}//close catch
	}//close function write to disk
	
	public void writeBlock(ArrayList<String> input){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			blockCounter++;
			//Seperate block
			bfr.write("[BLOCK "+blockCounter+"]\n");
			//Write TOKEN INFO
			for (int i = 0 ; i < input.size() ; i++ )
				bfr.write(input.get(i)+"\n");
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n");
		}//close catch
	}//close function write to disk

	public void writeTokenBlock(ArrayList<SPIMIToken> input){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			blockCounter++;
			//Seperate block
			bfr.write("[BLOCK "+blockCounter+"]\n");
			//Write TOKEN INFO
			for (int i = 0 ; i < input.size() ; i++ )
				bfr.write(input.get(i).getTerm()+"\t\t\t\t\t\t\t\t"+input.get(i).getDocID()+"\n");
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n");
		}//close catch
	}//close function write to disk

	public void writeSpimiDictionary(SPIMIDictionary input){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			blockCounter++;
			//Seperate block
			bfr.write("[BLOCK "+blockCounter+"]\n");
			//Write TOKEN INFO
			for (int i = 0 ; i < input.getSize() ; i++ )
				bfr.write(input.getBlock().get(i).getTerm()+"\t\t\t\t\t\t\t\t"+input.getBlock().get(i).getDocID()+"\n");
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n");
		}//close catch
	}//close function write to disk

	public void writeSpimiDictionaries(SPIMIDictionary[] input){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			blockCounter = 0;
			//Separate block
			for (int i = 0 ; i < input.length ; i++ ){
				blockCounter++;
				//System.out.println("WRITING BLOCK " + blockCounter + " TO DISK");
				bfr.write("[BLOCK " + blockCounter + "]\n");
				//Write TOKEN INFO
				for (int j = 0 ; j < input[i].getSize() ; j++ )
					bfr.write(input[i].getBlock().get(j).toString()+"\n");
			}//close for i each block
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n");
		}//close catch
	}//close function write to disk
	
	public void writeSpimiDictionaries(SPIMIDictionary[] input, String fileName){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			blockCounter = 0;
			//Separate block
			for (int i = 0 ; i < input.length ; i++ ){
				blockCounter++;
				//System.out.println("WRITING BLOCK " + blockCounter + " TO DISK");
				bfr.write("[BLOCK " + blockCounter + "]\n");
				//Write TOKEN INFO
				for (int j = 0 ; j < input[i].getSize() ; j++ )
					bfr.write(input[i].getBlock().get(j).toString()+"\n");
			}//close for i each block
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n");
		}//close catch
	}//close function write to disk
	
	public void writeMergedSpimiDictionaries(ArrayList<SPIMIDictionary> input){
		try{
			String  mergedDiskFileName = "Disk2MergedBlocks.txt";
			FileWriter fw = new FileWriter( mergedDiskFileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			blockCounter = 0;
			//Separate block
			for (int i = 0 ; i < input.size() ; i++ ){
				blockCounter++;
				//System.out.println("WRITING BLOCK " + blockCounter + " TO DISK");
				bfr.write("[BLOCK " + blockCounter + "]\n");
				//Write TOKEN INFO
				for (int j = 0 ; j < input.get(i).getSize() ; j++ )
					bfr.write(input.get(i).getToken(j).toString()+"\n");
			}//close for i each block
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER - WRITING MERGED BLOCKS\n");
		}//close catch
	}//close function write to disk
	
	public void writeSpimiDictionaries(ArrayList<SPIMIDictionary> input, String fileName){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			blockCounter = 0;
			//Separate block
			for (int i = 0 ; i < input.size() ; i++ ){
				blockCounter++;
				//System.out.println("WRITING BLOCK " + blockCounter + " TO DISK");
				bfr.write("[BLOCK " + blockCounter + "]\n");
				//Write TOKEN INFO
				for (int j = 0 ; j < input.get(i).getSize() ; j++ )
					bfr.write(input.get(i).getToken(j).toString()+"\n");
			}//close for i each block
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER - WRITING MERGED BLOCKS\n");
		}//close catch
	}//close function write to disk

	public void writeToDisk(ArrayList<String> input){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			for (int i = 0 ; i < input.size() ; i++ )
				bfr.write(input.get(i)+"\n");
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n");
		}//close catch
	}//close function write to disk

	//WRITE TO DISK
	public void writeToDisk(String input){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			bfr.write(input);
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n");
		}//close catch
	}//close function write to disk

}//close class disk writer
