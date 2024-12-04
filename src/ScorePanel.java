import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements Runnable {

    Thread thread;
    PacMan pacMan;
    JLabel label = new JLabel();

    public ScorePanel(PacMan pacMan){
        this.setBackground(Color.BLUE);
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
        if (pacMan.score < 30){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.white);}
        else if (pacMan.score >= 30 && pacMan.score <= 80){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.pink);}
        else if (pacMan.score > 80 && pacMan.score < 154){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.red);}
        else {label.setText("WINNER\uD83C\uDFC6"); this.setBackground(Color.GREEN); label.setForeground(Color.red);}
        label.setSize(500, 50);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        this.add(label);
    }
}
