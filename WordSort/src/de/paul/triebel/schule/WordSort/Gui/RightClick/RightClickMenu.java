package de.paul.triebel.schule.WordSort.Gui.RightClick;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragObject;

public class RightClickMenu extends JPopupMenu {
	
	public RightClickMenu(DragObject o) {
		super();
		
		JMenuItem i1 = new JMenuItem("123");
		JMenuItem i2 = new JMenuItem((String) main.getLanguageFile().get("color"));
		JMenuItem i3 = new JMenuItem((String) main.getLanguageFile().get("remove"));
		
		i2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ColorPopup(o);
				close();
			}
		});
		i3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				o.removeLine();
				close();
			}
		});
		
		add(i2);
		addSeparator();
		add(i3);
	}

	private void close() {
		setVisible(false);
		main.getGui().dragPanel.repaint();
	}
}
