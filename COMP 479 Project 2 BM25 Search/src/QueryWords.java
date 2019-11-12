import java.util.ArrayList;

/*

 MICHAEL GARNER
 26338739
 CONCORDIA UNIVERSITY
 COMP 479 

 */
/*
 
 	LISTS OF WORDS TO DEMO THE BM25 INDEX AND RANK SYSTEM
 
 */

/*
 
 //		//QUERY  AND RESULTS
//		String[] queries = {"HELLO", "WORLD", "COCOA", "CANADA", "ARDVARK", "INFORMATION", "RETRIEVAL"};
//		String[] q1 = {"HELLO", "WORLD"};
//		String[] q2 = {"INFORMATION", "RETRIEVAL"};
//		String[] q3 = {"CANADA", "ARDVARK"};
//		String[] q4 = {"Jimmy", "Carter"};
//		String[] q5 = {"Green", "Party"};
//		String[] q6 = {"Innovations",  "in", "telecommunication"};
//		String[] q7 = {"environmentalist", "ecologist"};
//		//ASSIGNED QUERIES
//		//COMPRESS ASSIGNED QUERIES
//		q4[0] = "jimmy";
//		q4[1] = "carter";
//		q5[0] = "green";
//		q5[1] = "party";
//		q6[2] = new String();
//		q6[0] = "innovations";
//		q6[1] = "telecommunication";
 
 */
public class QueryWords {

	//ATTRIBUTES
	public String[] q1 = {"democrats", "welfare", "healthcare", "reform", "policies" };
	public String[] q2 = {"drug", "company", "bankruptcies" };
	public String[] q3 = {"george", "bush" };
	
	public ArrayList<String[]> queries = new ArrayList<String[]>();
	
	//CONSTRUCTOR
	public QueryWords(){
		queries.add(q1);
		queries.add(q2);
		queries.add(q3);
	}//close constructor
	
	//SETTERS AND GETTERS
	public ArrayList<String[]> getWords(){ return this.queries; }
	public String[] getWords(int i){ return this.queries.get(i); }
	
}//CLOSE CLASS QUERY WORDS
