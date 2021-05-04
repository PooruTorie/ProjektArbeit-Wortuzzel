package de.paul.triebel.schule.WordSort.Gui.Drag;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Data.Config;
import de.paul.triebel.schule.WordSort.Gui.Gui;
import de.paul.triebel.schule.WordSort.Gui.FileFilter.ExportFilter;
import de.paul.triebel.schule.WordSort.Gui.FileFilter.OpenFilter;
import de.paul.triebel.schule.WordSort.Gui.FileFilter.SaveFilter;
import de.paul.triebel.schule.WordSort.Gui.Input.Exceptions.toLongWordException;
import de.paul.triebel.schule.WordSort.PDF.PDFConverter;
import de.paul.triebel.schule.WordSort.Utils.FileUtils;
import de.paul.triebel.schule.WordSort.Utils.MathUtils;

public class DragPanel extends JPanel {
	
	public Gui gui;
	
	public static ArrayList<ArrayList<DragObject>> words = new ArrayList<>();
	
	public ArrayList<DragObject> selected = new ArrayList<>();
	
	public DragPanel(Gui gui) {
		super();
		
		this.gui = gui;
		
		setBackground(new Color(224, 248, 239));
		
		setLayout(null);
		
		setBounds(0, (gui.getHeight()/8)+1, gui.getWidth(), gui.getHeight());
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				dragEnd = new Point(x, y);
				select(dragStart, dragEnd);
				repaint();
			}
		});
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				dragStart = new Point(x, y);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				dragStart = null;
				dragEnd = null;
				repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				select(dragStart, dragEnd);
				repaint();
			}
		});
		
		oPSize = getBounds();
	}
	
	public void select(Point start, Point end) {
		for (ArrayList<DragObject> arrayList : words) {
			for (DragObject o : arrayList) {
				if (start != null && end != null) {
					if (new Rectangle(start.x, start.y, end.x-start.x, end.y-start.y).contains(o.getBounds())) {
						if (!selected.contains(o)) {
							o.selected(true);
							selected.add(o);
						}
					} else {
						if (selected.contains(o)) {
							o.selected(false);
							selected.remove(o);
						}
					}
				} else {
					if (selected.contains(o)) {
						o.selected(false);
						selected.remove(o);
					}
				}
			}
		}
	}
	
	public Point dragEnd, dragStart;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		if (dragStart != null && dragEnd != null) {
			g2.setColor(new Color(204, 208, 255, 100));
			g2.fillRect(dragStart.x, dragStart.y, dragEnd.x-dragStart.x, dragEnd.y-dragStart.y);
		}
	}
	
	public Point randomPos(String word, Dimension size) throws toLongWordException {
		Random r = new Random();
		try {
			return new Point((int) (r.nextInt((int) (getWidth()-(size.getWidth()*2)))+size.getWidth()), (int) (r.nextInt((int) (getHeight()-(size.getHeight()*2)))+size.getHeight()));
		} catch (Exception e) {
			throw new toLongWordException(word);
		}
	}
	
	public void addLine(String text) {
		Random cr = new Random();
		Color color = new Color(cr.nextInt(155)+100, cr.nextInt(155)+100, cr.nextInt(155)+100);
		String[] wordsString = text.split(" ");
		ArrayList<DragObject> wordwords = new ArrayList<>();
		for (String word : wordsString) {
			if (!word.equals("")) {
				DragObject o;
				try {
					o = new DragObject(word, color, randomPos(word, DragObject.getSize(word)), words.size());
					wordwords.add(o);
					add(o);
				} catch (toLongWordException e) {
					JOptionPane.showMessageDialog(main.getGui(), ((String) main.getLanguageFile().get("longerror")).replace("%word%", "\""+e.getWord()+"\""), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		words.add(wordwords);
		repaint();
	}
	
	public boolean[] isInPanel(Point pos, Dimension size) {
		return MathUtils.inside(new Rectangle(pos, size), new Rectangle(getSize()));
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
			
			if (saveFile.exists()) {
				int status = JOptionPane.showConfirmDialog(main.getGui(), (String) main.getLanguageFile().get("override"), (String) main.getLanguageFile().get("override"), JOptionPane.WARNING_MESSAGE);
				if (status == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
			
			FileUtils.compileToFile(saveFile, words);
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
					BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(openFile), "UTF-8"));;
					
					while (r.ready()) {
						addLine(r.readLine());
					}
					
					r.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (extension.equals("wuzz")) {
				clear();
				
				words = FileUtils.compileFromFile(openFile);
				
				for (ArrayList<DragObject> o : words) {
					for (DragObject dragObject : o) {
						add(dragObject);	
					}
				}
			}
		}
		
		repaint();
	}

	public void clear() {
		for (ArrayList<DragObject> o : words) {
			for (DragObject dragObject : o) {
				remove(dragObject);	
			}
		}
		words.clear();
		repaint();
	}

	public static void updateFont() {
		for (ArrayList<DragObject> arrayList : words) {
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
			
			if (exportFile.exists()) {
				int status = JOptionPane.showConfirmDialog(main.getGui(), (String) main.getLanguageFile().get("override"), (String) main.getLanguageFile().get("override"), JOptionPane.WARNING_MESSAGE);
				if (status == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
			
			PDFConverter.toPDF(exportFile);
		}
		
		repaint();
	}
	
	Rectangle oPSize;

	public void resizeComponents() {
		Rectangle size = getBounds();
		
		for (ArrayList<DragObject> arrayList : words) {
			for (DragObject o : arrayList) {
				Point oPos = o.getLocation();
				Point nPos = new Point();
				
				nPos.x = (int) MathUtils.clamp(0, size.width, MathUtils.remap(oPSize.x, oPSize.width, size.x, size.width, oPos.x));
				nPos.y = (int) MathUtils.clamp(0, size.height, MathUtils.remap(oPSize.y, oPSize.height, size.y, size.height, oPos.y));
				
				o.setLocation(nPos);
			}
		}
		
		oPSize = getBounds();
	}

	public void clearSelected() {
		for (DragObject o : selected) {
			o.selected(false);
		}
		selected.clear();
		System.out.println(selected);
	}
}
