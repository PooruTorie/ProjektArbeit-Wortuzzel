package de.paul.triebel.schule.WordSort.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragPanel;
import de.paul.triebel.schule.WordSort.Gui.Input.InputField;
import de.paul.triebel.schule.WordSort.assets.Assets;

public class Gui extends JFrame implements ComponentListener {
	
	private final static String title = "Wortuzzel";
	
	public DragPanel dragPanel;
	public InputField inputField;

	private JPanel version;
	
	public Gui() {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		try {
			BufferedImage icon = ImageIO.read(Assets.getFile("textures/icon.png"));
			setIconImage(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setFont(CustomFont.get(20));
		setBackground(new Color(248, 241, 239));
		
		
		setSize(getGraphicsConfiguration().getBounds().getSize());
		
		setLayout(new BorderLayout());
		
		GuiMenu.update(this);
		
		inputField = new InputField(this);
		add(inputField, BorderLayout.NORTH);
		dragPanel = new DragPanel(this);
		add(dragPanel, BorderLayout.CENTER);
		
		version = new JPanel();
		version.setLayout(new BorderLayout());
		JLabel v = new JLabel("   Version: "+main.VERSION);
		v.setFont(CustomFont.get(30));
		version.add(v , BorderLayout.LINE_START);
		JLabel c = new JLabel("Creator: Paul Triebel   ");
		c.setFont(CustomFont.get(30));
		version.add(c , BorderLayout.LINE_END);
		version.setBackground(inputField.getBackground());
		add(version, BorderLayout.PAGE_END);
		
		setupStartFrame();
		
		setVisible(true);
		
		addComponentListener(this);
		
		requestFocus();
	}

	public Gui(DragPanel dragPanel) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		try {
			BufferedImage icon = ImageIO.read(Assets.getFile("textures/icon.png"));
			setIconImage(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setFont(CustomFont.get(20));
		setBackground(new Color(248, 241, 239));
		
		setSize(getGraphicsConfiguration().getBounds().getSize());
		
		setLayout(new BorderLayout());
		
		GuiMenu.update(this);
		
		inputField = new InputField(this);
		add(inputField, BorderLayout.NORTH);
		this.dragPanel = dragPanel;
		add(dragPanel, BorderLayout.CENTER);
		
		version = new JPanel();
		version.setLayout(new BorderLayout());
		JLabel v = new JLabel("   Version: "+main.VERSION);
		v.setFont(CustomFont.get(30));
		version.add(v , BorderLayout.LINE_START);
		JLabel c = new JLabel("Creator: Paul Triebel   ");
		c.setFont(CustomFont.get(30));
		version.add(c , BorderLayout.LINE_END);
		version.setBackground(inputField.getBackground());
		add(version, BorderLayout.PAGE_END);
		
		setVisible(true);
		
		setupStartFrame();
		
		addComponentListener(this);
		
		requestFocus();
	}

	private void setupStartFrame() {
		if ((boolean) main.getConfig().get("show_start_screen")) {
			new StartScreen(this);
		}
	}

	public DragPanel close() {
		setVisible(false);
		dispose();
		return dragPanel;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		dragPanel.resizeComponents();
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}
}
