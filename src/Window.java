import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    JFrame window;
    GamePanel gamePanel;
    ScorePanel scorePanel;
    Sound sound;

    public Window() throws HeadlessException {

        sound = new Sound("src/sounds/playing-pac-man-6783.wav");

        window = new JFrame("PacMan - GAME");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setBounds(0,0,815,730);
        window.setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        scorePanel = new ScorePanel(gamePanel.pacMan);
        gamePanel.add(scorePanel);

        window.add(scorePanel, BorderLayout.NORTH);
        window.add(gamePanel, BorderLayout.CENTER);

        scorePanel.startScoreThread();
        gamePanel.startGameThread();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
