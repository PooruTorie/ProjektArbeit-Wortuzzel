package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.paul.triebel.schule.WordSort.main;

public class GuiMenu extends JMenuBar {
	
	public static void update(Gui gui) {
		gui.setJMenuBar(new GuiMenu());
	}
	
	public GuiMenu() {
		super();
		
		add(GuiMenu.get("test"));
	}
	
	private static JMenu get(String st) {
		JMenu menu = new JMenu((String) main.getLanguageFile().get(st));
		
		menu.add(GuiMenu.getItem(st));
		
		return menu;
	}

	private static JMenuItem getItem(String st) {
		JMenuItem menu = new JMenuItem();
		menu.setText(st);
		return menu;
	}
}
