import java.util.ArrayList;

/*
 MICHAEL GARNER
 26338739
 CONCORDIA UNIVERSITY
 COMP 479
 */

/*
 * 	CLASS TO STORE DATA FROM REUTERS FILE DATA
 */

public class ReutersArticle {

	//VARIABLES
	//STATIC INTEGERS FOR AVERAGE DOCUMENT LENGTH
	static int numberOfDocuments = 0;
	static int averageDocLength = -1;
	//Token
	private int docLength = 0;
	private ArrayList<Token> tokens = new ArrayList<Token>();
	//Tags
	private String oldId = "-1";
	private String newId = "-1";
	private String date = null;
	private String dateline = null;
	private String author = null;
	private String title = null;
	private String topics = null;				//<D>
	private String people = null;				//<D>			
	private String places = null;				//<D>
	private String orgs = null;					//<D>
	private String exchanges = null;			//<D>
	private String companies = null;			//<D>
	private String text = null;	
	private String unknown = null;
	private String body = null;
	//Data Lists
	private ArrayList<String> topicsList = new ArrayList<String>();
	private ArrayList<String> peopleList = new ArrayList<String>();
	private ArrayList<String> orgsList = new ArrayList<String>();
	private ArrayList<String> placesList = new ArrayList<String>();
	private ArrayList<String> companiesList = new ArrayList<String>();
	private ArrayList<String> exchangesList = new ArrayList<String>();

	//DISPLAY
	//PRINT
	public void print(){
		String output = "\nPinting Reuter Article:\t\toldId= " + oldId + "\t\tnewId= " + newId;
		if (title != null)
			output += "\ntitle: " + title;
		if (date != null)
			output += "\ndate: " + date;
		if (dateline != null)
			output += "\ndateline: " + dateline;
		if (author != null)
			output += "\nauthor: " + author;
		if ( topicsList.size() > 0 )
			for (int i = 0 ; i < topicsList.size(); i++ )
				output += ("\nTopicsList "+i+" :"+topicsList.get(i));
		if ( peopleList.size() > 0 )
			for (int i = 0 ; i < peopleList.size(); i++ )
				output += ("\nPeopleList "+i+" :"+peopleList.get(i));
		if ( placesList.size() > 0 )
			for (int i = 0 ; i < placesList.size(); i++ )
				output += ("\nPlacesList "+i+" :"+placesList.get(i));
		if ( orgsList.size() > 0 )
			for (int i = 0 ; i < orgsList.size(); i++ )
				output += ("\nOrgsList "+i+" :"+orgsList.get(i));
		if ( exchangesList.size() > 0 )
			for (int i = 0 ; i < exchangesList.size(); i++ )
				output += ("\nExchangesList "+i+" :"+exchangesList.get(i));
		if ( companiesList.size() > 0 )
			for (int i = 0 ; i < companiesList.size(); i++ )
				output += ("\nCompaniesList "+i+" :"+companiesList.get(i));
		if (unknown != null)
			output += "\nunknown: " + unknown;
		if (text != null)
			if (body != null)
				output += "\nbody:\n" + body;
		output += "\ntext:\n" + text;
		System.out.println(output);
	}//close print data

	public void printTokens(){
		System.out.println("\nPinting Reuter Article Tokens:");
		if (tokens != null & tokens.size() > 0 )
			for (int i=0 ; i < tokens.size() ; i++ )
				tokens.get(i).print();
	}//close function print tokens

	//SETTERS & GETTERS
	public String getDate() {return date;}
	public void setDate(String date) {	this.date = date;	}
	public String getTopics() {	return topics;	}
	public void setTopics(String topics) {	this.topics = topics;}
	public String getPeople() {	return people;}
	public void setPeople(String people) {	this.people = people;}
	public String getTitle() {	return title;	}
	public void setTitle(String title) {	this.title = title;	}
	public String getText() {	return text;	}
	public void setText(String text) {	this.text = text;	}
	public String getPlaces() {	return places;	}
	public void setPlaces(String places) {	this.places = places;	}
	public String getOrgs() {	return orgs;	}
	public void setOrgs(String orgs) {	this.orgs = orgs;	}
	public String getExchanges() {	return exchanges;	}
	public void setExchanges(String exchanges) {	this.exchanges = exchanges;	}
	public String getCompanies() {	return companies;	}
	public void setCompanies(String companies) {	this.companies = companies;	}
	public String getUnknown() {	return unknown;	}
	public void setUnknown(String unknown) {	this.unknown = unknown;	}
	public String getBody() {return body; } 
	public void setBody(String body) {	this.body = body; }
	public String getDateline() {	return dateline; }
	public String getOldId() {	return oldId;	}
	public void setOldId(String oldId) {	this.oldId = oldId;	}
	public String getNewId() {	return newId;	}
	public void setNewId(String newId) {	this.newId = newId;	}
	public void setDateline(String dateline) {	this.dateline = dateline;	}
	public String getAuthor() {	return author;	}
	public void setAuthor(String author) {	this.author = author;	}
	public Token getToken(int index){ return tokens.get(index); }
	public int countTokens(){ return this.tokens.size(); }

