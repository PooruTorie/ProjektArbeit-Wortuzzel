package me.paul.WordSorter.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.io.File;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;

import me.paul.WordSorter.main;
import me.paul.WordSorter.popups.KeyBordInput;
import me.paul.WordSorter.popups.Settings;
import me.paul.WordSorter.print.Printer;
import me.paul.api.DragDrop.JElements.JMovePanel;

public class onMenuBarClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Gui.close) {
			System.exit(0);
		}
		if (e.getSource() == Gui.settings) {
			new Settings(Gui.jf);
		}
		if (e.getSource() == Gui.open) {
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
			fc.setToolTipText("Select File");
			fc.setAcceptAllFileFilterUsed(false);
			Action details = fc.getActionMap().get("viewTypeDetails");
			details.actionPerformed(null);
			int response = fc.showOpenDialog(Gui.jf);
			if (response == JFileChooser.APPROVE_OPTION) {
				Gui.readFile(fc.getSelectedFile());
			}
		}
		if (e.getSource() == Gui.n) {
			main.g.getMovepanel().removeAll();
			Gui.jf.repaint();
		}
		if (e.getSource() == Gui.print) {
			new Printer();
		}
		if (e.getSource() == Gui.keybord) {
			new KeyBordInput(Gui.jf);
		}
	}

}