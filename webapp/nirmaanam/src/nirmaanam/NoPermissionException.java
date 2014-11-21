package nirmaanam;

public class NoPermissionException extends Exception{
	public NoPermissionException(){
		super();
	}
	public NoPermissionException(String message){
		super(message);
	}
}