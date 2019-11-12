/*

 MICHAEL GARNER
 26338739
 CONCORDIA UNIVERSITY
 COMP 479 

 */

/*
 	PROJECT 2
 	BM25 RANKED SEARCH RETRIEVAL

 	RANKING OF DOCUMENT = IDF * ....

 	N = NUMBER OF DCOUMENTS = 12578
 	TF = TERM FREQUENCY, NUMBER OF TIMES THE TERM APPEAR IN A DOCUMENT	 = FREQUENCY (EACH TERM/DOCUMENT PAIR IN POSTINGS LIST)
 	DF = DOCUMENT FREQUENCY, NUMBER OF DOCUMENTS THE TERM IS FOUND IN	 = LENGTH OF POSTTINGS LIST 
 	DL = DOCUMENT LENGTH
 	AVDL = AVERAGE DOCUMENT LENGTH

 	IDF = LOG( N / DF )	

 	0 < B < 1	TUNING PARAMETER
 	K > 0, ?TERM FREQUENCY?

 	BM25 =  IDF * TF * (K + 1) /(K * (1 - B + B*DL/AVDL) +  TF) 

 	BM25 =  LOG(N/DF) * TF * (K + 1) /(K * (1 - B + B*DL/AVDL) +  TF)

 */

/*
 * 	USING STANFORD NLP LIBRARY FOR TOKENIZATION
 * 
 * 	AN EXERPT OF CODE HAS BEEN TAKEN DIRECTLY FROM THE STANFORD NLP API INSTRUCTIONAL WEBSITE
 * 	TO SET UP THE PIPELINE LEADING TO THE TOKENS
 */

/*
 * PROF NOTES
 * 
 * DONT USE ANYTHING PRE
 * 
 * BLOCK IS OF 500 REUTER ARTICLES, NOT TOKENS
 * 
 */

import java.util.ArrayList;


public class driver {

