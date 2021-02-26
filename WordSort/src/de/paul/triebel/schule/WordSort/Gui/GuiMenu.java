package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Options.Options;
import de.paul.triebel.schule.WordSort.assets.assets;

public class GuiMenu extends JMenuBar {
	
	public static void update(Gui gui) {
		GuiMenu.gui = gui;
		
		gui.setJMenuBar(new GuiMenu());
	}

	private static Gui gui;
	
	public GuiMenu() {
		super();
		
		add(GuiMenu.get("file"));
		add(GuiMenu.get("view"));
		add(GuiMenu.get("help"));
	}
	
	private static JMenu get(String st) {
		JMenu menu = new JMenu((String) main.getLanguageFile().get(st));
		menu.setFont(CustomFont.get(28));
		for (JMenuItem i : getItems(menu, st)) {
			if (i == null) {
				menu.addSeparator();
			} else {
				i.setFont(CustomFont.get(18));
				menu.add(i);
			}
		}
		return menu;
	}

	private static ArrayList<JMenuItem> getItems(JMenu menu, String st) {
		
		ArrayList<JMenuItem> menus = new ArrayList<>();
		switch (st) {
		case "file":
			menus.add(createMenuItem((String) main.getLanguageFile().get("new"), new Runnable() {
				
				@Override
				public void run() {
					gui.dragPanel.clear();
				}
			}));
			menus.add(null);
			menus.add(createMenuItem((String) main.getLanguageFile().get("open"), new Runnable() {
				
				@Override
				public void run() {
					gui.dragPanel.openFromFile(null);
				}
			}));
			menus.add(createMenuItem((String) main.getLanguageFile().get("save"), new Runnable() {
				
				@Override
				public void run() {
					gui.dragPanel.openSaveFile();
				}
			}));
			menus.add(null);
			menus.add(createMenuItem((String) main.getLanguageFile().get("export"), new Runnable() {
				
				@Override
				public void run() {
					gui.dragPanel.exportPDF();
				}
			}));
			break;
		case "view":
			menus.add(createMenuItem((String) main.getLanguageFile().get("options"), new Runnable() {
				
				@Override
				public void run() {
					Options.open();
				}
			}));
			break;
		case "help":
			menus.add(createMenuItem((String) main.getLanguageFile().get("tutorial"), new Runnable() {
				
				@Override
				public void run() {
					try {
						Desktop.getDesktop().open(assets.getFile("video.mp4"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
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
