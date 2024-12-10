import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame{

    JFrame window;
    GamePanel gamePanel;
    ScorePanel scorePanel;
    LifePanel lifePanel;
    Sound sound;
    String playerName;
    private static Window board;

    private Window() throws HeadlessException {
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
        lifePanel = new LifePanel(gamePanel.pacMan, gamePanel);

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
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image background = ImageIO.read(getClass().getResource("/pic/background.jpg"));
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    g.setColor(new Color(0, 0, 0));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(800, 600));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

//        JLabel welcomeLabel = new JLabel("Welcome to the Pacman game!");
//        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        welcomeLabel.setForeground(Color.CYAN);
//        panel.add(welcomeLabel, gbc);

        gbc.gridy++;
        JLabel nameLabel = new JLabel("Please enter your name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        nameLabel.setForeground(Color.YELLOW);
        panel.add(nameLabel, gbc);

        gbc.gridy++;
        JTextField nameField = new JTextField(900);
        nameField.setMaximumSize(new Dimension(200, 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameField, gbc);

        gbc.gridy++;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton okButton = createStyledButton("OK");
        JButton cancelButton = createStyledButton("Cancel");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, gbc);

        JDialog dialog = new JDialog();
        dialog.setTitle("Welcome");
        dialog.setModal(true);
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        // משתנה לאחסון שם המשתמש
        final String[] userName = {null};

        // פעולה ללחיצה על אישור
        Runnable onOkPressed = () -> {
            if (nameField.getText().trim().isEmpty()) {
                userName[0] = "Anonymous";
            } else {
                userName[0] = nameField.getText().trim();
            }
            dialog.dispose();
        };

        // טיפול בלחיצה על אישור
        okButton.addActionListener(e -> onOkPressed.run());

        // הוספת מאזין לשדה הטקסט ללחיצה על Enter
        nameField.addActionListener(e -> onOkPressed.run());

        // טיפול בלחיצה על ביטול
        cancelButton.addActionListener(e -> System.exit(0));

        // הוספת מאזין לסגירת החלון
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        dialog.setVisible(true);

        return userName[0] != null ? userName[0] : "Player";
    }

    // פונקציה ליצירת כפתור מעוצב
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 40));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        return button;
    }

    public static Window createBoard(){
        if (board == null){
            board = new Window();
        }
        return board;
    }

}
