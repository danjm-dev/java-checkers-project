package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import game.GUI;
import game.Game;

public class GameFrame extends JFrame {
	
	private GUI gui;
	
	public GameFrame(String frameName, GUI gui) {
		super(frameName);
		this.gui = gui;
		
		setSize(new Dimension(700, 700));
		setPreferredSize(new Dimension(700, 700));
	}
	
	private Game getGame() {
		return this.gui.game;
	}
}
