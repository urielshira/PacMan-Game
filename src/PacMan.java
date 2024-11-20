import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PacMan extends Entity {

    GamePanel gp;
    keyHandler keyH;

    public int screenX;
    public int screenY;

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
    }

    public void setValue(){
        x = gp.tileSize * 14;
        y = gp.tileSize * 6;
        speed = 4;
        direction = "up";
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

        //if collision is false, player can move
        if (collisionOn == false){
            switch (direction){
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;
                case "right": x += speed; break;
            }
        }
        spriteCounter++;
        if (spriteCounter > 50){
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
            statics = ImageIO.read(getClass().getResourceAsStream("/pic/static PacMan.jpg"));
            up = ImageIO.read(getClass().getResourceAsStream("/pic/up_PacMan.jpg"));
            down = ImageIO.read(getClass().getResourceAsStream("/pic/down_PacMan.jpg"));
            left = ImageIO.read(getClass().getResourceAsStream("/pic/left_PacMan.jpg"));
            right = ImageIO.read(getClass().getResourceAsStream("/pic/right_PacMan.jpg"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.yellow);
//        g2.fillOval(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if (spriteNum == 1)
                    image = statics;
                if (spriteNum == 2)
                    image = up;
                break;
            case "down":
                if (spriteNum == 1)
                    image = statics;
                if (spriteNum == 2)
                    image = down;
                break;
            case "left":
                if (spriteNum == 1)
                    image = statics;
                if (spriteNum == 2)
                    image = left;
                break;
            case "right":
                if (spriteNum == 1)
                    image = statics;
                if (spriteNum == 2)
                    image = right;
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
