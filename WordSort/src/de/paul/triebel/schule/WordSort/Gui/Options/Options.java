package de.paul.triebel.schule.WordSort.Gui.Options;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.Gui;

public class Options {

	public static void open() {
		System.out.println("Options");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		JDialog meinJDialog = new JDialog(main.getGui());
		
        meinJDialog.setTitle((String) main.getLanguageFile().get("options"));
        meinJDialog.setSize(screenSize.width/2, screenSize.height/2);
        
        meinJDialog.setModal(true);
        meinJDialog.setVisible(true);
	}

}
