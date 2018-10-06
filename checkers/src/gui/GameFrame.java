package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import game.GUI;
import game.Game;

public class GameFrame extends JFrame {
	
	private GUI gui;
	public Container c;
	
	public GameFrame(String frameName, GUI gui) {
		super(frameName);
		this.gui = gui;
		
		setSize(new Dimension(700, 700));
		setPreferredSize(new Dimension(700, 700));
		setMinimumSize(new Dimension(200,200));
		
		
		// add components to content pane
	    this.c = getContentPane();

	    // set layout
	    //setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
	    setLayout(new BorderLayout());

	}
	
	private Game getGame() {
		return this.gui.game;
	}
}
