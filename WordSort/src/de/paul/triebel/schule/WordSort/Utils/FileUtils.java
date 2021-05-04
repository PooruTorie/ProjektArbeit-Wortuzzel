package de.paul.triebel.schule.WordSort.Utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
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
			DataOutputStream out = new DataOutputStream(new FileOutputStream(saveFile));
			
			for (int i = 0; i < objects.size(); i++) {
				for (DragObject o : objects.get(i)) {
					out.writeUTF(o.getText().substring(1, o.getText().length()-1));
					out.writeInt(i);
					out.writeFloat(MathUtils.remap(0, main.getGui().dragPanel.getSize().width+(o.getSize().width/2), 0, 100, o.getX()));
					out.writeFloat(MathUtils.remap(0, main.getGui().dragPanel.getSize().height+(o.getSize().height/2), 0, 100, o.getY()));
					out.writeInt(o.getBackground().getRGB());
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
			DataInputStream r = new DataInputStream(new FileInputStream(openFile));
			ArrayList<ArrayList<DragObject>> os = new ArrayList<>();
			
			while (r.available() > 0) {
				String text = r.readUTF();
				int index = r.readInt();
				float xV = r.readFloat();
				float yV = r.readFloat();
				int rgb = r.readInt();
				
				Dimension size = DragObject.getSize(" "+text+" ");
				
				int x = (int) MathUtils.remap(0, 100, 0, main.getGui().dragPanel.getSize().width+(size.width/2), xV);
				int y = (int) MathUtils.remap(0, 100, 0, main.getGui().dragPanel.getSize().height+(size.height/2), yV);
				
				Point pos = new Point(x, y);
				
				ArrayList<DragObject> wos = new ArrayList<>();
				try {
					wos = os.get(index);
				} catch (Exception e) {}
				
				DragObject o = new DragObject(new String(text), new Color(rgb), pos, index);
				wos.add(o);
				
				try {
					os.set(index, wos);
				} catch (Exception e) {
					os.add(wos);
				}
			}
			r.close();
			
			return os;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(main.getGui(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
}
