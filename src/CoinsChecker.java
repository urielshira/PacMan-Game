public class CoinsChecker {

    GamePanel gp;

    public CoinsChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkCoin(Entity entity){

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
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileM.tile[tile1].type == "coin" && gp.tileM.tile[tile2].type == "coin"){
                    gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 2;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tile1].type == "coin" && gp.tileM.tile[tile2].type == "coin"){
                    gp.tileM.mapTileNum[entityLeftCol][entityBottomRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 2;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile[tile1].type == "coin" && gp.tileM.tile[tile2].type == "coin"){
                    gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityLeftCol][entityBottomRow] = 2;
                }
                break;

            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tile1].type == "coin" && gp.tileM.tile[tile2].type == "coin"){
                    gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 2;
                    gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 2;
                }
                break;
        }

    }

}
