package com.company;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {

    private drawingComponent dC;
    private GameOfLife game;
    // private boolean[][] grid;
    private int CELL_SIZE;
    private int GRID_HEIGHT;
    private int GRID_WIDTH;
    private final int BUTTON_GAP_X = 50;
    private int BUTTON_X;
    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_GAP_Y = 50;
    private final int BUTTON_HEIGHT = 50;

    public ClickListener(drawingComponent dC, GameOfLife game, int cellSize) {
        this.dC = dC;
        this.game = game;
        // this.grid = grid;
        this.CELL_SIZE = cellSize;
        this.GRID_HEIGHT = game.getHEIGHT();
        this.GRID_WIDTH = game.getWIDTH();
        BUTTON_X = GRID_WIDTH * CELL_SIZE + BUTTON_GAP_X;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int r = y / CELL_SIZE;
        int c = x / CELL_SIZE;

        // System.out.printf("r = %d\nc = %d\n", r, c);

        if (r < GRID_HEIGHT && c < GRID_WIDTH) game.change(r,c);

        if (x >= BUTTON_X && x <= BUTTON_X + BUTTON_WIDTH) {
            int deltaY = BUTTON_GAP_Y + BUTTON_HEIGHT;
            int buttonYMin = BUTTON_GAP_Y;
            int buttonYMax = BUTTON_GAP_Y + BUTTON_HEIGHT;
            if (y >= buttonYMin && y <= buttonYMax) {
                game.nextGeneration();
                // dC.setGrid(grid);
            }

            buttonYMax += deltaY;
            buttonYMin += deltaY;
            if (y >= buttonYMin && y <= buttonYMax) {
                dC.changeAuto();
            }

            buttonYMax += deltaY;
            buttonYMin += deltaY;
            if (y >= buttonYMin && y <= buttonYMax) {
                game.reset();
            }

            buttonYMax += deltaY;
            buttonYMin += deltaY;
            if (y >= buttonYMin && y <= buttonYMax) {
                game.gliderGun();
            }


        }

        dC.repaint();
    }

}
