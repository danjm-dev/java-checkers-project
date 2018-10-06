package game;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;

import gui.ControlPanel;
import gui.GameFrame;
import gui.GraphicsDisplay;

public class GUI {
	public Game game;

	public GameFrame frame;
	public GraphicsDisplay display;
	public ControlPanel controls;

	public GUI(Game game) {
		this.game = game;

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				initialise();
			}

		});

	}

	private void initialise() {
		this.frame = new GameFrame("Checkers", this);
		this.display = new GraphicsDisplay(this);
		this.controls = new ControlPanel(this);

		this.frame.c.add(controls, BorderLayout.NORTH);
		this.frame.c.add(display, BorderLayout.CENTER);
		this.frame.setVisible(true);
		
	}
}
