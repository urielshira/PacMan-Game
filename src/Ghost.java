import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Ghost extends Entity{

    GamePanel gp;
    Random random = new Random();
    String[] dir = {"up", "down", "left", "right"};
    public int screenX;
    public int screenY;


    public Ghost(GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(4,4,32,32);

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setValue();
        getGhostImg();
    }


    public void setValue(){
    }


    public void update(){
    }


    public void getGhostImg(){
    }

    public void draw(Graphics2D g2){
    }

}