	public static void main(String[] args){

		//VARIABLES
		ArrayList<ArrayList<String>> reutersDocuments;
		ArrayList<String> articles = new ArrayList<String>();				//LIST OF ARTICLES
		ArrayList<ReutersArticle> reutersArticles = new ArrayList<ReutersArticle>();
		ReuterFileReader fileReader = new ReuterFileReader();
		ReuterArticleExtractor rae = new ReuterArticleExtractor();
		TagParser tagger = new TagParser();
		int tokenCount = 0;
		Disk_Writer diskWriter = new Disk_Writer();
		BM25Dictionary dictionary = new BM25Dictionary();
		BM25Indexer bm25Indexer = new BM25Indexer();
		float avdl = 0;
		WordQuery query = new WordQuery();
		QueryWords words = new QueryWords();
		
		//WELCOME MESSAGE
		System.out.println("COMP 479 PROJECT 2 BM25 RANKED RETRIEVAL\nMICHAEL GARNER 26338739\n");			

		//Read in all Files for pre-processing
		System.out.println("\nREADING IN REUTER FILES");
		reutersDocuments = fileReader.readFiles();

		/*
		//Print all strings
		System.out.println("\nNow printing all strings from the reutersDocuments\n");
		for (int i = 0 ; i < reutersDocuments.size() ; i++){
			System.out.println("Reuters Document #" + i + "\n");
			for (int j = 0 ; j < reutersDocuments.get(i).size() ; j++)
				System.out.println(j + ": " + reutersDocuments.get(i).get(j) );
		}//close for i each Reuters document
		 */

		//READ IN ALL TEXT
		System.out.println("\nTOKENIZING REUTER ARTICLES\n");
		for (int i = 0 ; i < reutersDocuments.size() ; i++)						//for each file
			rae.splitArticles(reutersDocuments.get(i), articles);

		/*
		//Print all articles
		System.out.println("\nNow printing all " + articles.size() + " articles:\n");
		for (int i = 0 ; i < articles.size() ; i++)
			System.out.println(i + ": " + articles.get(i));
		 */

		//ORGANIZE ARTICLES INTO REUTERSDOC TYPE OBJECTS
		//GET AND ADD NEWID / OLDID
		System.out.println("\nPARSING REUTER TAGS\n");
		for (int i = 0 ; i < articles.size() ; i++ ){
			//Create Reuters document, Organize it using its class method and add it to the list
			ReutersArticle reuterArticle = new ReutersArticle();
			tagger.getTagValues(reuterArticle, articles.get(i) );
			reutersArticles.add(reuterArticle);
			if (reutersArticles.get( reutersArticles.size()-1).isBlank() && i > 0)
				reutersArticles.remove( reutersArticles.size()-1 );
		}//close for i
		//ADD THE NEWID AND OLDID #S FROM THE REUTER ARTICLE EXTRACTOR
		//NEWID
		if (reutersArticles.size()==rae.getNewID().size())
			for (int i = 0 ; i < reutersArticles.size(); i++ )
				reutersArticles.get(i).setNewId( rae.getNewID().get(i) );
		else
			System.out.println("\nTHERE IS A MISMATCH IN THE NUMBER OF REUTER ARTICLES AND NUMBER OF NEWID'S\n");
		//OLDID
		if (reutersArticles.size()==rae.getOldID().size())
			for (int i = 0 ; i < reutersArticles.size(); i++ )
				reutersArticles.get(i).setOldId( rae.getOldID().get(i) );
		else
			System.out.println("\nTHERE IS A MISMATCH IN THE NUMBER OF REUTER ARTICLES AND NUMBER OF OLDID'S\n");

		//TOKENIZE REUTER ARTICLES
		System.out.println("\nTHE REUTER ARTICLES ARE TOKENIZED FOR STREAMING");
		for (int i = 1 ; i < reutersArticles.size() ; i++ )
			reutersArticles.get(i).tokenize();

		/*
		//PRINT REUTERS OBJECTS
		for (int i = 1 ; i < reutersArticles.size() ; i++ ){
			//System.out.println("\n"+ i + ":");
			//reutersArticles.get(i).print();
			//reutersArticles.get(i).printTokens();
		}//close for i
		 */

		//VERIFY ARTICLES = REUTERS OBJECTS
		System.out.println("\nTHERE ARE " + (reutersArticles.size()-1)+" REUTER ARTICLES");
		//TOKEN COUNTER
		for (int i = 0 ; i < reutersArticles.size() ; i++)
			tokenCount += reutersArticles.get(i).countTokens();
		System.out.println("\nTHERE ARE " + tokenCount + " TOKENS IN THE CORPUS\n");

		//COMPRESS ARTILCE TOKENS USING LOSSY COMPRESSION
		System.out.println("\nCOMPRESSING TOKENS USING LOSSY COMPRESSION");
		for (int i = 0 ; i < reutersArticles.size() ; i++)
			reutersArticles.get(i).compressTokens();
		tokenCount = 0;
		for (int i = 0 ; i < reutersArticles.size() ; i++)
			tokenCount += reutersArticles.get(i).countTokens();
		System.out.println("\nTHE CORPUS HAS BEEN COMPRESSED TO " + tokenCount);	 
		
		//GET DOCUMENT LENGTHS AND AVERAGE DOCUMENT LENGTH
		for (int i = 0 ; i < reutersArticles.size() ; i++ ) 
			avdl += reutersArticles.get(i).countTokens();
		avdl /=  reutersArticles.size();
		
		System.out.println("\nTHE AVERAGE DOCUMENT LENGTH IS " + avdl + "\n");

		//CONSTRUCT INDEX
		//bm25Indexer.constructPartialIndex(reutersArticles, dictionary, avdl);
		bm25Indexer.constructIndex(reutersArticles, dictionary, avdl);
		
		//Calculate BM25 RANKS
		dictionary.calculateBM25();
		
		//WRITE DICTIONARY TO DISK (Disk.txt)
		diskWriter.write( dictionary );
		
		//QUERY  AND RESULTS
		for (int i = 0 ; i < words.getWords().size() ; i++)
			query.printOrSearchID(words.getWords(i), dictionary);

		//CLOSING MESSAGE
		System.out.println("\n\nALGORITHM IS COMPLETE! :)");

	}//close main loop
}//close driver class