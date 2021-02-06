package ui;

import gameboard.GameBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setVisible(true);
    }
}
