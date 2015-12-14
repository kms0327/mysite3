package com.hanains.mysite.exception;

public class RepositoryException extends RuntimeException {
	public RepositoryException(){
		super("Repository Excpetion");
	}
	
	public RepositoryException(String message){
		super("RepositoryException : " +message);
	}
	
}
