package me.paul.WordSorter.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import assets.assets;
import me.paul.WordSorter.main;
import me.paul.WordSorter.listener.JTextInput;
import me.paul.WordSorter.listener.RightClickListener;
import me.paul.api.DragDrop.JElements.JMoveComponent;
import me.paul.api.DragDrop.JElements.JMovePanel;

public class Gui {
	
	public static JFrame jf;
	
	private static JMenu File;
	private static JMenu in;
	private static JMenu view;
	static JMenuItem open;
    static JMenuItem close;
    static JMenuItem settings;
    static JMenuItem n;
    static JMenuItem keybord;
    static JMenuItem print;
    static JMovePanel mp;
    public static BufferedImage icon;
    public static Font f;
	
	public Gui StartGui() {
		jf = new JFrame(main.Language.get("JFrame"));
		try {
			icon = ImageIO.read(assets.getFile("Data/icon.png"));
			jf.setIconImage(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(Toolkit.getDefaultToolkit().getScreenSize().width+16, (int) (Toolkit.getDefaultToolkit().getScreenSize().height+16-(Toolkit.getDefaultToolkit().getScreenSize().height-GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight())));
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		mp = new JMovePanel(Color.LIGHT_GRAY, new Point(0,0), jf.getSize());
		
		open = new JMenuItem(main.Language.get("Open"));
		close = new JMenuItem(main.Language.get("Close"));
		n = new JMenuItem(main.Language.get("New"));
		settings = new JMenuItem(main.Language.get("Settings"));
		keybord = new JMenuItem(main.Language.get("Keyboard"));
		print = new JMenuItem(main.Language.get("Print"));
		
		File = new JMenu(main.Language.get("File"));
		File.add(n);
		n.addActionListener(new onMenuBarClick());
		File.add(print);
		print.addActionListener(new onMenuBarClick());
		File.add(open);
		open.addActionListener(new onMenuBarClick());
		File.add(close);
		close.addActionListener(new onMenuBarClick());
		
		view = new JMenu(main.Language.get("View"));
		view.add(settings);
		settings.addActionListener(new onMenuBarClick());
		
		in = new JMenu(main.Language.get("Input"));
		in.add(keybord);
		keybord.addActionListener(new onMenuBarClick());
		
		JMenuBar bar = new JMenuBar();
		bar.add(File);
		bar.add(in);
		bar.add(view);
		jf.add(bar, BorderLayout.NORTH);
		
		jf.add(mp);
		
		jf.setVisible(true);
		jf.setLayout(null);
		jf.requestFocus();
		
		f = new Font("FONT", jf.getFont().getStyle(), main.Config.getFontSize());
		return this;
	}
	
	public static JMovePanel getMovepanel() {
		return mp;
	}
	
	public static void readFile(File f) {
		try {
			BufferedReader r = new BufferedReader(new FileReader(f));
			while (r.ready()) {
				String line = r.readLine();
				splitLine(line);
			}
			r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		jf.dispose();
	}
	
	public static void splitLine(String line) {
		Color linec = getRandomColor();
		ArrayList<JMoveComponent> comps = new ArrayList<JMoveComponent>();
		for (String st : line.split(" ")) {
			if (st.startsWith("?")) {
				st = st.replace("?", "");
				JTextInput l = new JTextInput(linec, st);
				l.setAutoscrolls(false);
				Rectangle rec = JMoveComponent.getSize(st, f);
				l.setBounds(rec);
				JMoveComponent c = new JMoveComponent(linec, l);
				c.pose(mp.getRandomPos());
				c.setVisible(true);
				c.addMouseListener(new RightClickListener());
				c.getIniComponent().setFont(f);
				mp.add(c);
				comps.add(c);
				SwingUtilities.updateComponentTreeUI(jf);
			} else {
				JLabel l = new JLabel(st);
				Rectangle rec = JMoveComponent.getSize(st, f);
				l.setBounds(rec);
				JMoveComponent c = new JMoveComponent(linec, l);
				c.pose(mp.getRandomPos());
				c.setVisible(true);
				c.addMouseListener(new RightClickListener());
				c.getIniComponent().setFont(f);
				mp.add(c);
				comps.add(c);
				SwingUtilities.updateComponentTreeUI(jf);
			}
		}
		for (JMoveComponent c : comps) {
			main.words.put(c, comps);
		}
	}

	private static Color getRandomColor() {
		Random r = new Random();
		return new Color(r.nextInt(25)*10, (r.nextInt(10)+15)*10, (r.nextInt(10)+12)*10);
	}
	
}
