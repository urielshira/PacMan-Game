import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LifePanel extends JPanel {

    PacMan pacMan; // התייחסות לפקמן
    BufferedImage heart; // תמונת הלב

    public LifePanel(PacMan pacMan) {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(0, 50));
        this.pacMan = pacMan;
        this.setVisible(true);
        loadHeartImage(); // טעינת תמונת הלב
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // הצגת הטקסט LIFE
        g.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        g.setColor(Color.YELLOW);
        g.drawString("LIFE: " + pacMan.life, 10, 35);

        // ציור הלבבות לפי ערך החיים הנוכחי
        for (int i = 0; i < pacMan.life; i++) {
            g.drawImage(heart, 600 + i * 40, 12, 30, 30, this);
        }
    }

    private void loadHeartImage() {
        try {
            heart = ImageIO.read(getClass().getResourceAsStream("/pic/heart.JPG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // פונקציה לעדכון הלייף ורענון המסך
    public void updateLifePanel() {
        repaint();
    }
}
