import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    JFrame window;
    GamePanel gamePanel;
    ScorePanel scorePanel;
    LifePanel lifePanel;
    Sound sound;

    public Window() throws HeadlessException {

        sound = new Sound("src/sounds/playing-pac-man-6783.wav");

        window = new JFrame("PacMan - GAME");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setBounds(0,0,815,770);
        window.setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        scorePanel = new ScorePanel(gamePanel.pacMan);
        lifePanel = new LifePanel(gamePanel.pacMan);


        gamePanel.add(scorePanel);
        gamePanel.add(lifePanel);

        window.add(scorePanel, BorderLayout.NORTH);
        window.add(lifePanel, BorderLayout.CENTER);
        window.add(gamePanel, BorderLayout.SOUTH);

        scorePanel.startScoreThread();
        gamePanel.startGameThread();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
