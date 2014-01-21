package stack_evaluation;

import java.util.StringTokenizer;
import diskcluster.InputReader;

/**
 * Write a fully-documented class named Evaluator. This class will contain a main 
 * method that prompts the user for a postfix expression, evaluates the expression,
 * and prints the result. The user is then asked if he/she wishes to enter another 
 * expression, and the process continues until the user wishes to exit the program.
 * 
 * Here is the procedure you should use in order to perform this task:
•	Prompt the user for the expression and use a StringTokenizer to break the 
    String into tokens using whitespace (i.e. spaces and tabs) as delimiters.
•	For each token produced by the StringTokenizer, construct a Token object and 
    insert it into a list named inputList in the order that the Token objects were 
    constructed. Remember, this list can be an ArrayList or LinkedList from the 
    Java API, or you may define your own list class.
•	Using the algorithm discussed in class for evaluating a postfix expression,
	evaluate the contents of inputList and display the results to the user.
	
	INPUT FORMAT:
•	All tokens (operators, numbers) are separated by spaces in the input.
•	Valid operators include addition (+), subtraction (-), multiplication (*), 
	division (/), and exponents (^).
•	You may assume that all expressions entered by the user are legal postfix 
	expressions that are in the previously mentioned form (no error checking is 
	required).
•	You may assume there are no negative numbers, and will be no division by zero.
	
	OUTPUT FORMAT:
•	Prompt the user whenever any information is required.
•	All calculations with division are assumed to be using integer division
	(i.e. 7/4 = 1).
	
	Please enter a postfix expression: 9 3 5 + -
	The value of your expression is 1
	Would you like to enter another expression (y or n)? y
	
	Please enter a postfix expression: 2 2 8 * 16 - ^
	The value of your expression is 1
	Would you like to enter another expression (y or n)? y
	
	Please enter a postfix expression: 32 9 / 8 * 10 +
	The value of your expression is 34
	Would you like to enter another expression (y or n)? y
	
	Please enter a postfix expression: 20 82 3 10 * 16 5 - + / ^
	The value of your expression is 400
	Would you like to enter another expression (y or n)? y
	
	Please enter a postfix expression: 22 9 7 / * 8 2 - +
	The value of your expression is 28
	Would you like to enter another expression (y or n)? n
	
 * 
 * @author Ying
 * Date: Oct-6-2010
 *
 */

public class Evaluator {

	public int evaluate(int a, int b, char op){
		int result = -1 ;
		
		switch(op){
			case'+':
				result = a+b;
				break;
			
			case'-':
				result = a-b;
				break;
			
			case'*':
				result = a*b;
				break;
		
			case'/':
				result = a/b;
				break;
			
			case'^': 
				//result = (a)^(b); //math is wrong 
				result = (int) Math.pow(a,b);

			
			default:
				break;
		}
		
		return result;
		
	}
	
	
	public static void main(String args[]){
		Evaluator e = new Evaluator();
		//InputReader ir = new InputReader();
		InputReader ir = InputReader.getInputReader();
		StringTokenizer st ;
		
		while(true){
			System.out.print("Enter a postfix expression: ");
			st = new StringTokenizer(ir.getInput());
			ListStack<Token> stack = new ListStack<Token>();
			//Stack<Token> stack = new Stack<Token>();
			
		    while (st.hasMoreTokens()) {
		         
		    	Token t = new Token(st.nextToken());
		        
		    	if(!t.isOperator()){ //operand
		    		
		    		try {
						stack.push(t);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    		
		    	}
		    	
		    	else{ //operator 
		    		
		    		try {
		    			int operand2 = stack.pop().getValue();
		    			int operand1 = stack.pop().getValue();
		    		
		    			int temp = e.evaluate( operand1, operand2, t.getOperator());
		    			String tempStr = Integer.toString(temp);
		    			stack.push(new Token(tempStr));
		    		} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
		    }//end while hasNextToken()
		    
		    
		    if(stack.size()==1){
		    	try {
					System.out.println("result: " + stack.pop().getValue());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
		    }//end if 
			
		    System.out.print("another expression? (y or n): ");
		    String choice = ir.getInput();
		
		    if(choice.toLowerCase().charAt(0)=='y'){
		    	continue ;
		    }
		    	
		    else if(choice.toLowerCase().charAt(0)=='n'){  
		    	System.out.println("Exited.");
		    	System.exit(0);
		    }
		}//end while 
		
	}//end main

}



