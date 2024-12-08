import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Pink extends Ghost{

    public Pink(GamePanel gp) {
        super(gp);
    }


    @Override
    public void setValue() {
        super.setValue();
        x = gp.tileSize * 6;
        y = gp.tileSize * 5;
        speed = gp.tileSize/2;
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
            pink = ImageIO.read(getClass().getResourceAsStream("/pic/pink.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if (gCPM){g2.drawImage(collision, x, y, gp.tileSize, gp.tileSize, null);}
        else g2.drawImage(pink, x, y, gp.tileSize, gp.tileSize, null);
    }
}
