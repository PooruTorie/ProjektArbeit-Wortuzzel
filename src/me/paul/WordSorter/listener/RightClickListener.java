package me.paul.WordSorter.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.paul.WordSorter.gui.Gui;
import me.paul.api.DragDrop.JElements.JMoveComponent;

public class RightClickListener implements MouseListener {
	
	@Override
	public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }
	
	@Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        RightClickMenu menu = new RightClickMenu(Gui.jf , (JMoveComponent) e.getSource());
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
}
