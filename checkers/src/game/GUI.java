package game;

import javax.swing.SwingUtilities;

import gui.GameFrame;
import gui.GraphicsDisplay;

public class GUI {
	public Game game;

	public GameFrame frame;
	public GraphicsDisplay display;

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

		this.frame.add(display);
		this.frame.setVisible(true);
		
	}
}
