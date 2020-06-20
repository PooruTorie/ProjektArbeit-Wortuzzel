package me.paul.WordSorter.print;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.nio.BufferUnderflowException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import me.paul.api.DragDrop.JElements.JMoveComponent;
import me.paul.api.DragDrop.JElements.JMovePanel;

public class A4Paper implements Printable {
	
	private JMovePanel mp;
	private int m;
	private double width;
	
	public A4Paper(JMovePanel d, int m, double width) {
		mp = d;
		this.m = m;
		this.width = width;
	}

	@Override
	public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex < m) {
			Graphics2D g2d = (Graphics2D)g;
		    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			BufferedImage img = new BufferedImage(mp.getWidth(), mp.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics imgg = img.getGraphics();
			if (mp.getComponentCount() > 0) {
				for (Component c : mp.getComponents()) {
					Rectangle rec = ((JMoveComponent) c).getBounds();
					imgg.setColor(c.getBackground());
					imgg.fillRect((int) rec.getX(), (int) rec.getY(), (int) rec.getWidth(), (int) rec.getHeight());
					imgg.setColor(c.getForeground());
					Component in = ((JMoveComponent) c).getIniComponent();
					if (in instanceof JLabel) {
						JLabel l = (JLabel) in;
						if (l.getText() != "") {
							imgg.setFont(l.getFont());
							imgg.drawString(l.getText(), (int) ((int) rec.getX()+getSize(l.getText(), l.getFont()).getWidth()/3.8), (int) ((int) rec.getY()+rec.getHeight()*0.7));
						}
						if (l.getIcon() != null) {
							imgg.drawImage(iconToImage(l.getIcon()), (int) rec.getX(), (int) ((int) rec.getY()), null);
						}
					} else if (in instanceof JTextComponent) {
						JTextComponent l = (JTextComponent) in;
						if (l.getText() != "") {
							imgg.setFont(l.getFont());
							imgg.drawString(l.getText(), (int) ((int) rec.getX()+getSize(l.getText(), l.getFont()).getWidth()/3.8), (int) ((int) rec.getY()+rec.getHeight()*0.7));
						}
					}
				}
			}
			Dimension d = scaleTo(new Dimension(img.getWidth(), img.getHeight()), width-pageFormat.getImageableX()*2);
			g2d.drawImage(img, 0, 0, (int) d.getWidth(), (int) d.getHeight(), null);					
			return Printable.PAGE_EXISTS;
		}
		return Printable.NO_SUCH_PAGE;
	}

	private Dimension scaleTo(Dimension d, double width) {
		double p = (d.getHeight()*100)/d.getWidth();
		int newHeight = (int) ((width*p)/100);
		d.setSize(width, newHeight);
		return d;
	}
	
	public static Dimension getSize(String st, Font f) {
		int length = st.length()+1;
		return new Dimension((int) ((int) 30+((f.getSize()/1.2)*length)), (int) (1.5*f.getSize()));
	}
	
	static Image iconToImage(Icon icon) {
		   if (icon instanceof ImageIcon) {
		      return ((ImageIcon)icon).getImage();
		   } 
		   else {
		      int w = icon.getIconWidth();
		      int h = icon.getIconHeight();
		      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		      GraphicsDevice gd = ge.getDefaultScreenDevice();
		      GraphicsConfiguration gc = gd.getDefaultConfiguration();
		      BufferedImage image = gc.createCompatibleImage(w, h);
		      Graphics2D g = image.createGraphics();
		      icon.paintIcon(null, g, 0, 0);
		      g.dispose();
		      return image;
		   }
		 }

}
