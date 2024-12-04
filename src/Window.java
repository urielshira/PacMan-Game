import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    JFrame window;
    GamePanel gamePanel;
    ScorePanel scorePanel;
    LifePanel lifePanel;
    Sound sound;
    String playerName;

    public Window() throws HeadlessException {
        // הצגת חלון דיאלוג להזנת שם המשתמש
        playerName = showWelcomeDialog();

        sound = new Sound("src/sounds/playing-pac-man-6783.wav");

        window = new JFrame("PacMan - GAME");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setBounds(0,0,815,770);
        window.setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        scorePanel = new ScorePanel(gamePanel.pacMan, playerName);
        lifePanel = new LifePanel(gamePanel.pacMan);

        gamePanel.add(scorePanel);
        gamePanel.add(lifePanel);

        window.add(scorePanel, BorderLayout.CENTER);
        window.add(lifePanel, BorderLayout.NORTH);
        window.add(gamePanel, BorderLayout.SOUTH); // לוח המשחק

        scorePanel.startScoreThread();
        gamePanel.startGameThread();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private String showWelcomeDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("ברוך הבא למשחק PacMan!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nameLabel = new JLabel("אנא הכנס את שמך:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(200, 25));

        panel.add(welcomeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // מרווח
        panel.add(nameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // מרווח
        panel.add(nameField);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Welcome",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION && !nameField.getText().isEmpty()) {
            return nameField.getText();
        } else {
            return "Player"; // ברירת מחדל אם לא הוזן שם
        }
    }

}
