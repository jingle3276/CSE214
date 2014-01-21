package dmv_queue;

/**
 *  Write a fully documented class named Simulator that contains one static 
 *  method that will simulate the system. Your method should return the average 
 *  waiting time per customer after the simulation is completed. The waiting 
 *  time for each customer is the time that customer waits on a line before is 
 *  being served (the waiting time should not include the processing time). 
 *  This method should accept the following parameters:
•	numServices - the number of services provided in the simulation (an int)
•	numAgents - the number of agents (an int)
•	arrivalProb - the probability that a customer arrives during any given 
	minute (a double)
•	maxProcessingTime - the maximum time in minutes needed to process a 
	customer's request (an int)
•	simMinutes - number of minutes to be simulated (an int)
	
	HINT 1: Before a customer arrives on a line, you will create a Customer 
	instance and put it on a line. The arrivalTime will be the current time on 
	the simulation clock, and the processingTime will be a random number 
	between 1 and maxProcessingTime. You also need to generate a random number 
	between 1 and numServices to determine the waiting line (required service) 
	for this customer.
	HINT 2: After an agent is done with a customer, you must generate a random 
	number between 1 and numServices to determine which line the next person 
	should be selected from. If the selected line is empty, you must select 
	another line. Be careful not to be trapped in an infinite loop in case all 
	lines are empty.
	NOTE 1: Remember to throw an exception if one of the input parameters is 
	invalid.
	NOTE 2: Use Math.random() to generate the random numbers you need. Don't 
	use the Random class.
	NOTE 3: When the simulation ends, don't worry about any customers waiting 
	in the line(s).
 
 * 
 * @author Ying
 *
 */

public class Simulator {

	public static int simulate(int numServices, int numAgents, int arrivalProb, 
		int minute, int maxProcessingTime, int simMinutes){
	
		
		int totoalWaitTime = 0;
		int customerServed = 0;
		int currentTime = 0; 
		
		int servingTime ;
		
		Queue[] customers = new Queue[numServices];
		
		BooleanSource arrival = new BooleanSource(arrivalProb);
		
		for(; currentTime<maxProcessingTime; currentTime++){
			
			//a customer arrived 
			if(arrival.occurs()){
				Customer c = new Customer();
				c.setArrivalTime(currentTime);
  				c.setProcessingTime((int)(Math.random()*maxProcessingTime));
				
  				int line =(int)(Math.random()*numServices);
  				customers[line].enqueue(c);
  				
			}
			
			//a customer has been served 
			//else if(servingTime==0){
				
				
				
				
			//}
			
			//just serve the customer 
			else{
				
				
			}
			
			
			
		}
		
		
		
		
		
		return 1;
	}
	
	public static void main(String[] args){
		//int processTime = (int)Math.random()*30;
		
		System.out.println((int)(Math.random()*30));
		
	}
	
}
