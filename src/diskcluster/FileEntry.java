package diskcluster;

public class FileEntry {
	
	private String _fileName ; 
	private DiskClusterList _clusterList; 
	
	public FileEntry(String name){
		_fileName = name; 
		_clusterList = new DiskClusterList();
	}
	
	public void setFileName(String name){
		_fileName = name ;
	}
	
	public String getFileName(){
		return _fileName ; 
	}
	
	public DiskClusterList getDiskClusterList(){
		return _clusterList ;
	}
	
	public void printFile(){
		//System.out.println("File Name: "+ _fileName);
		_clusterList.printAllClusters();
	
	}
	
	
}
