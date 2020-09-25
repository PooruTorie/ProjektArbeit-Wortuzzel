package de.paul.triebel.schule.WordSort.Gui.Drag;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import de.paul.triebel.schule.WordSort.Gui.Gui;
import de.paul.triebel.schule.WordSort.Gui.FileFilter.OpenFilter;
import de.paul.triebel.schule.WordSort.Gui.FileFilter.SaveFilter;
import de.paul.triebel.schule.WordSort.Utils.FileUtils;

public class DragPanel extends JPanel {
	
	public Gui gui;
	
	public ArrayList<DragObject> objects = new ArrayList<>();
	
	public DragPanel(Gui gui) {
		super();
		
		this.gui = gui;
		
		setBackground(new Color(224, 248, 239));
		
		setLayout(null);
		
		setBounds(0, (gui.getHeight()/8)+1, gui.getWidth(), gui.getHeight());
	}
	
	public Point randomPos(Dimension size) {
		Random r = new Random();
		return new Point((int) (r.nextInt((int) (getWidth()-(size.getWidth()*2)))+size.getWidth()), (int) (r.nextInt((int) (getHeight()-(size.getHeight()*2)))+size.getHeight()));
	}
	
	public void addLine(String text) {
		Random cr = new Random();
		Color color = new Color(cr.nextInt(155)+100, cr.nextInt(155)+100, cr.nextInt(155)+100);
		String[] words = text.split(" ");
		for (String word : words) {
			if (!word.equals("")) {
				DragObject o = new DragObject(word, color, randomPos(DragObject.getSize(word)));
				objects.add(o);
				add(o);
			}
		}
		repaint();
	}

	public void openSaveFile() {
		JFileChooser save = new JFileChooser();
		
		save.setFileSelectionMode(JFileChooser.FILES_ONLY);
		save.addChoosableFileFilter(new SaveFilter());
		save.setAcceptAllFileFilterUsed(false);
		
		save.showSaveDialog(gui);
		
		File saveFile = save.getSelectedFile();
		
		if (saveFile != null) {		
			if (!saveFile.getName().contains(".wuzz")) {
				if (saveFile.getName().contains(".")) {
					String[] nameTiles = saveFile.getName().replace('.', '#').split("#");
					String name = "";
					if (nameTiles.length > 1) {
						for (String n : Arrays.copyOf(nameTiles, nameTiles.length-1)) {
							name+=n;
						}
					} else {
						name = nameTiles[0];
					}
					saveFile = new File(saveFile.getParent()+"/"+name+".wuzz");
				} else {
					saveFile = new File(saveFile.getAbsolutePath()+".wuzz");
				}
			}
			
			FileUtils.compileToFile(saveFile, objects);
		}
		
		gui.repaint();
	}

	public void openFromFile(File openFile) {
		if (openFile == null) {
			JFileChooser open = new JFileChooser();
			
			open.setFileSelectionMode(JFileChooser.FILES_ONLY);
			open.addChoosableFileFilter(new OpenFilter());
			open.setAcceptAllFileFilterUsed(false);
			
			open.showOpenDialog(gui);
			
			openFile = open.getSelectedFile();
		}
		
		if (openFile != null) {
			String extension = FileUtils.getExtension(openFile);
			
			if (extension.equals("txt")) {
				try {
					BufferedReader r = new BufferedReader(new FileReader(openFile));
					
					while (r.ready()) {
						addLine(r.readLine());
					}
					
					r.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (extension.equals("wuzz")) {
				clear();
				
				objects = FileUtils.compileFromFile(openFile);
				
				for (DragObject o : objects) {
					add(o);
				}
			}
		}
		
		gui.repaint();
	}

	public void clear() {
		for (DragObject o : objects) {
			remove(o);
		}
		objects.clear();
		repaint();
	}
}
