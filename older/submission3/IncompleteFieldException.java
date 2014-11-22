package nirmaanam;

class IncompleteFieldException extends Exception{
	public IncompleteFieldException(){
		super();
	}
	public IncompleteFieldException(String message){
		super(message);
	}
}