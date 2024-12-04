package puzzle;

import java.util.Arrays;

public class Puzzle implements PuzzleInterface {
	// gesuchter Zielzustand
	public static Puzzle goal = new Puzzle(new int[][] {
			{1, 2, 3},
			{8, 0, 4},
			{7, 6, 5}
	});

	public int[][] state;
	public String path;

	// initialisiere Puzzle mit gegebenen Werten
	public Puzzle (int a, int b, int c, int d, int e, int f, int g, int h, int i) {
		this.state = new int[][]{
			{a, b, c},
			{d, e, f},
			{g, h, i}
		};
		this.path = "";
	}

	// initialisiere Puzzle mit 2D-Array
	public Puzzle (int[][] state) {
		this.state = state;
		this.path = "";
	}

	public Puzzle(int[][] state, String path) {
		// Create a deep copy of the state array to ensure newPuzzle doesn't share the same reference
		this.state = new int[3][3];
		for (int i = 0; i < 3; i++) {
			this.state[i] = Arrays.copyOf(state[i], 3);
		}
		this.path = path;
	}
	
	// Zaehlung der falsch platzierten Kacheln 1 bis 8
	@Override
	public int countWrongTiles() {
		int count = 0;
		for(int r=0; r<3; r++){
			for(int c=0; c<3; c++){
				if (state[r][c] != goal.state[r][c] && state[r][c] != 0) count++;
			}
		}
		return count;
	}
	
	// Berechnung der Summe aller (vertikalen und horizontalen) Distanzen der Kacheln 1 bis 8 zur jeweiligen Zielposition
	@Override
	public int manhattanDist() {
		int dist = 0;

		for(int r=0; r<3; r++) {
			for(int c=0; c<3; c++) {
				int val = state[r][c];
				// skip empty cell
				if(val == 0) continue;

				int[] goalPos = new int[2];
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if(goal.state[i][j] == val) {
							goalPos[0] = i;
							goalPos[1] = j;
						}
					}
				}

				dist += Math.abs(r - goalPos[0]) + Math.abs(c - goalPos[1]);
			}
		}

		return dist;
	}

	public int[] getEmptyCellPosition(){
		int[] emptyPos = new int[2];
		for(int r=0; r<3; r++) {
			for(int c=0; c<3; c++) {
				if(state[r][c] == 0) {
					emptyPos[0] = r;
					emptyPos[1] = c;
					return emptyPos;
				}
			}
		}
		return emptyPos;
	}

	@Override
	public boolean canMoveLeft() { // check if the cell with 0 can move left
		int[] emptyPos = getEmptyCellPosition();
		if(emptyPos[1] == 0) return false;
		return true;
	}
	
	@Override
	public boolean canMoveRight() {
		int[] emptyPos = getEmptyCellPosition();
		if(emptyPos[1] == 2) return false;
		return true;
	}
	
	@Override
	public boolean canMoveUp() {
		int[] emptyPos = getEmptyCellPosition();
		if(emptyPos[0] == 0) return false;
		return true;
	}
	
	@Override
	public boolean canMoveDown() {
		int[] emptyPos = getEmptyCellPosition();
		if(emptyPos[0] == 2) return false;
		return true;
	}
	
	@Override
	public Puzzle moveLeft() {
		// get empty cell position
		int[] emptyPos = getEmptyCellPosition();

		// create a new puzzle
		Puzzle newPuzzle = new Puzzle(state, path + "L");

		// swap the empty cell with the cell on the left
		int row = emptyPos[0];
		int col = emptyPos[1];
		newPuzzle.state[row][col] = newPuzzle.state[row][col-1];
		newPuzzle.state[row][col-1] = 0;

		return newPuzzle;
	}
	
	@Override
	public Puzzle moveRight() {
		// get empty cell position
		int[] emptyPos = getEmptyCellPosition();

		// create a new puzzle
		Puzzle newPuzzle = new Puzzle(state, path + "R");

		// swap the empty cell with the cell on the right
		int row = emptyPos[0];
		int col = emptyPos[1];
		newPuzzle.state[row][col] = newPuzzle.state[row][col+1];
		newPuzzle.state[row][col+1] = 0;

		return newPuzzle;
	}
	
	@Override
	public Puzzle moveUp() {
		// get empty cell position
		int[] emptyPos = getEmptyCellPosition();

		Puzzle newPuzzle = new Puzzle(state, path + "U");

		// swap the empty cell with the cell on top
		int row = emptyPos[0];
		int col = emptyPos[1];
		newPuzzle.state[row][col] = newPuzzle.state[row-1][col];
		newPuzzle.state[row-1][col] = 0;

		return newPuzzle;
	}
	
	@Override
	public Puzzle moveDown() {
		// get empty cell position
		int[] emptyPos = getEmptyCellPosition();

		Puzzle newPuzzle = new Puzzle(state, path + "D");

		// swap the empty cell with the cell below
		int row = emptyPos[0];
		int col = emptyPos[1];
		newPuzzle.state[row][col] = newPuzzle.state[row+1][col];
		newPuzzle.state[row+1][col] = 0;

		return newPuzzle;
	}

	@Override
	public boolean equals(Puzzle p) {
		for(int r=0; r<3; r++) {
			for(int c=0; c<3; c++) {
				if(state[r][c] != p.state[r][c]) return false;
			}
		}
		return true;
	}

	// Ausgabe des Zustands als String
	@Override
	public String toString() {
		String str = "\n";
		for(int r=0; r<3; r++) {
			str += "[";
			for(int c=0; c<3; c++) {
				str += state[r][c];
				if(c<2) str += ", ";
			}
			str += "]\n";
		}
		return str;
	}
}
