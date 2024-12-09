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
        speed = gp.tileSize/2;
        direction = dir[random.nextInt(dir.length)];
        isVulnerable = false; // נניח שהרוחות לא פגיעות בתחילת המשחק
        isActive = true;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void getGhostImg() {
        super.getGhostImg();
        try {
            yellow = ImageIO.read(getClass().getResourceAsStream("/pic/yellow.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if (gCPM){g2.drawImage(collision, x, y, gp.tileSize, gp.tileSize, null);}
        else if (runGhost){g2.drawImage(deadGhost, x, y, gp.tileSize, gp.tileSize, null);}
        else g2.drawImage(yellow, x, y, gp.tileSize, gp.tileSize, null);
    }
}
