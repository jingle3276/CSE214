package diskcluster;

import friendlist.IllegalArgumentException;

public class FileAllocationManager {

	private final int MAX_DATA_SIZE = 70;
	private final int FILE_COUNT = 100; 
	
	private FileEntry [] fileArray ; 
	
	private static Clusters cluster ; 
	private int fileCount ;
	
	
	public FileAllocationManager(){
		fileArray = new FileEntry [FILE_COUNT] ; 
		cluster = new Clusters();
		fileCount = 0 ;
		
	}
	
	public void appendClusterToTail(String fileName, String data) throws IllegalArgumentException {
		
		if(data.length()==0 || data.length()>MAX_DATA_SIZE){
			throw new IllegalArgumentException();
		}
		
		int index = getFileIndex(fileName);
		
		if(index==-1){ //a new fileName
			fileArray[fileCount] = new FileEntry(fileName);
			fileArray[fileCount].getDiskClusterList().addClusterToTail( allocateCluster(), data); 			
			fileCount = fileCount + 1 ;
		}
		
		else{ //existing fileName
			//fileArray[index].setFileName(fileName);
			fileArray[index].getDiskClusterList().addClusterToTail( allocateCluster(), data); 						
		}
				
	}
	
	public boolean containsCluster(String fileName, int clusterNumber) throws FileNotExsitException{
		
		int index = getFileIndex(fileName); 
	
		if(index==-1){
			throw new FileNotExsitException();
		}
		
		else{ //file exists 
			return fileArray[index].getDiskClusterList().containsCluster(clusterNumber);
		}
		
	}
	
	public void deleteAllClusters(String fileName) throws FileNotExsitException, IllegalArgumentException, ListEmptyException{
		int index = getFileIndex(fileName); 
		
		if(index==-1){
			throw new FileNotExsitException( );
		}
		
		else{
			fileArray[index].getDiskClusterList().removeAllClusters();			
		}
	}
	
	public void insertClusterAtPosition(String fileName, String data , int position ) throws IllegalArgumentException{
		
		if(data.length()==0 || data.length()>MAX_DATA_SIZE){
			throw new IllegalArgumentException();
		}
		
		int fileIndex = getFileIndex(fileName);
		
		if(fileIndex!=-1){ //existing file, do the insert 
			fileArray[fileIndex].getDiskClusterList().insertCluster(allocateCluster(), data, position);  			
		}
		
		else { //new file, create file and do the insert 
			fileArray[fileCount] = new FileEntry(fileName);
			fileArray[fileCount].getDiskClusterList().insertCluster(allocateCluster(), data, position);
			fileCount = fileCount + 1 ; 
		}
	}
	
	public void printAllClusters(String fileName){
		
		int fileIndex = getFileIndex(fileName);
		
		if(fileIndex!=-1){ //existing file 
			System.out.println(fileArray[fileIndex].getFileName()+" contents: ");
			fileArray[fileIndex].printFile(); 
		}
		else{
			System.out.println(fileName+ " not exist!");
		}
		
	}
	
	public void removeCluster(String fileName, int position) throws Exception{
		
		int fileIndex = getFileIndex(fileName);
		
		if(fileIndex!=-1){ //exsiting file 
			fileArray[fileIndex].getDiskClusterList().removeCluster(position);
			cluster.setClusterFalse(position);
			
		}
		
		else{
			throw new FileNotExsitException();
		}	
	}
	
	
	/**
	 * get the index of the fileArray[] by the file name
	 * if the file exists in the array, return the index. 
	 * otherwise, return -1 
	 * 
	 */
	public int getFileIndex(String fileName){
		
		for(int i=0; i<fileCount; i++){
			if(fileArray[i].getFileName().equalsIgnoreCase(fileName)) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	/**
	 * get the next available cluster number from the Cluster object, 
	 * and then set this slot to 'true' to mark it be used. 
	 * 
	 */
	public int allocateCluster(){
		
		int clusterNumber = cluster.nextAvailableCluster(); 
		try {
			cluster.setClusterTrue(clusterNumber);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clusterNumber; 
		
	}
	
	public void printMsg(){
		System.out.println("A) Append Cluster To Tail");
		System.out.println("C) Contains Cluster");
		System.out.println("D) Delete All Clusters");
		System.out.println("I) Insert Cluster At Position");
		System.out.println("P) Print All Clusters");
		System.out.println("R) Remove Cluster");
		System.out.println("E) Reverse a file(new)");
		System.out.println("Q) Quit");
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public void reserve(String fileName) throws ListEmptyException{
		int fileIndex = getFileIndex(fileName);
		fileArray[fileIndex].getDiskClusterList().reverse();
		
	}
	
	public static void main(String args[]){
		
		FileAllocationManager m = new FileAllocationManager();
		//InputReader ir = new InputReader();
		InputReader ir = InputReader.getInputReader();
		
		m.printMsg();
		while(true){
			//System.out.println();
			System.out.print("Select an option: ");

			String input = ir.getInput();
			char c ;
			
			if(!input.equals("")){
				c = input.toUpperCase().charAt(0);
				
				String fileName  ;
				String data ; 
				int position ; 
				int clusterNumber;
			
				switch (c) {

				case 'A': 
					System.out.print("Enter File Name> ");
					fileName = ir.getInput(); 
					System.out.print("Enter data> ");
					data = ir.getInput();
					try {
						m.appendClusterToTail(fileName, data);
						System.out.println("Insertion Successful!");
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					break ;
					
				case 'R':  
					System.out.print("Enter File Name> ");
					fileName = ir.getInput();
					System.out.print("Enter position> ");
					position = Integer.parseInt(ir.getInput());
					
					try {
						m.removeCluster(fileName, position);
						System.out.println("Removal Successful!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;	
					
				case 'P':  
					System.out.print("Enter File Name> ");
					fileName = ir.getInput();
					m.printAllClusters(fileName);
					
					break;
					
				case 'I':  
					System.out.print("Enter File Name> ");
					fileName = ir.getInput();
					System.out.print("Enter Data> ");
					data = ir.getInput();
					System.out.print("Enter Position> ");
					position = Integer.parseInt( ir.getInput());
					
					try {
						m.insertClusterAtPosition(fileName, data, position);
						System.out.println("Insertion Successful!");
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case 'C':
					System.out.print("Enter file name> ");
					fileName = ir.getInput();
					System.out.print("Enter Cluster Number> ");
					clusterNumber = Integer.parseInt(ir.getInput());
					try {
						if(m.containsCluster(fileName, clusterNumber)){
							System.out.println(fileName + " contains Cluster " + clusterNumber);
						}
						else {
							System.out.println(fileName + " doesn't contain Cluster " + clusterNumber);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break ;

				case 'D':  //Remove all cluster of a file name 
					System.out.print("Enter file Name> ");
					fileName = ir.getInput();
					try {
						m.deleteAllClusters(fileName);
						System.out.println("Removal Successful!");
					} catch (FileNotExsitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ListEmptyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;	
					
				case 'Q':
					System.out.println("Quit programm.");
					System.exit(0);				
					break;
					
				//additional for practice
				case 'E':
					System.out.println("Enter file name to Reseve: ");
					fileName = ir.getInput();
					try {
						m.reserve(fileName);
						System.out.println("reverse successfully.");
					} catch (ListEmptyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				default : 
					break;
				}//end switch  				
			}//end if
			
		}//end while  
			
	}//end main 
	
}
