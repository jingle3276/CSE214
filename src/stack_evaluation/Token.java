package stack_evaluation;

/**
 * Write a fully-documented class named Token that will store information about 
 * a given token in the input String (either an operator or an integer operand). 
 *
 * This class should have a constructor that accepts a String as a 
 * parameter (the actual corresponding part of the input String) and 
 * initializes its member variables appropriately. The class should have an int 
 * named value, a char named operator, and a boolean variable named isOperator 
 * as its three member variables. If the input String to the constructor 
 * corresponds to an operator, isOperator is true and operator is set to the 
 * appropriate operator (the variable value will be undefined). If the input 
 * String to the constructor is an integer, isOperator is false and value will be 
 * the numerical value of the String (the variable operator will be undefined).
 *
 * This class should also provide accessor methods named isOperator(), getValue(), 
 * and getOperator(). If getValue() or getOperator() are called when the value of 
 * the corresponding variable is undefined, an exeption should be thrown.
 *	
 * @author Ying
 *
 */

public class Token {
	
	private int value ; 
	private char operator ; 
	private boolean isOperator ; 
	
	public Token(String input){ //assume input contains only 1 char
		if(input.equals("+") || input.equals("-") || input.equals("*") 
			|| input.equals("/") || input.equals("^") ) 
		{
			isOperator = true;
			operator = input.charAt(0);
		}
		
		else {
			isOperator = false;
			value = Integer.parseInt(input);
		}
	}
	
	public boolean isOperator (){
		return isOperator ; 
	}
	
	public int getValue(){
		return value ;
	}
	public char getOperator(){
		return operator ;
	}
	
	public String toString(){
		if(isOperator)
			return Character.toString(operator);
		else 
			return Integer.toString(value);
	}
	
}
