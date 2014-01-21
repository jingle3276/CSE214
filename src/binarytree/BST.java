package binarytree;

import linkedlist.IntLinkedList;
import linkedlist.LNode;

public class BST {
	private BTNode root ; 
	
	
	/**
	 * Construct a empty BST. 
	 */
	public BST(){
		root = null;
	}
	
	
	public BTNode getRoot(){
		return root;
	}
	
	public boolean isEmpty(){
		return (root==null);
	}

	
	/**
	 * Consider two cases. Inserting into a empty tree, then just make root = 
	 * the new node. 
	 * 
	 * non-empty case: first situation, when cursor has no left-child, and the 
	 * item is less than cursor. then just make the newNode to the left child of 
	 * cursor. Second situation, when the cursor has left-child, cannot directly
	 * insert, need to move cursor to curosr.getLeft. and check the cursor again.
	 * 
	 * Complexity: O(log(n))
	 * 
	 * @param item
	 * @throws Exception 
	 */
	
	public void insert (int item) throws Exception{
		
		BTNode newNode = new BTNode(item);
		
		if(root==null){ //empty case
			root = newNode ;
			return ;
		}	
		
		//not-empty case
		BTNode cursor = root ; 
		boolean done = false;
		
		while(!done){
			if(item < cursor.getData()){
				
				if(cursor.getLeft()==null){  //the left of the cursor is null
											 //just insert to the left. 
					cursor.setLeft(newNode);
					done = true;
				}
				else						// the left is not null, need to 
					cursor = cursor.getLeft(); // move the cursor one level down.
					
			}
			
			else if(item > cursor.getData()){
				
				if(cursor.getRight()==null){
					cursor.setRight(newNode);
					done = true;
				}
				else
					cursor = cursor.getRight();
				
			}
			else {  //item = cursor.getData, inserting an duplicate
				throw new Exception("Inserting a duplicate itme into the BST.");
			}
		
		}//end while 
	}
	
	/**
	 * Recursively inserting an item into the BST.
	 * @param item
	 * @throws Exception 
	 */
	
	public void insertRecursive(int item){
		if(root==null) //empty case
			root = new BTNode(item);
		else  //non-empty cases
			insertRecursive(root, item);
	}
	
	/**
	 * helper method for inserRecursive method
	 * 
	 * @param cursor
	 * @param item
	 */
	
	private void insertRecursive(BTNode cursor, int item){
		
		if(cursor.getLeft()==null && item < cursor.getData())
			cursor.setLeft(new BTNode(item));
		else if(cursor.getRight()==null && item > cursor.getData()) 
			cursor.setRight(new BTNode(item));    //base case
		
		else if(item < cursor.getData())          //recursive case
			insertRecursive(cursor.getLeft(), item);
		
		else 
			insertRecursive(cursor.getRight(),item);
	}
	
	/**
	 * recursive remove the item. 
	 * 
	 * @param item
	 * @return true if the successfully removed the target. 
	 * @throws Exception
	 */
	
	public boolean removeRecursive(int item) throws Exception{
		if(root==null)
			throw new Exception("The tree is empty.");
		
		if(contain(root, item)){ 
			root = removeRecursive(root, item);//root should be assigned back
										      //(hint from Prof. Esmaili)
			return true; 
		}
		else 
			return false;
	}
	/**
	 * private helper method for removeRecursive method.
	 * @param cursor
	 * @param item
	 * @return
	 */
	
	private BTNode removeRecursive(BTNode cursor, int item){
		//base case, cursor is pointing to the target or a leaf
		if(item != cursor.getData() && cursor.isLeaf())
			return cursor ;   //case1, not found the item in the BST
		else if(item == cursor.getData() && cursor.getLeft()==null)
			return cursor.getRight();  
		else if(item == cursor.getData() && cursor.getRight()==null)
			return cursor.getLeft();
		else if(item == cursor.getData() && cursor.getRight()!=null 
				 						 &&	cursor.getLeft()!=null	){
			//found target, but it has two children. 
			cursor.setData(cursor.getLeft().getRightMost().getData());
			cursor.setLeft(cursor.getLeft().removeRightMost() );
			return cursor; 
		}
		
		//recursive case
		else if(item < cursor.getData() ){
			cursor.setLeft( removeRecursive(cursor.getLeft(),item) );
			return cursor; 
		}
		else{ 
			cursor.setRight( removeRecursive(cursor.getRight(),item) );
			return cursor;
		}
	}
	
	
	
