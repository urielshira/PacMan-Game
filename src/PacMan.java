import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PacMan extends Entity {

    GamePanel gp;
    keyHandler keyH;

    public PacMan(GamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setValue();
        getPacmanImg();
    }

    public void getPacmanImg(){

        try {
            up = ImageIO.read(getClass().getResourceAsStream("/pic/up_PacMan.jpg"));
            down = ImageIO.read(getClass().getResourceAsStream("/pic/down_PacMan.jpg"));
            left = ImageIO.read(getClass().getResourceAsStream("/pic/left_PacMan.jpg"));
            right = ImageIO.read(getClass().getResourceAsStream("/pic/right_PacMan.jpg"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setValue(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void update(){
        if (keyH.up && y > speed){
            direction = "up";
            y -= speed;
        } else if (keyH.down && y < gp.screenHeight-(gp.tileSize+speed)) {
            direction = "down";
            y += speed;
        } else if (keyH.left && x > speed) {
            direction = "left";
            x -= speed;
        } else if (keyH.right && x < gp.screenWidth-(gp.tileSize+speed)) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.yellow);
//        g2.fillOval(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                image = up;
                break;
            case "down":
                image = down;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
