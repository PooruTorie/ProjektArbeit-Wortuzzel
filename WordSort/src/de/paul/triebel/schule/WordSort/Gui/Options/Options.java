package de.paul.triebel.schule.WordSort.Gui.Options;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.paul.triebel.schule.WordSort.main;

public class Options {

	private static JDialog settings;

	public static void open() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		settings = new JDialog(main.getGui());
		
		settings.setTitle((String) main.getLanguageFile().get("options"));
		settings.setSize(screenSize.width/2, screenSize.height/2);
		settings.setLayout(new CardLayout());
        
		settings.add(getMenu("Font"));
        
		settings.setModal(true);
		settings.setVisible(true);
	}

	private static JPanel getMenu(String label) {
		JPanel menu = new JPanel();
		JLabel JLabel = new JLabel();
		final JSlider slider = new JSlider();
		
		slider.setMinimum(20);
		slider.setMaximum(50);
		
		slider.setValue((int) main.getFontSize());
		
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JLabel.setText(label+": "+slider.getValue());
				main.setFontSize(slider.getValue());
			}
		});
		
		JLabel.setText(label+": "+slider.getValue());
		
		menu.add(JLabel);
		menu.add(slider);
		
		return menu;
	}

}
