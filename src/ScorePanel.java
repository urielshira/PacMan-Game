import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements Runnable {

    Thread thread;
    PacMan pacMan;
    JLabel label = new JLabel();

    public ScorePanel(PacMan pacMan){
        this.setBackground(Color.ORANGE);
        this.setPreferredSize(new Dimension(0, 40));
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
        if (pacMan.score < 150){
            label.setText("SCORE : " + pacMan.score + " Life: ");
        }else {label.setText("WINNER\uD83C\uDFC6"); this.setBackground(Color.GREEN);}
        label.setSize(500, 50);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        this.add(label);
    }
}
