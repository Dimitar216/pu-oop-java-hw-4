package tiles;

import java.awt.*;

public class GpsTile extends Tile{

    /**
     * Constructor for Tile
     *
     * @param row          row position
     * @param col          col position
     * @param widthOfTile  width of tile
     * @param heightOfTile height of tile
     */
    public GpsTile(int row, int col, int widthOfTile, int heightOfTile) {
        super(row, col, widthOfTile, heightOfTile);
    }

    @Override
    public void render(Graphics g){
        Graphics2D lineDrawer = (Graphics2D) g;
        int tileX =  (this.col * this.widthOfTile);
        int tileY =  (this.row * this.heightOfTile);

        g.setColor(Color.RED);
        g.fillRect(tileX,tileY,this.widthOfTile, this.heightOfTile);

        Tile.outlineRenderer(g,lineDrawer, tileX, tileY);
    }
}
