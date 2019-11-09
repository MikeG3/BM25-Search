/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*
 * 

 PUNCTUATION SPLITTER REMOVES THE PUNCTUATIONS, LIKE PERIODS AND COMMAS ATTACHED TO THE END OF THE WORD.

 SPLITS CASES WHERE A WORD IS DIVIDED BY 

 */

public class PunctuationRemover {

	//SERVICE METHODS
	public String removeEndPunctuations(String input){
		String output = input;
		char period = '.';
		char comma = ',';
		char dash = '-';
		int p = (int) period;
		int c = (int) comma;
		int d = (int) dash;
		char lastChar = input.charAt(input.length()-1);
		int inInt = (int) lastChar;
		if ( input.length() > 1)
			if (inInt == p || inInt == c || inInt == d)
				output = input.substring(0, input.length()-1);
		return output;
	}//close punctuation remover function

}//close class punctuation remover
