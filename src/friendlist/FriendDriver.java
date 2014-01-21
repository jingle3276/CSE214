package friendlist;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FriendDriver {
	
	private FriendList fl = null ; 
	//private char inputChar ;

	private InputStreamReader inStream  ;
	private BufferedReader stdin ; 
	

	public FriendDriver () {
		fl = new FriendList();
		inStream = new InputStreamReader(System.in) ; 
		stdin = new BufferedReader(inStream); 
	} 
	
	public void printMsg(){
		System.out.println("A) Add a Friend to the list");
		System.out.println("F) Find a Friend to the list");
		System.out.println("L) Dispaly all Friends in the list");
		System.out.println("R) Remove a Friend from the list");
		System.out.println("Q) Quit the program");
	}
	
	public String getUserInput( ){
		
		String data = null;
		
		try {
			data = stdin.readLine();//block here 
		} catch (IOException e1) {

			e1.printStackTrace();
		} 
		return data; 
	}
	
	public void doChoice (char c)  {
		
		switch (c) {
		
		case 'A': // add a friend
			System.out.print("Enter a name:");
			String name = getUserInput() ; 
			
			try {
				fl.addFriend(name);
				System.out.println("Name added successfuly.");
			} catch (ListFullException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			} catch (DuplicateNameException e) {
				// TODO Auto-generated catch block
				System.out.println("  Error: " + name + " already in the list.");
				//e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break ;
			
		case 'R': //Remove a friends
			System.out.print("Enter a name to remove: ");
			
			try {
				String nameToRemove = getUserInput(); 
				
				if( fl.removeFriend(nameToRemove)){
					System.out.println("Name removed successfuly.");
				} 
				else {
					System.out.println("  Error: " + nameToRemove + " not in the list.");
				}
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case 'F': //Find a friend
			System.out.print("Enter name: ");
			String nameToFind = getUserInput();
			boolean found = false ; 
			
			try {
				found = fl.contains(nameToFind);
				
				if(found){
					System.out.println(nameToFind+" is in the list.");
				}
				else{
					System.out.println(nameToFind+ " is not in the list.");
				}
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case 'L': // Display the list
			System.out.println(fl.toString());
			break;
			
		case 'Q':
			System.out.println("Quit programm.");
			System.exit(0);
			
			break;
		
		case '\n':
			System.out.println();
			break;
			
		default : 
			
			
		} 
	} 
	
	public static void main (String args[]) {
		
		FriendDriver fd = new FriendDriver () ;
		char c  ;
		
		fd.printMsg();
		while(true){
			System.out.print("Enter your Choice: ");
		
			String input = fd.getUserInput(); 
			
			if(input.equals("")){ //user input nothing, just a enter 
				continue ;
			}
			
			else {  
				c = input.toUpperCase().charAt(0); 
				fd.doChoice(c);	
				System.out.println();
			}
			
		}//end while 
		
	}//end main 
}
