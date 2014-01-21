package binarytaxonomytree;

import java.io.Serializable;

/**
 * 
 * 2. Write a fully documented class named BTTree that will represent the 
 * binary taxonomy tree. It should contain a reference to a BTTNode 
 * representing the root of the tree and a constructor that sets up a tree 
 * with a single question and the resulting two questions (one for yes, one 
 * for no): public BTTree(String question, String yesGuess, String noGuess)
 * 
 * The initial tree should contain a root with two leaf children, where the 
 * root contains the question, the left child contains the guess for an answer
 * of yes, and the right child contains the guess for an answer of no.
 * 
 * The BTTree class should also contain another method called query() that 
 * will begin the exeuction process of the game by calling query on the root
 * of the tree. NOTE: Keep in mind that this class will have to implement the
 * Serializable interface
 * 
 * @author Ying
 *
 */


public class BTTree implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3838699175591701693L;
	private BTTNode root ; 
	
	public BTTree(String question, String yes, String no ){
		
		root = new BTTNode(question);
		root.setLeft(new BTTNode(yes));
		root.setRight(new BTTNode(no));
		
	}
	
	public void query(){
		root.query();
	}
	
}
