package dmv_queue;

/**
 * Write a fully documented class named Customer that contains two instance 
 * variables of type int: "arrivalTime" (the time the customer arrives on a line) 
 * and "processingTime" (the time to process the customer's request). Both of 
 * these times are represented in minutes. Include any constructors, accessors 
 * and mutators that you need for this object class.
 * 
 * @author Ying
 *
 */

public class Customer {
	private int arrivalTime; 
	private int processingTime; 
	
	public Customer(){
		arrivalTime = 0;
		processingTime = 0;
	}
	
	public int getArrivalTime(){
		return arrivalTime;
	}
	
	public void setArrivalTime(int time){
		arrivalTime = time;
	}

	public int getProcessingTime(){
		return processingTime;
	}
	
	public void setProcessingTime(int time){
		processingTime = time;
	}
}
