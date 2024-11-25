import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements Runnable {

    Thread thread;
    PacMan pacMan;
    JLabel label = new JLabel();

    public ScorePanel(PacMan pacMan){
        this.setBackground(Color.ORANGE);
        this.setPreferredSize(new Dimension(0, 70));
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
        if (pacMan.score < 154){
            label.setText("SCORE : " + pacMan.score);
        }else {label.setText("**WINNER!**"); this.setBackground(Color.GREEN);}
        label.setSize(150, 50);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(label);
    }
}
