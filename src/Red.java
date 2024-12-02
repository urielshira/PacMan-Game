import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Red extends Ghost{

    public Red(GamePanel gp) {
        super(gp);
    }


    @Override
    public void setValue() {
        super.setValue();
        x = gp.tileSize * 7;
        y = gp.tileSize * 5;
        speed = gp.tileSize;
        direction = dir[random.nextInt(dir.length)];
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void getGhostImg() {
        try {
            red = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_red.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(red, x, y, gp.tileSize, gp.tileSize, null);
    }
}
