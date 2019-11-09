/*

MICHAEL GARNER
26338739
COMP 479

 */

/*
 IDENTIFIES THE TAGS FOUND IN REUTERS FILES
 
 DOES NOT SUPPORT IDENTIFICATION OF NESTED TAGS 
 IE. <TEXT>t<BODY>b</BODY></TEXT>	 -> TEXT: 	t
 									-> BODY:	b
 
 */

import java.util.ArrayList;

public class TagParser {

	//VARIABLES
	static ArrayList<String> openTags = new ArrayList<String>();
	static ArrayList<String> closeTags = new ArrayList<String>();
	private String textInTag, head, tail;
	private String openTag, closeTag;
	private int openTagIndex, closeTagIndex;

	//CONSTRUCTORS
	public TagParser(){
		openTags.add("<DATE>");
		openTags.add("<DATELINE>");
		openTags.add("<TOPICS>");
		openTags.add("<PEOPLE>");
		openTags.add("<TITLE>");
		openTags.add("<TEXT>");
		openTags.add("<PLACES>");
		openTags.add("<ORGS>");
		openTags.add("<EXCHANGES>");
		openTags.add("<COMPANIES>");
		openTags.add("<UNKNOWN>");
		openTags.add("<BODY>");
		openTags.add("<AUTHOR>");
		closeTags.add("</DATE>");
		closeTags.add("</DATELINE>");
		closeTags.add("</TOPICS>");
		closeTags.add("</PEOPLE>");
		closeTags.add("</TITLE>");
		closeTags.add("</TEXT>");
		closeTags.add("</PLACES>");
		closeTags.add("</ORGS>");
		closeTags.add("</EXCHANGES>");
		closeTags.add("</COMPANIES>");
		closeTags.add("</UNKNOWN>");
		closeTags.add("</BODY>");
		closeTags.add("</AUTHOR>");
	}//close default constructor

	//SERVICE METHODS
	//PASS IN REUTERS DOC OBJECT AND A LIST OF LINES IN IT, SAVE TAG VALUES TO THE APPROPRIATE ATTRIBUTE IN THE REUTERS DOC OBJECT
	public void getTagValues(ReutersArticle reuterObject, String article){
		//FOR EACH TAG
		for (int i = 0 ; i < openTags.size() ; i++){
			openTag = openTags.get(i);
			closeTag = closeTags.get(i);
			//Check if opening and closing tags exist in the article
			if ( article.contains(openTag) && article.contains(closeTag) ){
				//GET THE INDEX OF THE OPEN TAG AND CLOSING TAG
				openTagIndex = article.indexOf( openTag ) + openTag.length();
				closeTagIndex = article.indexOf( closeTag );
				//SAVE THE TEXT INSIDE INTO THE PROPER DATA LOCATION (REUTERS_DOC.TAG)
				textInTag = article.substring(openTagIndex, closeTagIndex);
				reuterObject.setTag(openTag, textInTag);			
			}//close if the opening and closing tags were found
		}//close for each tag

		//REMOVE ALL THE TAGS FROM THE TEXT
		reuterObject.removeNestedTags();;

		//SEPERATE DATA <D> </D> TAGS FOR PEOPLE, PLACES, TOPICS, ORGS, COMPANIES, EXCHANGES
		seperateData(reuterObject);
	}//close GET TAG VALUES METHOD

	//REMOVE NESTED TAGS
	//Located in ReutersArticle class
	
	//PEOPLE, PLACES, TOPICS, ORGS, COMPANIES, EXCHANGES CONTAIN <D></D>
	public void seperateData( ReutersArticle reuter ){
		if ( reuter.getPeople() != null)
			if ( !reuter.getPeople().equals("") ){
				String splitString[] = reuter.getPeople().split("<D>");
				for (int i = 1 ; i < splitString.length ; i++){
					splitString[i] = splitString[i].replace("</D>", "");
					reuter.addPeople( splitString[i]);		//must remove </D> tag
				}//close for i each string that was split
			}//close if people data available
		if ( reuter.getPlaces() != null)
			if ( !reuter.getPlaces().equals("") ){
				String splitString[] = reuter.getPlaces().split("<D>");
				for (int i = 1 ; i < splitString.length ; i++){
					splitString[i] = splitString[i].replace("</D>", "");
					reuter.addPlaces( splitString[i]);		//must remove </D> tag
				}//close for i each string that was split
			}//close if people data available
		if ( reuter.getTopics() != null)
			if ( !reuter.getTopics().equals("") ){
				String splitString[] = reuter.getTopics().split("<D>");
				for (int i = 1 ; i < splitString.length ; i++){
					splitString[i] = splitString[i].replace("</D>", "");
					reuter.addTopics( splitString[i]);		//must remove </D> tag
				}//close for i each string that was split
			}//close if people data available
		if ( reuter.getOrgs() != null)
			if ( !reuter.getOrgs().equals("") ){
				String splitString[] = reuter.getOrgs().split("<D>");
				for (int i = 1 ; i < splitString.length ; i++){
					splitString[i] = splitString[i].replace("</D>", "");
					reuter.addOrgs( splitString[i]);		//must remove </D> tag
				}//close for i each string that was split
			}//close if people data available
		if ( reuter.getExchanges() != null)
			if ( !reuter.getExchanges().equals("") ){
				String splitString[] = reuter.getExchanges().split("<D>");
				for (int i = 1 ; i < splitString.length ; i++){
					splitString[i] = splitString[i].replace("</D>", "");
					reuter.addExchanges( splitString[i]);		//must remove </D> tag
				}//close for i each string that was split
			}//close if people data available
		if ( reuter.getCompanies() != null)
			if ( !reuter.getCompanies().equals("") ){
				String splitString[] = reuter.getCompanies().split("<D>");
				for (int i = 1 ; i < splitString.length ; i++){
					splitString[i] = splitString[i].replace("</D>", "");
					reuter.addCompanies( splitString[i]);		//must remove </D> tag
				}//close for i each string that was split
			}//close if people data available

	}//close function seperate data



}//CLOSE CLASS tag parser
