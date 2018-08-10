package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GameOfLife {
    private boolean[][] grid;
    private final int HEIGHT;
    private final int WIDTH;
    private static final char ALIVE = '*';
    private static final char DEAD = '-';
    private int numAlive;

    public GameOfLife(boolean[][] grid, int numAlive) {
        this.grid = grid;
        HEIGHT = grid.length;
        WIDTH = grid[0].length;
        this.numAlive = numAlive;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public boolean isAlive(int r, int c) {
        return grid[r][c];
    }

    public int getNumAlive() {
        return numAlive;
    }


    public int numberOfLiveNeighbors(int r, int c) {
        int numNeighbors = 0;
        final int LEFT = c - 1;
        final int RIGHT = c + 1;
        final int ABOVE = r - 1;
        final int BELOW = r + 1;

        if (ABOVE >= 0) {
            if (grid[ABOVE][c]) numNeighbors++;
            if (LEFT >= 0 && grid[ABOVE][LEFT]) numNeighbors++;
            if (RIGHT < WIDTH && grid[ABOVE][RIGHT]) numNeighbors++;
        }

        if (LEFT >= 0 && grid[r][LEFT]) numNeighbors++;
        if (RIGHT < WIDTH && grid[r][RIGHT]) numNeighbors++;

        if (BELOW < HEIGHT) {
            if (grid[BELOW][c]) numNeighbors++;
            if (LEFT >= 0 && grid[BELOW][LEFT]) numNeighbors++;
            if (RIGHT < WIDTH && grid[BELOW][RIGHT]) numNeighbors++;
        }

        return numNeighbors;
    }

    public void nextGeneration() {
        boolean[][] nextGrid = new boolean[HEIGHT][WIDTH];
        for (int r = 0; r < HEIGHT; r++) {
            for (int c = 0; c < WIDTH; c++) {
                int numNeighbors = numberOfLiveNeighbors(r, c);
                boolean isInitiallyAlive = grid[r][c];
                if (numNeighbors == 3) nextGrid[r][c] = true;
                else if (numNeighbors == 2 && isInitiallyAlive) nextGrid[r][c] = true;
                boolean isNowAlive = nextGrid[r][c];
                if (isInitiallyAlive != isNowAlive) {
                    if (isNowAlive) numAlive++;
                    else numAlive--;
                }
            }
        }
        grid = nextGrid;
    }

    public void change(int r, int c) {
        grid[r][c] = !grid[r][c];

        if (grid[r][c]) numAlive++;
        else numAlive--;
    }

    public void reset() {
        grid = new boolean[HEIGHT][WIDTH];
        numAlive = 0;
    }

    public void gliderGun() {
        if (HEIGHT < 11) throw new IllegalStateException("Grid height not enough, needs 11");
        if (WIDTH < 38) throw new IllegalStateException("Grid width not enough, needs 38");

        reset();
        change(1, 25);
        change(2, 23);
        change(2, 25);
        change(3, 13);
        change(3, 14);
        change(3, 21);
        change(3, 22);
        change(3, 35);
        change(3, 36);
        change(4, 12);
        change(4, 16);
        change(4, 21);
        change(4, 22);
        change(4, 35);
        change(4, 36);
        change(5, 1);
        change(5, 2);
        change(5, 11);
        change(5, 17);
        change(5, 21);
        change(5, 22);
        change(6, 1);
        change(6, 2);
        change(6, 11);
        change(6, 15);
        change(6, 17);
        change(6, 18);
        change(6, 23);
        change(6, 25);
        change(7, 11);
        change(7, 17);
        change(7, 25);
        change(8, 12);
        change(8, 16);
        change(9, 13);
        change(9, 14);
    }

    private boolean[][] copy(boolean[][] array) {
        boolean[][] copy = new boolean[array.length][array[0].length];
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[0].length; c++) {
                copy[r][c] = array[r][c];
            }
        }
        return copy;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (boolean[] row : grid) {
            for (boolean b : row) {
                if (b) sb.append(ALIVE);
                else sb.append(DEAD);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        BufferedReader br;
        br = new BufferedReader(new FileReader(file));

        int height = Integer.parseInt(br.readLine());
        int width = Integer.parseInt(br.readLine());

        boolean[][] seed = new boolean[height][width];

        String st;
        int row = 0;
        int numAlive = 0;
        while ((st = br.readLine()) != null) {
            if (st.length() != width) {
                System.out.printf("Invalid number of characters in row %d\n", row);
                break;
            } else {
                for (int i = 0; i < width; i++) {
                    char c = st.charAt(i);
                    if (c == ALIVE) {
                        seed[row][i] = true;
                        numAlive++;
                    }
                }
            }
            if (++row == height) break;
        }

        GameOfLife test = new GameOfLife(seed, numAlive);
        System.out.println("Seed Array:\n" + test);

        int[][] neighbors = new int[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                neighbors[r][c] = test.numberOfLiveNeighbors(r, c);
            }
        }
        // System.out.println("Number of neighbors:");
        // printArray(neighbors);

        int generations = Integer.parseInt(args[1]);
        for (int gen = 1; gen <= generations; gen++) {
            test.nextGeneration();
            System.out.printf("Generation %d\n", gen);
            System.out.println(test);
        }
    }

    public static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int n : row) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
