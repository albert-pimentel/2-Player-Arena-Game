import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Knight extends Player {
    public String img_file = "files/knight0.png";
    public static final int WIDTH = 120;
    public static final int HEIGHT = 100;
    public static final int HEALTH = 200;
    public static final int ATTACK = 175;
    private BufferedImage img;
    
    public Knight(int xPos, int yPos) {
        super(xPos, yPos, WIDTH, HEIGHT, HEALTH, ATTACK, "files/knight0.png");
        try {
            img = ImageIO.read(new File(img_file));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    @Override
    public void changeImage(int imgSuffix) {
    	String originalImg = this.getImgFile() + "";
    	this.setImgFile("files/knight" + imgSuffix + ".png");
        try {
                this.img = ImageIO.read(new File(this.getImgFile()));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
            this.setImgFile(originalImg);
        }
    }
    
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, 200 + 120 * this.getPx(), 100 * this.getPy(), this.getWidth(),
        			this.getHeight(), null);
        g.drawString(this.getHealth() + " / 200", 225 + 120 * this.getPx(), 100 * this.getPy());
    }
    
    @Override
    public boolean canAttack(Player p) {
		int facing = this.getDirectionFacing();
		if (facing == 0) {
			if (this.getPy() == 0) { 
				return false;
			}
			if (p.getPx() == this.getPx() &&  
				p.getPy() == this.getPy() - 1) {
				return true;
			}
		}
		if (facing == 1) {
			if (this.getPx() == 4) {
				return false;
			}
			if (p.getPx() == this.getPx() + 1 &&
					p.getPy() == this.getPy()) {
				return true;
			}
		}
		if (facing == 2) {
			if (this.getPy() == 4) {
				return false;
			}
			if (p.getPx() == this.getPx() && 
					p.getPy() == this.getPy() + 1) {
				return true;
			}
		}
		if (facing == 3) {
			if (this.getPx() == 0) {
				return false;
			}
			if (p.getPx() == this.getPx() - 1 && 
					p.getPy() == this.getPy()) {
				return true;
			}
		}
		return false;
    }
}