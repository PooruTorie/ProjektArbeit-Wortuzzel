package de.paul.triebel.schule.WordSort.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Drag.DragPanel;
import de.paul.triebel.schule.WordSort.Gui.Input.InputField;
import de.paul.triebel.schule.WordSort.assets.assets;

public class Gui extends JFrame implements ComponentListener {
	
	private final static String title = "Wortuzzel";
	
	public DragPanel dragPanel;
	public InputField inputField;
	
	public Gui() {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		try {
			BufferedImage icon = ImageIO.read(assets.getFile("textures/icon.png"));
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
			BufferedImage icon = ImageIO.read(assets.getFile("textures/icon.png"));
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
