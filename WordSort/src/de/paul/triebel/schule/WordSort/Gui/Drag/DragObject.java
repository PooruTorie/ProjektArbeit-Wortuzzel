package de.paul.triebel.schule.WordSort.Gui.Drag;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;

import javax.swing.JLabel;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.CustomFont;
import de.paul.triebel.schule.WordSort.Gui.RightClick.RightClickMenu;

public class DragObject extends JLabel implements MouseListener, MouseMotionListener {
	
	public static Font font = CustomFont.get(50);
	
	private Color borderColor;
	public int index;
	
	public DragObject(String text, Color color, Point position, int index) {
		super(" "+text+" ");
		this.index = index;
		
		setBackground(color);
		setBorderColor(Color.BLACK);
		
		setFont(font);
		
		setBounds(new Rectangle(position, getSize(" "+text+" ")));
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setComponentPopupMenu(new RightClickMenu(this));
	}

	public static Dimension getSize(String text) {
		FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);
		Dimension size = font.getStringBounds(text, frc).getBounds().getSize();
		return new Dimension(size.width+5, size.height);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getBackground());
		g2.fillRoundRect(+5, +10, getWidth()-10, getHeight()-12, 10, 10);

		g2.setColor(getBorderColor());
		g2.setStroke(new BasicStroke(4));
		g2.drawRoundRect(+5, +10, getWidth()-10, getHeight()-12, 10, 10);
		
		super.paintComponent(g);
	}
	
	private Point lastPos;
	private Point clickScreenOffset;

	@Override
    public void mousePressed(MouseEvent e) {
	    clickScreenOffset = e.getLocationOnScreen();
	
	    lastPos = getLocation();
	    
	    main.getGui().dragPanel.setComponentZOrder(this, 0);
	    repaint();
    }
	
	@Override
    public void mouseDragged(MouseEvent e) {
		int deltaX = e.getXOnScreen() - clickScreenOffset.x;
		int deltaY = e.getYOnScreen() - clickScreenOffset.y;
		
		setLocation(lastPos.x + deltaX, lastPos.y + deltaY);
		repaint();
    }
	
	@Override
	public void mouseEntered(MouseEvent e) {
		setBorderColor(Color.GRAY);
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBorderColor(Color.BLACK);
		repaint();
	}
	
	public void removeLine() {
		ArrayList<DragObject> os = main.getGui().dragPanel.objects.get(index);
		main.getGui().dragPanel.objects.remove(index);
		for (DragObject o : os) {
			main.getGui().dragPanel.remove(o);
		}
	}
	
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		repaint();
	}
	
	public Color getBorderColor() {
		return borderColor;
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
}
