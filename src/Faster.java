import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Faster extends Ghost{

    public Faster(GamePanel gp) {
        super(gp);
    }



    @Override
    public void setValue() {
        super.setValue();
        x = gp.tileSize * 8;
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
        super.getGhostImg();
        try {
            faster = ImageIO.read(getClass().getResourceAsStream("/pic/faster.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if (gCPM){g2.drawImage(collision, x, y, gp.tileSize, gp.tileSize, null);}
        else g2.drawImage(faster, x, y, gp.tileSize, gp.tileSize, null);
    }
}
