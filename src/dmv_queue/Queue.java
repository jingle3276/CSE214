package dmv_queue;

import java.util.Vector;


/**
 * Write a fully documented class named Queue that is a subclass of Vector. 
 * Your queue class should define operations for isEmpty, enqueue, dequeue and 
 * size*, along with a constructor that defines an empty queue. Read through 
 * the Java API documentation for the Vector class to find out which methods 
 * act the same as enqueue and dequeue. Remember that your queue will hold 
 * items of type Object.
 * the "size" method simply returns the number of elements in the queue
 * 
 * @author Ying
 *
 */

public class Queue<E> extends Vector<E>{

	/**
	 * @return true if the queue is empty
	 */
	public boolean isEmpty(){
		return super.isEmpty();
	}

	/**
	 * insert a item into the rear of the queue.
	 * @param item
	 */
	public void enqueue(E item){
		//elementData[elementCount] = item;
		//elementCount++;
		this.add(item);
	}
	
	/**
	 * remove the first element in the queue.
	 * 
	 * @return the first element was removed
	 */
	public E dequeue(){
		return this.remove(0);
	}
	
	/**
	 * 
	 * @return the size of the queue
	 */
	public int size(){
		return super.size();
	}
	
	public static void main(String args[]){
		
		Queue<Integer> q = new Queue<Integer>();
		System.out.println(q.isEmpty());
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(6);

		System.out.println(q.dequeue());
		System.out.println(q.capacity());

	}
}
