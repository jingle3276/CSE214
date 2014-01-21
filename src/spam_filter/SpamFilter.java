package spam_filter;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Write a fully documented class named SpamFilter that does the work of a 
 * spam filter.  This class must implement the Serializable interface; in 
 * addition, it musthave the following instance variables:
 
   SpamFilter must include the following methods:
 
 * @author Ying
 *
 */

public class SpamFilter implements Serializable{

	private static final long serialVersionUID = -1986612757327378419L;
	
	/**
	 * A hash table that contains all the "bad" words that the spam filter 
	 * is looking for.
	 */
	private Hashtable<String, String> badWords ; 
	
	
	public SpamFilter(){
		badWords = new Hashtable<String,String>();
	}
	
	/**
	 * Inserts a word into the Hashtable.  This word will be considered by 
	 * the spam filter to be a "bad" word, and every instance of this word 
	 * within the email will increase that email's likeliness of being marked 
	 * as spam.
	 * 
	 * @param word
	 */
	public void insert(String word){
		badWords.put(word, word);

	}
	
	/**
	 * 	Removes a word from the Hashtable.  
	 * 
	 * @param word
	 * @throws ElementNotFoundException If the String is not in the 
	 * 	Hashtable
	 */
	public void remove(String word) throws ElementNotFoundException{
		if(badWords.remove(word) == null)
			throw new ElementNotFoundException();
	}

	/**
	 * Returns true if the given Stringis found within the Hashtable; 
	   false otherwise.
	
	 * @param checkMe
	 * @return
	 */
	public boolean isBadWord(String checkMe){
		
		return badWords.contains(checkMe);
	}
	
	/**
	 *
	Checks if the given message is to be considered spam.  It first splits 
	the email up word-by-word, and checks if each word is a "bad" word by 
	checking if it is in the Hashtable.  The String can be split using the 
	StringTokenizer class, or using the String.split() method (since 
	StringTokenizer is deprecated, an example of using String.split() is 
	given towards the bottom of the page).  For simplicity's sake, we will 
	assume there is no punctuation.  However, words may contain both upper 
	and lower case letters - keep this in mind (example:  the words "mortgage"
	and "MorTGagE" are equivalent; if one is matched, both should be matched).
	Finally, it returns the percentage of words that were hit (i.e. the number
	of words that matched in the Hashtable divided by the total number of 
	words in the message).
	 */
	
	public float checkEmail(String message){
		
		String [] words = message.toLowerCase().split(" ");
		int count = 0;
		
		for(int i =0; i<words.length;i++){
			if(badWords.contains(words[i]))
				count++;
		}
		
		return (count/words.length);
		
	}
	
	
	
	
}
