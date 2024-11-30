package puzzle;

public interface PuzzleInterface {

		public int countWrongTiles();
		
		public int manhattanDist();

		public boolean canMoveLeft();
		
		public boolean canMoveRight();
		
		public boolean canMoveUp();
		
		public boolean canMoveDown();
		
		public Puzzle moveLeft();
		
		public Puzzle moveRight();
		
		public Puzzle moveUp();
		
		public Puzzle moveDown();

		public boolean equals(Puzzle p);

}
