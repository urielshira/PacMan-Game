import java.util.Random;
import javax.swing.Timer;

public class CheryChecker {

    GamePanel gp;
    Sound sound;

    public int cherryCol = 0; // עמודת הדובדבן
    public int cherryCol2 = 0;
    public int cherryRow = 0; // שורת הדובדבן
    public int cherryRow2 = 0;

    public Timer cherryTimer; // טיימר להזזת הדובדבן
    TileManager tileManager;

    public CheryChecker(GamePanel gp, TileManager tileManager) {
        this.tileManager = tileManager;
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
        Random rand2 = new Random();
        boolean validPosition = false;

        // הסרת הדובדבן מהמיקום הקודם (אם קיים)
//        if (cherryCol >= 0 && cherryRow >= 0) {
//            tileManager.mapTileNum[cherryCol][cherryRow] = 2; // הפיכת המיקום הקודם לרקע ריק
//        }
        // בחירת מיקום רנדומלי חדש לדובדבן
        while (!validPosition) {
            cherryCol = rand.nextInt(gp.maxScreenCol);
            cherryCol2 = rand.nextInt(gp.maxScreenCol);
            cherryRow = rand.nextInt(gp.maxScreenRow);
            cherryRow2 = rand.nextInt(gp.maxScreenRow);
            // בדיקת חוקיות המיקום (אין אריח חסום או דובדבן אחר)
            if ((tileManager.mapTileNum[cherryCol][cherryRow] != 0 && tileManager.mapTileNum[cherryCol2][cherryRow2] != 0) &&
                    (tileManager.mapTileNum[cherryCol][cherryRow] != 4 && tileManager.mapTileNum[cherryCol2][cherryRow2] != 4)) {
                tileManager.mapTileNum[cherryCol][cherryRow] = 3; // הצבת דובדבן במקום החדש
                tileManager.mapTileNum[cherryCol2][cherryRow2] = 3;
                validPosition = true;
            }
        }
        // עדכון המיקום החדש על המסך
        gp.repaint();
    }

    public void startCherryTimer() {
        cherryTimer = new Timer(5000, e -> { // הגדר זמן יותר ארוך אם תרצה לדחוף דובדבן כל 5 שניות
            // איפוס מיקום קודם של הדובדבן
            tileManager.mapTileNum[cherryCol][cherryRow] = 1; // הפיכת המיקום לריק
            tileManager.mapTileNum[cherryCol2][cherryRow2] = 1;
            spawnCherry(); // הצבת דובדבן חדש
        });
        cherryTimer.start();
    }
}
