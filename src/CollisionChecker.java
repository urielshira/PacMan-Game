public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width; //
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height; //

        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = (entityRightX-1)/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBottomRow = (entityBottomY-1)/gp.tileSize;

        int tile1, tile2;

        switch (entity.direction){

            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tile1].collision || gp.tileM.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tile1].collision || gp.tileM.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tile1].collision || gp.tileM.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tile1].collision || gp.tileM.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
        }

    }
}
