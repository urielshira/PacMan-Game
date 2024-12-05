import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PacMan extends Entity {

    GamePanel gp;
    TileManager tileManager;
    keyHandler keyH;
    Sound sound;
    LifePanel lifePanel = new LifePanel(this);

    public int screenX;
    public int screenY;
    public int score = 0;
    public int life = 3;
    public int level = 1;
    public int upCoinLevel = 200;
    boolean pmCollisionGhost = false;

    public PacMan(GamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle(4,4,32,32);
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setValue();
        getPacmanImg();
    }

    public void setValue(){
        x = gp.tileSize * 14;
        y = gp.tileSize * 6;
        speed = gp.tileSize;
        direction = "left";
    }

    public void update(){
        pmCollisionGhost = false;
        if (keyH.up){
            direction = "up";
        } else if (keyH.down) {
            direction = "down";
        } else if (keyH.left) {
            direction = "left";
        } else if (keyH.right) {
            direction = "right";
        }
        //check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.coinsChecker.checkCoin(this);
        gp.cheryChecker.checkChery(this);

        //if collision is false, player can move
        if (!collisionOn){
            switch (direction){
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;
                case "right": x += speed; break;
            }
        }
        if (score >= upCoinLevel){gp.resetGame(); JOptionPane.showMessageDialog(null,
                "YOU ARE CHAMPION!!\nGO TO THE NEXT LEVEL!"); keyH.resetKeys();}
        //התנגשות של הפקמן עם רוח
        if (samePos(this, gp.blue) || samePos(this, gp.green) || samePos(this, gp.yellow) ||
                samePos(this, gp.red) || samePos(this, gp.pink) || samePos(this, gp.faster)){
            sound = new Sound("src/sounds/died.wav");
            pmCollisionGhost = true;
            gp.repaint();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            keyH.resetKeys();
            this.setValue();
            gp.blue.setValue();
            gp.green.setValue();
            gp.red.setValue();
            gp.yellow.setValue();
            gp.pink.setValue();
            gp.faster.setValue();
            life--;
            if (life == 0) {
                // אם נגמרו החיים - להציג הודעת "Game Over" מעוצבת
                gp.repaint();
                try {
                    Thread.sleep(2000); // ממתין לפני הצגת הודעת "Game Over"
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showGameOverDialog();
                System.exit(0); // סיום התוכנית
            }
        }
        spriteCounter++;
        if (spriteCounter >= 1){
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void getPacmanImg(){
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/pic/up_open.jpg"));
            upClose = ImageIO.read(getClass().getResourceAsStream("/pic/up_close.jpg"));
            down = ImageIO.read(getClass().getResourceAsStream("/pic/down_open.jpg"));
            downClose = ImageIO.read(getClass().getResourceAsStream("/pic/down_close.jpg"));
            left = ImageIO.read(getClass().getResourceAsStream("/pic/left_open.jpg"));
            leftClose = ImageIO.read(getClass().getResourceAsStream("/pic/left_close.jpg"));
            right = ImageIO.read(getClass().getResourceAsStream("/pic/right_open.jpg"));
            rightClose = ImageIO.read(getClass().getResourceAsStream("/pic/right_close.jpg"));
            dead = ImageIO.read(getClass().getResourceAsStream("/pic/pacman-dead.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if (pmCollisionGhost){image = dead;}
        else {
            switch (direction){
                case "up":
                    if (spriteNum == 1)
                        image = upClose;
                    if (spriteNum == 2)
                        image = up;
                    break;
                case "down":
                    if (spriteNum == 1)
                        image = downClose;
                    if (spriteNum == 2)
                        image = down;
                    break;
                case "left":
                    if (spriteNum == 1)
                        image = leftClose;
                    if (spriteNum == 2)
                        image = left;
                    break;
                case "right":
                    if (spriteNum == 1)
                        image = rightClose;
                    if (spriteNum == 2)
                        image = right;
                    break;
            }
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public boolean samePos(Entity entity1, Entity entity2){
        Rectangle rectangle1 = new Rectangle(entity1.x, entity1.y, gp.tileSize, gp.tileSize);
        Rectangle rectangle2 = new Rectangle(entity2.x, entity2.y, gp.tileSize, gp.tileSize);
        return rectangle1.intersects(rectangle2);
    }


    private void showGameOverDialog() {
        // יצירת פאנל מעוצב
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));
        panel.setPreferredSize(new Dimension(800, 600));

        // כותרת
        JLabel titleLabel = new JLabel("Game Over!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 80));
        titleLabel.setForeground(Color.RED);

        // ניקוד סופי
        JLabel scoreLabel = new JLabel("Your final score: " + score, JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        scoreLabel.setForeground(Color.CYAN);

        // תמונה או אייקון
        JLabel iconLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/pic/game_over.png")); // טוען את התמונה המקורית

        // שינוי גודל התמונה
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH); // ממדים חדשים
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        iconLabel.setIcon(scaledIcon);
        iconLabel.setHorizontalAlignment(JLabel.CENTER);

        // הוספת אלמנטים לפאנל
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(iconLabel, BorderLayout.CENTER);
        panel.add(scoreLabel, BorderLayout.SOUTH);

        // יצירת כפתור "אוקי" מעוצב
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(700, 750));
        okButton.setBackground(Color.RED);
        okButton.setForeground(Color.BLACK);
        okButton.setFont(new Font("Arial", Font.BOLD, 30));
        okButton.setFocusPainted(false);
        okButton.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
        okButton.addActionListener(e -> System.exit(0)); // פעולה לכפתור

        // עיצוב החלון של JOptionPane
        UIManager.put("OptionPane.background", new Color(30, 30, 30));
        UIManager.put("Panel.background", new Color(30, 30, 30));
        UIManager.put("Button.background", okButton.getBackground());
        UIManager.put("Button.foreground", okButton.getForeground());
        UIManager.put("Button.font", okButton.getFont());
        UIManager.put("Button.border", okButton.getBorder());

        // הצגת החלונית
        JOptionPane.showMessageDialog(null, panel, "Game Over", JOptionPane.PLAIN_MESSAGE);
    }

}
