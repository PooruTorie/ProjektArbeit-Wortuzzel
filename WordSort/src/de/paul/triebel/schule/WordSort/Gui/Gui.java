package de.paul.triebel.schule.WordSort.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragPanel;
import de.paul.triebel.schule.WordSort.Gui.input.InputField;

public class Gui extends JFrame {
	
	private final static String title = "Wortuzzel";
	
	public DragPanel dragPanel;
	public InputField inputField;
	
	public Gui() {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setFont(CustomFont.get(20));
		setBackground(new Color(248, 241, 239));
		
		setSize(getGraphicsConfiguration().getBounds().getSize());
		
		setLayout(new BorderLayout());
		
		GuiMenu.update(this);
		
		inputField = new InputField(this);
		add(inputField, BorderLayout.NORTH);
		dragPanel = new DragPanel(this);
		add(dragPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		requestFocus();
	}
}
