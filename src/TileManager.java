import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum [][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/map1.txt");

    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/pic/tile.jpg"));
            tile[0].collision = true;
            tile[0].type = "tile";

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/pic/coin.jpg"));
            tile[1].collision = false;
            tile[1].type = "coin";


            tile[2] = new Tile();
            tile[2].collision = false;
            tile[2].type = "empty";

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/pic/chery.jpg"));
            tile[3].collision = false;
            tile[3].type = "chery";

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String map){

        try {
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow){

                String line = br.readLine();
                while (col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }

        }catch (Exception e){}


    }

    public void draw(Graphics2D g2){

//        g2.drawImage(tiles[0].image, 0, 0, gp.tileSize, gp.tileSize, null);

        int col = 0, row = 0, x = 0, y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
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
