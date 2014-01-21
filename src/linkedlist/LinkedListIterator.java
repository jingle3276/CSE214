package linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<E> implements Iterator<E>{

	protected LNode<E> cursor ; 
	
	public LinkedListIterator (LinkedList<E> l){
		cursor = l.getHead();
	}
	
	@Override
	public boolean hasNext() {
		return (cursor!=null);
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		
		if(cursor==null)	
			throw new NoSuchElementException();  //in java.util
		
		E answer = cursor.getNode();
		cursor = cursor.getNextNode();
		return answer;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Nice try, bozo."); //in java.lang
	}

}
