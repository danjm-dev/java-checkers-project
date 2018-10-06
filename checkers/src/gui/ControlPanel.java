package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.GUI;

public class ControlPanel extends JPanel implements ActionListener {
	
	private GUI gui;
	
	public JLabel currentPlayer;
	public JButton button;

	public ControlPanel(GUI gui) {
		this.gui = gui;
		
		setLayout(new BorderLayout());
		
		currentPlayer = new JLabel("Turn: Player 1 (Blue)");
		button = new JButton("End Turn");
		
		add(currentPlayer, BorderLayout.CENTER);
		add(button, BorderLayout.EAST);
		
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) {
			gui.game.nextPlayer();
		}
	}
}
