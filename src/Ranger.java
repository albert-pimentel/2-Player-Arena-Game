import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ranger extends Player {
    public String img_file = "files/ranger0.png";
    public static final int WIDTH = 120;
    public static final int HEIGHT = 100;
    public static final int HEALTH = 125;
    public static final int ATTACK = 75;
    private BufferedImage img;
    
    public Ranger(int xPos, int yPos) {
        super(xPos, yPos, WIDTH, HEIGHT, HEALTH, ATTACK, "files/ranger0.png");

        try {
            this.img = ImageIO.read(new File(img_file));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    public void changeImage(int imgSuffix) {
    	String originalImg = this.getImgFile() + "";
    	this.setImgFile("files/ranger" + imgSuffix + ".png");
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
        g.drawString(this.getHealth() + " / 125", 225 + 120 * this.getPx(), 100 * this.getPy());
    }
    
    @Override
    public boolean canAttack(Player p) {
    	int facing = this.getDirectionFacing();
		if (facing == 0) {
			if (p.getPx() == this.getPx() && 
					p.getPy() < this.getPy()) {
				return true;
			}
		}
		if (facing == 1) {
			if (p.getPy() == this.getPy() && 
					p.getPx() > this.getPx()) {
						return true;
					}
		}
		if (facing == 2) {
			if (p.getPx() == this.getPx() && 
					p.getPy() > this.getPy()) {
				return true;
			}
		}
		if (facing == 3) {
			if (p.getPy() == this.getPy() && 
					p.getPx() < this.getPx()) {
				return true;
			}
		}
		return false;
    }
}