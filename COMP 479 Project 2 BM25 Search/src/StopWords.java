/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

 	WORDS TO AVOID IN THE DICTIONARY
 	
 	LIST TAKEN FROM : 
 	https://gist.github.com/sebleier/554280
 	"a", "about", "above", "after", "again", "against", "ain", "all", "am", "an", "and", "any", "are", "aren", "aren't", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can", "couldn", "couldn't", "d", "did", "didn", "didn't", "do", "does", "doesn", "doesn't", "doing", "don", "don't", "down", "during", "each", "few", "for", "from", "further", "had", "hadn", "hadn't", "has", "hasn", "hasn't", "have", "haven", "haven't", "having", "he", "her", "here", "hers", "herself", "him", "himself", "his", "how", "i", "if", "in", "into", "is", "isn", "isn't", "it", "it's", "its", "itself", "just", "ll", "m", "ma", "me", "mightn", "mightn't", "more", "most", "mustn", "mustn't", "my", "myself", "needn", "needn't", "no", "nor", "not", "now", "o", "of", "off", "on", "once", "only", "or", "other", "our", "ours", "ourselves", "out", "over", "own", "re", "s", "same", "shan", "shan't", "she", "she's", "should", "should've", "shouldn", "shouldn't", "so", "some", "such", "t", "than", "that", "that'll", "the", "their", "theirs", "them", "themselves", "then", "there", "these", "they", "this", "those", "through", "to", "too", "under", "until", "up", "ve", "very", "was", "wasn", "wasn't", "we", "were", "weren", "weren't", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "won", "won't", "wouldn", "wouldn't", "y", "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself", "yourselves", "could", "he'd", "he'll", "he's", "here's", "how's", "i'd", "i'll", "i'm", "i've", "let's", "ought", "she'd", "she'll", "that's", "there's", "they'd", "they'll", "they're", "they've", "we'd", "we'll", "we're", "we've", "what's", "when's", "where's", "who's", "why's", "would

 */
/*
 ["i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"]
 */

import java.util.ArrayList;

public class StopWords {
	
	//ATTRIBUTES
	//static ArrayList<String> stopWords = new ArrayList<String>();
	//FOR LOSSY COMPRESSION
	private String[] stopWord =  {"BC-CHAMPION-PRODUCTS-&lt;CH", "BC-USX-&lt;X", "&#2;", "&#3;", "&#127;", "&#5;&#5;&#5;a", "&#5;&#5;&#5;c", "&#5;&#5;&#5;cq", "&#5;&#5;&#5;e", "&#5;&#5;&#5;eq", "&#5;&#5;&#5;f", "&#5;&#5;&#5;fq", "&#5;&#5;&#5;g", "&#5;&#5;&#5;gq", "&#5;&#5;&#5;l", "&#5;&#5;&#5;lq", "&#5;&#5;&#5;m", "&#5;&#5;&#5;mq", "&#5;&#5;&#5;rm", "&#5;&#5;&#5;t", "&#5;&#5;&#5;tq", "&#5;&#5;&#5;y", "&#5;&#5;&#5;v", "a", "about", "above", "after", "again", "against", "ain", "all", "am", "an", "and", "any", "are", "aren", "aren't", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can", "couldn", "couldn't", "d", "did", "didn", "didn't", "do", "does", "doesn", "doesn't", "doing", "don", "don't", "down", "during", "each", "few", "for", "from", "further", "had", "hadn", "hadn't", "has", "hasn", "hasn't", "have", "haven", "haven't", "having", "he", "her", "here", "hers", "herself", "him", "himself", "his", "how", "i", "if", "in", "into", "is", "isn", "isn't", "it", "it's", "its", "itself", "just", "ll", "m", "ma", "me", "mightn", "mightn't", "more", "most", "mustn", "mustn't", "my", "myself", "needn", "needn't", "no", "nor", "not", "now", "o", "of", "off", "on", "once", "only", "or", "other", "our", "ours", "ourselves", "out", "over", "own", "re", "s", "same", "shan", "shan't", "she", "she's", "should", "should've", "shouldn", "shouldn't", "so", "some", "such", "t", "than", "that", "that'll", "the", "their", "theirs", "them", "themselves", "then", "there", "these", "they", "this", "those", "through", "to", "too", "under", "until", "up", "ve", "very", "was", "wasn", "wasn't", "we", "were", "weren", "weren't", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "won", "won't", "wouldn", "wouldn't", "y", "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself", "yourselves", "could", "he'd", "he'll", "he's", "here's", "how's", "i'd", "i'll", "i'm", "i've", "let's", "ought", "she'd", "she'll", "that's", "there's", "they'd", "they'll", "they're", "they've", "we'd", "we'll", "we're", "we've", "what's", "when's", "where's", "who's", "why's", "would" };
	//PREPROCESSING
	private String[] stopRegEx = {"(&#22;)(.*)(;reute)", "(&lt;)(.*)", "(BC-&lt;)(.*)"};
	private char[] stopChars = {'(', ')', '"', '\'', ',', '.', '<', '>'};
	private String[] stopSeq = {"(", ")", "\"", ",", ".", "<", ">","BC-USX-&lt;X","&lt;CH>", "&lt;CH", "&lt;", "bc-******", "bc-/", "bc--", "bc-", "reuter", "reuters", "&#22;&#22;&#1;f0", "&#31;reute", "&#22;&#22;&#1;f", "&#5;", "&amp", "&#127;"};
	private String[] whiteSpace = {"", " ", "\t", "\t\t", "\t\t\t"};
	//CONSTRUCTOR
	public StopWords(){}//close constructor
	
	//SETTERS AND GETTERS
	public String[] getStopWords(){ return this.stopWord; }
	public String[] getStopRegEx(){ return this.stopRegEx; }
	public char[] getStopChars(){ return this.stopChars; }
	public char getStopChars(int i){ return this.stopChars[i]; }
	public String[] getStopSeq(){ return this.stopSeq; }
	public String getStopSeq(int i){ return this.stopSeq[i]; }
	public String[] getWhiteSpace(){ return this.whiteSpace; }
}//close class stop words
