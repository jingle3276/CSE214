package spam_filter;


/**
 * Write a fully documented class named SpamDriver that contains a spam 
 * filter.  This will be the class responsible for saving and loading the 
 * spam filter, as well as displaying the user interface.  SpamDriver must 
 * contain the following static instance variables:

 * @author Ying
 *
 */
public class SpamDriver {
	
	/**
	 * The spam filter.
	 */
	private static SpamFilter filter ;
	
	/**
	 * The name of the file in which the spam filter will be saved.
	 */
	private static final String FILENAME = "SpamFilter.obj" ; 
	
	/**
	 * The minimum percentage of "bad" words (with respect to the total 
	 * number of words) in an email needed for that email to be considered 
	 * spam. 
	 */
	private static final float SPAM_RATIO = (float) 0.05 ;
	
	//SpamDriver must include the following static methods:
	
	/**
	 * Loads the serialized object stored in FILENAME and stores it into 
	 * filter.  If no file named FILENAME is found, create a new SpamFilter 
	 * instance.
	 *
	 */
	private static void loadFilter(){
		
	}
	
	/**
	 * Saves filter into the file named FILENAME.
	 */
	private static void  saveFilter(){
		
	}
	
	/**
	 *  The main method.  From here, you will call loadFilter() and 
	 *  saveFilter() at the appropriate times.  In addition, you will 
	 *  display a menu and handle the following user-input commands:
	 */
	 public static void main(String[] args){
		 
		 
		 /**
	 
			•	C (Check Emails)
			Loads a file containing information about the emails.  Input:  Name of file
			
			•	I (Insert Word)
			Inserts a word into the spam filter.  Input:  Word to insert
			
			•	R (Remove Word)
			Removes a word from the spam filter.  Input:  Word to remove
			
			•	S (Search for Word)
			
			Searches for a word within the spam filter.  Input:  Word to search for.
			
			•	Q (Quit Program)
			Quit program
			The C option will work as follows:  it takes the name of an input file (described below) which will describe all of the emails.  It then creates EmailInfo instance for each email, setting each of the appropriate fields.  It then passes the message of the email to the SpamFilter object to check for spam, and displays the results in a tabular format (also described below)
			
			FILE FORMAT:  The C option will take in as input the name of a file.  The file will contain the recipient, author, date received, subject of the email, as well as the name the file where the message of the email may be found, in that order.  These tokens will be separated by tab characters ('\t', in Java).  A sample input file may be found here:
			SampleEmailInfo.txt and the associated email1.txt and email2.txt.
			If you make your own sample input files, make sure that each token is separated by a tab character ('\t'), not a space.
										
		  */
 
	 }

}
