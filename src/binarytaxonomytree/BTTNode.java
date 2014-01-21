package binarytaxonomytree;

import java.io.Serializable;

import diskcluster.InputReader;


// implement the Serializable
public class BTTNode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5100893815966689013L;
	private String data ; 
	private BTTNode left ; 
	private BTTNode right;
	
	/**
	 * @param initData
	 */
	public BTTNode(String initData){
		data = initData; 
		left = null;
		right = null;
	}
	
	/**
	 * @return the data stored in the node
	 */
	public String getData(){
		return data;
	}
	
	/**
	 * set the new data of the current node
	 * @param newData
	 */
/*	public void setData(String newData){
		data = newData;
	}*/
	
	/**
	 * @return left child of current node
	 */
	public BTTNode getLeft(){
		return left;
	}
	
	/** 
	 * @return right child of current node
	 */
	public BTTNode getRight(){
		return right;
	}
	
	/**
	 * set the left child of current node
	 * @param node
	 */
	public void setLeft(BTTNode node){
		left = node ;
	}
	
	/**
	 * set the right child current node
	 * @param node
	 */
	public void setRight(BTTNode node){
		right = node ; 
	}
	
	/**
	 * @return true if the current node is a leaf (no children).
	 */
	public boolean isLeaf(){
		return ( left==null && right==null );
	}
	
	/**
	 * execute the game 
	 * The query method will handle all of the user interaction with the 
	 * program during the course of a game. It should perform different 
	 * actions depending on whether or not the current node is a leaf node.
	 * If the node is not a leaf node, you should print the node's question 
	 * (message) and prompt the user for a yes or no response. If the user 
	 * enters yes, call the query method on the left child. Otherwise, call 
	 * the query method on the right child. This recursive method allows 
	 * execution to follow a logical path along the tree. 
	 * If the node is a leaf node, you should prompt the user with the 
	 * question "Are you thinking of a(n) " followed by the item that is 
	 * stored in message. If the answer is yes, simply reply that the computer 
	 * has won the game. Otherwise, you must update the tree so that the 
	 * computer can now distinguish between its guess and what you were 
	 * thinking of. Prompt the user to input a question that will have a 
	 * different answer for the object you were thinking of than for the guess 
	 * that the computer had. Next, ask the user if, when the question is 
	 * asked, the item that the user was thinking of would be replied to 
	 * with a yes or a no.

	 */
	public void query(){

		InputReader ir = InputReader.getInputReader() ;
		
		if(!isLeaf()){
			//you should print the node's question (message) and prompt 
			//the user for a yes or no response. If the user enters yes, 
			//call the query method on the left child. Otherwise, call 
			//the query method on the right child. This recursive method 
			//allows execution to follow a logical path along the tree. 
			
			String ans = ir.getInput(data); //show the question ? 
			
			if(ans.equalsIgnoreCase("y")){
				left.query();	
			}
			
			if(ans.equalsIgnoreCase("n")){
				right.query();
			}
			
		}
		
		else{ //isLeaf
			
			//prompt the user with the question "Are you thinking of a(n) "
			//followed by the item that is stored in message
			
			//get user input
			//If the answer is yes, simply reply that the computer has won 
			//InputReader ir = new InputReader() ; 
			String ans = ir.getInput("Are you thinking of a(n)" + data );
			
			if(ans.equalsIgnoreCase("y")){
				System.out.println("The computer has won the game!");
			}
			
			//else
			//you must update the tree so that the computer can now 
			//distinguish between its guess and what you were thinking of.
			
			//Prompt the user to input a question that will have a different 
			//answer for the object you were thinking of than for the guess 
			//that the computer had. Next, ask the user if, when the question
			//is asked, the item that the user was thinking of would be 
			//replied to with a yes or a no.

			else{ //user won the game, now learn from user 
				BTTNode newNode; // will hold the new object
				BTTNode currentNode; // will replace the current node
				ans = ir.getInput("You win!  What were you thinking of?");
				newNode = new BTTNode(ans);
				
				ans = ir.getInput("Please enter a question to distinguish " +
						"a(n) " +data+ " from a(n) " + newNode.getData());
				currentNode = new BTTNode(data); 
				data = ans;
				
				ans = ir.getInput("If you were thinking of a(n) " 
						+ newNode.getData()+ 
						" what would the answer to that question be?");
				
				if(ans.equals("y")){ 
					setLeft(newNode);
					setRight(currentNode);
				}
				else{
					setLeft(currentNode);
					setRight(newNode);
				} 
					 
			}
			
		}
		
	}
	
}
