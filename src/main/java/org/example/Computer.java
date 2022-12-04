package org.example;

import java.util.ArrayList;

public class Computer extends Player {
    private GamingCell[][] cells;
    private int[][][][] pointsForComputer;

    public Computer(String color, GamingCell[][] cells, int[][][][] pointsForComputer) {
        super(color);
        this.cells = cells;
        this.pointsForComputer = pointsForComputer;
    }
    @Override
    public int[] AskForMove() {
        double max = 0;
        int[] coordinatesForMax = new int[2];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (color == "white" && cells[x][y].GetChip().GetCondition() == "availableWhite") {
                    double temp = pointsForComputer[x][y][0][1] + FindSs(x,y);
                    if (temp > max) {
                        max = temp;
                        coordinatesForMax[0] = x;
                        coordinatesForMax[1] = y;
                    }

                } else if (color == "black" && cells[x][y].GetChip().GetCondition() == "availableBlack") {
                    double temp = pointsForComputer[x][y][0][0] + FindSs(x,y);
                    if (temp > max) {
                        max = temp;
                        coordinatesForMax[0] = x;
                        coordinatesForMax[1] = y;
                    }
                }
            }
        }
        if (coordinatesForMax[0] == 0 && coordinatesForMax[1] == 3) {
            System.out.println();
        }
        return coordinatesForMax;
    }
    private double FindSs(int x, int y) {
        if (x == 0 && y == 0 || x == 7 && y == 7 || x == 7 && y == 1 || x == 1 && y == 7) {
            return 0.8;
        }
        if (x % 7 == 0  || y % 7 == 0) {
            return 0.4;
        }
        return 0;
    }
}
