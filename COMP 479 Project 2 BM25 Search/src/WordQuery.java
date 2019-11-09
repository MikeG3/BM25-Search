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

	//SERVICE METHODS	
	//SEARCH AND RETURN NEWID (DOC ID)
	//SEARCH AND RETURN TOKENS
	//SEARCH AND PRINT
	//SEARCH AND GIVE ARTICLE

	//SINGLE TERM
	public ArrayList<SPIMIToken> search(String query, SPIMIDictionary dictionary){
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ )
			if ( dictionary.getToken(i).getTerm().equals(query))
				tokens.add(dictionary.getToken(i));
		return tokens;
	}//close function search
	public ArrayList<Integer> searchDocID(String query, SPIMIDictionary dictionary){
		ArrayList<Integer> id = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ )
			if ( dictionary.getToken(i).getTerm().equals(query))
				for (int j = 0 ; j < dictionary.getToken(i).getDocID().size() ; j++)
					id.add(dictionary.getToken(i).getDocID().get(j));
		return id;
	}//close function search
	public ArrayList<SPIMIToken> search(String query, SPIMIDictionary[] dictionary){
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
		ArrayList<SPIMIToken> tempTokens = new ArrayList<SPIMIToken>();
		for (int i = 0 ; i < dictionary.length ; i++){
			tempTokens = search(query, dictionary[i]);
			if (tempTokens.size() > 0)
				for (int j = 0 ;  j < tempTokens.size() ; j++ )
					tokens.add(tempTokens.get(j));
		}//close for i	
		return tokens;
	}//close function search
	public ArrayList<SPIMIToken> search(String query, ArrayList<SPIMIDictionary> dictionary){
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
		ArrayList<SPIMIToken> tempTokens = new ArrayList<SPIMIToken>();
		for (int i = 0 ; i < dictionary.size() ; i++){
			tempTokens = search(query, dictionary.get(i));
			if (tempTokens.size() > 0)
				for (int j = 0 ;  j < tempTokens.size() ; j++ )
					tokens.add(tempTokens.get(j));
		}//close for i	
		return tokens;
	}//close function search
	public ArrayList<Integer> searchDocID(String query, SPIMIDictionary[] dictionary){
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<Integer> idtemp = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.length ; i++ ){
			idtemp = searchDocID(query, dictionary[i]);
			if (idtemp.size() > 0)
				for (int j = 0 ; j < idtemp.size() ; j++)
					id.add(idtemp.get(j));
		}//close for i
		return id;
	}//close function search
	public ArrayList<Integer> searchDocID(String query, ArrayList<SPIMIDictionary> dictionary){
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<Integer> idtemp = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.size() ; i++ ){
			idtemp = searchDocID(query, dictionary.get(i));
			if (idtemp.size() > 0)
				for (int j = 0 ; j < idtemp.size() ; j++)
					id.add(idtemp.get(j));
		}//close for i
		return id;
	}//close function search

	//AND SEARCH
	public ArrayList<SPIMIToken> andSearch(String[] query, SPIMIDictionary dictionary){
		boolean match;
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
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
	public ArrayList<SPIMIToken> andSearch(String[] query, SPIMIDictionary[] dictionary){
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
		ArrayList<SPIMIToken> tokensTemp = new ArrayList<SPIMIToken>();
		for (int i = 0 ; i < dictionary.length ; i++ ){
			tokensTemp = andSearch(query, dictionary[i]);
			for ( int j = 0 ; j < tokensTemp.size() ; j++ )
				tokens.add(tokensTemp.get(j));
		}//close for i
		return tokens;
	}//close function search
	public ArrayList<SPIMIToken> andSearch(String[] query, ArrayList<SPIMIDictionary> dictionary){
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
		ArrayList<SPIMIToken> tokensTemp = new ArrayList<SPIMIToken>();
		for (int i = 0 ; i < dictionary.size() ; i++ ){
			tokensTemp = andSearch(query, dictionary.get(i));
			for ( int j = 0 ; j < tokensTemp.size() ; j++ )
				tokens.add(tokensTemp.get(j));
		}//close for i
		return tokens;
	}//close function search
	public ArrayList<Integer> andSearchDocID(String[] query, SPIMIDictionary dictionary){
		boolean match;
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = true;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( !dictionary.getToken(i).getTerm().equals(query[j]))
					match = false;
			if (match)
				for ( int j = 0 ; j <  dictionary.getToken(i).getDocID().size() ; j++ )
					ints.add( dictionary.getToken(i).getDocID().get(j) );
		}//close for j
		return ints;
	}//close function search
	public ArrayList<Integer> andSearchDocID(String[] query, SPIMIDictionary[] dictionary){
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ArrayList<Integer> intsTemp = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.length ; i++ ){
			intsTemp = andSearchDocID(query, dictionary[i]);
			for ( int j = 0 ; j <  intsTemp.size() ; j++ )
				ints.add(intsTemp.get(j));
		}//close for j
		return ints;
	}//close function search
	public ArrayList<Integer> andSearchDocID(String[] query, ArrayList<SPIMIDictionary> dictionary){
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ArrayList<Integer> intsTemp = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.size() ; i++ ){
			intsTemp = andSearchDocID(query, dictionary.get(i));
			for ( int j = 0 ; j <  intsTemp.size() ; j++ )
				ints.add(intsTemp.get(j));
		}//close for j
		return ints;
	}//close function search
	
	//OR SEARCH
	public ArrayList<SPIMIToken> orSearch(String[] query, SPIMIDictionary dictionary){
		boolean match;
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ )
			for (int  j= 0 ; j < dictionary.getSize() ; j++ ){
				match = false;
				for ( int k = 0 ; k <  query.length ; k++ )
					if ( dictionary.getToken(j).getTerm().equals(query[k]))
						match = true;
				if (match)
					tokens.add(dictionary.getToken(j));
			}//close for j
		return tokens;
	}//close function search
	public ArrayList<SPIMIToken> orSearch(String[] query, SPIMIDictionary[] dictionary){
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
		ArrayList<SPIMIToken> tokensTemp = new ArrayList<SPIMIToken>();
		for (int i = 0 ; i < dictionary.length ; i++ ){
			tokensTemp = orSearch(query, dictionary[i]);
			for ( int j = 0 ; j < tokensTemp.size() ; j++ )
				tokens.add(tokensTemp.get(j));
		}//close for i
		return tokens;
	}//close function search
	public ArrayList<SPIMIToken> orSearch(String[] query, ArrayList<SPIMIDictionary> dictionary){
		ArrayList<SPIMIToken> tokens = new ArrayList<SPIMIToken>();
		ArrayList<SPIMIToken> tokensTemp = new ArrayList<SPIMIToken>();
		for (int i = 0 ; i < dictionary.size() ; i++ ){
			tokensTemp = orSearch(query, dictionary.get(i));
			for ( int j = 0 ; j < tokensTemp.size() ; j++ )
				tokens.add(tokensTemp.get(j));
		}//close for i
		return tokens;
	}//close function search
	public ArrayList<Integer> orSearchDocID(String[] query, SPIMIDictionary dictionary){
		boolean match;
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = false;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( dictionary.getToken(i).getTerm().equals(query[j]))
					match = true;
			if (match)
				for ( int j = 0 ; j <  dictionary.getToken(i).getDocID().size() ; j++ )
					ints.add( dictionary.getToken(i).getDocID().get(j) );
		}//close for j
		return ints;
	}//close function search
	public ArrayList<Integer> orSearchDocID(String[] query, SPIMIDictionary[] dictionary){
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ArrayList<Integer> intsTemp = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.length ; i++ ){
			intsTemp = orSearchDocID(query, dictionary[i]);
			for ( int j = 0 ; j <  intsTemp.size() ; j++ )
				ints.add(intsTemp.get(j));
		}//close for j
		return ints;
	}//close function search
	public ArrayList<Integer> orSearchDocID(String[] query, ArrayList<SPIMIDictionary> dictionary){
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ArrayList<Integer> intsTemp = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.size() ; i++ ){
			intsTemp = orSearchDocID(query, dictionary.get(i));
			for ( int j = 0 ; j <  intsTemp.size() ; j++ )
				ints.add(intsTemp.get(j));
		}//close for j
		return ints;
	}//close function search

	//PRINT DOCUMENT ID
	//SINGLE SEARCH
	public void printSearchID(String query, SPIMIDictionary[] dictionary){
		ArrayList<Integer> ids = searchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR: \"" + query + "\" " + "\n" + ids.size() + "\t");
		if ( ids.size() < 1 )
			System.out.println( "KEYWORD NOT FOUND\n" );
		else {
			System.out.println( "MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print
	public void printSearchID(String query, ArrayList<SPIMIDictionary> dictionary){
		ArrayList<Integer> ids = searchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR: \"" + query + "\" " + "\n" + ids.size() + "\t");
		if ( ids.size() < 1 )
			System.out.println( "KEYWORD NOT FOUND\n" );
		else {
			System.out.println( "MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print
	public void printSearchID(String query, SPIMIDictionary dictionary){
		ArrayList<Integer> ids = searchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR: \"" + query + "\" " + "\n" + ids.size() + "\t");
		if ( ids.size() < 1 )
			System.out.println( "KEYWORD NOT FOUND\n" );
		else {
			System.out.println( "MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print
	//AND SEARCH
	public void printAndSearchID(String[] query, SPIMIDictionary[] dictionary){
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
			System.out.println( "\n" + ids.size() + " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print
	public void printAndSearchID(String[] query, ArrayList<SPIMIDictionary> dictionary){
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
	public void printAndSearchID(String[] query, SPIMIDictionary dictionary){
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
	//OR SEARCH
	public void printOrSearchID(String[] query, SPIMIDictionary[] dictionary){
		ArrayList<Integer> ids = orSearchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR: \n");
		for (int i = 0 ; i < query.length ; i++){
			System.out.print("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				System.out.print("OR ");
		}//close for i
		if ( ids.size() < 1 )
			System.out.println( "\nKEYWORDS NOT FOUND\n" );
		else {
			System.out.println( "\n" + ids.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print
	public void printOrSearchID(String[] query, ArrayList<SPIMIDictionary> dictionary){
		ArrayList<Integer> ids = orSearchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR: \n");
		for (int i = 0 ; i < query.length ; i++){
			System.out.print("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				System.out.print("OR ");
		}//close for i
		if ( ids.size() < 1 )
			System.out.println( "\nKEYWORDS NOT FOUND\n" );
		else {
			System.out.println( "\n" + ids.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print
	public void printOrSearchID(String[] query, SPIMIDictionary dictionary){
		ArrayList<Integer> ids = orSearchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR: \n");
		for (int i = 0 ; i < query.length ; i++){
			System.out.print("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				System.out.print("OR ");
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
