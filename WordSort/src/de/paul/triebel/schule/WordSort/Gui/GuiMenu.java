package de.paul.triebel.schule.WordSort.Gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
					JDialog d = new JDialog(main.getGui(), (String) main.getLanguageFile().get("help"));
					d.setFont(CustomFont.get(10));
					d.setSize(400, 280);
					d.setLayout(new GridLayout(0, 2));
					
					JPanel textPanel = new JPanel();
					JLabel l = new JLabel((String) main.getLanguageFile().get("tutorial"));
					l.setFont(CustomFont.get(main.getFontSize()));
					textPanel.add(l);
					d.add(textPanel);
					
					try {
						ImageIcon icon = new ImageIcon(ImageIO.read(assets.getFile("textures/video.png")));
						ImageIcon iconSelected = new ImageIcon(ImageIO.read(assets.getFile("textures/videoSelected.png")));
						
						JButton play = new JButton(icon);
						
						play.setBackground(new Color(0, 0, 0, 0));
						play.setOpaque(false);
						play.setContentAreaFilled(false);
						play.setBorderPainted(false);
						play.setFocusPainted(false);
						
						play.addMouseListener(new MouseListener() {
							
							@Override
							public void mouseEntered(MouseEvent e) {
								play.setIcon(iconSelected);
								d.repaint();
							}

							@Override
							public void mouseExited(MouseEvent e) {
								play.setIcon(icon);
								d.repaint();
							}

							@Override
							public void mouseClicked(MouseEvent e) {}

							@Override
							public void mousePressed(MouseEvent e) {}

							@Override
							public void mouseReleased(MouseEvent e) {}
						});
						
						play.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									Desktop.getDesktop().open(assets.getFile("video.mp4"));
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						});
						textPanel.add(play);
						
						JLink link = new JLink("Haben sie Fragen oder Fehler?", new URI("https://github.com/NutellaJunge/ProjektArbeit-Wortuzzel/issues"));
						d.add(link);
						
						d.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
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
