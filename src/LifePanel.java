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

    public LifePanel(PacMan pacMan) {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(0, 50));
        this.pacMan = pacMan;
        this.setVisible(true);
    }

    public void startScoreThread(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true){
            repaint();
            try {
                Thread.sleep(150);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        label.setText("LIFE : " + pacMan.life);
        label.setForeground(Color.yellow);

        label.setSize(150, 50);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        this.add(label);
    }

    public void getHeartImg(){
        try {
            heart = ImageIO.read(getClass().getResourceAsStream("/pic/heart.JPG"));
        }catch (IOException e){
            e.printStackTrace();
        }
}
}
