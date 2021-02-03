package tiles;

import java.awt.*;

public class StartingTile extends Tile {

    /**
     * Constructor for StartingTile
     * @param row row position
     * @param col col position
     * @param widthOfTile width of tile
     * @param heightOfTile height of tile
     */
    public StartingTile(int row, int col,int widthOfTile,int heightOfTile){
        super(row,col,widthOfTile,heightOfTile);
    }

    /**
     * renders tile on the board based on row/col and width/height.
     * @param g graphics component
     */
    @Override
    public void render(Graphics g){
        Graphics2D lineDrawer = (Graphics2D) g;
        int tileX =  (this.col * this.widthOfTile);
        int tileY =  (this.row * this.heightOfTile);

        g.setColor(Color.WHITE);
        g.fillRect(tileX,tileY,this.widthOfTile, this.heightOfTile);

        Tile.outlineRenderer(g,lineDrawer, tileX, tileY);
    }

}
