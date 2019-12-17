public class TileBoard {
	private Player player1;
	private Player player2; 
	private int[][] tiles;
	
	// Constructor
	public TileBoard(Player p1, Player p2, int[][] tiles) {
		this.player1 = p1;
		this.player2 = p2;
		this.tiles = tiles;
	}
	
	public Player getPlayer1() {
		return this.player1;
	}
	
	public Player getPlayer2() {
		return this.player2;
	}
	
	public int[][] getTiles() {
		return this.tiles;
	}
	
	public void setPlayer1(Player p1) {
		this.player1 = p1;
	}
	
	public void setPlayer2(Player p2) {
		this.player2 = p2;
	}
	
	public void setTiles(int[][] tiles) {
		this.tiles = tiles;
	}
	
	// Ex 0, 1, 2 for Knight with most wins, Ranger with next, and Bomber 
	// with least.
	public int[] getWinOrder() {
		int[] unsorted = ScoreObtainer.obtainUnsortedScores();
		int[] sorted = ScoreObtainer.sortArrayDescending(unsorted);
		
		int[] winOrder = new int[3];
		
		if (unsorted[0] == sorted[0]) {
			if (unsorted[1] == sorted[1]) {
				winOrder[0] = 0;
				winOrder[1] = 1;
				winOrder[2] = 2;
			}
			else {
				winOrder[0] = 0;
				winOrder[1] = 2;
				winOrder[2] = 1;
			}
		}
		else {
			if (unsorted[1] == sorted[0]) {
				if (unsorted[0] == sorted[1]) {
					winOrder[0] = 1;
					winOrder[1] = 0;
					winOrder[2] = 2;
				}
				else {
					winOrder[0] = 1;
					winOrder[1] = 2;
					winOrder[2] = 0;
				}
			}
			else {
				if (unsorted[2] == sorted[0]) {
					if (unsorted[1] == sorted[1]) {
						winOrder[0] = 2;
						winOrder[1] = 1;
						winOrder[2] = 0;
					}
					else {
						winOrder[0] = 2;
						winOrder[1] = 0;
						winOrder[2] = 1;
					}
				}
			}
		}
		return winOrder;
	}
	
	
	public void movePlayer1To(int newXPos, int newYPos) {
		// If new position is outside of tileboard, do nothing
		if (newXPos < 0 || newYPos < 0 || 
				newXPos >= this.getTiles().length || 
				newYPos >= this.getTiles()[0].length) {
			return;
		}
		// If new position is at same position as player2, 
		// do nothing
		if (newXPos == this.getPlayer2().getPx() && 
			newYPos == this.getPlayer2().getPy()) {
			return;
		}
		this.getPlayer1().setPx(newXPos);
		this.getPlayer1().setPy(newYPos);
	}
	
	public void movePlayer2To(int newXPos, int newYPos) {
		// If new position is outside of tileboard, do nothing
		if (newXPos < 0 || newYPos < 0 || 
				newXPos >= this.getTiles().length || 
				newYPos >= this.getTiles()[0].length) {
			return;
		}
		// If new position is at same position as player1, 
		// do nothing
		if (newXPos == this.getPlayer1().getPx() && 
			newYPos == this.getPlayer1().getPy()) {
			return;
		}
		this.getPlayer2().setPx(newXPos);
		this.getPlayer2().setPy(newYPos);
	}	
}