	
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	
/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameController extends JPanel {
	
	private TileBoard gameBoard;
    private Player player1;
    private Player player2;
    
    public boolean playing = false; 
    private JLabel status; // Current status text, i.e. "Running..."
    
    public static final int COURT_WIDTH = 815;
    public static final int COURT_HEIGHT = 540;
    
    public static final int INTERVAL = 35;
    
    public GameController(JLabel status) {
    	
        setBorder(BorderFactory.createLineBorder(Color.RED));
        
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!
        
        
        setFocusable(true);
        
        KeyListener listenForMovement = (new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    gameBoard.movePlayer2To(player2.getPx() - 1, player2.getPy());
                    gameBoard.getPlayer2().setDirectionFacing(3);
                    gameBoard.getPlayer2().changeImage(3);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                	gameBoard.movePlayer2To(player2.getPx() + 1, player2.getPy());
                	gameBoard.getPlayer2().setDirectionFacing(1);
                	gameBoard.getPlayer2().changeImage(1);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                	gameBoard.movePlayer2To(player2.getPx(), player2.getPy() + 1);
                	gameBoard.getPlayer2().setDirectionFacing(2);
                	gameBoard.getPlayer2().changeImage(2);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                	gameBoard.movePlayer2To(player2.getPx(), player2.getPy() - 1);
                	gameBoard.getPlayer2().setDirectionFacing(0);
                	gameBoard.getPlayer2().changeImage(0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_W) {
                	gameBoard.movePlayer1To(player1.getPx(), player1.getPy() - 1);
                	gameBoard.getPlayer1().setDirectionFacing(0);
                	gameBoard.getPlayer1().changeImage(0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                	gameBoard.movePlayer1To(player1.getPx(), player1.getPy() + 1);
                	gameBoard.getPlayer1().setDirectionFacing(2);
                	gameBoard.getPlayer1().changeImage(2);
                }
                else if (e.getKeyCode() == KeyEvent.VK_A) {
                	gameBoard.movePlayer1To(player1.getPx() - 1, player1.getPy());
                	gameBoard.getPlayer1().setDirectionFacing(3);
                	gameBoard.getPlayer1().changeImage(3);
                }
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                	gameBoard.movePlayer1To(player1.getPx() + 1, player1.getPy());
                	gameBoard.getPlayer1().setDirectionFacing(1);
                	gameBoard.getPlayer1().changeImage(1);
                }
                else if (e.getKeyCode() == KeyEvent.VK_Q) {
                	player1.attack(player2);
                }
                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	player2.attack(player1);
                }
            }
        });
        
        addKeyListener(listenForMovement);
        
        this.status = status;
    }
    
    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	this.pickRandomClasses();
    	
        int[][] tiles = new int[5][5];
        gameBoard = new TileBoard(player1, player2, tiles);
        
        playing = true;
        status.setText("Running...");
        
        requestFocusInWindow();
    }
    
    private void pickRandomClasses() {
    	double rndm1 = Math.random();
    	double rndm2 = Math.random();
    	
    	if (rndm1 < 0.333) {
    		player1 = new Knight(0, 2);
    	}
    	else {
    		if (rndm1 < 0.666) {
    			player1 = new Ranger(0, 2);
    		}
    		else {
    			player1 = new Bomber(0, 2);
    		}
    	}
    	if (rndm2 < 0.333) {
    		player2 = new Knight(4, 2);
    	}
    	else {
    		if (rndm2 < 0.666) {
    			player2 = new Ranger(4, 2);
    		}
    		else {
    			player2 = new Bomber(4, 2);
    		}
    	}
    }
    
    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
            if (player1.getHealth() == 0) {
                playing = false;
                status.setText("Player 2 wins!");
                if (player2.getAttack() == 175) {
                	ScoreObtainer.incrementKnightScore();
                }
                if (player2.getAttack() == 75) {
                	ScoreObtainer.incrementRangerScore();
                }
                if (player2.getAttack() == 80) {
                	ScoreObtainer.incrementBomberScore();
                }
            } else if (player2.getHealth() == 0) {
                playing = false;
                status.setText("Player 1 wins!");
                if (player1.getAttack() == 175) {
                	ScoreObtainer.incrementKnightScore();
                }
                if (player1.getAttack() == 75) {
                	ScoreObtainer.incrementRangerScore();
                }
                if (player1.getAttack() == 80) {
                	ScoreObtainer.incrementBomberScore();
                }
            }
            repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 800, 800);
        
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 200, 550);
        
		g.setColor(Color.RED);
		g.drawString("HIGH SCORES", 55, 25);
		
		for (int i = 0; i < 3; i++) {
			if (gameBoard.getWinOrder()[i] == 0) {
			g.drawString("Knight wins:" + ScoreObtainer.getKnightScore() , 55, 55 + 30 * i);
			}
			if (gameBoard.getWinOrder()[i] == 1) {
			g.drawString("Ranger wins:" + ScoreObtainer.getRangerScore() , 55, 55 + 30 * i);
			}
			if (gameBoard.getWinOrder()[i] == 2) {
			g.drawString("Bomber wins:" + ScoreObtainer.getBomberScore() , 55, 55 + 30 * i);
			}
		}
		
		g.drawString("INSTRUCTIONS", 50, 250);
		g.drawString("This is a 2 player arena game.", 15, 275);
		g.drawString("There are 3 classes:", 15, 300);
		g.drawString("knight, ranger, and bomber. ", 15, 325);
		g.drawString( "Classes are chosen randomly.", 15, 350);
		g.drawString("Player1: Use WASD to move", 15, 375);
		g.drawString("Player1: Use Q to attack", 15, 400);
		g.drawString("Player2: Use arrow keys to move", 15, 425);
		g.drawString("Player2: Use ENTER to attack", 15, 450);
		g.drawString("First to reach 0 health loses.", 15, 475);
		g.drawString("Highest scores are recorded", 15, 500);
		
        
        player1.draw(g);
        player2.draw(g);
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}