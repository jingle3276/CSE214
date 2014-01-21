package linkedlist;

import java.util.Iterator;

import stack_evaluation.Token;

/**
 * The singley-linked list class. Each list has a head and tail and an int size. 
 * The list is generic for all types of the object.
 * 
 * @author Ying
 */

public class LinkedList<E> implements Iterable<E> {
	private LNode<E> head ;
	private LNode<E> tail ; 
	private int size ;
	
	/**
	 * constructor 
	 */
	public LinkedList (){
		head = null ; 
		tail = null ;
		size = 0 ;  
	}
	

	/**
	 * @return size of the linkedList
	 */
	public int getSize(){
		return size ; 
	}
	
	/**
	 * add a new LNode to the list
	 * Compelxity: O(1)
	 * @param data
	 */
	public void addToHead(E data){
		
		if(head==null){ //empty case
			LNode<E> newNode = new LNode<E>(data);
			head = newNode ; 
			tail = head; 
			size++ ;
		}
		
		else{ //non-empty case 
			LNode<E> newNode = new LNode<E>(data);
			newNode.setNextNode(head);
			head = newNode ;
			size++;
		}
		
	}
	
	/**
	 * append to tail
	 */
	
	public void appendToTail(E data){
		LNode<E> newNode = new LNode<E>(data);
		
		if(head==null) {//empty case
			head = tail = newNode;
		}
		
		else{
			tail.setNextNode(newNode);
			tail = newNode;
		}
		size++;
	}
	
	
	
	/**
	 * remove the head of the list.
	 * Complexity: O(1)
	 * 
	 * @return 
	 * @throws Exception
	 */
	
	public E removeHead() throws Exception{
		if(size==0){
			throw new Exception("Lisk is Empty.");
		}
		
		E temp =  (E) head.getNode(); 
		
		if(size==1){ //only one element
			head = null; 
			tail = null;
			size--;
		}
		else{
			head = head.getNextNode();
			size--;
		}
		return temp; 
	}
	/**
	 * 
	 * @return the head of the list
	 */
	public LNode<E> getHead(){
		return head ;
	}

	/**
	 * @return the tail of the list
	 */
	public LNode<E> getTail(){
		return tail ;
	}
	
	/**
	 * recursive implementation 
	 * @throws Exception 
	 */
	public void reverse( ) throws Exception{
		
		if(size==1)
			return  ;
		
		//E head = getHead().getNode();
		E head = removeHead();
		reverse( ); 
		appendToTail(head);
 
	}
	

	/**
	 * iterative implementation of reverseList
	 */
	public void reverseIter(){
		if(size==1)
			return;
		
		LNode<E> prev = null, cursor=head, next=head.getNextNode();
		tail = head;
		
		while(cursor!=null){
			
			cursor.setNextNode(prev);
			prev = cursor;
			cursor = next;
			
			if(next!=null)//must check here ! 
				next = next.getNextNode();
			
		}
		head = prev;
		
	}
	
	public String toString(){
		StringBuffer out = new StringBuffer();
		LNode<E> cursor = head;
		out.append("[ ");
		while(cursor!=null){
			out.append(cursor.getNode().toString()+" ");
			cursor = cursor.getNextNode();
		}
		out.append("]");
		return out.toString();
		
	}
	
	
	/**
	 * This is a factory method. 
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator<E>(this);
	}


	
	public static void main(String args[]){
	
		LinkedList<Token> l = new LinkedList<Token>();
		
		try {
			l.addToHead(new Token("3"));
			l.addToHead(new Token("5"));
			l.addToHead(new Token("+"));
			l.appendToTail(new Token("7"));
			System.out.println(l.toString());
			//l.removeHead();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		/*for(Token t : l){
			System.out.println(t.getValue());	
		}*/
		Iterator<Token> it = l.iterator(); 
		
		Token t ; 
		while(it.hasNext()){
			t = it.next();
			//System.out.println(t.getValue());
		}
		
		//System.out.println(l.getSize());
	}
	
}
