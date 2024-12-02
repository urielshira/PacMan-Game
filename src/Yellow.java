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
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void getGhostImg() {
        try {
            yellow = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_yellow.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(yellow, x, y, gp.tileSize, gp.tileSize, null);
    }
}
