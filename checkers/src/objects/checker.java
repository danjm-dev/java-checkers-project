package objects;

import java.awt.Color;

import game.Game.CheckerDirection;
import game.Game.CheckerTeam;

public class checker {

	private CheckerTeam team;
	private CheckerDirection direction;
	private Color color;
	private boolean king;

	public checker(CheckerTeam team) {
		this.team = team;
		king = false;

		if (this.team == CheckerTeam.TOP) {
			direction = CheckerDirection.DOWN;
			this.color = Color.ORANGE;
		} else {
			direction = CheckerDirection.UP;
			this.color = Color.CYAN;
		}
	}

	public CheckerTeam getTeam() {
		return this.team;
	}

	public void switchDirection() {
		if (this.direction == CheckerDirection.DOWN) {
			this.direction = CheckerDirection.UP;
		} else {
			this.direction = CheckerDirection.DOWN;
		}
	}

	public CheckerDirection getDirection() {
		return this.direction;
	}

	public void makeKing() {
		this.king = true;
		if (this.team == CheckerTeam.BOTTEM) {
			this.color = Color.BLUE;
		} else {
			this.color = Color.RED;
		}
		this.direction = CheckerDirection.BOTH;
	}
	
	public boolean isKing() {
		return king;
	}
	
	public Color getColor() {
		return this.color;
	}
}
