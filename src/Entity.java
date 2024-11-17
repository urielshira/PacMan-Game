import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y, speed;

    public BufferedImage up, down, left, right, statics;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;

}
