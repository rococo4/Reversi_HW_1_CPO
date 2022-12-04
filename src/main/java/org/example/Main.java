package org.example;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GamingField g = new GamingField();
        Player player1;
        Player player2;
        Game game;
        int type = ChooseTypeOfGame();
        switch (type) {
            case 0:
                player1 = new Computer("black", g.getCells(), g.getPointsForComputer());
                player2 = new Player("white");
                game = new Game(g, player1, player2);
                game.StartGame();
            case 1:
                player1 = new Computer("black", g.getCells(), g.getPointsForComputer());
                player2 = new Computer("white", g.getCells(), g.getPointsForComputer());
                game = new Game(g, player1, player2);
                game.StartGame();
            case 2:
                player1 = new Player("black");
                player2 = new Player("white");
                game = new Game(g, player1, player2);
                game.StartGame();
        }

    }

    private static int ChooseTypeOfGame() {
        System.out.println("0 - игра с роботом(черный, вы белые) 1 - робот против робота, 2 - человек с человеком(первым ходят черные)");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}