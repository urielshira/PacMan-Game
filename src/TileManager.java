import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/pic/tile.jpg"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

//        g2.drawImage(tiles[0].image, 0, 0, gp.tileSize, gp.tileSize, null);

        int col = 0, row = 0, x = 0, y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            g2.drawImage(tiles[0].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }


        }

    }
}