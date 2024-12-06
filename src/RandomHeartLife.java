import javax.swing.*;
import java.util.Random;

public class RandomHeartLife {

    GamePanel gp;
    Sound sound;

    public int heartCol = 0; // עמודת הלב
    public int heartRow = 0; // שורת הלב

    public Timer heartTimer; // טיימר להזזת הלב
    TileManager tileManager;

    public RandomHeartLife(GamePanel gp, TileManager tileManager) {
        this.tileManager = tileManager;
        this.gp = gp;
    }

    public void checkHeart(Entity entity){
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = entityRightX/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBottomRow = entityBottomY/gp.tileSize;

        int tile1, tile2;
        if (gp.pacMan.life < 3){
        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopY - entity.speed + 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tile1].type.equals("heart") && gp.tileM.tile[tile2].type.equals("heart")){
                    gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 2;
                    sound = new Sound("src/sounds/next_level.wav");
                    gp.pacMan.life++;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomY + entity.speed - 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tile1].type.equals("heart") && gp.tileM.tile[tile2].type.equals("heart")){
                    gp.tileM.mapTileNum[entityLeftCol][entityBottomRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 2;
                    sound = new Sound("src/sounds/next_level.wav");
                    gp.pacMan.life++;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftX - entity.speed + 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tile1].type.equals("heart") && gp.tileM.tile[tile2].type.equals("heart")){
                    gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityLeftCol][entityBottomRow] = 2;
                    sound = new Sound("src/sounds/next_level.wav");
                    gp.pacMan.life++;
                }
                break;

            case "right":
                entityRightCol = (entityRightX + entity.speed - 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tile1].type.equals("heart") && gp.tileM.tile[tile2].type.equals("heart")){
                    gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 2;
                    sound = new Sound("src/sounds/next_level.wav");
                    gp.pacMan.life++;
                }
                break;
        }
    }
    }

    public void spawnHeart() {
        Random rand = new Random();
        Random rand2 = new Random();
        boolean validPosition = false;

        // בחירת מיקום רנדומלי חדש ללב
        while (!validPosition) {
            heartCol = rand.nextInt(gp.maxScreenCol);
            heartRow = rand.nextInt(gp.maxScreenRow);
            // בדיקת חוקיות המיקום (אין אריח חסום או לב אחר)
            if (tileManager.mapTileNum[heartCol][heartRow] == 2 || tileManager.mapTileNum[heartCol][heartRow] == 1) {
                tileManager.mapTileNum[heartCol][heartRow] = 5; // הצבת לב במקום החדש
                validPosition = true;
            }
        }
        // עדכון המיקום החדש על המסך
        gp.repaint();
    }

    public void startHeartTimer() {
        heartTimer = new Timer(5000, e -> { // הגדר זמן יותר ארוך אם תרצה לדחוף דובדבן כל 5 שניות
            // איפוס מיקום קודם של הלב
            tileManager.mapTileNum[heartCol][heartRow] = 1; // הפיכת המיקום לריק
            spawnHeart(); // הצבת לב חדש
        });
        heartTimer.start();
    }
}
