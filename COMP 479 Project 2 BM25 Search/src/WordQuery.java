/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

	Searches Dictionary for keywords (terms)

	BY FORMATTING QUERIES TO
	 A. PRODUCT OF SUMS
	 B. SUM OF PRODUCTS
	ALL QUERY PERMUTATIONS CAN BE MADE

 */
import java.util.ArrayList;

public class WordQuery {
	/*
	//BM25
	public ArrayList<BM25Token> bm25Search(String[] input, BM25Dictionary dic){
		ArrayList<BM25Token> out = new ArrayList<BM25Token>();
		ArrayList<BM25Token> sorted = new ArrayList<BM25Token>();
		out = orSearch(input, dic);
		//RANK ALL OF THE HITS BY SUMMING THEIR RANKS
		if (out.size() > 1){
			int[] score = new int[out.size()];
			//FOR EACH MATCH, MAKE A SCORE OF 0 FOR IT
			for (int i = 0 ; i < score.length ; i++ ){
				score[i] = 0;
				//FOR EACH QUERY WORD, IF FOUND, ADD THE BM25RANK TO THE SCORE
				for (int j = 0 ; j < input.length ; j++){
					if ( search(input[j]))
				}//close for j
			}//close for i
		}//close if there is more than 1 match
		//LOOP FOR SORTING BY HIGHEST TO LOWEST
		return sorted;
	}//close function bm25 search
	 */
	//SERVICE METHODS	
	//SEARCH AND RETURN NEWID (DOC ID)
	//SEARCH AND RETURN TOKENS
	//SEARCH AND PRINT
	//SEARCH AND GIVE ARTICLE

	//SINGLE TERM
	public boolean search(String query, BM25Dictionary dic){
		for (int i = 0 ; i < dic.getSize() ; i++ )
			if ( dic.getToken(i).getTerm().equals(query))
				return true;
		return false;
	}//close search function
	public boolean searchContains(String query, BM25Dictionary dic){
		for (int i = 0 ; i < dic.getSize() ; i++ )
			if ( dic.getToken(i).getTerm().contains(query))
				return true;
		return false;
	}//close search function
	
	//AND SEARCH
	public ArrayList<BM25Token> andSearch(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<BM25Token> tokens = new ArrayList<BM25Token>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = true;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( !dictionary.getToken(i).getTerm().equals(query[j]))
					match = false;
			if (match)
				tokens.add(dictionary.getToken(i));
		}//close for j
		return tokens;
	}//close function search
	public ArrayList<BM25Posting> andSearchPostings(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<BM25Posting> ints = new ArrayList<BM25Posting>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = true;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( !dictionary.getToken(i).getTerm().equals(query[j]))
					match = false;
			if (match)
				for ( int j = 0 ; j <  dictionary.getToken(i).getPostings().size() ; j++ )
					ints.add( dictionary.getToken(i).getPostings().get(j) );
		}//close for j
		return ints;
	}//close function search
	public ArrayList<Integer> andSearchDocID(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = true;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( !dictionary.getToken(i).getTerm().equals(query[j]))
					match = false;
			if (match)
				for ( int j = 0 ; j <  dictionary.getToken(i).getPostings().size() ; j++ )
					ints.add( dictionary.getToken(i).getPostings().get(j).getDocID() );
		}//close for j
		return ints;
	}//close function search
	

	//OR SEARCH
	//SEARCH FOR EXACT MATCH FOR ANY QUERY WORD IN THE DICTIONARY
	public ArrayList<BM25Token> orSearch(String[] query, BM25Dictionary dictionary){
		boolean duplicate;
		//SEARCH FOR EXACT MATCH FOR EACH QUERY WORD IN THE DICTIONARY
		ArrayList<BM25Token> out = new ArrayList<BM25Token>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ )
			for (int j = 0 ; j < query.length ; j++ )
				if (dictionary.getToken(i).getTerm().equals(query[j])){
					duplicate = false;
					//ENSURE NOT A DUPLICATE
					for (int k = 0 ; k < out.size() ; k++)
						if (dictionary.getToken(i).equals( dictionary.getToken(k) ) )
							duplicate = true;
					if (!duplicate)
						out.add(dictionary.getToken(i));
				}//close if matched query word
		return out;
	}//close function search
	//SEARCH FOR MATCH that CONTAINS ANY QUERY WORD IN THE DICTIONARY
	public ArrayList<BM25Token> orSearchContains(String[] query, BM25Dictionary dictionary){
		boolean duplicate;
		//SEARCH FOR EXACT MATCH FOR EACH QUERY WORD IN THE DICTIONARY
		ArrayList<BM25Token> out = new ArrayList<BM25Token>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ )
			for (int j = 0 ; j < query.length ; j++ )
				if (dictionary.getToken(i).getTerm().contains(query[j])){
					duplicate = false;
					//ENSURE NOT A DUPLICATE
					for (int k = 0 ; k < out.size() ; k++)
						if (dictionary.getToken(i).equals( dictionary.getToken(k) ) )
							duplicate = true;
					if (!duplicate)
						out.add(dictionary.getToken(i));
				}//close if matched query word
		return out;
	}//close function search
	
	
	//OR SEARCH
	public ArrayList<BM25Posting> orSearchDocIDPostings(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<BM25Posting> ints = new ArrayList<BM25Posting>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = false;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( dictionary.getToken(i).getTerm().equals(query[j]))
					match = true;
			if (match)
				for ( int j = 0 ; j <  dictionary.getToken(i).getPostings().size() ; j++ )
					ints.add( dictionary.getToken(i).getPostings().get(j) );
		}//close for j
		return ints;
	}//close function search
	public ArrayList<Integer> orSearchDocID(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = false;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( dictionary.getToken(i).getTerm().equals(query[j]))
					match = true;
			if (match)
				//add all docId's
				for ( int j = 0 ; j < dictionary.getToken(i).getPostings().size() ; j++ )
					ints.add( dictionary.getToken(i).getPostings().get(j).getDocID() );
		}//close for j
		//remove duplicates
		for (int i = 0 ; i < ints.size() ; i++)
			for (int j = (i+1) ; j < ints.size(); j++)
				if (ints.get(i) == ints.get(j))
					ints.remove(j);
		return ints;
	}//close function search


	//PRINT DOCUMENT ID
	public void printAndSearchID(String[] query, BM25Dictionary dictionary){
		ArrayList<Integer> ids = andSearchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR: \n");
		for (int i = 0 ; i < query.length ; i++){
			System.out.print("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				System.out.print("AND ");
		}//close for i
		if ( ids.size() < 1 )
			System.out.println( "\nKEYWORDS NOT FOUND\n" );
		else {
			System.out.println( "\n" + ids.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print
	public void printOrSearchID(String[] query, BM25Dictionary dictionary){
		ArrayList<Integer> ids = orSearchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR RANKED BY BM25 VALUE: \n");
		for (int i = 0 ; i < query.length ; i++){
			System.out.print("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				System.out.print(", ");
		}//close for i
		if ( ids.size() < 1 )
			System.out.println( "\nKEYWORDS NOT FOUND\n" );
		else {
			System.out.println( "\n" + ids.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print


}//close class word query
