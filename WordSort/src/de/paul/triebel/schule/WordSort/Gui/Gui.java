package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gui extends JFrame {
	
	private final static String title = "WordSort";
	
	public Gui() {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setSize(getGraphicsConfiguration().getBounds().getSize());
		
		setLayout(null);
		
		GuiMenu.update(this);
		
		repaint();
		
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		paintComponents(g);
	}
}
