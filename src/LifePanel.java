import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LifePanel extends JPanel implements Runnable{

    PacMan pacMan; // התייחסות לפקמן
    Thread thread;
    BufferedImage heart ; // תמונת הלב
    JLabel label = new JLabel();
    public static int ghostVulnerableTime = 5; // זמן ספירה לאחור לפגיעות הרוחות
    GamePanel gp;
    public Timer countdownTimer;

    public LifePanel(PacMan pacMan, GamePanel gp) {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(0, 50));
        this.pacMan = pacMan;
        this.gp = gp;
        this.add(label);
        this.setVisible(true);
    }

    public void startScoreThread(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            repaint(); // מצייר מחדש את ה-LifePanel
            try {
                Thread.sleep(150); // השהייה של שנייה אחת
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startCountdownTimer() {
        countdownTimer = new Timer(1000, e -> {
            if (ghostVulnerableTime > 0 && gp.bigCoinChecker.counter) {
                ghostVulnerableTime--;
                repaint();
            } else {
                countdownTimer.stop();
            }
        });
        countdownTimer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        String text = "LIFE : " + pacMan.life + "         LEVEL: " + pacMan.level;

        if (gp.bigCoinChecker.counter) {
            text += "         time left: " + ghostVulnerableTime + " s";
        }

        if (pacMan.life == 3){label.setForeground(Color.green);}
        else if(pacMan.life == 2){label.setForeground(Color.yellow);}
        else {label.setForeground(Color.red);}

        label.setSize(800, 50);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        label.setText(text);
    }

    public void getHeartImg(){
        try {
            heart = ImageIO.read(getClass().getResourceAsStream("/pic/heart.JPG"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
