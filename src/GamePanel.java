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
    Sound sound;

    PacMan pacMan = new PacMan(this, keyH);
    Blue blue = new Blue(this);
    Green green = new Green(this);
    Yellow yellow = new Yellow(this);
    Red red = new Red(this);
    Pink pink = new Pink(this);

    TileManager tileM = new TileManager(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public CoinsChecker coinsChecker  = new CoinsChecker(this);
    public CheryChecker cheryChecker = new CheryChecker(this);

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
        if (pacMan.keyH.up || pacMan.keyH.down || pacMan.keyH.left || pacMan.keyH.right){
            blue.update();
            green.update();
            yellow.update();
            red.update();
            pink.update();
        }
    }

    // פונקציה שמאתחלת את המשחק
    public void resetGame() {
        sound = new Sound("src/sounds/next_level.wav");
        // איפוס הציון, החיים, הרמה והמיקום
        pacMan.life = 3;
        pacMan.level++;
        pacMan.upCoinLevel += 154;
        tileM.loadMap("/map1.txt");
        pacMan.setValue();
        // איפוס הרוחות
        blue.setValue();
        green.setValue();
        red.setValue();
        yellow.setValue();
        pink.setValue();
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
