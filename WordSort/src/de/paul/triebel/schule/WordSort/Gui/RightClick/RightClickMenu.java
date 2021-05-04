package de.paul.triebel.schule.WordSort.Gui.RightClick;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragObject;

public class RightClickMenu extends JPopupMenu {
	
	private JMenuItem i1, i2, i3;

	public RightClickMenu(DragObject o) {
		super();
		
		i1 = new JMenuItem((String) main.getLanguageFile().get("color"));
		i2 = new JMenuItem((String) main.getLanguageFile().get("remove"));
		i3 = new JMenuItem((String) main.getLanguageFile().get("connect"));
		
		i1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ColorPopup(o);
				close();
			}
		});
		i2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				o.removeLine();
				close();
			}
		});
		i3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				o.connectSelected();
				close();
			}
		});
	}
	
	@Override
	public void show(Component var1, int var2, int var3) {
		removeAll();
		add(i1);
		if (!main.getGui().dragPanel.selected.isEmpty()) {
			add(i3);
		}
		addSeparator();
		add(i2);
		super.show(var1, var2, var3);
	}

	private void close() {
		setVisible(false);
		main.getGui().dragPanel.repaint();
	}
}
