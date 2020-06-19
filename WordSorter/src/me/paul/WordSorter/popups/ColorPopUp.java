package me.paul.WordSorter.popups;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.Icon;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.paul.WordSorter.main;
import me.paul.WordSorter.gui.Gui;
import me.paul.api.DragDrop.JElements.JMoveComponent;

public class ColorPopUp extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public ColorPopUp(JFrame jf, JMoveComponent c) {
		JDialog jd = new JDialog(jf, main.Language.get("Color"));
		jd.setLocation(c.getParent().getParent().getMousePosition());
		jd.setSize(new Dimension(600, 250));
		JColorChooser cc = new JColorChooser(c.getBackground());
		cc.setPreviewPanel(new JPanel());
		cc.getSelectionModel().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				for (JMoveComponent c : main.words.get(c)) {
					c.setColor(cc.getColor());
					c.setBackground(cc.getColor());
				}
			}
		});
		jd.add(cc);
		jd.setVisible(true);
	}
	
}
