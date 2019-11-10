/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

	PERFORM LOSSY COMPRESSION TO REDUCE THE DICTIONARY SIZE

	AS PER PROJECT OUTLINE, USE LOSSY COMPRESSION FROM TABLE 5.1
	TABLE 5.1
				(distinct) terms 	nonpositional postings 		tokens (= number of positientries in postings)
				number 	D% 	T%		number	D%	T% 				number	D%	T%
unfiltered 		484,494 			109,971,179 				197,879,290
no numbers 		473,723 -2 -2 		100,680,242 -8 -8 			179,158,204 -9 -9
case folding 	391,523 -17 -19 	96,969,056 -3 -12 			179,158,204 -0 -9
30 stop words 	391,493 -0 -19 		83,390,443 -14 -24 			121,857,825 -31 -38
150 stop words 	391,373 -0 -19 		67,001,847 -30 -39 			94,516,599 -7 -52
stemming 		322,383 -17 -33 	63,812,300 -4 -42 			94,516,599 -0 -52

 */

public class CompressorLossy {

	//SERVICE METHODS
	//LOSSY COMPRESSION
	public String lossyCompression(String in){
		String out = in;
		StopWords sw = new StopWords();
		Character dot = new Character('.');
		Character space = new Character(' ');
		Character tab = new Character('	');
		Character quote = new Character('"');
		boolean isNumber = true;
		//CASE FOLDING
		out = out.toLowerCase();
		//REMOVE STOP WORDS
		//System.out.println("compressor debug 1\tin=" + in +"\tout="+out );
		for (int i = 0 ; i < sw.getStopWords().length ; i++ )
			if ( out.equals( sw.getStopWords()[i] ))
				return "";
		//REMOVE CHARACHTERS
		for (int i = 0 ; i < sw.getStopSeq().length ; i++)
			out = out.replace(sw.getStopSeq(i), " ");

		//System.out.println("compressor debug 2\tin=" + in +"\tout="+out );
		//REMOVE WHITESPACE IN LAST INDEX OF THE STRING
		//FIRST SPACE or "
		while ( out.length() > 0 && (space.equals(out.charAt(0)) || tab.equals(out.charAt(0)) || quote.equals(out.charAt(0))) )
			out = out.substring( 1, out.length());

		//System.out.println("compressor debug 3\tin=" + in +"\tout="+out );
		//END SPCACES or "
		while ( out.length() > 0 && (space.equals(out.charAt(out.length()-1)) || tab.equals(out.charAt(out.length()-1)) || quote.equals(out.charAt(out.length()-1)) ) )
			out = out.substring( 0, out.length()-1);

		//System.out.println("compressor debug 4\tin=" + in +"\tout="+out );
		//REMOVE STOP WORDS
		for (int i = 0 ; i < sw.getStopWords().length ; i++ )
			if ( out.equals( sw.getStopWords()[i] ))
				return "";

		//System.out.println("compressor debug 5\tin=" + in +"\tout="+out );
		//REMOVE NUMBERS
		for (int i = 0 ; i < out.length() ; i++)
			if ( !Character.isDigit( out.charAt(i) ) && !dot.equals(out.charAt(i))&& !space.equals(out.charAt(i)) )
				isNumber = false;
		if (isNumber || out.length() == 1 )
			return "";
		//System.out.println("compressor debug 6\tin=" + in +"\tout="+out +"\tisNumber="+ isNumber );
		return out;
	}//close function lossy compression

}//close class compressor
