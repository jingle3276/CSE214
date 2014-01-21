package diskcluster;

public class DiskClusterNode {
	
	private int _clusterNumber;
	private String _data ; 
	private DiskClusterNode _link; 
	
	public DiskClusterNode (int clusterNumber, String initialData) {
		_clusterNumber = clusterNumber ; 
		_data = initialData ;
		_link = null ; 
	}
	
	/**
	 * set the Cluster Number of this node
	 * @param clusterNumber
	 */
	public void setClusterNumber(int clusterNumber){
		_clusterNumber = clusterNumber ; 
	}
	
	/**
	 * get the cluster number of the node  
	 * @return clusterNumber 
	 */
	public int getClusterNumber (){
		return _clusterNumber ; 
	}
	
	/**
	 * set the data of this node 
	 * @param data
	 */
	public void setData(String data){
		_data = data ; 
	}
	
	/**
	 * 
	 * @return the data of this node 
	 */
	public String getData(){
		return _data ; 
	}
	
	/**
	 * set the link to the next node 
	 * @param node
	 */
	public void setNextNode(DiskClusterNode node){
		_link = node ; 
	}
	
	/**
	 * 
	 * @return the next node it linked to 
 	 */
	public DiskClusterNode getNextNode() {
		return _link ; 
	}
	
}
