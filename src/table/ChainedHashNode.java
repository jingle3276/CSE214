package table;

public class ChainedHashNode<E> {

	E element ; 
	Object key ; 
	ChainedHashNode<E> link;
	
	
	public ChainedHashNode(Object key, E element){
		this.key = key ;
		this.element = element;
		
	}
	
	public ChainedHashNode<E> getNext(){
		return link;
	}
	
	public void setNext(ChainedHashNode<E> next){
		link = next;
	}
	
	public Object getKey(){
		return key;
	}
	
	public void setKey(Object key){
		this.key = key;
	}
	
	public E getData(){
		return element;
	}
	public void setData(E data){
		this.element = data;
	}
	
}
