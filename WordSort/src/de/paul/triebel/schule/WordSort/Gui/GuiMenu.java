package de.paul.triebel.schule.WordSort.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import de.paul.triebel.schule.WordSort.JLink;
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
			menu.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {}
				
				@Override
				public void mouseEntered(MouseEvent e) {}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						JDialog d = new JDialog(main.getGui(), (String) main.getLanguageFile().get("help"));
						d.setFont(CustomFont.get(10));
						d.setLayout(new GridLayout(2, 1));
							
						
						JPanel linkPanel = new JPanel();
						linkPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
						JLink link = new JLink("Haben sie Fragen oder Fehler gefunden?", new URI("https://github.com/NutellaJunge/ProjektArbeit-Wortuzzel/issues"));
						link.setFont(CustomFont.get(60));
						linkPanel.add(link);
						d.add(linkPanel);
						
						JPanel changeLogPanel = new JPanel();
						changeLogPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
						String list = "";
						for (String log : main.log) {
							list+="<li>"+log+"</li>";
						}
						JLabel title = new JLabel("<html>Neuerungen:<br><ul>"+list +"</ul></html>");
						title.setFont(CustomFont.get(30));
						changeLogPanel.add(title);
						d.add(changeLogPanel);
						
						d.pack();
						
						d.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
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
