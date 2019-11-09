/*

 MICHAEL GARNER
 26338739
 CONCORDIA UNIVERSITY
 COMP 479

 */

/*

	TAKES IN REUTERS ARTICLE AND RETURNS LISTY OF TOKENS


 */

import java.util.ArrayList;

public class Token {

	//ATTRIBUTES
	private String value = "";
	private String reuterTag = null;
	private String newId = "-2";
	private String oldId = "-2";
	private static PunctuationRemover pr = new PunctuationRemover();

	//CONSTRUCTOR
	public Token(){}
	//public Token(String newId, String oldId  ){ this.newId = newId;	this.oldId = oldId; }
	public Token(String value){ this.value = value;}
	public Token(String value, String type){this.value = value;	this.reuterTag = type;}
	public Token(String value, String type, String newId, String oldId){
		this.value = value;	
		this.reuterTag = type;
		this.newId = newId;	
		this.oldId = oldId;
	}//close constructor
	public Token(String value, String type, String newId, String oldId, String tag){
		this.value = value;	
		this.reuterTag = type;
		this.newId = newId;	
		this.oldId = oldId;
		this.reuterTag = tag;
	}//close constructor
	
	//SETTER AND GETTERS
	public String getValue() {	return value; }
	public void setValue(String value) { this.value = value;	}
	public String getReuterTag() {	return reuterTag;	}
	public void setReuterTag(String reuterTag) { this.reuterTag = reuterTag;	}
	public String getNewId() {	return newId; }
	public void setNewId(String newId) { this.newId = newId; }
	public String getOldId() {	return oldId; }
	public void setOldId(String oldId) { this.oldId = oldId; }
	
	//DISPLAY
	public void print(){
		System.out.println("\nToken ID = " + newId + "\toldId = " + oldId + "\nvalue: " + value + "\nToken Tag: " + reuterTag);
	}//close print function

	//SERVICE METHODS	
	//TOKENIZE
	public static void tokenizeText(ArrayList<Token> tokens, String text, String tag, String newId, String oldId ){
		Token token;
		//SPLIT TEXT BY WHITESPACE
		String[] words = text.split("\\s");
		//TOKENIZE
		for (int i = 0 ; i < words.length ; i++){
			if ( !words[i].equals("") && !words[i].equals(" ") && !words[i].equals("  ")){
				//REMOVE PUNCTUATION IF ENDS WITH , OR .
				words[i] = pr.removeEndPunctuations( words[i] );
				token = new Token(words[i], tag, newId, oldId);
				tokens.add(token);
			}//close if not whitespace
		}//close for i each word

	}//close function tokenize


}//Close class tokens
