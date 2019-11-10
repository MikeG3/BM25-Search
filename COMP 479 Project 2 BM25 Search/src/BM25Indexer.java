/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*
 * 
    BM25 Index Constructor
    
    Make BM25 DICTIONARY = LIST of BM25 TOKENS 
    
    BM25TOKENS = STRNIG-TERM + LIST OF BM25POSTING
    
    LIST OF BM25POSTING = DOCID + FREQUENCY
    
 	SPIMI Single Pass In Memory Index -> 1 chunk
 */

import java.util.ArrayList;

public class BM25Indexer {

	//ATTRIBUTES

	//SERVICE METHODS
	//CONSTRUCT INDEX
	/*
	 LOOP FOR EACH ARTICLE
	 	LOOP FOR EACH TOKEN
	 		BM25Dictionary.addToken(Term, DocID)
	 			BM25Token.addPosting(Term, DocID)
	 */
	public void constructIndex(ArrayList<ReutersArticle> articles, BM25Dictionary dictionary, double avdl){
		//VARIABLES
		BM25Token token;
		String term;
		String tag;
		int docID;
		int corpusSize = 0;
		dictionary.setAVDL(avdl);
		dictionary.setNumOfDocs(articles.size());
			for (int i = 0 ; i < articles.size() ; i++){
				if ( i % 1000 == 0 )
					System.out.println("CONSTRUCTED RANKED INDEXES FOR THE FIRST " + i + " ARTICLES");
					for (int j = 0 ; j < articles.get(i).countTokens() ; j++ ){
						corpusSize++;
						//GET THE TERM AND DOCID
						term = articles.get(i).getToken(j).getValue();
						docID = Integer.parseInt(articles.get(i).getToken(j).getNewId());
						tag = articles.get(i).getToken(j).getReuterTag();
						//SEARCH AND ADD THE DATA
						dictionary.searchAndAdd( term, docID, tag, articles.get(i).countTokens() );
					}//close for j each token in the article
			}//close loop for i each 500 articles
			//SORT DICTIONARY
			dictionary.sort();
			dictionary.sortPostings();
			//GET AVERAGE DOC LENGTH
			dictionary.setAVDL( corpusSize / articles.size() );
	}//close function construct Index		
	public void constructPartialIndex(ArrayList<ReutersArticle> articles, BM25Dictionary dictionary, double avdl){
		//VARIABLES
		BM25Token token;
		String term;
		int docID;
		String tag;
		int corpusSize = 0;
		dictionary.setNumOfDocs(articles.size());
		dictionary.setAVDL(avdl);
			for (int i = 0 ; i < 4001 ; i++){
				if ( i % 1000 == 0 )
					System.out.println("CONSTRUCTED RANKED INDEXES FOR THE FIRST " + i + " ARTICLES");
					for (int j = 0 ; j < articles.get(i).countTokens() ; j++ ){
						//GET THE TERM AND DOCID
						term = articles.get(i).getToken(j).getValue();
						docID = Integer.parseInt(articles.get(i).getToken(j).getNewId());
						tag = articles.get(i).getToken(j).getReuterTag();
						//SEARCH AND ADD THE DATA
						dictionary.searchAndAdd( term, docID, tag, articles.get(i).countTokens() );
					}//close for j each token in the article
			}//close loop for i each 500 articles
			//SORT DICTIONARY
			dictionary.sort();
	}//close function construct Index	

	//SETTER AND GETTERS

	//DISPLAY

}//close class SPIMI

