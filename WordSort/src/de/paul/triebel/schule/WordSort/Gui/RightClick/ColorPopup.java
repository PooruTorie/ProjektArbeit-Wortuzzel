package de.paul.triebel.schule.WordSort.Gui.RightClick;

import java.awt.Color;
import javax.swing.JColorChooser;
import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragObject;

public class ColorPopup {
	
	public ColorPopup(DragObject o) {
		Color c = JColorChooser.showDialog(o, "", o.getBackground());
		
		for (DragObject d : main.getGui().dragPanel.objects.get(o.index)) {
			d.setBackground(c);
		}
	}
}
