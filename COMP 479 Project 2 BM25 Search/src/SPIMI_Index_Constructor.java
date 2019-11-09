/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*
 * 

 	SPIMI Single Pass In Memory Index

 	1. SPIMI scans through blocks from a token stream as input, (blocks can fit directly in memory, not diskspace)
 	2. Automatically indexes Terms by Document ID (read in by Document and then incremented)
 	3. SPIMI sorts blocks of terms, and processes them as follows:
 		a. removes and counts frequency of Terms throughout the stream
		b. 

 */

import java.util.ArrayList;

public class SPIMI_Index_Constructor {

	//ATTRIBUTES

	//SERVICE METHODS
	//CONSTRUCT INDEX
	/*
	 PASS IN ALL REUTER ARTICLES (21578) AND DICTIONARIES (44)
	 1. LOOP FOR EACH DICTIONARY
	  2. LOOP FOR EACH CONSECUTIVE 500 ARILCLES
	  	3. LOOP FOR EACH TOKEN IN EACH ARTICLE

	 */
	public void constructIndex(ArrayList<ReutersArticle> articles, SPIMIDictionary[] dictionaries){
		//VARIABLES
		int articleCounter = 0;
		//LOOP FOR 500 ARTICLE BLOCK
		for (int k = 0 ; k < 44 ; k++ ){
			System.out.println("Constructing index for BLOCK "+k);
			dictionaries[ k ] = new SPIMIDictionary();
			for (int i = 0 ; i <= 500 ; i++){
				if ( articleCounter < articles.size() ){
					for (int j = 0 ; j < articles.get(articleCounter).countTokens() ; j++ ){
						//SPIMI CONSTRUCTION ADDS EACH TERM TO THAT DICTIONARY
						SPIMIToken token = new SPIMIToken(articles.get(articleCounter).getToken(j).getValue(), articles.get(articleCounter).getToken(j).getNewId() );
						dictionaries[k].searchAndAdd( token );
					}//close for j each token in the article
					articleCounter++;
				}//close if theres any articles left
			}//close loop for i each 500 articles
			//SORT DICTIONARY
			dictionaries[k].sort();
		}//close while counting each article
	}//close function construct Index		

	//MERGE BLOCKS
	/*
	 * 	AS PER PROJECT INSTRUCTIONS, MERGE EVERYTHING INTO 2 FINAL BLOCKS
	 */
	public void mergeBlocks(SPIMIDictionary[] dicIn, SPIMIDictionary[] dicOut){
		System.out.println("\nMERGING " + dicIn.length + " DICITONARIES");
		int half = dicIn.length/2;
		//INITIALIZE THE FIRST DICTIONARY TO 
		dicOut[0] = dicIn[0]; 
		for (int i = 1 ; i < half ; i++ ){
			System.out.println("MERGING DICTIONARY " + i);
			for (int j = 0 ; j < dicIn[i].getSize() ; j++ )
				dicOut[0].searchAndAdd( dicIn[i].getToken(j));
		}//CLOSE FOR I
		dicOut[1] = dicIn[half];
		for (int i = half+1 ; i < dicIn.length ; i++ ){
			System.out.println("MERGING DICTIONARY " + i);
			for (int j = 0 ; j < dicIn[i].getSize() ; j++ )
				dicOut[0].searchAndAdd( dicIn[i].getBlock().get(j));
		}//close for i
	}//close function merge blocks
	/*
	 * 	AS PER PROJECT UPDATE, 25000 terms per block
	 */
	public ArrayList<SPIMIDictionary> mergeBlocks2(SPIMIDictionary[] dicIn){
		//loop for each dictionary
		//loop for each term per dictionary
		//if 25000 terms -> new dictionary;
		System.out.println("\nMERGING " + dicIn.length + " DICITONARIES");
		ArrayList<SPIMIDictionary> dicOut = new ArrayList<SPIMIDictionary>();
		SPIMIDictionary dic = new SPIMIDictionary();
		for (int i = 0 ; i < dicIn.length ; i++ ){
			System.out.println("MERGING DICTIONARY " + i);
			for (int j = 0 ; j < dicIn[i].getSize() ; j++ )
				if ( dic.getSize() <= 25000)
					dic.searchAndAdd( dicIn[i].getToken(j));
				else{
					System.out.println("MAX TERMS OF 25000 REACHED\nCREATING NEW BLOCK");
					dicOut.add(dic);
					dic = new SPIMIDictionary();
					dic.searchAndAdd( dicIn[i].getToken(j) );
				}//close else
		}//close for i
		return dicOut;
	}//close function merge blocks

	public ArrayList<SPIMIDictionary> mergeBlocks2(ArrayList<SPIMIDictionary> dicIn){
		//loop for each dictionary
		//loop for each term per dictionary
		//if 25000 terms -> new dictionary;
		System.out.println("\nMERGING " + dicIn.size() + " DICITONARIES");
		ArrayList<SPIMIDictionary> dicOut = new ArrayList<SPIMIDictionary>();
		SPIMIDictionary dic = new SPIMIDictionary();
		for (int i = 0 ; i < dicIn.size() ; i++ ){
			System.out.println("MERGING DICTIONARY " + i);
			for (int j = 0 ; j < dicIn.get(i).getSize() ; j++ )
				if ( dic.getSize() <= 25000)
					dic.searchAndAdd( dicIn.get(i).getToken(j));
				else{
					System.out.println("MAX TERMS OF 25000 REACHED\nCREATING NEW BLOCK");
					dicOut.add(dic);
					dic = new SPIMIDictionary();
					dic.searchAndAdd( dicIn.get(i).getToken(j) );
				}//close else
		}//close for i
		return dicOut;
	}//close function merge blocks

