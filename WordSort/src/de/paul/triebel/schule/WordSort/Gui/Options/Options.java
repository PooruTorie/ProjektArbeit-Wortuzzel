package de.paul.triebel.schule.WordSort.Gui.Options;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Data.LanguageFile;

public class Options {

	private static JDialog settings;

	public static void open() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		settings = new JDialog(main.getGui());
		
		settings.setTitle((String) main.getLanguageFile().get("options"));
		settings.setSize(screenSize.width/2, screenSize.height/2);
		settings.setLayout(new BoxLayout(settings.getContentPane(), BoxLayout.PAGE_AXIS));
        
		JPanel menuFont = new JPanel();
		JLabel FontLabel = new JLabel();
		final JSlider FontSlider = new JSlider();
		
		FontSlider.setMinimum(20);
		FontSlider.setMaximum(50);
		
		FontSlider.setValue((int) main.getFontSize());
		
		FontSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				FontLabel.setText(main.getLanguageFile().get("settingsfont")+": "+FontSlider.getValue());
				main.setFontSize(FontSlider.getValue());
			}
		});
		
		FontLabel.setText(main.getLanguageFile().get("settingsfont")+": "+FontSlider.getValue());
		
		menuFont.add(FontLabel);
		menuFont.add(FontSlider);
		settings.add(menuFont);
		
		JPanel menuLanguage = new JPanel();
		JLabel LanguageLabel = new JLabel();
		LanguageFile[] langs = main.getAllLanguageFiles();
		final JComboBox<LanguageFile> LanguageDropDown = new JComboBox<>(langs);

		int i = 0;
		
		for (LanguageFile l : langs) {
			if (l.getFile().getName().equals(main.getLanguageFile().getFile().getName())) {
				break;
			}
			i++;
		}
		
		LanguageDropDown.setSelectedIndex(i);
		
		LanguageDropDown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.updateLanguage((LanguageFile) LanguageDropDown.getSelectedItem());
			}
		});
		
		LanguageLabel.setText(main.getLanguageFile().get("settingslanguage")+": ");
		
		menuLanguage.add(LanguageLabel);
		menuLanguage.add(LanguageDropDown);
		settings.add(menuLanguage);
        
		settings.setModal(true);
		settings.setVisible(true);
	}

}
