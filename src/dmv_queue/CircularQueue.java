package dmv_queue;

public class CircularQueue {

	int[] arr ; 
	int head ;
	int tail ;
	int maxSize ; 
	
	
	public CircularQueue(int initSize){
		arr = new int[initSize];
		maxSize = initSize ;
		head = -1 ;
		tail = -1 ; 
	}
	
	
	public void enqueue(int data){
		if((tail+1)%maxSize == head){
			System.out.println("Quene is full, not insert");
			return ;
		}
			
		if(head==-1){
			head =0;
			tail = 0;
		}
		else{
			tail = (tail + 1) % maxSize ; 
		}
		
		arr[tail] = data;
		
	}
	
	public int dequeue(){
		
		if(head==-1){
			System.out.println("Quene is empty");
			return -1 ;
		}
		
		
		int result = arr[head];
		
		if(head==tail){
			head = -1;
			tail = -1;
		}
		else{
			head =( head + 1 ) % maxSize ;
		}
		
		return result ; 
	}
	
	
	
}
