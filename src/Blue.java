import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Blue extends Ghost{

    public Blue(GamePanel gp) {
        super(gp);
    }

    @Override
    public void setValue() {
        x = gp.tileSize * 7;
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
            blue = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_blue.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(blue, x, y, gp.tileSize, gp.tileSize, null);
    }
}
