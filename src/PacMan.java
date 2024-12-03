import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PacMan extends Entity {

    GamePanel gp;
    keyHandler keyH;
    Sound sound;
    LifePanel lifePanel = new LifePanel(this);

    public int screenX;
    public int screenY;
    public int score;
    public int life = 3;

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

        //if collision is false, player can move
        if (!collisionOn){
            switch (direction){
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;
                case "right": x += speed; break;
            }
        }
        //התנגשות של הפקמן עם רוח
        if (samePos(this, gp.blue) || samePos(this, gp.green) || samePos(this, gp.yellow) ||
                samePos(this, gp.red) || samePos(this, gp.pink)){
            sound = new Sound("src/sounds/died.wav");
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
            life--;
            if (life <= 0){
                // אם נגמרו החיים - להציג הודעה או לסיים משחק
                JOptionPane.showMessageDialog(null, "Game Over!");
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public boolean samePos(Entity entity1, Entity entity2){
        Rectangle rectangle1 = new Rectangle(entity1.x, entity1.y, gp.tileSize, gp.tileSize);
        Rectangle rectangle2 = new Rectangle(entity2.x, entity2.y, gp.tileSize, gp.tileSize);
        return rectangle1.intersects(rectangle2);
    }
}
