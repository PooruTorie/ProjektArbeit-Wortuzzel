package me.paul.WordSorter.popups;

import java.awt.Dimension;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import me.paul.WordSorter.main;
import me.paul.WordSorter.gui.Gui;

public class KeyBordInput {
	
	public KeyBordInput(JFrame owner) {
		JDialog jd = new JDialog(owner, main.Language.get("Keyboard"));
		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jd.setSize(new Dimension(300, 70));
		jd.setResizable(false);
		
		JEditorPane in = new JEditorPane();
		in.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					in.setText("");
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					jd.dispose();
				} else {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String inSt = in.getText();
						if (!inSt.equals("")) {
							Gui.splitLine(inSt);
						}
					}
				}
			}
		});
		jd.add(in);
		
		jd.setModal(true);
		jd.setVisible(true);
		jd.toFront();
	}
	
}
