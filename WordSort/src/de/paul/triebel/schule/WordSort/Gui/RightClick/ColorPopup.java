package de.paul.triebel.schule.WordSort.Gui.RightClick;

import java.awt.Color;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.plaf.ColorChooserUI;

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
