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
    public boolean gCPM = false;
    public boolean runGhost = false;
    public boolean isVulnerable = false;
    public boolean isActive = true; // הרוח פעילה בתחילת המשחק


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

        if (!isActive) return; // אם הרוח לא פעילה, לא לעדכן אותה

        //check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if (!collisionOn){
            switch (direction){
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;
                case "right": x += speed; break;
            }
        } else {
            // אם יש התנגשות, בחר כיוון חדש רנדומלי
            direction = dir[random.nextInt(dir.length)];
        }
    }


    public void getGhostImg(){
        try {
            collision = ImageIO.read(getClass().getResourceAsStream("/pic/collision.png"));
            deadGhost = ImageIO.read(getClass().getResourceAsStream("/pic/run.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        if (!isActive) return; // אם הרוח לא פעילה, לא לצייר אותה


    }

}
