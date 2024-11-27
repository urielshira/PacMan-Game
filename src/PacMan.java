import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PacMan extends Entity {

    GamePanel gp;
    keyHandler keyH;

    public int screenX;
    public int screenY;
    public int score;


    public PacMan(GamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 30;
        solidArea.height = 30;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setValue();
        getPacmanImg();

        ScorePanel scorePanel = new ScorePanel(this);
        gp.add(scorePanel);
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
        spriteCounter++;
        if (spriteCounter > 1){
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
}
