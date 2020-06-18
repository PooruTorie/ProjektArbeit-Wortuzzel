package me.paul.WordSorter.print;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.PageAttributes.OrientationRequestedType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import org.apache.logging.log4j.core.Layout;

import me.paul.WordSorter.main;

public class Printer {
	
	PrinterJob Printer = PrinterJob.getPrinterJob();
	
	public Printer() {
		try {
			PageFormat pf = Printer.pageDialog(Printer.defaultPage());
			if (Printer.printDialog()) {
				double i;
				if (pf.getOrientation() == 1) {
					i = pf.getPaper().getWidth();
				} else {
					i = pf.getPaper().getHeight();
				}
				Printer.setPrintable(new A4Paper(main.g.getMovepanel(), Printer.getCopies(), i), pf);
				Printer.print();
			}
		} catch (PrinterException e1) {
		}
	}
	
}
