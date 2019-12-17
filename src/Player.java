
import java.awt.Graphics;

public abstract class Player {
    /*
    /* Current position of the player 
     * px - Position on int[]
     * py - Position on int[0][]
     */
    private int px; 
    private int py;
    
    /* Size of object, in pixels. */
    private int width;
    private int height;

    private int health;
    private int attack;
    
    private String img_file;
    
    // 0 for north, 1 for east, 2 for south, 3 for west
    // All players face north by default
    private int directionFacing;
    
    /**
     * Constructor
     */
    public Player(int px, int py, int width, int height, int health, int attack, String imgfile) {
        this.px = px;
        this.py = py;
        this.width  = width;
        this.height = height;
        this.health = health;
        this.attack = attack;
        this.directionFacing = 0;
        this.img_file = imgfile;
    }

    /*** GETTERS **********************************************************************************/
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getHealth() {
    	return this.health;
    }
    
    public int getAttack() {
    	return this.attack;
    }
    
    public int getDirectionFacing() {
    	return this.directionFacing;
    }
    
    public String getImgFile() {
    	return this.img_file;
    }

    /*** SETTERS **********************************************************************************/
    public void setPx(int px) {
        this.px = px;
    }
    
    public void setPy(int py) {
        this.py = py;
    }
    
    public void setWidth(int width) {
    	this.width = width;
    }
    
    public void setHeight(int height) {
    	this.height = height;
    }
    
    public void setHealth(int health) {
    	this.health = health;
    }
    
    public void setAttack(int attack) {
    	this.attack = attack;
    }
    
    public void setImgFile(String imgFile) {
    	this.img_file = imgFile;
    }
    
    public void setDirectionFacing(int direction) {
    	if (direction < 0 || direction > 3) {
    		return;
    	}
    	this.directionFacing = direction;
    }
    
    public void attack(Player p) {
    	if (this.canAttack(p)) {
    		if (p.getHealth() - this.getAttack() < 0) {
    			p.setHealth(0);
    		}
    		else {
    			p.setHealth(p.getHealth() - this.getAttack());
    		}
    	}
    }
    
    /**
     * Default draw method that provides how the object should be drawn in the GUI. This method does
     * not draw anything. Subclass should override this method based on how their object should
     * appear.
     * 
     * @param g The <code>Graphics</code> context used for drawing the object. Remember graphics
     * contexts that we used in OCaml, it gives the context in which the object should be drawn (a
     * canvas, a frame, etc.)
     */
    public abstract void draw(Graphics g);
    
    /**
     * 
     * Checks if the player in question can attack another player
     * 
     * @param p The Player to be attacked.
     */
    public abstract boolean canAttack(Player p);
    
    /**
     * 
     * Changes the image to a directional image, where 0 represents 
     * north, 1 east, 2 south, 3 west.
     * 
     * @param imgSuffix, the directional to be changed to
     */
    public abstract void changeImage(int imgSuffix);
    
}