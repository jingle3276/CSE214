package binarytree;

import stack_evaluation.Stack;

public class BTNode {
	private int data ; 
	private BTNode left;
	private BTNode right;
	
	public BTNode(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	public BTNode(int data, BTNode left, BTNode right){
		this.data = data; 
		this.left = left; 
		this.right = right; 
	}
	
	
	public void setData(int newData){
		data = newData;
	}
	
	public int getData(){
		return data;
	}
	
	public BTNode getLeft(){
		return left ; 
	}
	
	public void setLeft(BTNode newLeft){
		left = newLeft ;
	}
	
	public BTNode getRight(){
		return right; 
	}
	
	public void setRight(BTNode newRight){
		right = newRight ; 
	}
	
	public boolean isLeaf (){
		return ( (left==null) && (right==null) );
	}
	
	/**
	 * recursive method 
	 * (very similar to binary search)
	 * 
	 * @param item
	 * @return
	 */
	
	public boolean contain(int item){
		
		if(item==this.data)
			return true; 
		
		if(item>this.data && right!=null)
			return right.contain(item);
		else if(item<this.data && left!=null)
			return left.contain(item);
		else 
			return false;
	}
	
	/**
	 * if the node has no right child, it return itself
	 * otherwise, recursively call itself to go down until reach a node 
	 * without a right child. 
	 * 
	 * @return the rightmost node from this node.
	 */
	
	public BTNode getRightMost(){
		if(right==null)
			return this;
		
		else 
			return right.getRightMost();
	}
	
	/**
	 * if the node has no right child, it return itself
	 * otherwise, recursively call itself to go down until reach a node 
	 * without a right child. 
	 * 
	 * @return the rightmost node from this node.
	 */
	
	public BTNode getLeftMost(){
		if(left==null)
			return this;
		
		else 
			return left.getLeftMost();
	}
	
	/**
	 * if the node has no left child, we simply return a reference to the 
	 * rest of the tree, which is on the right side. 
	 * 
	 * if the node has a left, recursively call removeLeftMost() unitl reaches
	 * the end node. then, the parent of the end-node, set its left to the end-node
	 * 's right(cound be null if the end-node has no right child). Finally, the 
	 * parent returns itself to the upper level. 
	 * 
	 *  e.g.            original node.    6        removed        6     
	 *  								/   \ 					 / \ 
	 *  							   3	 8 					5   8 
	 * 								  	\   / \ 				   / \ 	j
	 *		 						  m	 5 7   10                 7  10 
	 * 
	 * @return 
	 */
	public BTNode removeLeftMost(){
		if(left==null)    
			return right ;    
		
		else{
			left =  left.removeLeftMost();
			return this;
		}	
	}
	/**
	 * idea refer to removeLeftMost() 
	 * 
	 * 		            original node.    6        removed        6     
	 *  								/   \ 					 / \ 
	 *  							   3	 8 					5   7 
	 * 								   	\   /  				     \  	
	 *		 						  	 5 7                      5   
	 * 
	 * @return
	 */
	
	public BTNode removeRightMost(){
		if(right==null)
			return left; 
		
		else{
			right = right.removeRightMost();
			return this;
		}
	}
	
	
	/**
	 * apply only to BST. 
	 * get the second largest node in the BST. 
	 * 
	 * get the largest node in the BST first. 
	 * case1: if the largest has no child, then its parent is the second largest.
	 * (if the largest is the root, then )
	 * case2: if the largest has left subTree, then return the left subTree's 
	 * rightMost node.  
	 * 
	 * @throws Exception 
	 * 
	 * 
	 */
	public BTNode get2ndLargest() throws Exception{
		
		if(this.isLeaf()){
			throw new Exception("No second Largest.");
		}
		
		if(right==null && left!=null) //root with a left child only
			return left.getRightMost();
		
		else{ 
			BTNode cursor = this ;
			BTNode parentOfCursor = null ; 
			while(cursor.right!=null){
				parentOfCursor = cursor; 
				cursor = cursor.getRight();
			}
			
			if(cursor.isLeaf()){
				return parentOfCursor;
				
			}	
			else{ //cursor has left
				return cursor.left.getRightMost();	
			}
		
		}
		
	}
	
	
	/**
	 * copy a binary tree. (must be static) 
	 * @param src -a reference to the root node o binary tree that will be copied
	 * (which may be an empty tree where source is null) 
	 * @return the method has made a copy of the binary tree starting at the
	 * source. The return value is a reference to the root of the copy. 
	 * 
	 * 			6            
	 * 		   / \ 	 
	 * 		  3   9    construct new node order: 3->7->9->6 (postorder).    				
	 * 		     /
	 * 			7 
	 * Must construct a tree in post-order: left-sub--tree, right-sub-tree, root
	 *  	
	 */
	                                       
	public static BTNode treeCopy(BTNode src){ 
		BTNode leftCopy, rightCopy; 
		
		if(src==null){
			return null ;
		}
		
		leftCopy = treeCopy(src.getLeft());
		rightCopy = treeCopy(src.getRight());
		return new BTNode(src.getData(), leftCopy, rightCopy);	
	}
	
	public static int treeSize(BTNode src){
		
		if(src==null)
			return 0 ;
		
		return treeSize(src.getLeft()) + treeSize(src.getRight()) + 1 ;
		
	}
	
	
	/**
	 * inorder traveral of the tree
	 * printout the root when visiting it
	 * 
	 */
	
	public void printInorder(){
		
		if(left!=null)	
			getLeft().printInorder();
		
		System.out.println(data);
		
		if(right!=null)
			getRight().printInorder();
	}
	
	/**
	 * non-recursive traversal 
	 * 
	 * @param args
	 * @throws Exception 
	 */
	
	public void preorderIter( ) throws Exception{
		Stack<BTNode> stack = new Stack<BTNode>();
		
		stack.push(this); 
		
		while(!stack.isEmpty()){
			BTNode node = stack.pop();
			if(node!=null)
				System.out.println(node.getData());
			
			if(node.getRight()!=null)
				stack.push(node.getRight());
			
			if(node.getLeft()!=null)
				stack.push(node.getLeft());
			
		}
		
	}
	
	/**
	 *   8                0 
	 *  / \              / \
	 * 10  1     ->     1   1 
	 *  \   \           \    \
	 *   3   9           2    2 
	 *  /               / 
	 * 2               3   
	 * 
	 * Idea: using recursion, pass a parameter in the method
	 * 
	 * @param root
	 */
	public void setDepth(int depth){
		data = depth ; 
		if(left!=null)
			left.setDepth(depth+1);
		
		if(right!=null)
			right.setDepth(depth+1);
		
	}
	
	
	/**
	 * side-effect: change the every node in the tree
	 * CSE214 sample midterm2 , the last question. 
	 */
	public void childCount (){
		
		int leftSum = 0, rightSum=0;
		
		if(left!=null){
			left.childCount();
			leftSum = left.getData()+1; 
		}
			
		if(right!=null){
			right.childCount();
			rightSum = right.getData()+1;
		}
		
		data = leftSum + rightSum ;
		
	}
	
	public int childCount2(){
		if(this.isLeaf()){
			data = 0;
			return data;
		}
		
		int leftCount = 0, rightCount=0;
		if(left!=null)
			leftCount = left.childCount2()+1;
		if(right!=null)
			rightCount = right.childCount2()+1;
		
		data = leftCount+rightCount;
		return data;
	}
	
	/**
	 * childcount3: no side-effect. the data will never change after run
	 * 
	 */
	public int childCount3(){
		if(this.isLeaf())
			return 0 ; 
		
		int leftSum =0 ,rightSum =0; 
		
		if(left!=null)
			leftSum = left.childCount3()+1; 
		if(right!=null)
			rightSum = right.childCount3()+1;
		
		return leftSum + rightSum ; 
		
	}
	
	/**
	 * for practice, remove recursive 
	 * @param args
	 */
	
	public BTNode removeBST(int item){
		if(data==item && this.isLeaf())
			return null;
		else if(data==item && right==null){
			return left;
		}
		else if(data==item && left==null){
			return right;
		}
		else if(data==item){
			data = left.getRightMost().getData();
			left.removeRightMost();
			return this;
		}
		
		else if(item>data && right!=null){
			right = right.removeBST(item);
			return this;
		}
		
		else if(item<data && left!=null){
			left = left.removeBST(item);
			return this;
		}
		else{
			return this;
		}
		
	}
	


	public static void main(String args[]){
		BTNode root = new BTNode(83);
		root.setLeft(new BTNode(65));
		root.setRight(new BTNode(98));
		root.getRight().setLeft( new BTNode(90));
		root.getRight().setRight( new BTNode(100));
		
		//root.setDepth(0);
		System.out.println(root.childCount3());
		
		//System.out.println(root.contain(98));
		//root.removeBST(98);
		//System.out.println(root.contain(98));
		//System.out.println(root.childCount2());
		try {
			//root.preorderIter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
