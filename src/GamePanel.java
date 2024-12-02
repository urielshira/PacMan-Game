import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //screen setting
    public final int tileSize = 40; // 48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 16;

    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    keyHandler keyH = new keyHandler();
    Thread gameThread;

    PacMan pacMan = new PacMan(this, keyH);
    Blue blue = new Blue(this);
    Green green = new Green(this);
    Yellow yellow = new Yellow(this);
    Red red = new Red(this);
    Pink pink = new Pink(this);

    TileManager tileM = new TileManager(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public CoinsChecker coinsChecker  = new CoinsChecker(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        while (gameThread != null){
            update();
            repaint();
            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        pacMan.update();
        blue.update();
        green.update();
        yellow.update();
        red.update();
        pink.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        blue.draw(g2);
        green.draw(g2);
        yellow.draw(g2);
        red.draw(g2);
        pink.draw(g2);
        pacMan.draw(g2);

        g2.dispose();
    }
}
