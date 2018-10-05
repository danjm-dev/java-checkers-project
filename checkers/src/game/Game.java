package game;

import java.awt.Color;
import java.awt.Graphics;

import objects.checker;

public class Game {

	public enum CheckerTeam {
		BOTTEM, TOP;
	}

	public enum CheckerDirection {
		UP, DOWN, BOTH;
	}

	GUI gui;

	checker[][] checkers;
	checker selectedChecker;
	int selectedCheckerI;
	int selectedCheckerJ;

	public Game() {
		gui = new GUI(this);
		reset();

	}

	private void reset() {
		checkers = new checker[8][8];

		checkers[1][0] = new checker(CheckerTeam.TOP);
		checkers[3][0] = new checker(CheckerTeam.TOP);
		checkers[5][0] = new checker(CheckerTeam.TOP);
		checkers[7][0] = new checker(CheckerTeam.TOP);

		checkers[0][1] = new checker(CheckerTeam.TOP);
		checkers[2][1] = new checker(CheckerTeam.TOP);
		checkers[4][1] = new checker(CheckerTeam.TOP);
		checkers[6][1] = new checker(CheckerTeam.TOP);

		checkers[1][2] = new checker(CheckerTeam.TOP);
		checkers[3][2] = new checker(CheckerTeam.TOP);
		checkers[5][2] = new checker(CheckerTeam.TOP);
		checkers[7][2] = new checker(CheckerTeam.TOP);

		/////////////////////////////////////////////////

		checkers[0][5] = new checker(CheckerTeam.BOTTEM);
		checkers[2][5] = new checker(CheckerTeam.BOTTEM);
		checkers[4][5] = new checker(CheckerTeam.BOTTEM);
		checkers[6][5] = new checker(CheckerTeam.BOTTEM);

		checkers[1][6] = new checker(CheckerTeam.BOTTEM);
		checkers[3][6] = new checker(CheckerTeam.BOTTEM);
		checkers[5][6] = new checker(CheckerTeam.BOTTEM);
		checkers[7][6] = new checker(CheckerTeam.BOTTEM);

		checkers[0][7] = new checker(CheckerTeam.BOTTEM);
		checkers[2][7] = new checker(CheckerTeam.BOTTEM);
		checkers[4][7] = new checker(CheckerTeam.BOTTEM);
		checkers[6][7] = new checker(CheckerTeam.BOTTEM);
	}

