package de.paul.triebel.schule.WordSort.Utils;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragObject;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragPanel;

public class FileUtils {
	
	public static String getExtension(File f) {
		String[] nameTiles = f.getName().replace('.', '#').split("#");
        String extension = nameTiles[nameTiles.length-1];
        return extension;
	}

	public static void compileToFile(File saveFile, ArrayList<DragObject> objects) {
		try {
			FileWriter out = new FileWriter(saveFile);
			
			for (DragObject o : objects) {
				out.write(13);
				
				out.write(""+o.getText());
				out.write(0);
				out.write(""+o.getX());
				out.write(0);
				out.write(""+o.getY());
				out.write(0);
				out.write(""+o.getBackground().getRGB());
				out.write(0);
				
				out.flush();
			}
			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(main.getGui(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static ArrayList<DragObject> compileFromFile(File openFile) {
		try {
			FileReader r = new FileReader(openFile);
			ArrayList<Byte> d = new ArrayList<>();
			
			while (r.ready()) {
				d.add((byte) r.read());
			}
			r.close();
			
			Iterator<Byte> data = d.iterator();
			
			ArrayList<DragObject> os = new ArrayList<>();
			
			while (data.hasNext()) {
				byte startByte = data.next();
				if (startByte == 13) {
					byte[] text = getnextBytes(data);
					byte[] x = getnextBytes(data);
					byte[] y = getnextBytes(data);
					byte[] rgb = getnextBytes(data);
					
					Point pos = new Point(Integer.parseInt(new String(x)), Integer.parseInt(new String(y)));
					
					os.add(new DragObject(new String(text), new Color(Integer.parseInt(new String(rgb))), pos));
				}
			}
			
			return os;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(main.getGui(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	private static byte[] getnextBytes(Iterator<Byte> d) {
		ArrayList<Byte> data = new ArrayList<>();
		
		while (d.hasNext()) {
			byte b = d.next();
			if (b == 0) {
				return allToByte(data);
			} else {
				data.add(b);
			}
		}
		
		return allToByte(data);
	}

	private static byte[] allToByte(ArrayList<Byte> data) {
		byte[] d = new byte[data.size()];
		for (int i = 0; i < data.size(); i++) {
			d[i] = data.get(i);
		}
		return d;
	}
}
