import org.junit.Test;
import static org.junit.Assert.*;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
    @Test
    public void testKnightCanAttack() {
    	Player knight = new Knight(0, 2);
	    Player ranger = new Ranger(0, 1);
		assertTrue(knight.canAttack(ranger));
    }
    
    @Test
    public void testKnightAttack() {
        Player knight = new Knight(0, 2);
	    Player ranger = new Ranger(0, 1);
        knight.attack(ranger);
        assertEquals(0, ranger.getHealth());
    }
    
    @Test
    public void testRangerCanAttack() {
        Player ranger = new Ranger(0, 3);
        Player bomber = new Bomber(0, 2);
        assertTrue(ranger.canAttack(bomber));
    }
    
    @Test
    public void testRangerAttack() {
        Player ranger = new Ranger(0, 3);
        Player bomber = new Bomber(0, 2);
        ranger.attack(bomber);
        assertEquals(75, bomber.getHealth());
    }
    
    @Test
    public void testBomberCanAttack() {
        Player ranger = new Ranger(0, 3);
        Player bomber = new Bomber(0, 2);
        assertTrue(bomber.canAttack(ranger));
    }
    
    @Test
    public void testBomberAttack() {
        Player ranger = new Ranger(0, 3);
        Player bomber = new Bomber(0, 2);
        bomber.attack(ranger);
        assertEquals(45, ranger.getHealth());
    }
    
    @Test
    public void testChangeImageInValid() {
       Player knight = new Knight(0, 2);
       knight.changeImage(500);
       Player ranger = new Ranger(0, 3);
       ranger.changeImage(500);
       Player bomber = new Bomber(0, 4);
       bomber.changeImage(500);
       assertEquals("files/knight0.png", knight.getImgFile());
       assertEquals("files/ranger0.png", ranger.getImgFile());
       assertEquals("files/bomber0.png", bomber.getImgFile());
    }
    
    @Test
    public void testChangeImageValid() {
        Player knight = new Knight(0, 2);
        knight.changeImage(1);
        Player ranger = new Ranger(0, 3);
        ranger.changeImage(2);
        Player bomber = new Bomber(0, 4);
        bomber.changeImage(3);
        assertEquals("files/knight1.png", knight.getImgFile());
        assertEquals("files/ranger2.png", ranger.getImgFile());
        assertEquals("files/bomber3.png", bomber.getImgFile());
    }
    
    @Test
    public void movePlayerOutsideOfBoard() {
        Player p1 = new Ranger(0, 3);
        Player p2 = new Bomber(0, 2);
        int[][] tiles = new int[5][5];
        TileBoard game = new TileBoard(p1, p2, tiles);
        game.movePlayer1To(0, 6);
        assertEquals(0, p1.getPx());
        assertEquals(3, p1.getPy());
    }
    
    @Test 
    public void movePlayerOnTopOfOtherPlayer() {
        Player p1 = new Ranger(0, 3);
        Player p2 = new Bomber(0, 2);
        int[][] tiles = new int[5][5];
        TileBoard game = new TileBoard(p1, p2, tiles);
        game.movePlayer1To(0, 2);
        assertEquals(0, p1.getPx());
        assertEquals(3, p1.getPy());
    }
    
    @Test 
    public void movePlayerValid() {
        Player p1 = new Ranger(0, 3);
        Player p2 = new Bomber(0, 2);
        int[][] tiles = new int[5][5];
        TileBoard game = new TileBoard(p1, p2, tiles);
        game.movePlayer1To(1, 4);
        assertEquals(1, p1.getPx());
        assertEquals(4, p1.getPy());
    }
}