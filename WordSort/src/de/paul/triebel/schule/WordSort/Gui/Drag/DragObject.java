package de.paul.triebel.schule.WordSort.Gui.Drag;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import javax.swing.JLabel;

import de.paul.triebel.schule.WordSort.Gui.CustomFont;

public class DragObject extends JLabel implements MouseListener, MouseMotionListener {
	
	public static Font font = CustomFont.get(50);
	
	public DragObject(String text, Color color, Point position) {
		super(text);
		setBackground(color);
		
		setFont(font);
		
		setBounds(new Rectangle(position, getSize(text)));
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public static Dimension getSize(String text) {
		FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);
		Dimension size = font.getStringBounds(text, frc).getBounds().getSize();
		return new Dimension(size.width+5, size.height);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(-5, 0, getWidth()+5, getHeight(), 20, 20);
		
		super.paintComponent(g);
	}
	
	private Point lastPos;
	private Point clickScreenOffset;

	@Override
    public void mousePressed(MouseEvent e) {
      clickScreenOffset = e.getLocationOnScreen();

      lastPos = getLocation();
    }
	
	@Override
    public void mouseDragged(MouseEvent e) {
      int deltaX = e.getXOnScreen() - clickScreenOffset.x;
      int deltaY = e.getYOnScreen() - clickScreenOffset.y;

      setLocation(lastPos.x + deltaX, lastPos.y + deltaY);
    }

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
}
