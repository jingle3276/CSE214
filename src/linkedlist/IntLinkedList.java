package linkedlist;

/**
 * has-A implementation of the generic LikedList class. 
 * 
 * @author Ying
 *
 */

public class IntLinkedList  {

	private LinkedList<Integer> intList;
	
	public IntLinkedList(){
		intList = new LinkedList<Integer>();
	} 
	
	/**
	 * get the size of the linkedList
	 * @return
	 */
	public int getSize(){
		return  intList.getSize() ; 
	}
	
	/**
	 * @return the head of the intList
	 */
	public LNode<Integer> getHead(){
		return intList.getHead();
	}
	
	/**
	 * add a new LNode to the list
	 * Compelxity: O(1)
	 * @param data                        
	 */
	public void addToHead(int data){
		intList.addToHead(data);
	}
	
	/**
	 * remove the head of the list.
	 * Complexity: O(1)
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public int removeHead() throws Exception{
		
		return intList.removeHead();
		
	}
	
	public void reverse(){
		intList.reverseIter();
	}
	
	public void reverse2() throws Exception{
		intList.reverse();
	}
	
	public String toString(){
		
		LNode<Integer> cursor = intList.getHead(); 
		StringBuffer out = new StringBuffer();
		
		while(cursor!=null){
			out.append(cursor.getNode() + " ");
			cursor = cursor.getNextNode();
		}
		
		return out.toString();
	
	}
	
	
	/**
	 * Complexity: O(n), 1 iteration 
	 * 
	 * @return
	 */
	
	public LNode<Integer> getLargest(){
		LNode<Integer> max = intList.getHead() ; 
		LNode<Integer> nodePtr = intList.getHead() ;
		
		while(nodePtr!=null){
			
			if(nodePtr.getNode()>max.getNode()){
				max = nodePtr;
			}
			nodePtr=nodePtr.getNextNode();
		}
		
		return max ;
	}
	
	/**
	 * 
	 * Complexity: O(n), 1 interation, two pointers.
	 * @return
	 */
	
	public LNode<Integer> getSecond(){
		LNode<Integer> max ;
		LNode<Integer> second ;
		
		if(intList.getHead().getNode()>intList.getHead().getNextNode().getNode()){
			max = intList.getHead();
			second = intList.getHead().getNextNode();
		}
		else{
			second = intList.getHead();
			max = intList.getHead().getNextNode();
		}
		
		LNode<Integer> nodePtr = intList.getHead();
		
		while(nodePtr!=null){
			if(nodePtr.getNode()>=max.getNode()){ //= is important 
				
				if(second.getNode()<max.getNode()){ //very important 
					second = max ;
				}
				
				max = nodePtr;	
			}
			
			if(nodePtr.getNode()<max.getNode() && nodePtr.getNode()>second.getNode()){
				second = nodePtr; //new second 
			}

			nodePtr=nodePtr.getNextNode();
		}
		
		return second ;
	}

	/**
	 * idea: use kth iterations. every time, update the variable largest.
	 * at the kth time, the variable will be the kth largest value. 
	 * 
	 * Complexity: O(kn)   
	 * 
	 * @param k (<= n)
	 * @return the kth largest node in the list. 
	 */
/*	public LNode<Integer> getKth(int k){
		LNode<Integer> largest  ;
		LNode<Integer> second  ;
		LNode<Integer> nodePtr = intList.getHead();
		
		int i = 0;
		
		while(i<k && nodePtr!=null){
			largest = getLargest(); 
			
			
		}

	}*/
	
	public static void main(String args[]){
		IntLinkedList l = new IntLinkedList();
		l.addToHead(3);
		l.addToHead(1);
		l.addToHead(5);
		l.addToHead(7);
		l.addToHead(9);
		
		l.addToHead(0);
		l.addToHead(6);
		l.addToHead(9);
		l.addToHead(5);
		
		System.out.println(l.toString());
 
		try {
			l.reverse2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(l.toString());
		//System.out.println(l.getSecond().getNode());
		
	}
	
 
 	
}
