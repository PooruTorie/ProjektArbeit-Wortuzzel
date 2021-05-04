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
import java.net.URI;
import java.net.URISyntaxException;

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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import de.paul.triebel.schule.WordSort.JLink;
import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.assets.assets;

public class StartScreen extends JDialog {

	public StartScreen(Gui gui) {
		super(gui, "Start Screen");
		
		setFont(CustomFont.get(50));
		setLayout(new GridLayout(4, 1));
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel l = new JLabel("   "+(String) main.getLanguageFile().get("tutorial")+"   ");
		l.setFont(CustomFont.get(main.getFontSize()*4));
		textPanel.add(l);
		add(textPanel);
		
		try {
			JPanel linkPanel = new JPanel();
			linkPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLink link = new JLink("Haben sie Fragen oder Fehler gefunden?", new URI("https://github.com/NutellaJunge/ProjektArbeit-Wortuzzel/issues"));
			link.setFont(CustomFont.get(60));
			linkPanel.add(link);
			add(linkPanel);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		JPanel changeLogPanel = new JPanel();
		changeLogPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		String list = "";
		for (String log : main.log) {
			list+="<li>"+log+"</li>";
		}
		JLabel title = new JLabel("<html>Neuerungen:<br><ul>"+list +"</ul></html>");
		title.setFont(CustomFont.get(30));
		changeLogPanel.add(title);
		add(changeLogPanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JCheckBox show = new JCheckBox("Show on Start");
		show.setFont(CustomFont.get(30));
		show.setSelected((boolean) main.getConfig().get("show_start_screen"));
		show.setBackground(Color.RED);
		
		JButton next = new JButton("Next >>>");
		next.setFont(CustomFont.get(30));
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
}
