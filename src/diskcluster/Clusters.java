package diskcluster;

import friendlist.IllegalArgumentException;

public class Clusters {

	private final int MAX_CLUSTERS = 150 ; 
	private boolean [] _clusterArray ;  
	
	public Clusters(){
		
		_clusterArray = new boolean [MAX_CLUSTERS];
		
		for(int i = 0; i<MAX_CLUSTERS; i++){
			_clusterArray[i] = false ;
		}
		
	}
	
	public void setClusterTrue(int position) throws IllegalArgumentException{
		if(position<0 || position>=MAX_CLUSTERS){
			throw new IllegalArgumentException();
		}
		
		_clusterArray[position] = true ; 
		
	}
	
	public void setClusterFalse(int position) throws IllegalArgumentException{
		if(position<0 || position>=MAX_CLUSTERS){
			throw new IllegalArgumentException();
		}
		_clusterArray[position] = false ;
	}
	
	public int nextAvailableCluster(){
		
		for(int i=0; i<MAX_CLUSTERS; i++){
			if(_clusterArray[i]==false){
				return i ; 
			}
		}
		
		return -1;
	}
	
}
