import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements Runnable {

    Thread thread;
    PacMan pacMan;
    JLabel label = new JLabel();

    public ScorePanel(PacMan pacMan){
        this.setBackground(Color.white);
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
        label.setText("SCORE : " + pacMan.score);
        label.setSize(150, 50);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(label);
    }
}
