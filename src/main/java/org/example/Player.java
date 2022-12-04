package org.example;

import java.util.Scanner;

public class Player {
    protected String color;

    public String getColor() {
        return color;
    }
    private void setColor(String value) {
        color = value;
    }

    public Player(String color) {
        setColor(color);
    }

    public int[] AskForMove() {
        System.out.println("Введите два числа через Enter");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        return new int[] {x,y};
    }

}
