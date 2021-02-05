package gameboard;

import tiles.Tile;
import ui.Modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard extends JFrame implements MouseListener {
    private final int startingPosition = ThreadLocalRandom.current().nextInt(1,5);
    private final Tile[][] tileCollection = new Tile[8][8];
    private final int TILE_SIZE = 100;
    private Tile selectedTile;

    public GameBoard(){
        StartTileInitializer();
        gpsTilesGenerator();
        uninhabitableTilesGenerator();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setVisible(true);
        this.addMouseListener(this);
        System.out.println(startingPosition);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardCoordinates(e.getY());
        int col = this.getBoardCoordinates(e.getX());
        if(row<=7&&col<=7){
            if(this.selectedTile !=null){
                Tile tile = selectedTile;
                if(!isUninhabitable(row,col)){
                    startTileMovement(row, col, tile);
                    return;
                } else {
                    Modal.render(this,"Warning!","Cannot enter uninhabitable area");
                }
            }
            if(this.hasBoardTile(row,col)){
                Tile tile = getBoardTile(row,col);
                if(tile.getColor().equals(Color.WHITE)){
                    this.selectedTile = this.getBoardTile(row,col);
                }else {Modal.render(this,"Warning!","You can select only the white tile"); }
            }else{Modal.render(this,"Warning!","Nothing to select.");}
        }else {Modal.render(this,"Warning!","Out of bounds");}
    }

    private void startTileMovement(int row, int col, Tile tile) {
        if(tile.isMoveValid(row,col)&& tile.isMovingTile()) {
            int tileRoll = ThreadLocalRandom.current().nextInt(1,11);
            int initialRow = tile.getRow();
            int initialCol = tile.getCol();
            tile.move(row, col);
            if(tileRoll>2) {
                this.tileCollection[tile.getRow()][tile.getCol()] = this.selectedTile;
                Tile tileOld = new Tile(initialRow, initialCol, TILE_SIZE, TILE_SIZE, Color.WHITE);
                tileOld.setMovingTile(false);
            } else {
                Tile UninhabitableTile = new Tile(row,col,TILE_SIZE,TILE_SIZE,Color.BLUE);
                this.tileCollection[UninhabitableTile.getRow()][UninhabitableTile.getCol()] = UninhabitableTile;
            }
            this.selectedTile = null;
            this.repaint();
        } else {
            Modal.render(this,"Warning!","Invalid move.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g){
        for(int row = 0; row < 8;row++){
            for(int col = 0; col < 8;col++){
                tileRenderer(g,row,col);
            }
        }
        for(int row = 0; row < 8;row++){
            for(int col = 0; col < 8;col++){
                if(this.tileCollection[row][col] == null) {
                    Tile tile = new Tile(row, col, TILE_SIZE, TILE_SIZE, Color.GREEN);
                    tile.render(g);
                }
            }
        }
    }

    private void StartTileInitializer() {
        if(startingPosition == 1){
            Tile startingTile = new Tile(0, 0,TILE_SIZE,TILE_SIZE,Color.WHITE);
            this.tileCollection[0][0] = startingTile;
        }else if(startingPosition == 2){
            Tile startingTile = new Tile(7, 0,TILE_SIZE,TILE_SIZE,Color.WHITE);
            this.tileCollection[7][0] = startingTile;
        }else if(startingPosition == 3){
            Tile startingTile = new Tile(0, 7,TILE_SIZE,TILE_SIZE,Color.WHITE);
            this.tileCollection[0][7] = startingTile;
        }else if(startingPosition == 4){
            Tile startingTile = new Tile(7, 7,TILE_SIZE,TILE_SIZE,Color.WHITE);
            this.tileCollection[7][7] = startingTile;
        }
    }

    private void uninhabitableTilesGenerator(){
        for(int i = 0; i<5;i++){
            int randomCoordinateRow = ThreadLocalRandom.current().nextInt(0,8);
            int randomCoordinateCol = ThreadLocalRandom.current().nextInt(0,8);
            if(tileCollection[randomCoordinateRow][randomCoordinateCol] == null){
                Tile uninhabitableTile = new Tile(randomCoordinateRow,randomCoordinateCol,TILE_SIZE,TILE_SIZE,Color.BLUE);
                tileCollection[randomCoordinateRow][randomCoordinateCol] = uninhabitableTile;
            } else {
                i--;
            }
        }
    }

    private void gpsTilesGenerator(){
        for(int i = 0; i<8;i++){
            int randomCoordinateRow = ThreadLocalRandom.current().nextInt(0,8);
            int randomCoordinateCol = ThreadLocalRandom.current().nextInt(0,8);
            if(tileCollection[randomCoordinateRow][randomCoordinateCol] == null){
                Tile gpsTile = new Tile(randomCoordinateRow,randomCoordinateCol,TILE_SIZE,TILE_SIZE,Color.RED);
                tileCollection[randomCoordinateRow][randomCoordinateCol] = gpsTile;
            } else {
                i--;
            }
        }
    }

    private void tileRenderer(Graphics g,int row,int col){
        if(this.hasBoardTile(row,col)){
            Tile tile = getBoardTile(row,col);
            if(tile.getColor().equals(Color.BLUE)){
                Tile uninhabitableTile = new Tile(row,col,TILE_SIZE,TILE_SIZE,Color.BLUE);
                uninhabitableTile.render(g);
            } else if(tile.getColor().equals(Color.RED)){
                Tile gpsTile = new Tile(row,col,TILE_SIZE,TILE_SIZE,Color.RED);
                gpsTile.render(g);
            } else if(tile.getColor().equals(Color.WHITE)){
                Tile startingTile = new Tile(row,col,TILE_SIZE,TILE_SIZE,Color.WHITE);
                startingTile.render(g);
            }
        }
    }

    private int getBoardCoordinates(int coordinates){
        return  coordinates/TILE_SIZE;
    }

    private Tile getBoardTile(int row, int col){
        return this.tileCollection[row][col];
    }

    private boolean hasBoardTile(int row,int col){
        return this.getBoardTile(row,col) !=null;
    }

    private boolean isUninhabitable(int row,int col){
        if(hasBoardTile(row,col)){
            Tile tile = getBoardTile(row,col);
            return tile.getColor().equals(Color.BLUE);
        }
        return false;
    }
}