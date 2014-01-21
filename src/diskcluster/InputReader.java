package diskcluster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * using the singleton design pattern for this class.
 * @author Ying
 *
 */
public class InputReader {

	private InputStreamReader inStream  ;
	private BufferedReader stdin ; 
	
	private static InputReader ir = null ;
	
	private InputReader(){
		inStream = new InputStreamReader(System.in) ; 
		stdin = new BufferedReader(inStream); 
	}
	
	/**
	 * return a singleton(only one) object of this class
	 * @return
	 */
	public static InputReader getInputReader(){
		
		if(ir==null){
			ir = new InputReader();
		}
		
		return ir ;
	}
	
	public String getInput(){
		
		String data = null;
		
		try {
			data = stdin.readLine();//block here 
		} catch (IOException e1) {

			e1.printStackTrace();
		} 
		return data; 
	}
	
	
	/**
	 * print out the promote message first and g
	 * @param msg
	 * @return the input user String
	 */
	public String getInput(String msg){
		if(msg.length()!=0){
			System.out.println(msg);
		}
		
		String data = null;
		
		try {
			data = stdin.readLine();//block here 
		} catch (IOException e1) {

			e1.printStackTrace();
		} 
		return data;
		
	}
	
}
