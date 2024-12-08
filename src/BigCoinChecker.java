public class BigCoinChecker {

    GamePanel gp;
    Sound sound;


    public BigCoinChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkBigCoin(Entity entity){

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

                if (gp.tileM.tile[tile1].type.equals("bigCoin") && gp.tileM.tile[tile2].type.equals("bigCoin")){
                    gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 2;
                    sound = new Sound("src/sounds/chery_eating.wav");
                    ghostRun();
                }
                break;

            case "down":
                entityBottomRow = (entityBottomY + entity.speed - 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tile1].type.equals("bigCoin") && gp.tileM.tile[tile2].type.equals("bigCoin")){
                    gp.tileM.mapTileNum[entityLeftCol][entityBottomRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 2;
                    sound = new Sound("src/sounds/chery_eating.wav");
                    ghostRun();
                }
                break;

            case "left":
                entityLeftCol = (entityLeftX - entity.speed + 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile[tile1].type.equals("bigCoin") && gp.tileM.tile[tile2].type.equals("bigCoin")){
                    gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityLeftCol][entityBottomRow] = 2;
                    sound = new Sound("src/sounds/chery_eating.wav");
                    ghostRun();
                }
                break;

            case "right":
                entityRightCol = (entityRightX + entity.speed - 25) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tile1].type.equals("bigCoin") && gp.tileM.tile[tile2].type.equals("bigCoin")){
                    gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 2;
                    sound = new Sound("src/sounds/chery_eating.wav");
                    ghostRun();
                }
                break;
        }
    }

    public void ghostRun(){
        gp.blue.runGhost = true;
        gp.red.runGhost = true;
        gp.faster.runGhost = true;
        gp.pink.runGhost = true;
        gp.yellow.runGhost = true;
    }

    public void ghostRunFalse(){
        gp.blue.runGhost = false;
        gp.red.runGhost = false;
        gp.faster.runGhost = false;
        gp.pink.runGhost = false;
        gp.yellow.runGhost = false;
    }
}
