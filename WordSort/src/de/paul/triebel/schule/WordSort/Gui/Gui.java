package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Gui extends JFrame {
	
	private final static String title = "WordSort";
	
	public Gui() {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		GuiMenu.update(getJMenuBar());
		
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		
	}
}
