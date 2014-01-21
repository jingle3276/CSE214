package stack_evaluation;

/**
 * Write a fully-documented class named Stack that is used as a 
   last-in/first-out mechanism for Token objects. You should provide a constructor and the methods 
 * push(), pop(), peek(), isEmpty(), isFull(), and size(), as were discussed in 
 * lecture. You may choose to use either the array or linked-list implementation 
 * for your stack and may assume that the maximum number of elements that the 
 * stack can hold will be MAX_SIZE = 100.
 * 
 * Note: You may use the Stack class from the Java API instead of implementing 
 * your own.
 * 
 * @author Ying
 *
 */

/**
 * Array implementation of the stack
 * The stack is generic typed  
 */

public class Stack<E> {
	private final int MAX_SIZE = 100;
	private E[] item ;
	private int size ; 
	
	public Stack(){
		item = (E[]) new Object[MAX_SIZE];
		size = 0 ;
	}
	
	public int size(){
		return size ;
	}
	
	public boolean isEmpty(){
		return (size==0);
	}
	
	public boolean isFull(){
		return (size==MAX_SIZE);
	}
	
	public void push(E data) throws Exception{
		if(isFull()){
			throw new Exception("Stack is full");
		}
		
		item[size]= data;
		size++;
	}
	
	public E pop() throws Exception{
		if(isEmpty()){
			throw new Exception("Stack is empty.");
		}
		
		E temp = item[size-1];
		size--;
		return temp; 
	}
	
	public E peek() throws Exception{
		if(isEmpty()){
			throw new Exception("Stack is empty.");
		}
		return item[size-1];
	}
	
	public static void main(String args[]){
		
		Stack<Token> s = new Stack<Token>();
		Token t1 = new Token("1");
		Token t2 = new Token("22");
		Token op = new Token("+");
		
		try {
			s.push(t1);
			s.push(t2);
			s.push(op);
			
			while(!s.isEmpty())	{
				if(s.peek().isOperator())
					System.out.print(s.pop().getOperator());
				else 
					System.out.print(s.pop().getValue());
				System.out.println(" "+ s.size());
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
