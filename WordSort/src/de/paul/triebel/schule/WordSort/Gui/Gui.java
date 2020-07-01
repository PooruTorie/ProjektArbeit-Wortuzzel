package de.paul.triebel.schule.WordSort.Gui;

import javax.swing.JFrame;

public class Gui extends JFrame {
	
	private final static String title = "WordSort";
	
	public Gui() {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setVisible(true);
	}
	
}
