import java.util.ArrayList;

/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

	TOKEN FOR BM25 DICTIONARY
	
	LIKE SPIMI DICTIONARY, BUT USES BM25POSTING INSTEAD OF SIMPLY THE DOC-ID

 */

public class BM25Token {

	//ATTRIBUTES
	private String term;
	private ArrayList<BM25Posting> posting = new ArrayList<BM25Posting>();
	private int docFrequency = 0;
	
	//CONSTRUCTORS
	public BM25Token(){}
	public BM25Token(String term, int docID, String tag, int docLength){ 
		this.term = term;
		this.addPosting(docID, tag, docLength);
	}//close constructor

	//SERVICE METHODS
	//ADD DOC ID
	public void addPosting(int docID, String tag, int docLength){
		//If there are already postings,see if it exists
		if (posting.size() > 0){
			for (int i = 0 ; i < posting.size() ; i++ )
				//if the posting is there, increment its frequency and return
				if ( posting.get(i).getDocID() == docID ){
					posting.get(i).incFreq();
					return;
				}//close if the docID is already in the postings list
		}//close if there are postings
		//Else, add it to the list
		BM25Posting post = new BM25Posting(docID, tag, docLength);
		posting.add(post);
	}//close function add Posting
	
	public void calculateBM25(int avdl){
		for (int i = 0 ; i < posting.size() ; i++ ){
			posting.get(i).calculateBM25(avdl);
		}//close for i
	}//close function calculate bm25
	
	//SETTERS AND GETTERS
	public String getTerm(){ return this.term; }
	public ArrayList<BM25Posting> getPostings(){ return this.posting; }
	
	//DISPLAY
	public void print(){
		System.out.println("BM25 TOKEN: "+ this.toString() );
	}//close function print
	
	public String toString(){
		String out = term + "\t\t\t";
		for (int i = 0 ; i < posting.size() ; i++ ){
			out += posting.get(i).toString();
			if (i != posting.size()-1 )
				out += ", ";
		}//close for i
		return out;
	}//close function to string
	
}//CLOSE CLASS BM25 TOKEN
