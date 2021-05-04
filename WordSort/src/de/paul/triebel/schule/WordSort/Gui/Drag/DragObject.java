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
import javax.xml.ws.soap.AddressingFeature;

import de.paul.triebel.schule.WordSort.main;
import de.paul.triebel.schule.WordSort.Gui.RightClick.RightClickMenu;
import de.paul.triebel.schule.WordSort.Utils.MathUtils;

public class DragObject extends JLabel implements MouseListener, MouseMotionListener {
	
	public static Font font = main.getDragObjectFont();
	
	private Color borderColor;
	public int index;
	
	private boolean selected;
	
	public DragObject(String text, Color color, Point position, int index) {
		super(text);
		this.index = index;
		
		setBackground(color);
		setBorderColor(Color.BLACK);
		
		setFont(font);
		
		setBounds(new Rectangle(position, getSize(" "+text+" ")));
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setComponentPopupMenu(new RightClickMenu(this));
	}
	
	@Override
	public void setBackground(Color bg) {
		int c = (bg.getRed()+bg.getGreen()+bg.getBlue())/3;
		if (c > 160) {
			setForeground(Color.BLACK);
		} else {
			setForeground(Color.WHITE);
		}
		super.setBackground(bg);
	}

	public static Dimension getSize(String text) {
		FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);
		Dimension size = font.getStringBounds(text, frc).getBounds().getSize();
		return new Dimension(size.width+5, (int) (size.height*(MathUtils.remap(20, 50, 1.5f, 1.4f, font.getSize()))));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		int round = (int) MathUtils.remap(20, 50, 2, 16, font.getSize());
		int strokeWidth = (int) MathUtils.remap(20, 50, 1, 4, font.getSize());
		
		g2.setColor(getBackground());
		g2.fillRoundRect(0+strokeWidth, 0+strokeWidth, getWidth()-(strokeWidth*2), getHeight()-(strokeWidth*2), round, round);
		
		g2.setColor(getBorderColor());
		g2.setStroke(new BasicStroke(strokeWidth));
		g2.drawRoundRect(0+strokeWidth, 0+strokeWidth, getWidth()-(strokeWidth*2), getHeight()-(strokeWidth*2), round, round);
		
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
		
		Point prePos = getLocation();
		Point pos = new Point(lastPos.x + deltaX, lastPos.y + deltaY);
		
		boolean[] wh = ((DragPanel) getParent()).isInPanel(pos, getSize());
		
		if (wh[0] || wh[1]) {
			if (wh[0]) {
				pos.setLocation(prePos.x, pos.y);
			}
	
			if (wh[1]) {
				pos.setLocation(pos.x, prePos.y);
			}
		} else if (wh[0] && wh[1]) {
			pos = prePos;
		}
		
		setLocation(pos);
		repaint();
    }
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (selected) {
			setBorderColor(new Color(100, 100, 200));
		} else {
			setBorderColor(Color.GRAY);
		}
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (selected) {
			setBorderColor(new Color(50, 50, 200));
		} else {
			setBorderColor(Color.BLACK);
		}
		repaint();
	}
	
	public void removeLine() {
		ArrayList<DragObject> os = DragPanel.words.get(index);
		DragPanel.words.remove(index);
		for (DragObject o : os) {
			main.getGui().dragPanel.remove(o);
		}
		for (ArrayList<DragObject> list : DragPanel.words) {
			for (DragObject o : list) {
				if (o.index > index) {
					o.index--;
				}
			}
		}
	}
	
	public void selected(boolean b) {
		selected = b;
		
		if (b) {
			setBorderColor(new Color(50, 50, 200));
		} else {
			setBorderColor(Color.BLACK);
		}
	}

	public void connectSelected() {
		ArrayList<DragObject> word = DragPanel.words.get(index);
		ArrayList<DragObject> selected = main.getGui().dragPanel.selected;
		
		String text = getText().substring(1, getText().length()-1);
		
		for (DragObject o : selected) {
			if (word.contains(o)) {
				if (o != this) {
					text += o.getText().substring(0, o.getText().length()-1);
					
					System.out.println(selected);
					
					main.getGui().dragPanel.remove(o);
					word.remove(o);
				}
			}
		}
		
		setText(text);
		
		DragPanel.words.set(index, word);
		main.getGui().dragPanel.clearSelected();
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
	
	@Override
	public String toString() {
		return getText().substring(1, getText().length()-2)+"_"+index;
	}
	
	public void update() {
		setFont(main.getDragObjectFont());
		setSize(getSize(getText()));
	}
	
	@Override
	public void setText(String text) {
		super.setText(" "+text+" ");
		setBounds(new Rectangle(getLocation(), getSize(" "+text+" ")));
	}
	
	public static void updateFont() {
		font = main.getDragObjectFont();
	}
}
