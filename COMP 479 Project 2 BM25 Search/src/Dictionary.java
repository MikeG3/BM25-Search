/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

	CONTAINS ALL OF TERMS

 */
import java.util.ArrayList;

public class Dictionary {

	//ATTRIBUTES
	private ArrayList<String> terms = new ArrayList<String>();

	//SERVICE METHODS
	//Search Dictionary
	public boolean search(String input){
		if (terms.size() > 0 )
			for (int i = 0 ; i < terms.size() ; i++)
				if (terms.get(i).equals(input))
					return true;
		return false;
	}//close function search 

	//Search and Add if not found
	public void searchAndAdd(String input){
		if (terms.size() > 0 )
			for (int i = 0 ; i < terms.size() ; i++)
				if (terms.get(i).equals(input))
					break;
		addTerm(input);
	}//close function search and add

	//SETTER AND GETTERS
	public void addTerm(String input){
		terms.add(input);
	}//close function add term
	
	//DISPLAY
	public void display(){
		System.out.println("\nPrinting terms in the DICTIONARY: \n");
		for (int i = 0 ; i < terms.size() ; i++ )
			System.out.println(i + terms.get(i) );
	}//close function display

}//close class dictionary
