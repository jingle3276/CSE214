package diskcluster;
import friendlist.IllegalArgumentException;

/**
 * SPECIAL NOTE: Be sure to implement these operations in the most efficient 
 * way that you can find. In your documentation for each operation(not 
 * the constructor), you should determine their order of complexity using 
 * Big-O notation, where N is the number of disk clusters in the list. 
 * You should write a comment in each method that gives the order of 
 * complexity along with a concise but accurate explanation of your result.
 * 
 * @author Ying
 */

public class DiskClusterList {

	private DiskClusterNode _head ;  
	private DiskClusterNode _tail ;
	//private DiskClusterNode _cursor ;
	private int _size ; 
	
	public DiskClusterList () {
		_head = null ; 
		_tail = null ;
		//_cursor = null ;
		_size = 0;
	}
	
	/**
	 * Creates a new DiskClusterNode with the given cluster number and data 
	 * and appends it to the tail of this DiskClusterList.
	 * 
	 * @param cluster
	 * @param data
	 * 
	 * complexity: O(1)
	 */
	public void addClusterToTail(int cluster, String data){
		DiskClusterNode newNode = new DiskClusterNode(cluster, data);
		
		if(_head!=null){ //not a empty list
			_tail.setNextNode(newNode);	
			_tail = newNode ;
		}
		
		else{  //empty list
			_tail = newNode ; 
			_head = newNode ;
		}
		
		_size = _size + 1 ;
	}
	
	/**
	 * Creates a new DiskClusterNode with the given cluster number and data 
	 * and appends it after the node in the list at the specified position. 
	 * Assume that the first node in the list is position 1. 
	 * If position is 0, insert the new node at the head of the list. 
	 * If position is less than 0 or if position is greater than the 
	 * number of nodes in the list, throw an appropriate exception 
	 * and do not perform the insertion. 
	 * 
	 * Note: when inserting into a list, need to consider empty case and non-empty case. 
	 * 
	 * @param cluster
	 * @param data
	 * @param position
	 * 
	 * complexity: O(n)
	 */
	public void insertCluster(int cluster, String data, int position) throws IllegalArgumentException{
		
		if( position<0 || position>_size){
			throw new IllegalArgumentException();
		}
		
		DiskClusterNode newNode = new DiskClusterNode(cluster, data);	
		
		/*if(position==0){ //insert the new node at the head of the list 
			
			newNode.setNextNode(_head); 
			_head = newNode; 
		}
		
		else { //insert in the middle of the list 
 			
			boolean done = false ; 
			DiskClusterNode nodePtr = getNodePtr(position) ;
			newNode.setNextNode(nodePtr.getNextNode());
			nodePtr.setNextNode(newNode);
		}*/
		
		if(_head==null){ //empty list 
			_head = newNode ;
			_tail = newNode ;
		}
		
		else { //non-empty list 
			if(position==0){ //inserting new head 
				newNode.setNextNode(_head);
				_head = newNode ;
			}
			else if(position==_size){//inserting new tail
				_tail.setNextNode(newNode);
				_tail = newNode ;
			}
			
			else { //inserting into the middle 
				DiskClusterNode nodePtr = getNodePtr(position) ;
				newNode.setNextNode(nodePtr.getNextNode());
				nodePtr.setNextNode(newNode);
			}

		}
		
		_size = _size + 1 ;
		
	}
	
