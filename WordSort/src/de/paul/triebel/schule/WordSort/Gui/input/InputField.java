package de.paul.triebel.schule.WordSort.Gui.input;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.CustomFont;
import de.paul.triebel.schule.WordSort.Gui.Gui;

public class InputField extends JPanel {
	
	public InputField(Gui gui) {
		super();
		setSize(100, 20);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		setBackground(new Color(204, 248, 239));
		setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		
		JLabel label = new JLabel((String) main.getLanguageFile().get("input")+"   ");
		
		label.setFont(CustomFont.get(50));
		
		JTextField input = new JTextField();
		
		input.setFont(CustomFont.get(50));
		
		add(label);
		add(input);
	}
	
}
