package br.com.randomthings.exception;

public class UnsupportedFile extends RuntimeException {
	
	public UnsupportedFile() {
		super();
	}
	
	public UnsupportedFile(String message) {
		super(message);
	}
	
}
