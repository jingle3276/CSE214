package friendlist;

public class DuplicateNameException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _name;

    public DuplicateNameException(String name){
        _name = name;
    }

    public String toString(){
        return "Duplicate Name in the List" + " " + _name ;
    }
}
