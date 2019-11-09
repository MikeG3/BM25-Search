/*
 * 	MICHAEL GARNER
 *  26338739
 *  SEPT. 3, 2019
 *  LAB 1
 *  COMP 479
 */

/*
 * 	CLASS TO SPLIT A REUTERS DOCUMENT INTO ARTICLES
 */

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ReuterArticleExtractor {

	//VARIABLES
	private ArrayList<String> newid = new ArrayList<String>();
	private ArrayList<String> oldid = new ArrayList<String>();
	private Pattern newidP = Pattern.compile("(.*)(NEWID=\")([0-9]*)(\")(.*)");
	private Pattern oldidP = Pattern.compile("(.*)(OLDID=\")([0-9]*)(\")(.*)");
	private Matcher matcher;

	//CONSTRUCTOR
	public ReuterArticleExtractor() {
		//SKIP FIRST 2 ID PLACES
		newid.add("-1");
		oldid.add("-1");
	}//close default constructor

	//METHODS
	public void splitArticles(ArrayList<String> stringsIn, ArrayList<String> articles ){
		String article = " ";
		for (int i = 0 ; i < stringsIn.size() ; i++)	{					//for each line 
			//CHECK FOR <newid=> TAG
			if ( Pattern.matches(".*NEWID=.*", stringsIn.get(i)) ) {
				//GET THE NEWID AND OLDID, THEN ADD TO THERE LISTS
				if ( Pattern.matches("(.*)(NEWID=\")([0-9]*)(\")(.*)", stringsIn.get(i) ) && Pattern.matches("(.*)(OLDID=\")([0-9]*)(\")(.*)", stringsIn.get(i))){
					matcher = newidP.matcher(stringsIn.get(i));
					if (matcher.find())
						newid.add(matcher.group(3));
					matcher = oldidP.matcher(stringsIn.get(i));
					if (matcher.find())
						oldid.add(matcher.group(3));
				}//close if finds regex
				//ADD THE ARTICLE
				articles.add( article );
				article = " ";
			}//close if article matched with regEx
			else 
				article += (" "+stringsIn.get(i));
		}//close for i processing all strings in document for text
		//article += stringsIn.get( stringsIn.size()-1);\
		articles.add(article);
	}//close function splitArticles


	public ArrayList<String> splitArticles(ArrayList<String> stringsIn ){
		ArrayList<String> articles = new ArrayList<String>();
		String article = "";
		for (int i = 0 ; i < stringsIn.size() ; i++)	{					//for each line 
			//CHECK FOR <newid=> TAG
			if ( Pattern.matches(".*NEWID=.*", stringsIn.get(i)) ) {
				//if (i > 2)
				articles.add( article );
				article = "";
			}//close if article matched with regEx
			else {
				article += stringsIn.get(i);
			}//close if reading articles
		}//close for i processing all strings in document for text
		return articles;
	}//close function splitArticles

	//GETTERS
	public ArrayList<String> getNewID() {return this.newid; }
	public ArrayList<String> getOldID() {return this.oldid; }

	//DISPLAY
	public void printId(){
		System.out.println("\nPrinting Ids in ReutersArticleExtractor:\n");
		for (int i = 0 ; i < newid.size() ; i++)
			System.out.println(i+": newid = "+ newid.get(i));
		for (int i = 0 ; i < oldid.size() ; i++)
			System.out.println(i+": oldid = "+ oldid.get(i));
	}//close function printID


}//CLOSE CLASS ReuterArticleExtractor
