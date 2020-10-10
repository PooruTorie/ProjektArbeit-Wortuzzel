package de.paul.triebel.schule.WordSort.Gui.Drag;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Data.Config;
import de.paul.triebel.schule.WordSort.Gui.Gui;
import de.paul.triebel.schule.WordSort.Gui.FileFilter.ExportFilter;
import de.paul.triebel.schule.WordSort.Gui.FileFilter.OpenFilter;
import de.paul.triebel.schule.WordSort.Gui.FileFilter.SaveFilter;
import de.paul.triebel.schule.WordSort.PDF.PDFConverter;
import de.paul.triebel.schule.WordSort.Utils.FileUtils;

public class DragPanel extends JPanel {
	
	public Gui gui;
	
	public static ArrayList<ArrayList<DragObject>> objects = new ArrayList<>();
	
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
		ArrayList<DragObject> wordObjects = new ArrayList<>();
		for (String word : words) {
			if (!word.equals("")) {
				DragObject o = new DragObject(word, color, randomPos(DragObject.getSize(word)), objects.size());
				wordObjects.add(o);
				add(o);
			}
		}
		objects.add(wordObjects);
		repaint();
	}

	public void openSaveFile() {
		JFileChooser save = new JFileChooser();
		
		String path = (String) main.getConfig().get(Config.SAVEFOLDER);
		if (path != null) {
			save = new JFileChooser(new File(path));
		}
		
		save.setFileSelectionMode(JFileChooser.FILES_ONLY);
		save.addChoosableFileFilter(new SaveFilter());
		save.setAcceptAllFileFilterUsed(false);
		
		save.showSaveDialog(gui);
		
		File saveFile = save.getSelectedFile();
		main.getConfig().saveSaveFolder(save.getCurrentDirectory());
		
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
		
		repaint();
	}

	public void openFromFile(File openFile) {
		if (openFile == null) {
			JFileChooser open = new JFileChooser();
			
			String path = (String) main.getConfig().get(Config.OPENFOLDER);
			if (path != null) {
				open = new JFileChooser(new File(path));
			}
			
			open.setFileSelectionMode(JFileChooser.FILES_ONLY);
			open.addChoosableFileFilter(new OpenFilter());
			open.setAcceptAllFileFilterUsed(false);
			
			open.showOpenDialog(gui);
			
			openFile = open.getSelectedFile();
			main.getConfig().saveOpenFolder(open.getCurrentDirectory());
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
				
				for (ArrayList<DragObject> o : objects) {
					for (DragObject dragObject : o) {
						add(dragObject);	
					}
				}
			}
		}
		
		repaint();
	}

	public void clear() {
		for (ArrayList<DragObject> o : objects) {
			for (DragObject dragObject : o) {
				remove(dragObject);	
			}
		}
		objects.clear();
		repaint();
	}

	public static void updateFont() {
		System.out.println(objects);
		for (ArrayList<DragObject> arrayList : objects) {
			for (DragObject o : arrayList) {
				o.update();
			}
		}
	}

	public void exportPDF() {
		JFileChooser save = new JFileChooser();
		
		String path = (String) main.getConfig().get(Config.EXPORTFOLDER);
		if (path != null) {
			save = new JFileChooser(new File(path));
		}
		
		save.setFileSelectionMode(JFileChooser.FILES_ONLY);
		save.addChoosableFileFilter(new ExportFilter());
		save.setAcceptAllFileFilterUsed(false);
		
		save.showSaveDialog(gui);
		
		File exportFile = save.getSelectedFile();
		main.getConfig().saveExportFolder(save.getCurrentDirectory());
		
		if (exportFile != null) {
			if (!exportFile.getName().contains(".pdf")) {
				if (exportFile.getName().contains(".")) {
					String[] nameTiles = exportFile.getName().replace('.', '#').split("#");
					String name = "";
					if (nameTiles.length > 1) {
						for (String n : Arrays.copyOf(nameTiles, nameTiles.length-1)) {
							name+=n;
						}
					} else {
						name = nameTiles[0];
					}
					exportFile = new File(exportFile.getParent()+"/"+name+".pdf");
				} else {
					exportFile = new File(exportFile.getAbsolutePath()+".pdf");
				}
			}
			
			PDFConverter.toPDF(exportFile);
		}
		
		repaint();
	}
}
