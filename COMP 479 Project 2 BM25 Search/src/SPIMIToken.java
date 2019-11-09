/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

	TOKEN FOR SPIMI DICTIONARY

 */

import java.util.ArrayList;

public class SPIMIToken {
	
	//ATTRIBUTES
	private String term;
	private ArrayList<Integer> docID = new ArrayList<Integer>();
	private int frequency = 1;
	private String tag = null;
	
	//CONSTRUCTORS
	public SPIMIToken(){}
	public SPIMIToken(String term, int docID){ this.term = term;	this.docID.add( docID ); }
	public SPIMIToken(String term, String docID){ 
		this.term = term;
		try{
		this.docID.add( Integer.parseInt(docID) ); 
		} catch (Exception e){
			System.out.println("\nERROR PARSING STRING TO INTEGER\n" + e);
		}//close try catch
	}//close constructor
	
	//SETTERS AND GETTERS
	public String getTerm(){ return this.term; }
	public ArrayList<Integer> getDocID(){ return this.docID; }
	public void appendDoc(int docid){
		//Loop to see if the id is already there, if so, increase frequency, else append the new docID
		for (int i = 0 ; i < docID.size() ; i++ )
			if (docID.get(i).intValue() == docid ){
				frequency++;
				return;
			}//close if
		docID.add( docid );
	}//close append doc
	
	//SERVICE METHODS
	
	//DISPLAY
	public void print(){
		System.out.println("SPIMI TOKEN: "+ this.toString() );
	}//close function print
	public String toString(){
		String out = term + "\t\t\t";
		for (int i = 0 ; i < docID.size() ; i++ ){
			out += docID.get(i);
			if (i != docID.size()-1 )
				out += ", ";
		}//close for i
		return out;
	}//close function to string

}//close class spimi token
