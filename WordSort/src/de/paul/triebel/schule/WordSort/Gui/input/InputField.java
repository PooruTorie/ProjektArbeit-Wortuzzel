package de.paul.triebel.schule.WordSort.Gui.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.paul.triebel.schule.WordSort.main;

public class InputField extends JPanel {
	
	public InputField() {
		super();
		setSize(100, 20);
		setLayout(new BorderLayout());
		
		setBackground(new Color(204, 248, 239));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JLabel label = new JLabel((String) main.getLanguageFile().get("input"));
		
		add(label, BorderLayout.WEST);
	}
	
}
