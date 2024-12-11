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
    Yellow yellow = new Yellow(this);
    Red red = new Red(this);
    Pink pink = new Pink(this);
    Faster faster = new Faster(this);


    public TileManager tileM = new TileManager(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public CoinsChecker coinsChecker = new CoinsChecker(this);
    public CheryChecker cheryChecker = new CheryChecker(this, tileM);
    public BigCoinChecker bigCoinChecker = new BigCoinChecker(this);
    public RandomHeartLife heartLife = new RandomHeartLife(this, tileM);
    public LifePanel lifePanel = new LifePanel(pacMan, this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        if (cheryChecker != null && heartLife != null) {
            cheryChecker.spawnCherry();
            cheryChecker.startCherryTimer();
            heartLife.spawnHeart();
            heartLife.startHeartTimer();
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        pacMan.update();
        if (pacMan.keyH.up || pacMan.keyH.down || pacMan.keyH.left || pacMan.keyH.right) {
            if (blue.isActive) blue.update();
            if (yellow.isActive) yellow.update();
            if (red.isActive) red.update();
            if (pink.isActive) pink.update();
            if (faster.isActive) faster.update();
        }
    }

    // פונקציה שמאתחלת את המשחק
    public void resetGame() {
        sound = new Sound("src/sounds/next_level.wav");
        // איפוס הציון, החיים, הרמה והמיקום
        pacMan.life = 3;
        pacMan.level++;
        pacMan.upCoinLevel += 200;
        tileM.loadMap("/map1.txt");
        pacMan.setValue();
        // איפוס הרוחות
        blue.setValue();
        red.setValue();
        yellow.setValue();
        pink.setValue();
        faster.setValue();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2); // צייר את הלוח
        if (blue.isActive) blue.draw(g2);
        if (yellow.isActive) yellow.draw(g2);
        if (red.isActive) red.draw(g2);
        if (pink.isActive) pink.draw(g2);
        if (faster.isActive) faster.draw(g2);
        pacMan.draw(g2);
        if (bigCoinChecker.counter && LifePanel.ghostVulnerableTime > 0){lifePanel.startCountdownTimer(); }

        if (!bigCoinChecker.counter){LifePanel.ghostVulnerableTime = 5;}

            g2.dispose();
        }

    }
