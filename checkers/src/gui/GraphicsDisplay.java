package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import game.GUI;
import game.Game;

public class GraphicsDisplay extends JPanel implements MouseListener {
	
	private GUI gui;
	
	public GraphicsDisplay(GUI gui) {
		this.gui = gui;
		this.setPreferredSize(new Dimension(500, 500));
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		this.addMouseListener(this);
	}
	
	
	
	public void redraw() {
	    repaint();
	  }

	  /**
	   * Paint component of GraphicsWindow, allows window to be painted on and is
	   * refreshed by a repaint call on this class.
	   */
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    this.setBackground(Color.WHITE);

	      this.getGame().draw(g, getWidth(), getHeight());
	    

	  }
	  
		private Game getGame() {
			return this.gui.game;
		}



		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {
			this.gui.game.setSelected(e.getX(), e.getY(), this.getWidth(), this.getHeight());
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			this.gui.game.moveSelected(e.getX(), e.getY(), this.getWidth(), this.getHeight());
		}


}
