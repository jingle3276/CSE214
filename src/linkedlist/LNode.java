package linkedlist;

/**
 * Node class for singely-linked list.
 * 
 * @author Ying
 *
 */

public class LNode<E> {
	
	private E _data ; 
	private LNode<E> _link; 
	
	public LNode ( E initialData) {
		_data = initialData ;
		_link = null ; 
	}
	
	/**
	 * set the data of this node 
	 * @param data
	 */
	public void setNode(E data){
		_data = data ; 
	}
	
	/**
	 * 
	 * @return the data of this node 
	 */
	public E getNode(){
		return _data ; 
	}
	
	/**
	 * set the link to the next node 
	 * @param node
	 */
	public void setNextNode(LNode<E> node){
		_link = node ; 
	}
	
	/**
	 * 
	 * @return the next node it linked to 
 	 */
	public LNode<E> getNextNode() {
		return _link ; 
	}
	
}
