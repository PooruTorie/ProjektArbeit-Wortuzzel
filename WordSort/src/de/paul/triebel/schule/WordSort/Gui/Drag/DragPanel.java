package de.paul.triebel.schule.WordSort.Gui.Drag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import de.paul.triebel.schule.WordSort.Gui.Gui;
import de.paul.triebel.schule.WordSort.Gui.FileFilter.OpenFilter;

public class DragPanel extends JPanel {
	
	public Gui gui;
	
	public DragPanel(Gui gui) {
		super();
		
		this.gui = gui;
		
		setBackground(new Color(224, 248, 239));
		
		setLayout(null);
		
		setBounds(0, (gui.getHeight()/8)+1, gui.getWidth(), gui.getHeight());
		
		add(new DragObject("Hi", Color.RED, new Point(0, 0)));
	}
	
	public void addLine(String string) {
	}

	public void openSaveFile() {
		JFileChooser save = new JFileChooser();
		save.showSaveDialog(gui);
	}

	public void openFromFile() {
		JFileChooser open = new JFileChooser();
		
		open.addChoosableFileFilter(new OpenFilter());
		open.setAcceptAllFileFilterUsed(true);
		
		open.showOpenDialog(gui);
	}
}
