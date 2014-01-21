package table;

public class Duration {
	
	private int min ; 
	private int sec ; 
	
	public Duration(int min, int sec){
		this.min = min ; 
		this.sec = sec ; 
	}
	
	/**
	 * 
	 */
	public int getMin(){
		return min;
	}
	
	public int hashCode(){
		int result = 17;
		result = 37 * result + sec; 
		result = 37*result + min; 
		return result ;
	}
	

}
