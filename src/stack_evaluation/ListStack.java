package stack_evaluation;
import linkedlist.LinkedList;

/**
 * LinkedList implementation of the stack.
 * The stack is generic  
 * 
 * Write a fully-documented class named Stack that is used as a 
 * last-in/first-out mechanism for Token objects. You should provide a constructor 
 * and the methods 
 * push(), pop(), peek(), isEmpty(), isFull(), and size(), as were discussed in 
 * lecture. You may choose to use either the array or linked-list implementation 
 * for your stack and may assume that the maximum number of elements that the 
 * stack can hold will be MAX_SIZE = 100.
 * 
 * Note: You may use the Stack class from the Java API instead of implementing 
 * your own.
 * 
 * @author Ying
 */

public class ListStack<E> {
	
	private final int MAX_SIZE = 100; 
	private LinkedList<E> list;
	
	public ListStack(){
		list = new LinkedList<E>();
		//size = 0;
	}
	
	/**
	 * push an item into the stack
	 * 
	 * @param item
	 * @throws Stack full Exception 
	 */
	public void push(E item) throws Exception{
		if(list.getSize()==MAX_SIZE){
			throw new Exception("Stack is full.");
		}
		list.addToHead(item);
	}
	/**
	 * pop up the top of the stack
	 * 
	 * @return object on the top of the stack
	 * @throws Empty stackException
	 */
	
	public E pop() throws Exception{
		return list.removeHead();
	}
	
	/**
	 * @return the object on top of the stack 
	 */
	public E peek(){
		return list.getHead().getNode();
	}
	
	/**
	 * @return if the stack is empty
	 */
	public boolean isEmpty(){
		return (list.getSize()==0);
	}
	/**
	 * @return if the stack is full.
	 */
	
	public boolean isFull(){
		return (list.getSize()==MAX_SIZE) ; 
	}
	
	/**
	 * @return the size of the stack.
	 */
	public int size(){
		return list.getSize();
	}
	
/*	public static void main(String args[]){
		ListStack<Token> st = new ListStack<Token>();
		try {
			System.out.println(st.isFull());
			
			st.push(new Token("2"));
			st.push(new Token("5"));
			st.push(new Token("/"));
			System.out.println(st.peek().getOperator());
			System.out.println(st.size());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
