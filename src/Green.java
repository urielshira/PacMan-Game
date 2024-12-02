import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Green extends Ghost{

    public Green(GamePanel gp) {
        super(gp);
    }


    @Override
    public void setValue() {
        super.setValue();
        x = gp.tileSize * 6;
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
            green = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_green.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(green, x, y, gp.tileSize, gp.tileSize, null);
    }
}
