package me.paul.WordSorter.popups;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import me.paul.WordSorter.main;
import me.paul.WordSorter.gui.Gui;
import me.paul.api.DragDrop.JElements.JMoveComponent;

public class Settings extends JDialog implements ActionListener, ChangeListener {
	
	private static final long serialVersionUID = 1L;
	private JSlider FontSize; 
	private JComboBox<String> Language;
	private HashMap<String, File> lang = new HashMap<>();
	
	public Settings(JFrame jf) {
		super(jf, main.Language.get("Settings"));
		JMenuBar settings = new JMenuBar();
		settings.setLayout(new GridLayout(0,1));
		FontSize = new JSlider();
		FontSize.setMajorTickSpacing(10);
		FontSize.setMinorTickSpacing(1);
		FontSize.setPaintTicks(true);
		FontSize.setPaintLabels(true);
		FontSize.addChangeListener(this);
		FontSize.setValue(Gui.f.getSize());
		settings.add(Menupanel(FontSize, main.Language.get("Font_Size")));
		
		Language = new JComboBox<>();
		for (String item : getLanguages()) {
			Language.addItem(item);
		}
		Language.setSelectedItem(main.Language.getName());
		Language.addActionListener(this);
		settings.add(Menupanel(Language, main.Language.get("Language")));
		
		add(settings);
		setSize(600, 590);
		setResizable(false);
		setVisible(true);
	}
	
	private List<String> getLanguages() {
		ArrayList<String> out = new ArrayList<>();
		for (File f : new File("Data/Lang").listFiles()) {
			if (f.getName().contains("lang")) {
				lang.put(new me.paul.WordSorter.Language(f.getName()).getName(), f);
				out.add(new me.paul.WordSorter.Language(f.getName()).getName());
			}
		}
		return out;
	}

	private JPanel Menupanel(Component c, String title) {
		JPanel p = new JPanel();
		p.add(new JLabel(title+": "));
		c.setSize(c.getWidth()*2, c.getHeight()*2);
		p.add(c);
		return p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Language) {
			main.Language.load(lang.get(Language.getSelectedItem()).getName());
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == FontSize) {
			Gui.f = new Font("FONT", Gui.f.getStyle(), FontSize.getValue());
			main.Config.setFontSize(FontSize.getValue());
			if (!main.words.isEmpty()) {
				for (ArrayList<JMoveComponent> a : main.words.values()) {
					for (JMoveComponent c : a) {
						c.getIniComponent().setFont(Gui.f);
						c.setBounds(JMoveComponent.getSize(((JLabel) c.getIniComponent()).getText(), Gui.f));
					}
				}
			}
		}
	}
}
