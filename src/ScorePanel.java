import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements Runnable {

    Thread thread;
    PacMan pacMan;
    JLabel label = new JLabel();

    public ScorePanel(PacMan pacMan){
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
        if (pacMan.level == 1){
            if (pacMan.score < 30){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.red); }
            else if (pacMan.score >= 30 && pacMan.score <= 80){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.yellow);}
            else if (pacMan.score > 80 && pacMan.score < 154){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.green);}
            else {label.setText("WINNER\uD83C\uDFC6"); this.setBackground(Color.GREEN); label.setForeground(Color.red);}
        }
        else if (pacMan.level == 2){
            if (pacMan.score < 180){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.red); this.setBackground(Color.BLACK);}
            else if (pacMan.score >= 180 && pacMan.score <= 250){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.yellow);}
            else if (pacMan.score > 250 && pacMan.score < 308){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.green);}
            else {label.setText("WINNER\uD83C\uDFC6"); this.setBackground(Color.GREEN); label.setForeground(Color.red);}
        }
        else if (pacMan.level == 3){
            if (pacMan.score < 350){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.red); this.setBackground(Color.BLACK);}
            else if (pacMan.score >= 350 && pacMan.score <= 410){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.yellow);}
            else if (pacMan.score > 410 && pacMan.score < 462){label.setText("SCORE : " + pacMan.score);label.setForeground(Color.green);}
            else {label.setText("WINNER\uD83C\uDFC6"); this.setBackground(Color.GREEN); label.setForeground(Color.red);}
        }
        label.setSize(500, 50);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        this.add(label);
    }
}
