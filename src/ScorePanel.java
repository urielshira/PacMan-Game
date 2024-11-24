import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements Runnable {

    Thread scoreThread;
    PacMan pacMan;
    JLabel label;

    public ScorePanel(PacMan pacMan){
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(0, 50));
        this.pacMan = pacMan;
        this.setVisible(true);

    }

    public void startScoreThread(){
        scoreThread = new Thread(this);
        scoreThread.start();
    }

    @Override
    public void run() {

    }
}
