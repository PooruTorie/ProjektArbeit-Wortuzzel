package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Options.Options;

public class GuiMenu extends JMenuBar {
	
	public static void update(Gui gui) {
		GuiMenu.gui = gui;
		
		gui.setJMenuBar(new GuiMenu());
	}

	private static Gui gui;
	
	public GuiMenu() {
		super();
		
		add(GuiMenu.get("file"));
		add(GuiMenu.get("options"));
	}
	
	private static JMenu get(String st) {
		JMenu menu = new JMenu((String) main.getLanguageFile().get(st));
		menu.setFont(CustomFont.get(28));
		for (JMenuItem i : getItems(menu, st)) {
			i.setFont(CustomFont.get(18));
			menu.add(i);
		}
		return menu;
	}

	private static ArrayList<JMenuItem> getItems(JMenu menu, String st) {
		
		ArrayList<JMenuItem> menus = new ArrayList<>();
		switch (st) {
		case "file":
			menus.add(createMenuItem((String) main.getLanguageFile().get("open"), new Runnable() {
				
				@Override
				public void run() {
					gui.dragPanel.openFromFile();
				}
			}));
			menus.add(createMenuItem((String) main.getLanguageFile().get("save"), new Runnable() {
				
				@Override
				public void run() {
					gui.dragPanel.openSaveFile();
				}
			}));
			break;
		case "options":
			menus.add(createMenuItem((String) main.getLanguageFile().get("open"), new Runnable() {
				
				@Override
				public void run() {
					Options.open();
				}
			}));
			break;
		}
		return menus;
	}

	private static JMenuItem createMenuItem(String name, Runnable run) {
		JMenuItem menu = new JMenuItem();
		menu.setText(name);
		if (run != null) {
			menu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					run.run();
				}
			});
		}
		return menu;
	}
}
