package de.paul.triebel.schule.WordSort.Gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import assets.assets;
import de.paul.triebel.schule.WordSort.main;

public class StartScreen extends JDialog implements MouseListener {
	
	private JButton play;
	
	Icon icon;
	Icon iconSelected;

	public StartScreen(Gui gui) {
		super(gui, "Start Screen");
		
		setFont(CustomFont.get(50));
		setLayout(new GridLayout(0, 1));
		
		JPanel textPanel = new JPanel();
		JLabel l = new JLabel((String) main.getLanguageFile().get("tutorial"));
		l.setFont(CustomFont.get(main.getFontSize()*4));
		textPanel.add(l);
		add(textPanel);
		
		try {
			icon = new ImageIcon(ImageIO.read(assets.getFile("textures/video.png")));
			iconSelected = new ImageIcon(ImageIO.read(assets.getFile("textures/videoSelected.png")));
			
			play = new JButton(icon);
			
			play.setBackground(new Color(0, 0, 0, 0));
			play.setOpaque(false);
			play.setContentAreaFilled(false);
			play.setBorderPainted(false);
			play.setFocusPainted(false);
			
			play.addMouseListener(this);
			play.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Desktop.getDesktop().open(assets.getFile("video.mp4"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			add(play);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JCheckBox show = new JCheckBox("Show on Start");
		show.setSelected((boolean) main.getConfig().get("show_start_screen"));
		show.setBackground(Color.RED);
		
		JButton next = new JButton("Next >>>");
		
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.getConfig().set("show_start_screen", show.isSelected());
				dispose();
			}
		});
		
		panel.add(show);
		panel.add(next);
		add(panel);
		
		setSize(gui.getSize());
		
		setVisible(true);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		play.setIcon(iconSelected);
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		play.setIcon(icon);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	
}