	//SET TAGS
	public void setTag( String tag, String text ){
		if (tag.equals("<DATE>"))
			this.setDate(text);
		else if (tag.equals("<DATELINE>"))
			this.setDateline(text);
		else if (tag.equals("<TOPICS>"))
			this.setTopics(text);
		else if (tag.equals("<TITLE>"))
			this.setTitle(text);
		else if (tag.equals("<TEXT>"))
			this.setText(text);
		else if (tag.equals("<PEOPLE>"))
			this.setPeople(text);
		else if (tag.equals("<PLACES>"))
			this.setPlaces(text);
		else if (tag.equals("<ORGS>"))
			this.setOrgs(text);
		else if (tag.equals("<COMPANIES>"))
			this.setCompanies(text);
		else if (tag.equals("<EXCHANGES>"))
			this.setExchanges(text);
		else if (tag.equals("<UNKNOWN>"))
			this.setUnknown(text);
		else if (tag.equals("<BODY>"))
			this.setBody(text);
		else if (tag.equals("<AUTHOR>"))
			this.setAuthor(text);
	}//close set tag

	public boolean isBlank(){
		boolean returnValue = false;
		if (date == null && dateline == null && topics == null && people == null && title == null && text == null && 
				places == null&& orgs == null && exchanges == null && companies == null && unknown == null && body == null )
			returnValue = true;
		return returnValue;
	}//close is blank

	//ADD  TO LIST
	public void addPeople(String in){ this.peopleList.add(in); }
	public void addPlaces(String in){ this.placesList.add(in); }
	public void addTopics(String in){ this.topicsList.add(in); }
	public void addOrgs(String in){ this.orgsList.add(in); }
	public void addExchanges(String in){ this.exchangesList.add(in); }
	public void addCompanies(String in){ this.companiesList.add(in); }

	//REMOVE NESTED TEXT TAGS
	public void removeNestedTags(){
		//VARIABLES
		String openTag, closeTag, head, tail;
		int openTagIndex, closeTagIndex;
		//FOR EACH TAG
		for (int i = 0 ; i < TagParser.openTags.size() ; i++){
			openTag = TagParser.openTags.get(i);
			closeTag = TagParser.closeTags.get(i);
			//Check if opening and closing tags exist in the article
			if ( this.getText()!=null && !this.getText().equals("") )
				if ( this.text.contains(openTag) && this.text.contains(closeTag) ){
					//GET THE INDEX OF THE OPEN TAG AND CLOSING TAG
					openTagIndex = this.text.indexOf( openTag );
					closeTagIndex = this.text.indexOf( closeTag ) + openTag.length() + 1;
					//SAVE THE TEXT INSIDE INTO THE PROPER DATA LOCATION (REUTERS_DOC.TAG)
					if ( openTagIndex > 0 && closeTagIndex < (this.getText().length()-1-closeTag.length()) ){
						head = this.getText().substring(0, openTagIndex);
						tail = this.getText().substring( closeTagIndex, this.getText().length());
						this.setText( (head+tail) );
					}//close if
					else if ( openTagIndex > 0 && closeTagIndex == (this.getText().length()) )
						this.setText( this.getText().substring(0, openTagIndex) );
					else if ( openTagIndex == 0 && closeTagIndex < (this.getText().length() ) )
						this.setText( this.getText().substring(closeTagIndex, this.getText().length() ) );
					else if ( openTagIndex == 0 && closeTagIndex == (this.getText().length() ) )
						this.setText("");	
				}//close if the opening and closing tags were found
		}//close for each tag
	}//close function remove nested tags

