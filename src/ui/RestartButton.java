package ui;

import gameboard.GameBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButton implements ActionListener {
    /**
     * generates new game board when clicked
     * @param e action event listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard gameBoard = new GameBoard();
    }
}
