import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements Runnable {

    Thread thread;
    PacMan pacMan;
    JLabel label = new JLabel();
    String playerName;

    public ScorePanel(PacMan pacMan, String playerName){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(0, 50));
        this.pacMan = pacMan;
        this.playerName = playerName;
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
            if (pacMan.score < 50){label.setText("SCORE : " + pacMan.score + "          USER NAME : " + playerName);label.setForeground(Color.red); }
            else if (pacMan.score >= 50 && pacMan.score <= 120){label.setText("SCORE : " + pacMan.score + "          USER NAME : " + playerName);label.setForeground(Color.yellow);}
            else if (pacMan.score > 120 && pacMan.score <= 200){label.setText("SCORE : " + pacMan.score + "          USER NAME : " + playerName);label.setForeground(Color.green);}
            else {label.setText(playerName + "  WIN\uD83C\uDFC6"); this.setBackground(Color.GREEN); label.setForeground(Color.red);}
        }
        else if (pacMan.level == 2){
            if (pacMan.score < 250){label.setText("SCORE : " + pacMan.score + "          USER NAME : " + playerName);label.setForeground(Color.red); this.setBackground(Color.BLACK);}
            else if (pacMan.score >= 250 && pacMan.score <= 320){label.setText("SCORE : " + pacMan.score + "          USER NAME : " + playerName);label.setForeground(Color.yellow);}
            else if (pacMan.score > 320 && pacMan.score <= 400){label.setText("SCORE : " + pacMan.score + "          USER NAME : " + playerName);label.setForeground(Color.green);}
            else {label.setText(playerName + "  WIN\uD83C\uDFC6"); this.setBackground(Color.GREEN); label.setForeground(Color.red);}
        }
        else if (pacMan.level == 3){
            if (pacMan.score < 450){label.setText("SCORE : " + pacMan.score + "          USER NAME : " + playerName);label.setForeground(Color.red); this.setBackground(Color.BLACK);}
            else if (pacMan.score >= 450 && pacMan.score <= 520){label.setText("SCORE : " + pacMan.score + "          USER NAME : " + playerName);label.setForeground(Color.yellow);}
            else if (pacMan.score > 520 && pacMan.score <= 600){label.setText("SCORE : " + pacMan.score + "          USER NAME : " + playerName);label.setForeground(Color.green);}
            else {label.setText(playerName + "  WIN\uD83C\uDFC6"); this.setBackground(Color.GREEN); label.setForeground(Color.red);}
        }
        label.setSize(500, 50);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        this.add(label);
    }
}
