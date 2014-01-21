package binarytaxonomytree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import diskcluster.InputReader;

/**
 * Write a fully documented class named GuessingGame that will contain the 
 * program's main method. The program should begin by trying to locate a file
 * named "tree.obj" that contains an existing BTTree object. If the file is 
 * found, load it into the program using the instructions given above and 
 * begin execution of the game. Otherwise, prompt the user for an initial 
 * question, one guess each for yes and no, and construct a new BTTree based 
 * upon this information. Run the game using the query method of the BTTree 
 * class and ask the user if he or she wishes to play again. Repeat the game 
 * as long as the user wishes and, as soon as he or she wishes to stop, save 
 * the BTTree to the file "tree.obj" using the instructions given above.
 * 
 * @author Ying
 *
 */

public class GuessingGame {

	
	public static void main(String[] args){
		
		
		FileInputStream inputFile = null;
		BTTree inputTree = null;
		BTTree btt = null;
		
		try {
			inputFile = new FileInputStream("tree.obj");
			ObjectInputStream inStream = new ObjectInputStream(inputFile);
			inputTree = (BTTree) inStream.readObject();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(inputTree!=null){
			btt = inputTree;
		}
		else{
			btt = new BTTree("Is it an animal?","Cat","Cars");
		}
		
		
		boolean done = false;
		String input ;
		InputReader ir = InputReader.getInputReader();
		

		while(!done){
			btt.query();
			
			input = ir.getInput("Play the game again ?").toLowerCase();
			
			if(input.charAt(0)=='y')
				continue; //go back to loop 
			else
				break; //terminate the loop
		}
		
		//save the state of the BTTree object
		try {
			FileOutputStream file = new FileOutputStream("tree.obj");
			ObjectOutputStream outStream = new ObjectOutputStream(file);
			outStream.writeObject(btt); 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
