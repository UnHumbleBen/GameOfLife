package com.company;

// import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class drawingComponent extends JPanel implements ActionListener {
    // Timer tm = new Timer(5, this);
    // int x = 0, velX = 2;

    // private boolean[][] grid;
    private final int GRID_HEIGHT;
    private final int GRID_WIDTH;
    private final int CELL_SIZE;
    private GameOfLife game;

    private final Color BORDER = Color.BLUE;
    private final Color ALIVE = Color.BLACK;
    private final Color DEAD = Color.WHITE;

    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 50;
    private final int BUTTON_GAP_X = 50;
    private final int BUTTON_X;
    private final int BUTTON_GAP_Y = 50;

    private boolean auto;
    private final int DELAY = 30;
    private Timer tm = new Timer(DELAY, this);

    public drawingComponent(int size, GameOfLife game) {
        // this.grid = grid;
        this.game = game;
        GRID_HEIGHT = game.getHEIGHT();
        GRID_WIDTH = game.getWIDTH();
        CELL_SIZE = size;
        BUTTON_X = GRID_WIDTH * CELL_SIZE + BUTTON_GAP_X;
        tm.start();
    }



    @Override
    public void paint(Graphics g) {
        paintGrid(g);
        paintButtons(g);
        paintStats(g);
    }

    public void paintStats(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int textX = BUTTON_X + BUTTON_WIDTH / 4;
        int borderY = CELL_SIZE * GRID_HEIGHT;
        int textY = borderY - BUTTON_GAP_Y;
        int buttonY = textY - BUTTON_HEIGHT / 2;
        Rectangle rectPopulation = new Rectangle(BUTTON_X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        g2.setColor(Color.WHITE);
        g2.fill(rectPopulation);
        g2.setColor(Color.BLACK);
        g2.draw(rectPopulation);

        String populationString = "Pop: " + game.getNumAlive();
        g.drawString(populationString, textX, textY);
        // System.out.println("ran");
    }

    public void paintButtons(Graphics g) {
        int buttonY = BUTTON_GAP_Y;
        int textX = BUTTON_X + BUTTON_WIDTH / 4 ;
        int textY = buttonY + BUTTON_HEIGHT / 2;
        int deltaY = BUTTON_HEIGHT + BUTTON_GAP_Y;
        Graphics2D g2 = (Graphics2D) g;

        Rectangle step = new Rectangle(BUTTON_X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        paintRectangle(g, step);
        g.drawString("Step", textX, textY);

        buttonY += deltaY;
        textY += deltaY;
        Rectangle autoButton = new Rectangle(BUTTON_X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        paintRectangle(g, autoButton);
        String strAuto = "Auto: ";
        if (auto) strAuto += "on";
        else strAuto += "off";
        g.drawString(strAuto, textX, textY);

        buttonY += deltaY;
        textY += deltaY;
        Rectangle resetButton = new Rectangle(BUTTON_X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        paintRectangle(g, resetButton);
        g2.drawString("reset", textX, textY);

        buttonY += deltaY;
        textY += deltaY;
        Rectangle gliderButton = new Rectangle(BUTTON_X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        paintRectangle(g, gliderButton);
        g2.drawString("Glider Gun", textX, textY);
    }

    public void paintGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int r = 0; r < GRID_HEIGHT; r++) {
            for (int c = 0; c < GRID_WIDTH; c++) {
                Rectangle cell = new Rectangle(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                if (game.isAlive(r, c)) g2.setColor(ALIVE);
                else g2.setColor(DEAD);
                g2.fill(cell);
                // g2.fillRect(r * CELL_SIZE, c * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                g2.setColor(BORDER);
                g2.draw(cell);
                // g2.drawRect(r * CELL_SIZE, c * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

//    public void setGrid(boolean[][] newGrid) {
//        grid = newGrid;
//    }

    public void changeAuto() {
        auto = !auto;
    }


    private void paintRectangle(Graphics g, Rectangle rect) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fill(rect);
        g2.setColor(Color.BLACK);
        g2.draw(rect);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (auto) {
            game.nextGeneration();
            repaint();
        }
    }
//    public void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//        g.fillRect(x, 30, 50, 30);
//
//        tm.start();
//    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        x = x + velX;
//        repaint();
//    }
}