	public void draw(Graphics g, int width, int height) {
		int size;
		if (width < height) {
			size = width;
		} else {
			size = height;
		}
		int tileSize = (size / 8);
		int ovalSize = tileSize - (tileSize / 6);
		int shiftSize = (tileSize - ovalSize) / 2;

		int index = 1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// draw tiles

				g.setColor(index % 2 == 0 ? Color.DARK_GRAY : Color.LIGHT_GRAY);
				g.fillRect((size / 8) * i, (size / 8) * j, size / 8, size / 8);
				g.setColor(Color.BLACK);
				g.drawRect((size / 8) * i, (size / 8) * j, size / 8, size / 8);

				// draw checkers
				if (checkers[i][j] != null) {

					g.setColor(this.checkers[i][j].getColor());
					g.fillOval((tileSize * i) + shiftSize, (tileSize * j) + shiftSize, ovalSize, ovalSize);
					g.setColor(Color.BLACK);
					g.drawOval((tileSize * i) + shiftSize, (tileSize * j) + shiftSize, ovalSize, ovalSize);

				}
				index++;
			}
			index++;
		}

	}

	public void setSelected(int mouseWidth, int mouseHeight, int windowWidth, int windowHeight) {
		int widthTileSize = windowWidth / 8;
		int heightTileSize = windowHeight / 8;

		int i = mouseWidth / widthTileSize;
		int j = mouseHeight / heightTileSize;

		if (checkers[i][j] != null) {
			this.selectedChecker = this.checkers[i][j];
			this.selectedCheckerI = i;
			this.selectedCheckerJ = j;
		}
		this.gui.display.redraw();
	}

	public void moveSelected(int mouseWidth, int mouseHeight, int windowWidth, int windowHeight) {
		int widthTileSize = windowWidth / 8;
		int heightTileSize = windowHeight / 8;

		int i = mouseWidth / widthTileSize;
		int j = mouseHeight / heightTileSize;

		if (canMove(this.selectedCheckerI, this.selectedCheckerJ, i, j)) {

			// check for opponent tile in jump move
			if (j == this.selectedCheckerJ + 2 && i == this.selectedCheckerI + 2) {
				this.checkers[this.selectedCheckerI + 1][this.selectedCheckerJ + 1] = null;

			}
			if (j == this.selectedCheckerJ + 2 && i == this.selectedCheckerI - 2) {
				this.checkers[this.selectedCheckerI - 1][this.selectedCheckerJ + 1] = null;

			}
			if (j == this.selectedCheckerJ - 2 && i == this.selectedCheckerI - 2) {
				this.checkers[this.selectedCheckerI - 1][this.selectedCheckerJ - 1] = null;

			}
			if (j == this.selectedCheckerJ - 2 && i == this.selectedCheckerI + 2) {
				this.checkers[this.selectedCheckerI + 1][this.selectedCheckerJ - 1] = null;

			}

			this.checkers[this.selectedCheckerI][this.selectedCheckerJ] = null;
			this.checkers[i][j] = this.selectedChecker;
			this.selectedChecker = null;

			// set king if on king row
			if (!this.checkers[i][j].isKing()) {
				if (this.checkers[i][j].getTeam() == CheckerTeam.TOP) {
					if (j == 7) {
						this.checkers[i][j].makeKing();
					}
				} else {
					if (j == 0) {
						this.checkers[i][j].makeKing();
					}
				}
			}

		}
		this.gui.display.redraw();
	}

	public boolean canMove(int sourceI, int sourceJ, int destI, int destJ) {
		CheckerTeam team = this.checkers[sourceI][sourceJ].getTeam();

		// check if desination tile already has checker
		if (this.checkers[destI][destJ] != null) {
			return false;
		}

		// conditions depending on direction
		if (this.checkers[sourceI][sourceJ].getDirection() == CheckerDirection.DOWN) {
			// check if checker is moving in correct Direction
			if (destJ <= sourceJ)
				return false;
			// check possible moves
			if (!((destJ == sourceJ + 1 && destI == sourceI + 1) || (destJ == sourceJ + 1 && destI == sourceI - 1)
					|| (destJ == sourceJ + 2 && destI == sourceI + 2)
					|| (destJ == sourceJ + 2 && destI == sourceI - 2))) {
				return false;
			}
			// check for opponent tile in jump move
			if (destJ == sourceJ + 2 && destI == sourceI + 2) {
				if (this.checkers[sourceI + 1][sourceJ + 1] == null
						|| this.checkers[sourceI + 1][sourceJ + 1].getTeam() == team)
					return false;
			}
			if (destJ == sourceJ + 2 && destI == sourceI - 2) {
				if (this.checkers[sourceI - 1][sourceJ + 1] == null
						|| this.checkers[sourceI - 1][sourceJ + 1].getTeam() == team)
					return false;
			}

		} else if (this.checkers[sourceI][sourceJ].getDirection() == CheckerDirection.UP) {
			// check if checker is moving in correct Direction
			if (destJ >= sourceJ)
				return false;

			// check possible moves
			if (!((destJ == sourceJ - 1 && destI == sourceI - 1) || (destJ == sourceJ - 1 && destI == sourceI + 1)
					|| (destJ == sourceJ - 2 && destI == sourceI - 2)
					|| (destJ == sourceJ - 2 && destI == sourceI + 2))) {
				return false;
			}
			// check for opponent tile in jump move
			if (destJ == sourceJ - 2 && destI == sourceI - 2) {
				if (this.checkers[sourceI - 1][sourceJ - 1] == null
						|| this.checkers[sourceI - 1][sourceJ - 1].getTeam() == team)
					return false;
			}
			if (destJ == sourceJ - 2 && destI == sourceI + 2) {
				if (this.checkers[sourceI + 1][sourceJ - 1] == null
						|| this.checkers[sourceI + 1][sourceJ - 1].getTeam() == team)
					return false;
			}

		} else {
			// check possible moves
			if (!((destJ == sourceJ - 1 && destI == sourceI - 1) || (destJ == sourceJ - 1 && destI == sourceI + 1)
					|| (destJ == sourceJ - 2 && destI == sourceI - 2) || (destJ == sourceJ - 2 && destI == sourceI + 2)
					|| (destJ == sourceJ + 1 && destI == sourceI + 1) || (destJ == sourceJ + 1 && destI == sourceI - 1)
					|| (destJ == sourceJ + 2 && destI == sourceI + 2)
					|| (destJ == sourceJ + 2 && destI == sourceI - 2))) {
				return false;
			}

			// check for opponent tile in jump move
			if (destJ == sourceJ + 2 && destI == sourceI + 2) {
				if (this.checkers[sourceI + 1][sourceJ + 1] == null
						|| this.checkers[sourceI + 1][sourceJ + 1].getTeam() == team)
					return false;
			}
			if (destJ == sourceJ + 2 && destI == sourceI - 2) {
				if (this.checkers[sourceI - 1][sourceJ + 1] == null
						|| this.checkers[sourceI - 1][sourceJ + 1].getTeam() == team)
					return false;
			}
			if (destJ == sourceJ - 2 && destI == sourceI - 2) {
				if (this.checkers[sourceI - 1][sourceJ - 1] == null
						|| this.checkers[sourceI - 1][sourceJ - 1].getTeam() == team)
					return false;
			}
			if (destJ == sourceJ - 2 && destI == sourceI + 2) {
				if (this.checkers[sourceI + 1][sourceJ - 1] == null
						|| this.checkers[sourceI + 1][sourceJ - 1].getTeam() == team)
					return false;
			}

		}

		// check for four different possable moves

		return true;

	}

	public static void main(String[] args) {
		Game game = new Game();
	}
}