	public void tokenize(){
		String[] splitWord;
		Token token;
		PunctuationRemover pr = new PunctuationRemover();
		if (title != null){
			splitWord = title.split("\\s");
			for ( int i = 0 ; i < splitWord.length ; i++ ){
				if (splitWord[i].length() > 1)
					splitWord[i] = pr.removeEndPunctuations( splitWord[i] );
				token = new Token( splitWord[i], "title", this.newId, this.oldId);
				if (!splitWord.equals("-") &&  !splitWord[i].equals("") && !splitWord[i].equals(" ") && !splitWord[i].equals("\t") && !splitWord[i].equals("\n") )
					tokens.add( token );
			}//close  for i each word that was split
		}//close if title
		if (date != null){
			splitWord = date.split("\\s");
			for ( int i = 0 ; i < splitWord.length ; i++ ){
				if (splitWord[i].length() > 1)
					splitWord[i] = pr.removeEndPunctuations( splitWord[i] );
				token = new Token( splitWord[i], "date", this.newId, this.oldId);
				if (!splitWord.equals("-") && !splitWord[i].equals("") && !splitWord[i].equals(" ") && !splitWord[i].equals("\t") &&  !splitWord[i].equals("\n") )
					tokens.add( token );
			}//close for i date
		}//close if date
		if (dateline != null){
			splitWord = dateline.split("\\s");
			for ( int i = 0 ; i < splitWord.length-1 ; i++ ){
				if (splitWord[i].length() > 1)
					splitWord[i] = pr.removeEndPunctuations( splitWord[i] );
				token = new Token( splitWord[i], "dateline", this.newId, this.oldId);
				if (!splitWord.equals("-") &&  !splitWord[i].equals("") && !splitWord[i].equals(" ") && !splitWord[i].equals("\t") && !splitWord[i].equals("\n") )
					tokens.add( token );
			}//close for i
		}//close if dateline
		if (author != null){
			token = new Token( this.author, "author", this.newId, this.oldId);
			tokens.add( token );
		}//close if author
		if (topics !=null & topicsList.size() > 0 )
			for (int i = 0 ; i <  topicsList.size() ; i++){
				token = new Token( this.topicsList.get(i), "topics", this.newId, this.oldId);
				tokens.add( token );
			}//close for i each topic
		if (people !=null & peopleList.size() > 0 )
			for (int i = 0 ; i <  peopleList.size() ; i++){
				token = new Token( this.peopleList.get(i), "people", this.newId, this.oldId);
				tokens.add( token );
			}//close for i each people
		if (places !=null & placesList.size() > 0 )
			for (int i = 0 ; i <  placesList.size() ; i++){
				token = new Token( this.placesList.get(i), "places", this.newId, this.oldId);
				tokens.add( token );
			}//close for i each places
		if (orgs !=null & orgsList.size() > 0 )
			for (int i = 0 ; i <  orgsList.size() ; i++){
				token = new Token( this.orgsList.get(i), "orgs", this.newId, this.oldId);
				tokens.add( token );
			}//close for i each orgs
		if (exchanges !=null & exchangesList.size() > 0 )
			for (int i = 0 ; i <  exchangesList.size() ; i++){
				token = new Token( this.exchangesList.get(i), "exchanges", this.newId, this.oldId);
				tokens.add( token );
			}//close for i each exchanges
		if (companies !=null & companiesList.size() > 0 )
			for (int i = 0 ; i <  companiesList.size() ; i++){
				token = new Token( this.companiesList.get(i), "companies", this.newId, this.oldId);
				tokens.add( token );
			}//close for i each company
		//BODY
		if (body !=null)
			Token.tokenizeText( tokens, this.body, "body", this.newId, this.oldId);
		//TEXT
		if (text !=null)
			Token.tokenizeText( tokens, this.text, "text", this.newId, this.oldId);
		//UNKNOWN
		if (unknown !=null)
			Token.tokenizeText( tokens, this.unknown, "unknown", this.newId, this.oldId);

	}//close function tokenize tags

	public void compressTokens(){
		CompressorLossy ls = new CompressorLossy();
		for (int i = 0 ; i < tokens.size() ; i++){
			//System.out.println("DEBUG RA.COMPRESS TOKENS VALUE = " + tokens.get(i).getValue() );
			tokens.get(i).setValue( ls.lossyCompression( tokens.get(i).getValue() ) );
			//System.out.println("COMPRESSED VALUE = " + tokens.get(i).getValue() );
			if ( tokens.get(i).getValue().equals("") || tokens.get(i).getValue().equals(" ") || tokens.get(i).getValue().equals("	") ){
				tokens.remove(i);
				i--;
			}//close if
		}//close for i
	}//close function compress tokens

}//close class
