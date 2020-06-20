package me.paul.WordSorter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import me.paul.WordSorter.main;
import me.paul.WordSorter.gui.Gui;
import me.paul.WordSorter.popups.ColorPopUp;
import me.paul.api.DragDrop.JElements.JMoveComponent;

class RightClickMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	
	JMenuItem color;
	JCheckBoxMenuItem clip;
	JMenuItem delet;
    
    public RightClickMenu(JFrame jf, JMoveComponent c) {
        color = new JMenuItem(main.Language.get("Color"));
        color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ColorPopUp(jf, c);
			}
		});
        add(color);
        clip = new JCheckBoxMenuItem(main.Language.get("Lock"), c.isLock());
        clip.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					c.setLock(true);
				} else {
					c.setLock(false);
				}
			}
		});
        add(clip);
        delet = new JMenuItem(main.Language.get("Delet"));
        delet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.words.remove(c);
				Gui.getMovepanel().remove(c);
				SwingUtilities.updateComponentTreeUI(jf);
			}
		});
        add(delet);
    }
}
