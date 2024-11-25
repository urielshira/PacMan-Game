import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Ghost extends Entity{

    GamePanel gp;
    public BufferedImage[] ghostImage;
    Random random = new Random();
    String[] dir = {"up", "down", "left", "right"};
    public int screenX;
    public int screenY;


    public Ghost(GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 30;
        solidArea.height = 30;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setValue();
        getPacmanImg();
    }


    public void setValue(){
        x = gp.tileSize * 7;
        y = gp.tileSize * 6;
        speed = 3;
        direction = "";
    }


    public void update(){
        direction = dir[random.nextInt(0, 4)];
        //check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if (!collisionOn){
            switch (direction){
                case "up": y -= speed;
                case "down": y += speed;
                case "left": x -= speed;
                case "right": x += speed;
            }
        }
    }


    public void getPacmanImg(){

        try {
            blue = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_blue.jpg"));
            green = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_green.jpg"));
            pink = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_pink.jpg"));
            red = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_red.jpg"));
            yellow = ImageIO.read(getClass().getResourceAsStream("/pic/ghost_yellow.jpg"));
            ghostImage = new BufferedImage[]{blue, green, pink, red, yellow};

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

//        g2.drawImage(blue, x, y-gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(green, x-gp.tileSize, y, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(pink, x+gp.tileSize, y, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(red, x-(gp.tileSize*2), y, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(yellow, x, y, gp.tileSize, gp.tileSize, null);
    }



}
