package de.paul.triebel.schule.WordSort.Gui.RightClick;

import java.awt.Color;

import javax.swing.JColorChooser;

import de.paul.triebel.schule.WordSort.Gui.Drag.DragObject;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragPanel;

public class ColorPopup {
	
	public ColorPopup(DragObject o) {
		Color c = JColorChooser.showDialog(o, "", o.getBackground());
		
		for (DragObject d : DragPanel.objects.get(o.index)) {
			d.setBackground(c);
		}
	}
}
