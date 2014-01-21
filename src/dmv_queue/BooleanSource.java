package dmv_queue;

/**
 * The probability of an event occurring is 1/4. Does the event occur? 
 * Generate a uniform random number between 0 and 1.
 *  - if the number is between 0 and 0,25, return TRUE. 
 *  - if the number is between 0,25 and 1, teturn FALSE. 
 * 
 * @author Ying
 *
 */

public class BooleanSource {
	private double probability ; 
	
	public BooleanSource(double p) throws IllegalArgumentException{
		if(p<0.0 || p>1.0){
			throw new IllegalArgumentException("probability is not between 0 and 1.");
		}
		
		probability = p;
	}
	
	public boolean occurs(){
		return (Math.random() < probability);
	}
	
}
