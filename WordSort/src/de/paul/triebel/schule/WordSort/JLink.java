package de.paul.triebel.schule.WordSort;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import javax.swing.JLabel;

public class JLink extends JLabel implements MouseListener {
	
	private URI link;

	public JLink(URI url) {
		super(url.toString());
		link = url;
		
		setForeground(Color.BLUE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		Font font = getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

        setFont(font.deriveFont(attributes));
		
		addMouseListener(this);
	}
	
	public JLink(String label, URI url) {
		super(label);
		link = url;
		
		setForeground(Color.BLUE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		Font font = getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

        setFont(font.deriveFont(attributes));
		
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			Desktop.getDesktop().browse(link);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
