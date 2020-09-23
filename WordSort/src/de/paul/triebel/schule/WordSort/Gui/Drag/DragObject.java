package de.paul.triebel.schule.WordSort.Gui.Drag;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import de.paul.triebel.schule.WordSort.Gui.CustomFont;

public class DragObject extends JLabel implements MouseListener {
	
	public DragObject(String text, Color color, Point position) {
		super(text);
		setBackground(color);
		
		setFont(CustomFont.get(50));
		
		setBounds(new Rectangle(position, getSize(text)));
	}

	private Dimension getSize(String text) {
		FontRenderContext frc = new FontRenderContext(getFont().getTransform(), true, true);
		Dimension size = getFont().getStringBounds(text, frc).getBounds().getSize();
		return new Dimension(size.width+5, size.height);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(-5, 0, getWidth()+5, getHeight(), 20, 20);
		
		super.paintComponent(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
