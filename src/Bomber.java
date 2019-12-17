import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bomber extends Player {
    public String img_file = "files/bomber0.png";
    public static final int WIDTH = 120;
    public static final int HEIGHT = 100;
    public static final int HEALTH = 150;
    public static final int ATTACK = 80;
    private BufferedImage img;
    
    public Bomber(int xPos, int yPos) {
        super(xPos, yPos, WIDTH, HEIGHT, HEALTH, ATTACK, "files/bomber0.png");
        
        try {
            this.img = ImageIO.read(new File(img_file));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    public void changeImage(int imgSuffix) {
    	String originalImg = this.getImgFile() + "";
    	this.setImgFile("files/bomber" + imgSuffix + ".png");
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
        g.drawString(this.getHealth() + " / 150", 225 + 120 * this.getPx(), 100 * this.getPy());
    }
    
    @Override
    public boolean canAttack(Player p) {
		if (p.getPx() <= this.getPx() + 1 && 
				p.getPx() >= this.getPx() - 1 && 
				p.getPy() <= this.getPy() + 1 &&
				p.getPy() >= this.getPy() - 1) {
			return true;
		}
		return false;
    }
}