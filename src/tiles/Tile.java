package tiles;

import java.awt.*;
import java.awt.geom.Line2D;

public class Tile {
    private int row;
    private int col;
    private final int widthOfTile;
    private final int heightOfTile;
    private final Color color;
    private final String selectedSymbol = "?";
    private boolean isPieceSelected = false;


    /**
     * Constructor for Tile
     * @param row row position
     * @param col col position
     * @param widthOfTile width of tile
     * @param heightOfTile height of tile
     */
    public Tile(int row, int col,int widthOfTile,int heightOfTile,Color color){
        this.row          = row;
        this.col          = col;
        this.widthOfTile  = widthOfTile;
        this.heightOfTile = heightOfTile;
        this.color = color;
    }

    /**
     * renders tile on the board based on row/col and width/height.
     * @param g graphics component
     */
    public void render(Graphics g) {
        Graphics2D lineDrawer = (Graphics2D) g;

        int tileX = (this.col * this.widthOfTile);
        int tileY = (this.row * this.heightOfTile);

        g.setColor(this.color);
        g.fillRect(tileX, tileY, this.widthOfTile, this.heightOfTile);

        outlineRenderer(g, lineDrawer, tileX, tileY);

        if (isPieceSelected&&this.color.equals(Color.WHITE)) {
            g.setColor(Color.BLACK);
            g.drawString(this.selectedSymbol, tileX + 50, tileY + 50);
        }
    }

    /**
     * changes row and col of tile depending on the parameters
     * @param row new row
     * @param col new col
     */
    public void move(int row,int col){
            this.row = row;
            this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPieceSelected(boolean pieceSelected) {
        isPieceSelected = pieceSelected;
    }

    public Color getColor() {
        return color;
    }

    /**
     * checks if the white tile's move is valid
     * @param moveRow row being moved to
     * @param moveCol col being moved to
     * @return true or false depending if the move is valid.
     */
    public boolean isMoveValid(int moveRow,int moveCol){
        if(this.getColor().equals(Color.WHITE)) {
            int rowCoefficient = Math.abs(moveRow - this.row);
            int colCoefficient = Math.abs(moveCol - this.col);

            return rowCoefficient == 0 && colCoefficient == 1 || rowCoefficient == 1 && colCoefficient == 0;
        }
        return false;
    }

    /**
     * checks if player is stuck
     * @param row row of tile being checked
     * @param col col of tile being checked
     * @param tile arr where the tiles are put in
     * @return true if stuck false if not
     */
    public boolean isPlayerStuck(int row,int col,Tile[][] tile){
        if(row+1<=7){ if (tile[row+1][col] != null) { if(tile[row+1][col].getColor().equals(Color.RED)){ return false;
                }
            } else { return false;
            }
        }
        if(row-1>=0){ if (tile[row-1][col] != null) { if(tile[row-1][col].getColor().equals(Color.RED)){ return false;
                }
            } else {
                return false;
            }
        }
        if(col + 1<=7){ if (tile[row][col+1] != null) { if(tile[row][col+1].getColor().equals(Color.RED)){ return false;
                }
            } else { return false;
            }
        }
        if(col -1 >= 0){ if (tile[row][col-1] != null) { if(tile[row][col-1].getColor().equals(Color.RED)){ return false;
                }
            } else { return false;
            }
        }
        return true;
    }

    /**
     * renders outlines for the tile
     * @param g graphics component
     * @param lineDrawer graphics 2d drawer
     * @param tileX x coordinate for line
     * @param tileY y coordinate for line
     */
    private static void outlineRenderer(Graphics g, Graphics2D lineDrawer, int tileX, int tileY) {
        g.setColor(Color.BLACK);
        Line2D line0 = new Line2D.Float(tileX, tileY, tileX, tileY +99);
        Line2D line1 = new Line2D.Float(tileX +99, tileY, tileX +99, tileY +99);
        Line2D line2 = new Line2D.Float(tileX, tileY, tileX +99, tileY);
        Line2D line3 = new Line2D.Float(tileX, tileY +99, tileX +99, tileY +99);
        //left
        lineDrawer.draw(line0);
        //right
        lineDrawer.draw(line1);
        //top
        lineDrawer.draw(line2);
        //bottom
        lineDrawer.draw(line3);
    }

}