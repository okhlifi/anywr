package com.anywr.oki.exception;

public class UserNotFoundException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6892769232726533573L;
	public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException() {}
}