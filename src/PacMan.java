import java.awt.*;

public class PacMan extends Element {

    GamePanel gp;
    keyHandler keyH;

    public PacMan(GamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setValue();
    }

    public void setValue(){
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update(){
        if (keyH.up && y > speed){
            y -= speed;
        } else if (keyH.down && y < gp.screenHeight-(gp.tileSize+speed)) {
            y += speed;
        } else if (keyH.left && x > speed) {
            x -= speed;
        } else if (keyH.right && x < gp.screenWidth-(gp.tileSize+speed)) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.yellow);
        g2.fillOval(x, y, gp.tileSize, gp.tileSize);

    }

}
