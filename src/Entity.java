import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    GamePanel gp;
    public int x, y, speed;

    public BufferedImage up, upClose, down, downClose, left, leftClose, right, rightClose,
            blue, pink, red, yellow, deadPM, collision, faster, deadGhost;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;

}