	/*
	 	PART 3 REINDEX USING LOSSY COMPRESSION TECHNIQUES
	 	IGNORE CASE
	 	REMOVE NUMBERS
	 	REMOVE STOP WORDS
	 */
	//USED FOR PROJECT 1
	public void constructCompressedIndex(ArrayList<ReutersArticle> articles, ArrayList<SPIMIDictionary> dictionaries){
		//VARIABLES
		int articleCounter = 0;
		CompressorLossy compressor = new CompressorLossy();
		String term;
		//LOOP FOR 500 ARTICLE BLOCK
		for (int k = 0 ; k < 44 ; k++ ){
			System.out.println("Constructing compressed index for BLOCK "+k);
			SPIMIDictionary dic = new SPIMIDictionary();
			for (int i = 0 ; i <= 500 ; i++){
				if ( articleCounter < articles.size() ){
					for (int j = 0 ; j < articles.get(articleCounter).countTokens() ; j++ ){
						//SPIMI CONSTRUCTION ADDS EACH TERM TO THAT DICTIONARY
						//GET AND CHECK VALUE WITH COMPRESSOR, IF RETURNED NULL OR "" VALUE, DONT ADD TOKEN
						term = compressor.lossyCompression( articles.get(articleCounter).getToken(j).getValue() );
						if (!term.equals("") && term !=null ){
							SPIMIToken token = new SPIMIToken(term, articles.get(articleCounter).getToken(j).getNewId() );
							dic.searchAndAdd( token );
						}//close if not compressed out token
					}//close for j each token in the article
					articleCounter++;
				}//close if theres any articles left
			}//close loop for i each 500 articles
			//SORT DICTIONARY
			dic.sort();
			dictionaries.add( dic );
		}//close while counting each article
	}//close function construct Index		
	
	//USE FOR PROJECT 2
	//1 SINGLE DICTIONARY
	public void constructBM25Index(ArrayList<ReutersArticle> articles, ArrayList<SPIMIDictionary> dictionaries){
		//VARIABLES
		CompressorLossy compressor = new CompressorLossy();
		String term;
		//LOOP FOR 500 ARTICLE BLOCK
		System.out.println("\nCONSTRUCTING COMPRESSED INDEX FOR REUTER ARTICLES");
		SPIMIDictionary dic = new SPIMIDictionary();
		for (int i = 0 ; i <= articles.size() ; i++){
				for (int j = 0 ; j < articles.get(i).countTokens() ; j++ ){
					//SPIMI CONSTRUCTION ADDS EACH TERM TO THAT DICTIONARY
					//GET AND CHECK VALUE WITH COMPRESSOR, IF RETURNED NULL OR "" VALUE, DONT ADD TOKEN
					term = compressor.lossyCompression( articles.get(i).getToken(j).getValue() );
					if (!term.equals("") && term !=null ){
						SPIMIToken token = new SPIMIToken(term, articles.get(i).getToken(j).getNewId() );
						dic.searchAndAdd( token );
					}//close if not compressed out token
				}//close for j each token in the article
		}//close loop for i each 500 articles
		//SORT DICTIONARY
		dic.sort();
		dictionaries.add( dic );
	}//close function construct Index	

	//1 SINGLE DICTIONARY
	public void constructBM25Index(ArrayList<ReutersArticle> articles, SPIMIDictionary dictionary){
			//VARIABLES
			CompressorLossy compressor = new CompressorLossy();
			String term;
			//LOOP FOR 500 ARTICLE BLOCK
			System.out.println("\nCONSTRUCTING COMPRESSED INDEX FOR REUTER ARTICLES");
			for (int i = 0 ; i < articles.size() ; i++){
				if ( i % 1000 == 0)
					System.out.println("INDEXED THE FIRST " + i + " ARTICLES");
					for (int j = 0 ; j < articles.get(i).countTokens() ; j++ ){
						//SPIMI CONSTRUCTION ADDS EACH TERM TO THAT DICTIONARY
						//GET AND CHECK VALUE WITH COMPRESSOR, IF RETURNED NULL OR "" VALUE, DONT ADD TOKEN
						term = compressor.lossyCompression( articles.get(i).getToken(j).getValue() );
						if (!term.equals("") && term !=null ){
							SPIMIToken token = new SPIMIToken(term, articles.get(i).getToken(j).getNewId() );
							dictionary.searchAndAdd( token );
						}//close if not compressed out token
					}//close for j each token in the article
			}//close loop for i each 500 articles
			//SORT DICTIONARY
			dictionary.sort();
		}//close function construct Index	


	//SETTER AND GETTERS

	//DISPLAY



}//close class SPIMI
