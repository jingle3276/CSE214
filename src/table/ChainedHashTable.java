package table;

 

public class ChainedHashTable<E> {

	private ChainedHashNode<E>[] table ;
	private int manyItems;
	

	public ChainedHashTable(int size){
		table =  new ChainedHashNode[size];
		manyItems = 0 ;
	}
	
	/**
	 * 
	 * @param key
	 * @param element
	 * @return
	 */
	public E put(Object key, E element){
		
		ChainedHashNode<E> cursor ;
		E answer = null;
		
		int index = hash(key);		
		cursor = table[index];
	
		while(cursor!=null){
			
			if(cursor.getKey().equals(key))
				break;
			
			cursor = cursor.getNext();
		}
		
		if(cursor==null){ //insert new to head
			ChainedHashNode<E> newNode = new ChainedHashNode<E>(key, element);
			newNode.setNext(table[index]);
			table[index] = newNode; 
			manyItems++;
		}
		
		else{ //replace
			answer = cursor.getData() ; 	
			cursor.setData(element);
  		}
	
		return answer;
	
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public E get (Object key){
		
		//E answer = null; 
		int index = hash(key);
		ChainedHashNode<E> cursor = table[index]; 
		
		while(cursor!=null){
			if(cursor.getKey().equals(key))
				return cursor.getData(); 
			cursor = cursor.getNext();
		}
		
		return null;
		
	}
	
	public E remove(Object key){
		
		E answer = null ; 
		int index = hash(key);
		
		ChainedHashNode<E> cursor = table[index];
		
		if(cursor==null){
			return null;
		}
		
		else if(cursor.getKey().equals(key)){ //key found in the head , remove head
			answer = cursor.getData();
			table[index] = cursor.getNext();
			manyItems--;
			return answer;
		}
		
		else {//key is not in the head, go through the list.
			ChainedHashNode<E> parentOfCursor = null ; 
			while(cursor!=null){
				if(cursor.getKey().equals(key)){ //found key, do the remove
					answer = cursor.getData();		
					parentOfCursor.setNext(cursor.getNext());
					manyItems--;
					return answer;
				}
				parentOfCursor = cursor;
				cursor = cursor.getNext();
			}
			//not found the key
			return null;
		
		}
		
	}

	public int size(){
		return manyItems;
	}
	
	private int hash(Object key){
		return Math.abs(key.hashCode()) % table.length ;	
	}
	
	
	public static void main(String args[]){
		
		ChainedHashTable<String> t = new ChainedHashTable<String>(11);
		
		t.put(1, "value1");
		t.put(2, "value2");
		t.put(3, "value3");
		t.put(12, "value12");
		t.put(23, "value23");
		t.remove(12);
		
		System.out.println(t.size());
		System.out.println(t.get(1));

	}
	
	
}
