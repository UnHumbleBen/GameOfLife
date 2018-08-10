package com.company;

import javax.swing.*;

public class GameOfLifeVisualizer {
    private static final int GRID_HEIGHT = 100;
    private static final int GRID_WIDTH = 100;
    private static final int CELL_SIZE = 8;
    private static final boolean[][] grid = new boolean[GRID_HEIGHT][GRID_WIDTH];

    private static final int EPSILON = 100;
    private static final int CANVAS_WIDTH = GRID_WIDTH * CELL_SIZE + 2 * EPSILON;
    private static final int CANVAS_HEIGHT = GRID_HEIGHT * CELL_SIZE + EPSILON;

    public static void main(String[] args) {
        JFrame window = new JFrame("Game Of Life");
        GameOfLife game = new GameOfLife(grid, 0);
        drawingComponent DC = new drawingComponent(CELL_SIZE, game);
        window.setContentPane(DC);
        DC.addMouseListener(new ClickListener(DC, game, CELL_SIZE));
        window.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }


}