	/**
	 * non-recursive implementation of remove. 
	 * 
	 * cases: 
	 * 
	 * 1. item is not in the tree, or the tree is empty, return false. 
	 * 
	 * 2. cursor(item) is root and root has no left child.      
	 * 
	 *  														 45 <- root
	 * 														      \ 	
	 *                                                            83 <-- new root 
	 *   														 /	\  	
	 * 															82	96 	
	 * 3. cursor is non-root and has no left child. 
	 * 															 45   
	 * 													         / \ 	
	 *                                                      -> 31   83  
	 *   														 \  	   	
	 * 															 37	   
	 * 4.curosr is root or non-root, but has two children.            
	 * 																/		 
	 * 															   45 <- cursor
	 * 															  /	\ 	
	 * 														     31  83		
	 *                                                            \
	 *                                                             41
	 *                                                             /
	 *                                                            39
	 * need to promote either the rightMost of the left subtree or the leftMost
	 * of the right subTree. 41 or 83 it this case. note: 41 has a left child,
	 * but it is fine. 
	 *                                                                                                                      
	 */
	public boolean remove (int item){
		
		BTNode cursor = root ; 
		BTNode parentOfCursor = null;  //need this for removal operation.
		
		while(cursor!=null && cursor.getData()!=item){ //setting cursor to target 
			parentOfCursor = cursor; 
			
			if(item > cursor.getData())
				cursor = cursor.getRight(); 
			else 
				cursor = cursor.getLeft();
		}
		
		if (cursor==null || cursor.getData()!=item ) //empty tree or not found 
			return false; 
		
		//now, cursor should pointing to the target node. 
		if(cursor==root && cursor.getLeft()==null)   //case 2 
			root = cursor.getRight();
		
		else if(cursor!=root && cursor.getLeft()==null){ //case 3
			if(cursor==parentOfCursor.getLeft())
				parentOfCursor.setLeft(cursor.getRight());
			else 
			 	parentOfCursor.setRight(cursor.getRight());
		}
			
		else{  //case 4, cursor is a root or non-root, with two children.  
			//update the crusor's value to the rightMost of the left subTree
			cursor.setData(cursor.getLeft().getRightMost().getData());
			cursor.setLeft(cursor.getLeft().removeRightMost());								
		}
		
		return true;
	
	}
	

	/**
	 * static method that test if a BST contains an item. 
	 * @param src the root of a tree
	 * @item target
	 * @return true if the item is found in the tree. 
	 * 
	 */
	
	public static boolean contain(BTNode src, int item){
		
		if(src.getData()==item)
			return true; 
		
		else if(item > src.getData() && src.getRight()!=null) 
			return contain(src.getRight(),item); 
		else if(item < src.getData() && src.getLeft()!=null) 
			return contain(src.getLeft(),item);
		else //reach a leaf or (a node with on child )but the data != the item to be found 
			return false;
	}
	
	
	public BTNode getLargest(){
		if(root==null)
			return null;
		else 
			return root.getRightMost();		
	}
	
	/**
	 * use a linked list to store all the node got from the inorder traversal 
	 * of the tree. Then the list is sorted, just get the kth item of the list.
	 * 
	 * @return kth largest node in the BST.
	 */
	
	public int getKthLargest(int k){
		
		IntLinkedList list = new IntLinkedList();
		getKthLargest(root, list);
		
		int i = 0;
		LNode<Integer> nodePtr = list.getHead();
		
		while(i<k-1){
			nodePtr = nodePtr.getNextNode();
			i++;
		}
		
		return nodePtr.getNode();
		
	}
	
	private void getKthLargest(BTNode node, IntLinkedList l){ 
		
		if(node.getLeft()!=null)
			getKthLargest(node.getLeft(),l);
		//if(node.isLeaf()){
		l.addToHead(node.getData());
		//}
		if(node.getRight()!=null){
			getKthLargest(node.getRight(),l);
		}
		
	}
	
	
	public static void main(String args[]){
		
		BST bst = new BST();
	 
		/*try {
			bst.insert(45);
			bst.insert(21);
			bst.insert(83);
			bst.insert(39);
			bst.insert(62);
			bst.insert(11);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
		bst.insertRecursive(45);
		bst.insertRecursive(20);
		bst.insertRecursive(61);
		bst.insertRecursive(11);
		bst.insertRecursive(35);
		//bst.insertRecursive(54);
		//bst.insertRecursive(59);
		
		/*bst.insertRecursive(6);
		bst.insertRecursive(15);
		bst.insertRecursive(27);
		*/
		//bst.insertRecursive(5);
		//bst.insertRecursive(12);
		
		try {
			//bst.removeRecursive(45);
			
			//System.out.println(bst.getRoot().get2ndLargest().getData());
			System.out.println(bst.getKthLargest(2));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//bst.remove(45);
		//System.out.println(contain(bst.getRoot(),62));
		
	}
	
}
