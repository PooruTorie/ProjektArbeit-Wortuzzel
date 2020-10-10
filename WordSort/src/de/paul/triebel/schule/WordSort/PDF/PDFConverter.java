package de.paul.triebel.schule.WordSort.PDF;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;

import de.paul.triebel.schule.WordSort.main;

public class PDFConverter {
	
	public static void toPDF(File f) {
		int[] options = openExportDialog();
		
		if (options[0] == 0) {
			PDDocument doc = new PDDocument();
			PDPage page = new PDPage(PDRectangle.A4);
			
			if (options[1] == 0) {
				page.setRotation(180);
			} else {
				page.setRotation(90);
			}
	        doc.addPage(page);
	        
	        try {
				PDPageContentStream cont = new PDPageContentStream(doc, page);
	            
				Dimension imgSize = main.getGui().dragPanel.getSize();
	            BufferedImage img = new BufferedImage(imgSize.width, imgSize.height, BufferedImage.TYPE_INT_ARGB);
	            
	            Graphics g = img.getGraphics();
	            main.getGui().dragPanel.paintComponents(g);
	            
	            img = rotate(img, page.getRotation()*-1);
	            img = resize(img, new Dimension((int) page.getArtBox().getWidth(), (int) page.getArtBox().getHeight()), options[1] == 1);
	            
	            cont.drawImage(JPEGFactory.createFromImage(doc, img), 0, 0);
	            
	            cont.close();
	            
	            doc.save(f);
	            
	            doc.close();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(main.getGui(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private static int[] openExportDialog() {
		int[] options = new int[2];
		
		JPanel exportPanel = new JPanel();
		exportPanel.add(new JLabel("<html><body>"+main.getLanguageFile().get("exportline1")+"<br><br>"+main.getLanguageFile().get("exportline2")+": </body></html>"));
		String[] oriantations = new String[] {(String) main.getLanguageFile().get("exportoriantation1"), (String) main.getLanguageFile().get("exportoriantation2")};
		JComboBox<?> oriantation = new JComboBox<String>(oriantations);
		exportPanel.add(oriantation);
		
		options[0] = JOptionPane.showConfirmDialog(main.getGui(), exportPanel, (String) main.getLanguageFile().get("exportline1"), JOptionPane.OK_CANCEL_OPTION);
		
		options[1] = oriantation.getSelectedIndex();
		
		return options;
	}

	private static BufferedImage resize(BufferedImage img, Dimension newSize, boolean revert) {  
	    int w = img.getWidth();
	    int h = img.getHeight();
	    if (revert) {
	    	newSize.width = (int) (img.getWidth()*((float) newSize.height/h));
	    } else {
	    	newSize.height = (int) (img.getHeight()*((float) newSize.width/w));
		}
	    BufferedImage dimg = new BufferedImage(newSize.width, newSize.height, img.getType());  
	    Graphics2D g = dimg.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	    g.drawImage(img, 0, 0, newSize.width, newSize.height, 0, 0, w, h, null);  
	    g.dispose();  
	    return dimg;  
	}
	
	private static BufferedImage rotate(BufferedImage img, int angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }
}
