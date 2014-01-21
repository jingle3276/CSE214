package table;
/**
 * Hash Table Implementation from the textbook. 
 * Using the linear Probing for collation. 
 * The flag boolean array hasBeenUsed is for searching the table. When ever 
 * a position i is used before or now, the corresponding boolean value in the
 * hasBeenUsed array would be true. When search for a key, first start from 
 * the hash(key) position, if no found, go to the nextIndex(i), until the 
 * hasBeenUsed(i) is false or looked at the whole table size.  
 * When inserting, just insert the element to hash(key) position, if it is 
 * being used, then look for the next available position(key==null) in the 
 * keys[] array. increment the manyItems and change the hasBeenUsed[i] to true.
 * 
 * 					Invariant for the table ADT
 * 1. The number of elements in the table is in the instance variable manyItems.
 * 
 * 2. The preferred location for an element with a given key is at index 
 * 	  hash(key). If an collision occurs, then next-Index is used to search 
 *    forward to find the next open address. When an open address is found at an
 *    index i, then the element itself is placed in data[i], and the element's 
 *    key is placed at key[i].
 *    
 * 3. An index i that is not currently used has data[i] and keys[i] set to null.
 * 
 * 4. If an index i has been used at some point(now or in the past), then 
 * 	  hasBeenUsed[i] is true; Otherwise, it is false. 
 * 
 * 
 * @author Ying
 *
 */

public class Table {
	private int manyItems ; 
	private Object[] keys ; 
	private Object[] data ; 
	private boolean[] hasBeenUsed ; 
	//private int capacity ; 
	
	
	/**
	 * Constructor 
	 * @param capacity
	 */
	public Table(int capacity){
		//this.capacity = capacity;
		keys = new Object[capacity];
		data = new Object[capacity];
		hasBeenUsed = new boolean[capacity];
		manyItems = 0; 
		
		//this.capacity = capacity;
	}
	
	
	/**
	 * rehash the table
	 * copy every old item to the new Table
	 *  
	 * @param newCapacity
	 */
	private void reHash(int newCapacity){
		
		Object[] newKeys = new Object[newCapacity];
		Object[] newData = new Object[newCapacity];
		boolean[] newHasBeenUsed = new boolean[newCapacity];
		
		int i = 0 ;
		int newIndex ; 
		while(i<capacity()){
			
			if(keys[i]!=null){	
				newIndex = hash(keys[i], newCapacity);
				newKeys[newIndex] = keys[i];
				newData[newIndex] = data[i];
				newHasBeenUsed[newIndex] = true; 
			}
			
			i++;
		}
		
		keys = newKeys; 
		data = newData;
		hasBeenUsed = newHasBeenUsed;
	}
	
	
	/**
	 * The method puts the new element in the table with the specified key. If 
	 * that key was already present in the table, then the put returns a reference
	 * to the old element; Otherwise put method returns null. 
	 * 
	 * @param key
	 * @param element
	 * @return the old Object if the inserting key is in the talbe. Otherwise,
	 * the null indicating it is a new key.
	 * @throws Exception 
	 */
	public Object put (Object key, Object element) {
		
		//check the load factor if need reHash()
		if(loadFactor()>=0.5)
			reHash(2*capacity());//reHash
		
		int index = findIndex(key);
	
		//index ==-1 just means the key is not present in the table, but 
		// the ideal position hash(key) might be taken by another item
		//(colliation), need to find next available position to do the insert.
		if( index==-1 ){ //the key is not present in 
												  //the table, insert it  
			index = hash(key,capacity());// the ideal position, might be taken
			while(keys[index]!=null){
				index = nextIndex(index); //notice: not index++;
			} //now the index pointing to the right position for inserting.
			
			keys[index] = key;
			data[index] = element;
			hasBeenUsed[index] = true;
			manyItems++;
			return null;
		}
		else { //replace an exiting 
			//element in the table with the same key.
			Object old = data[index];
			data[index] = element;
			return old;
		}
		
	}
	
	/**
	 * remove if the key is present in the table .
	 * 
	 * @param key
	 * @return the value of the removed key.
	 */
	public Object remove (Object key){
		int index = findIndex(key);
		
		if(index!=-1){ //found the key, do the remove
			Object old = data[index];
			data[index] = null;
			keys[index] = null;
			manyItems--;
			//don't change the hasBeenUsed[]
			return old;
		}
		else
			return null;
	}
	/**
	 * get the object of the specified key.
	 * @param key
	 * @return
	 */
	
	public Object get(Object key){
		int index = findIndex(key);
		
		if(index!=-1){//the key present
			return data[index];
		}
		
		else 
			return null;
	}
		
	/**
	 * @return the current size of the table.
	 */
	public int size(){
		return manyItems;
	}
	/**
	 * @return the capacity of the table.
	 */
	public int capacity(){
		return hasBeenUsed.length;
	}
	
	public double loadFactor(){
		return ((double)size()/(double)capacity());
	}
	
	/**
	 * The return value is a valid index of the table's arrays. The index is 
	 * calculated as the remainder when the absolute value of the key's hash 
	 * code is divided by the size of the tables' arrays. 
	 * 
	 * @param key
	 * @return
	 */
	private int hash(Object key, int size){
		return Math.abs( key.hashCode() ) % size;
	}
	
	/**
	 * normally will return i+1. if i reaches the end of the array, then 
	 * wraparound to position 0. 
	 * 
	 * @param i
	 * @return the return value is a valid index of the table's arrays. The index is calculated as 
	 * the remainder when the absolute value of the key's hash code is divided by the table's arrays. 
	 */
	
	private int nextIndex(int i){
		if(i==data.length-1){ //the last in the arrays
			return 0;
		}
		else 
			return i+1 ;
	}
	/**
	 * the value of findIndex(key)searches for the specified key. 
	 * If the key is found, the method returns the index of the key in the keys 
	 * array. 
	 * postcondition: if the specified key s found in the table, then return is 
 	 * the index of the specified key. Otherwise, the return value is -1. 
	 * 
	 * @return
	 */
	private int findIndex(Object key){
		
		int count = 0; 
		int i = hash(key,capacity()); //the ideal position of the input key
		
		while(count<hasBeenUsed.length && hasBeenUsed[i] ){ //look at the 
					//the array of the hasBeenUsed instead of data or keys
			if(key.equals(keys[i])) //found the same key in the table	
				return i ;
			
			else{
				i = nextIndex(i); //increment the index 
				count++; //increment the count.
			}
		}
		
		return -1 ; //not found the key, means the key never inserted before.
		
	}

	public static void main(String args[]){
		
		Table t = new Table(5);
		try {
			Duration d1 = new Duration(10,10);
			Duration d2 = new Duration(12,12);
			Duration d3 = new Duration(13,13);
			Duration d4 = new Duration(14,14);
			
			t.put(d1.hashCode(), d1);
			t.put(d2.hashCode(), d2);
			t.put(d3.hashCode(), d3);
			t.put(d4.hashCode(), d4);
			
			System.out.println(t.capacity());
			Duration d = (Duration) t.get(d4.hashCode());
			System.out.println(d.getMin());
			System.out.println(t.loadFactor());
			
			//t.remove(1);
			//t.put(12, "String 6.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
