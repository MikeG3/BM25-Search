/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

	DICTIONARY OF SPIMI TOKENS

	SPIMI TOKENS CONTAIN TERMS & DOCID

 */

import java.util.ArrayList;

public class SPIMIDictionary {

	//ATTRIBUTES
	private ArrayList<SPIMIToken> entry = new ArrayList<SPIMIToken>();

	//CONSTRUCTORS
	public SPIMIDictionary(){}		

	//SERVICE METHODS
	//SEARCH EXISTING TERMS
	public boolean searchExactMatch(String term){
		for (int i = 0 ; i < entry.size() ; i++)
			if ( entry.get(i).getTerm().equals(term))
				return true;
		return false;
	}//close function
	public boolean searchIncludes(String term){
		for (int i = 0 ; i < entry.size() ; i++)
			if ( entry.get(i).getTerm().contains(term))
				return true;
		return false;
	}//close function
	//SEARCH AND ADD TERM IF DOES NOT ALREADY EXIST
	public boolean searchExactMatchCaseInsinsitive(String term){
		for (int i = 0 ; i < entry.size() ; i++)
			if ( entry.get(i).getTerm().equalsIgnoreCase(term))
				return true;
		return false;
	}//close function

	//SORT DICTIONARY
	public void sort(){
		System.out.println("Sorting Dictionary");
		if ( entry.size() == 0 )
			System.out.println("\tERROR\tDICTIONARY IS EMPTY");
		else {
			//CREATE TEMP LIST AND CLEAR ALL TOKENS
			boolean highest = true;
			ArrayList<SPIMIToken> token = entry;
			entry = new ArrayList<SPIMIToken>();
			//ADD FIRST TOKEN
			entry.add(token.get(0));	
			//SORT TOKENS
			//FOR EACH TOKEN
			for (int i = 1 ; i < token.size() ; i++ ){
				highest = true;
				//SORT THROUGH ADDED TOKENS
				for (int j =  0 ; j < entry.size() ; j++ ){
					if (token.get(i).getTerm().compareToIgnoreCase(entry.get(j).getTerm() ) <= 0){
						entry.add(j, token.get(i));
						highest = false;
						break;
					}//close if
				}//close for j
				if (highest)
					entry.add( token.get(i) );
			}//close for i each token
		}//close else dictionary not empty
	}//close function sort

	//SEARCH AND ADD
	public void searchAndAdd(SPIMIToken input){
		for ( int i = 0 ; i < entry.size() ; i++ ){
			//IF THE TERM EXISTS, APPEND THAT DOCUMENT ID (OR INCREASE FREQUENCY IF ALREADY EXISTS THERE TOO)
			if ( entry.get(i).getTerm().equals(input.getTerm()) ){
				//System.out.println("SPIMIDictionary.searchAndAdd entry.get(i).getTerm() = " + entry.get(i).getTerm() +  "\t\tinput.getTerm() = " + input.getTerm());
				entry.get(i).appendDoc( input.getDocID().get(0) );
				return;
			}//close if the term matches an entry already found in the dictionary
		}//close while i
		//ELSE, ADD THAT TERM TO THE DICITONARY
		entry.add( input );	
	}//close function search and add

	//SETTERS AND GETTERS
	public void addToken(SPIMIToken token){ entry.add(token); }
	public SPIMIToken getToken(int token){ return entry.get(token); }
	public ArrayList<SPIMIToken> getBlock() { return entry; }
	public int getSize(){ return entry.size(); }

	//DISPLAY
	public void print(){
		System.out.println("PRINTING SPIMI DICTIONARY:\n");
		for (int i = 0 ; i < entry.size() ; i++){
			entry.get(i).print();
		}//close for i
	}//close function print

	public String toString(){
		String out = "";
		for (int i = 0 ; i < entry.size() ; i++){
			out += entry.get(i).toString() + "\n";
		}//close for i
		return out;
	}//close function print

}//close class dictionary
