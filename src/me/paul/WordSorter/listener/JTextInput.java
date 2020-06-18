package me.paul.WordSorter.listener;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import me.paul.WordSorter.gui.Gui;
import me.paul.api.DragDrop.JElements.JMoveComponent;

public class JTextInput extends JTextField {
	
	JTextInput instance = this;
	
	public JTextInput(Color linec, String st) {
		super(st.length());
		setDocument(new JLimitDocument(st.length()));
		setBackground(linec);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (st.equalsIgnoreCase(getText())) {
					disable();
					setVisible(false);
					
					JLabel l = new JLabel(st);
					Rectangle rec = JMoveComponent.getSize(st, Gui.f);
					l.setBounds(rec);
					Gui.jf.add(l);
				} else {
					setForeground(Color.RED);
				}
			}
		});
	}
}
