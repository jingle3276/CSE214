package friendlist;

public class FriendList {
	private static final int MAX_LIST_SIZE = 100; 
	private static final int MAX_NAME_SIZE = 30;
	
	private String [] _dataArray ; 
	private int _size ; 
	
	public FriendList(){ 
		_dataArray = new String[MAX_LIST_SIZE];
		_size = 0 ;
	}
	
	public Object clone() {
		
		FriendList newList = new FriendList();		
		
		for(int i=0; i<_size; i++){

			try {
				newList.addFriend(_dataArray[i]);
			} catch (ListFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		return newList;
	} 
		
	/**
	 * 
	 * @param name name of a Friend to be added to this FriendList Object
	 * @return
	 * @throws DuplicateNameException 
	 * @throws ListFullException 
	 * @throws IllegalArgumentException 
	 */
 
	public void addFriend(String name) throws ListFullException, DuplicateNameException, IllegalArgumentException {
		if(name.length()==0 || name.length()>MAX_NAME_SIZE){
			throw new IllegalArgumentException() ;
		}
		
		for(int i=0;  i<_size ; i++){
			if(_dataArray[i].equalsIgnoreCase(name)){
				throw new DuplicateNameException(name);
				//System.out.println("DuplicateNameException");
				//return ; 
			}
		}
		
		if(_size==MAX_LIST_SIZE){
			throw new ListFullException();
			//System.out.println("ListFullException");
			//return ;
		}
		
		_dataArray[_size] = name; 
		_size = _size + 1; 
	}
	
	/**
	 * O(1) removeal if don't need to maintain the order, just swap the 
	 * last item with the target. 
	 * 
	 * O(n) removal if the order to be maintained. 
	 * 
	 * @param name
	 * @return
	 * @throws IllegalArgumentException 
	 */
	public boolean removeFriend(String name) throws IllegalArgumentException{
		boolean found = false ; 
		
		if(name.length()==0 || name.length()>MAX_NAME_SIZE){
			throw new IllegalArgumentException() ;
		}
		
		for(int i=0; i<_size && !found; i++){
			
			if(_dataArray[i].equalsIgnoreCase(name)){
				found = true;
				//just swap the target with the last item in the array
				_dataArray[i] = _dataArray[_size-1];
				_size = _size - 1 ;
			}
		}
		
		return found ;
	}
	
	public int numFriends(){
		return _size ;
	}
	
	/**
	 * @param name the name of a Friend that may or may not be in this FriendList
	 * @return 
	 * @throws IllegalArgumentException 
	 */
	public boolean contains(String name) throws IllegalArgumentException{
		boolean found = false ; 
		
		if(name.length()==0 || name.length()>MAX_NAME_SIZE){
			throw new IllegalArgumentException() ;
		}
		
		for(int i=0; i<_size && !found; i++){
			if(_dataArray[i].equalsIgnoreCase(name)){
				found = true; 
			}
		}
		
		return found ;
	}
	
	public String toString(){
		String list = new String(); 
		for(int i=0; i<_size; i++){
			if(i!=0){
				list += ",";
			}
			list += _dataArray[i].toString(); 
		}
		return list; 
	}

	
}//end class 
