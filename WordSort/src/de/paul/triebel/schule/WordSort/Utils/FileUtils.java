package de.paul.triebel.schule.WordSort.Utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragObject;

public class FileUtils {
	
	public static String getExtension(File f) {
		String[] nameTiles = f.getName().replace('.', '#').split("#");
        String extension = nameTiles[nameTiles.length-1];
        return extension;
	}

	public static void compileToFile(File saveFile, ArrayList<ArrayList<DragObject>> objects) {
		try {
			FileWriter out = new FileWriter(saveFile);
			
			for (int i = 0; i < objects.size(); i++) {
				for (DragObject o : objects.get(i)) {
					out.write(13);
					
					out.write(""+o.getText().replace(" ", ""));
					out.write(0);
					out.write(""+i);
					out.write(0);
					out.write(""+MathUtils.remap(0, main.getGui().dragPanel.getSize().width+(o.getSize().width/2), 0, 100, o.getX()));
					out.write(0);
					out.write(""+MathUtils.remap(0, main.getGui().dragPanel.getSize().height+(o.getSize().height/2), 0, 100, o.getY()));
					out.write(0);
					out.write(""+o.getBackground().getRGB());
					out.write(0);
					
					out.flush();
				}
			}
			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(main.getGui(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static ArrayList<ArrayList<DragObject>> compileFromFile(File openFile) {
		try {
			FileReader r = new FileReader(openFile);
			ArrayList<Byte> d = new ArrayList<>();
			
			while (r.ready()) {
				d.add((byte) r.read());
			}
			r.close();
			
			Iterator<Byte> data = d.iterator();
			
			ArrayList<ArrayList<DragObject>> os = new ArrayList<>();
			
			while (data.hasNext()) {
				byte startByte = data.next();
				if (startByte == 13) {
					byte[] text = getnextBytes(data);
					int index = Integer.parseInt(new String(getnextBytes(data)));
					byte[] xV = getnextBytes(data);
					byte[] yV = getnextBytes(data);
					byte[] rgb = getnextBytes(data);
					
					Dimension size = DragObject.getSize(" "+text+" ");
					
					int x = (int) MathUtils.remap(0, 100, 0, main.getGui().dragPanel.getSize().width+(size.width/2), Float.parseFloat(new String(xV)));
					int y = (int) MathUtils.remap(0, 100, 0, main.getGui().dragPanel.getSize().height+(size.height/2), Float.parseFloat(new String(yV)));
					
					Point pos = new Point(x, y);
					
					ArrayList<DragObject> wos = new ArrayList<>();
					try {
						wos = os.get(index);
					} catch (Exception e) {}
					
					DragObject o = new DragObject(new String(text), new Color(Integer.parseInt(new String(rgb))), pos, index);
					wos.add(o);
					
					try {
						os.set(index, wos);
					} catch (Exception e) {
						os.add(wos);
					}
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
