/*

 MICHAEL GARNER
 COMP 479 
 Project 2

 */

/*

 	EVERY TIME A TERM IS FOUND, BM25POSTING CAPTURES:
 		THE DOCUMENT THAT IT IS FOUND IN
 		FREQUENCY OF THE TERM IN THAT DOCUMENT
 		CALCULATES: <SEE BELOW>
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
 	K1 > 0, ?TERM FREQUENCY?

 	BM25 =  IDF * TF * (K + 1) /(K * (1 - B + B*DL/AVDL) +  TF) 

 	BM25 =  LOG(N/DF) * TF * (K + 1) /(K * (1 - B + B*DL/AVDL) +  TF)

 */
public class BM25Posting {

	//VARIABLES
	private int docID = -1;
	private int frequency = 0;
	private String tag = null;
	private int docLength = -1;
	static int numOfDocs = -1;
	private double idf = -1;
	private double bm25Rank;
	private final double K = 1.0, B = 0.5;


	//CONSTRUCTOR
	public BM25Posting(int docID, String tag, int docLength){
		this.docID = docID;
		if (this.tag == null)
			this.tag = tag;
		this.docLength = docLength;
		frequency = 1;
	}//close constructor

	//SERVICE METHODS
	public void incFreq(){
		frequency++;
	}//close function inc freq

	//CALCULATE RANK VALUES
	public void calculateBM25(double avdl, int numOfDocs){
		this.numOfDocs = numOfDocs;
		calculateIDF();
		//BM25 =  IDF * TF * (K + 1) /(K * (1 - B + B*DL/AVDL) +  TF) 
		bm25Rank = idf * frequency * (K+1.0) / (K* (1-B+(B*(double) docLength/(double) avdl)) + frequency);
	}//close function calculate idf
	public void calculateIDF(){
		//IDF = LOG( N / DF )
		idf = Math.log( ( (double) numOfDocs / (double) frequency) ) ;
	}//close function calculate idf
	
	//STTERS AND GETTERS
	public int getFrequency(){ return this.frequency; }
	public int getDocID(){ return this.docID; }
	public double getRank(){ return this.bm25Rank; }

	//DISPLAY
	public String toString(){	return ( docID + " <" + tag + "> " + " (" + frequency + ")" + " Rank: " + bm25Rank); }//close function to string

}//CLOSE CLASS BM25 Posting
