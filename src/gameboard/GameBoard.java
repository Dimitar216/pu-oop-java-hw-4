package gameboard;

import tiles.StartingTile;
import tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard extends JFrame {
    private final int startingPosition = ThreadLocalRandom.current().nextInt(1,5);
    private final int TILE_SIZE = 100;
    public GameBoard(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        for(int row = 0; row < 8;row++){
            for(int col = 0; col < 8;col++){
                TileChooser(g, row, col);
            }
        }
    }

    private void TileChooser(Graphics g, int row, int col) {
        if(row == 0 && col == 0 && startingPosition == 1){ StartingTile startingTile = new StartingTile(row, col,TILE_SIZE,TILE_SIZE);
            startingTile.render(g);
        }else if(row == 7 && col == 0 && startingPosition == 2){ StartingTile startingTile = new StartingTile(row, col,TILE_SIZE,TILE_SIZE);
            startingTile.render(g);
        }else if(row == 0 && col == 7 && startingPosition == 3){ StartingTile startingTile = new StartingTile(row, col,TILE_SIZE,TILE_SIZE);
            startingTile.render(g);
        }else if(row == 7 && col == 7 && startingPosition == 4){ StartingTile startingTile = new StartingTile(row, col,TILE_SIZE,TILE_SIZE);
            startingTile.render(g);
        } else{ Tile tile = new Tile(row, col,TILE_SIZE,TILE_SIZE);
            tile.render(g);
        }
    }
}