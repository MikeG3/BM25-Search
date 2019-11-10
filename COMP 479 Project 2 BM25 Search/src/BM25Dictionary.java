/*

 MICHAEL GARNER
 26338739
 COMP 479 
 Project 2

 */

/*
	LIKE SPIMI DICTIONARY, BUT USES BM25POSTING INSYEAD OF SIMPLY THE DOC-ID
 */

import java.util.ArrayList;

public class BM25Dictionary {


	//ATTRIBUTES
	private ArrayList<BM25Token> terms = new ArrayList<BM25Token>();
	private double avdl;
	private int numOfDocs;
	
	//CONSTRUCTORS
	public BM25Dictionary(){}		

	//SERVICE METHODS
	//SEARCH EXISTING TERMS
	public boolean searchExactMatch(String term){
		for (int i = 0 ; i < terms.size() ; i++)
			if ( terms.get(i).getTerm().equals(term))
				return true;
		return false;
	}//close function
	public boolean searchIncludes(String term){
		for (int i = 0 ; i < terms.size() ; i++)
			if ( terms.get(i).getTerm().contains(term))
				return true;
		return false;
	}//close function
	//SEARCH AND ADD TERM IF DOES NOT ALREADY EXIST
	public boolean searchExactMatchCaseInsinsitive(String term){
		for (int i = 0 ; i < terms.size() ; i++)
			if ( terms.get(i).getTerm().equalsIgnoreCase(term))
				return true;
		return false;
	}//close function

	//SORT DICTIONARY
	public void sort(){
		System.out.println("Sorting Dictionary");
		if ( terms.size() == 0 )
			System.out.println("\tERROR\tDICTIONARY IS EMPTY");
		else {
			//CREATE TEMP LIST AND CLEAR ALL TOKENS
			boolean highest = true;
			ArrayList<BM25Token> token = terms;
			terms = new ArrayList<BM25Token>();
			//ADD FIRST TOKEN
			terms.add(token.get(0));	
			//SORT TOKENS
			//FOR EACH TOKEN
			for (int i = 1 ; i < token.size() ; i++ ){
				highest = true;
				//SORT THROUGH ADDED TOKENS
				for (int j =  0 ; j < terms.size() ; j++ ){
					if (token.get(i).getTerm().compareToIgnoreCase(terms.get(j).getTerm() ) <= 0){
						terms.add(j, token.get(i));
						highest = false;
						break;
					}//close if
				}//close for j
				if (highest)
					terms.add( token.get(i) );
			}//close for i each token
		}//close else dictionary not empty
		//SORT POSTINGS
		sortPostings();
	}//close function sort
	//SORT POSTINGS
	public void sortPostings(){
		System.out.println("\nSORTING POSTINGS\n");
		for (int i = 0 ; i < terms.size(); i++ ){
			terms.get(i).sortPostings();
		}//close for i each term
	}//close function sort postings
	
	//SEARCH AND ADD
	public void searchAndAdd(String term, int docID, String tag, int docLength){
		//search through all tokens in the dictionary
			//if found
				//*for all input.postings				*
					//*if input.postings.docId exists	* *
						//*increment freq				* * * appenDoc
					//*else								* *
						//*add that dicId to the list	*
			//else 
				//add that token
		for ( int i = 0 ; i < terms.size() ; i++ ){
			//IF THE TERM EXISTS, APPEND THAT DOCUMENT ID (OR INCREASE FREQUENCY IF ALREADY EXISTS THERE TOO)
			if ( terms.get(i).getTerm().equals( term ) ){
				terms.get(i).addPosting( docID, tag, docLength);
				return;
			}//close if the term matches an entry already found in the dictionary
		}//close while i
		//ELSE, ADD THAT TERM TO THE DICITONARY
		BM25Token bm25 = new BM25Token(term, docID, tag, docLength);
		terms.add( bm25 );	
	}//close function search and add

	//RANK POSTINGS
	public void calculateBM25(){
		System.out.println("\nRANKING THE DICTIONARY ENTRIES USING BM25");
		for (int i = 0 ; i < terms.size() ; i++ ){
			terms.get(i).calculateBM25(this.avdl, numOfDocs);
		}//close for i
		this.sortPostings();
	}//close function calculate bm25
	
	//SETTERS AND GETTERS
	public void addToken(BM25Token token){ terms.add(token); }
	public BM25Token getToken(int token){ return terms.get(token); }
	public ArrayList<BM25Token> getTerms() { return terms; }
	public int getSize(){ return terms.size(); }
	public void setAVDL(double avdl){ this.avdl = avdl; }
	public void setNumOfDocs(int in){ this.numOfDocs = in; }
	
	//DISPLAY
 	public void print(){
		System.out.println("PRINTING SPIMI DICTIONARY:\n");
		for (int i = 0 ; i < terms.size() ; i++){
			terms.get(i).print();
		}//close for i
	}//close function print

	public String toString(){
		String out = "";
		for (int i = 0 ; i < terms.size() ; i++){
			out += terms.get(i).toString() + "\n";
		}//close for i
		return out;
	}//close 	
	
}//CLOSE CLASS BM25DICTIONARY