	/**
	 * Removes the DiskClusterNode currently located at the specified position. 
	 * Make sure that you only remove the node at the specified position 
	 * (do not destroy the list). Assume that the first node in the list is 
	 * position 1. If position is less than or equal to 0 or if position is 
	 * greater than the number of nodes in the list, throw an appropriate 
	 * exception and do not perform any removal.
	 * 
	 * @param position
	 * 
	 * complexity: O(n)
	 * 
	 */
	public void removeCluster(int position) throws IllegalArgumentException, ListEmptyException{
		
		if(position<=0 || position>_size){
			throw new IllegalArgumentException();
		}
		
		if(_size<1){ //empty list 
			throw new ListEmptyException();
		}
		
		if( _size == 1 ){   
			
			_head = null;
			_tail = null; 
			 
		}
		
		else{ //_size >= 2 
			DiskClusterNode nodePtr = _head ;;
			
			if(position==1){ //remove the head
				_head = nodePtr.getNextNode();
			}
			else if(position == _size){ //remove the tail
				
				nodePtr = getNodePtr(_size-1); // get the node just before tail node
				nodePtr.setNextNode(null);
				_tail = nodePtr;
			}
			else {  //remove node in the middle 
				nodePtr = getNodePtr(position-1);
				nodePtr.setNextNode(nodePtr.getNextNode().getNextNode());
				
			}
		
		}
			
		_size = _size - 1 ;
	}
	
	/**
	 * Determines whether or not the given DiskClusterList has a 
	 * DiskClusterNode containing the given cluster number. 
	 * Returns true if such a DiskClusterNode exists or false otherwise. 
	 * 
	 * @param cluster
	 * @return
	 * 
	 * complexity: O(n)
	 */
	public boolean containsCluster(int cluster) {
		boolean found = false ; 
		
		DiskClusterNode nodePtr = _head ;
		int index = 0 ;
		
		while(index<_size){
			if(nodePtr.getClusterNumber()==cluster){
				found = true; 
				return found;
			}
			nodePtr = nodePtr.getNextNode();
			index++ ;
		}
		
		return found ; 
	}
	
	/**
	 * Displays a list of all cluster numbers and data (in the appropriate 
	 * order) that appear in this DiskClusterList. For each cluster, 
	 * the cluster number and data should be printed on the same line 
	 * and be separated by a space. Each cluster should be printed on its own 
	 * line. (see SAMPLE OUTPUT below).
	 * 
	 * complexity: O(n)
	 */
	public void printAllClusters() {
		DiskClusterNode nodePtr = _head;
		
		for(int i=0; i<_size ; i++){
			System.out.print(nodePtr.getClusterNumber() + " ");
			System.out.print(nodePtr.getData());
			System.out.println();
			nodePtr = nodePtr.getNextNode();
		}
	
	}
	
	/**
	 * Use the removeCluster method to remove all clusters that appear 
	 * in this DiskClusterList.
	 * 
	 * AE: First, you have to go through the linked list and free all clusters, then you can set the head to null. 
	 * 
	 * @throws IllegalArgumentException 
	 * @throws ListEmptyException 
	 * 
	 * complexity: O(n)
	 */
	public void removeAllClusters() throws IllegalArgumentException, ListEmptyException {

		while(_size>0){
			removeCluster(_size);
		}
	
	}
	
	/**
	 * 
	 * @param position 
	 * @return a nodePtr at the specified position 
	 * 
	 * complexity: O(n)
	 */
	private DiskClusterNode getNodePtr(int position){
		DiskClusterNode nodePtr = _head; 
		int index = 0;
		
		while(index<position-1){
			nodePtr = nodePtr.getNextNode();
			index++;
		}
		
		return nodePtr;
	}
	
	/*
	 * Additional methods for practice, not in hw2 description
	 */
	
	/**
	 * needs three pointers, and move sequencially 
	 */
	public void reverse () throws ListEmptyException{
		if(_head==null)
			throw new ListEmptyException();
		
		_tail = _head;
		
		DiskClusterNode nodePtr = _head ;
		DiskClusterNode prev    =  null ;
		DiskClusterNode next = null; 
		while(nodePtr!=null){
			next = nodePtr.getNextNode();
			nodePtr.setNextNode(prev);
			prev = nodePtr; 
			nodePtr = next;
		}
		
		_head = prev; //not nodePtr

	}
	
	public int getSize(){
		return _size ; 
	}
	
}
