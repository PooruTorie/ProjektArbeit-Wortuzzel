package de.paul.triebel.schule.WordSort.Gui.Input.Exceptions;

public class toLongWordException extends Exception {

	private String w;

	public toLongWordException(String word) {
		w = word;
	}
	
	public String getWord() {
		return w;
	}
}
