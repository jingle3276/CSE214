package heap;


/**
 * heap implementation using array. 
 * formula: give a node in position i , left child: 2i+1, right child
 * 2i+2; 
 * given a node in position, its parent node: (i-1)/2  
 * 
 * @author Ying
 */

public class Heap {

	private int[] data ; 
	private int heapSize; 
	private int maxSize; 
	
	public Heap(int initialSize){	
		data = new int[initialSize];
		heapSize = 0;
		maxSize = initialSize ;
	}
	
	/**
	 * insert the item into the first available position in the array. then 
	 * compare it with its parent, if it is greater, then swap with the parent, 
	 * until it reaches the root or it is smaller than the parent. 
	 * 
	 * @throws Exception
	 */
	
	public void insert(int item) throws Exception{
		if(heapSize==maxSize){
			throw new Exception("The heap is full.");
		}
		data[heapSize++] = item ;
		
		int i = heapSize ;
		while(i>0 && data[i] > data[(i-1)/2]){
			swap(i, (i-1)/2);
			i = (i-1)/2;	
		}
		
	}
	/**
	 * helper method for insert 
	 * 
	 * @param child
	 * @param parent
	 */
	
	private void swap(int i, int j){
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp; 
		
	}
	
	
	/**
	 * always get the value of the root and remove it and fix the heap. 
	 * replace it with a node in the deepest level of the tree, and 
	 * swap it with its children until the heap is fixed. 
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public int remove() throws Exception{
		if(heapSize==0){
			throw new Exception("the heap is empty.");
		}
		
		int temp = data[0];
		data[0] = data[heapSize-1];
		data[heapSize-1] = 0;
		heapSize--;
		fixHeap();
		return temp ;
		
	}
	
	/**
	 *  a better implementation of the fixHeap
	 *  down-ward fix the array. 
	 */
	private void fixHeap(){
		int i = 0;
		int childPos ;
		
		while(2*i+1<heapSize){
			childPos = 2*i+1;  //this is important! should inside the loop.
			if(childPos+1<heapSize && data[childPos+1]>data[childPos])
				childPos = childPos+1;
			
			if(data[i]<data[childPos]){
				swap(i,childPos);
				i = childPos;
			}
			else{
				break ;
			}
				
		}
		
	}
	
	
 /*
	 *//**
	 * helper method for remove()
	 * 
	 * after remove the first value, move the last item in the array to the top,
	 * then compare the top with its two children, swap with the one who is 
	 * the largest one. until no more larger children.  
	 * 
	 *//* 
	private void fixHeap(){
		int i = 0;
		int child1 , child2;
		while((i*2+1)<heapSize){
			child1 = 2*i+1;
			
			if((2*i+2)<heapSize){ //has two children
				child2 = 2*i+2; 
				
				if(data[child2]> data[i] && data[child2]>data[child1]){
					swap(child2,i);
					i = child2;
				}
				
				else if(data[child1]>data[i] && data[child1]>=data[child2]){
					swap(child1,i);
					i = child1;
				}
				else{
					break;
				}
				
			}
			
			else { //only one child, child1
				
				if(data[child1]>data[i]){
					swap(child1,i);
					i = child1;
				}
				else
					break;
			}
			
		}//end while

	} 
*/	
/*	private boolean hasChild(int position){
		return ( (position*2+1)<heapSize );
	}*/
	
	
	public int heapSize(){
		return heapSize;
	}
	
	public boolean isFull(){
		return (heapSize==maxSize);
	}
	
	public static void main(String[] args){
		
		Heap h = new Heap(10);
		try {
			h.insert(98);
			h.insert(56);
			h.insert(24);
			h.insert(34);
			h.insert(3);
			h.insert(17);
			
			System.out.println(h.remove());
			System.out.println(h.remove());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end main
	

}
