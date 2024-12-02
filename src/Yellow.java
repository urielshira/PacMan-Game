import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Yellow extends Ghost{

    public Yellow(GamePanel gp) {
        super(gp);
    }


    @Override
    public void setValue() {
        super.setValue();
        x = gp.tileSize * 8;
        y = gp.tileSize * 6;
        speed = 20;
        direction = dir[random.nextInt(dir.length)];
    }

    @Override
    public void update() {
        super.update();
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

    @Override
    public void getGhostImg() {
        super.getGhostImg();
        try {
            yellow = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_yellow.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.drawImage(yellow, x, y, gp.tileSize, gp.tileSize, null);
    }
}
