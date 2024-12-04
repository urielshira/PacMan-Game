import java.util.Random;
import javax.swing.Timer;


public class CheryChecker {


    GamePanel gp;
    Sound sound;

    public int cherryCol; // עמודת הדובדבן
    public int cherryRow; // שורת הדובדבן
    public Timer cherryTimer; // טיימר להזזת הדובדבן
    TileManager tileM;

    public CheryChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkChery(Entity entity){

        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = entityRightX/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBottomRow = entityBottomY/gp.tileSize;

        int tile1, tile2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopY - entity.speed + 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileM.tile[tile1].type.equals("chery") && gp.tileM.tile[tile2].type.equals("chery")){
                    gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 2;
                    gp.pacMan.score += 10;
                    sound = new Sound("src/sounds/chery_eating.wav");
                }
                break;

            case "down":
                entityBottomRow = (entityBottomY + entity.speed - 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tile1].type.equals("chery") && gp.tileM.tile[tile2].type.equals("chery")){
                    gp.tileM.mapTileNum[entityLeftCol][entityBottomRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 2;
                    gp.pacMan.score += 10;
                    sound = new Sound("src/sounds/chery_eating.wav");
                }
                break;

            case "left":
                entityLeftCol = (entityLeftX - entity.speed + 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile[tile1].type.equals("chery") && gp.tileM.tile[tile2].type.equals("chery")){
                    gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityLeftCol][entityBottomRow] = 2;
                    gp.pacMan.score += 10;
                    sound = new Sound("src/sounds/chery_eating.wav");
                }
                break;

            case "right":
                entityRightCol = (entityRightX + entity.speed - 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tile1].type.equals("chery") && gp.tileM.tile[tile2].type.equals("chery")){
                    gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 2;
                    gp.pacMan.score += 10;
                    sound = new Sound("src/sounds/chery_eating.wav");
                }
                break;
        }
    }

    public void spawnCherry() {
        Random rand = new Random();
        boolean validPosition = false;
        while (!validPosition) {
            // בחירת מיקום רנדומלי
            cherryCol = rand.nextInt(gp.maxScreenCol);
            cherryRow = rand.nextInt(gp.maxScreenRow);

            // בדיקת חוקיות המיקום (לדוגמה, שאין אריח חסום או דובדבן אחר)
            if (tileM.mapTileNum[cherryCol][cherryRow] == 2) { // 2 - רקע ריק
                tileM.mapTileNum[cherryCol][cherryRow] = 3; // הצבת הדובדבן
                validPosition = true;
            }
        }
    }

    public void startCherryTimer() {
        cherryTimer = new Timer(500, e -> {
            // איפוס מיקום קודם של הדובדבן
            tileM.mapTileNum[cherryCol][cherryRow] = 2; // הפיכת המיקום לריק
            spawnCherry(); // הצבת דובדבן חדש
        });
        cherryTimer.start();
    }

}
