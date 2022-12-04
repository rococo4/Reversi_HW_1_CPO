package org.example;

import java.util.Scanner;

public class Game {
    private Player playerBlack;
    private Player playerWhite;
    private GamingField field;
    private int steps = 0;
    private int whitePoints;
    private int blackPoints;

    public Game(GamingField field, Player playerBlack, Player playerWhite) {
        this.field = field;
        this.playerBlack = playerBlack;
        this.playerWhite = playerWhite;
    }

    public void Move(int x, int y) {
        if (steps % 2 == 0) {
            if (field.IsAvailable(x, y, playerBlack.getColor())) {
                field.MakeMove(x, y, playerBlack.getColor());
                steps++;
            } else {
                System.out.println("Черные не туда пошли");
                System.out.println(x);
                System.out.println(y);
            }
        } else {
            if (field.IsAvailable(x, y, playerWhite.getColor())) {
                field.MakeMove(x, y, playerWhite.getColor());
                steps++;
            } else {
                System.out.println("Белые не туда пошли");
                System.out.println(x);
                System.out.println(y);
            }
        }
    }

    public void TakeStepBack() {
        if (steps > 0) {
            field.TakeStepBack();
            steps--;
        } else {
            throw new ArrayStoreException("It is only the first step ");
        }
    }

    public Boolean CheckEnd() {
        return field.CheckEnd();
    }



    public void StartGame() {
        System.out.println(field.DrawField(playerBlack.color));
        do  {

            if (steps % 2 == 0) {
                System.out.println("Ход черного");
            } else {
                System.out.println("Ход белого");
            }
            int variantOfUser = CallMenu();
            switch (variantOfUser) {
                case 0:
                    if (steps % 2 == 0) {
                        if (field.SkipMove("black")) {
                            System.out.println("Черные пропускают ход");
                            steps++;
                        } else {
                            var coordinatesBlack = playerBlack.AskForMove();
                            Move(coordinatesBlack[0], coordinatesBlack[1]);
                        }
                    } else {
                        if (field.SkipMove("white")) {
                            System.out.println("Белые пропускают ход");
                            steps++;
                        } else {
                            var coordinatesWhite = playerWhite.AskForMove();
                            Move(coordinatesWhite[0], coordinatesWhite[1]);
                        }
                    }
                    break;
                case 1:
                    TakeStepBack();
                    break;
                default:
                    System.out.println("вы ввели что-то не то");
            }
            if (steps % 2 == 0) {
                System.out.println(field.DrawField(playerBlack.getColor()));
            } else {
                System.out.println(field.DrawField(playerWhite.getColor()));
            }

        } while (!CheckEnd());
        System.out.println("Игра окончена победили: ");
        System.out.println(CheckWinner());
        System.out.print("черные очки: ");
        System.out.println(blackPoints);
        System.out.print("белые очки: ");
        System.out.println(whitePoints);

    }

    private int CallMenu() {
        System.out.println("сделать ход - 0, один ход назад - 1");
        Scanner in = new Scanner(System.in);
        int result = in.nextInt();
        return result;
    }
    private String CheckWinner() {
        int white = 0;
        int black = 0;
        for (var i = 0; i < 8; i++) {
            for (var j = 0; j < 8; j++) {
                if (field.getCells()[i][j].GetChip().GetCondition() == "white") {
                    white++;
                } else if (field.getCells()[i][j].GetChip().GetCondition() == "black") {
                    black++;
                }
            }
        }
        blackPoints = black;
        whitePoints = white;
        if (white > black) {
            return "white";
        } else if (black > white) {
            return "black";
        } else {
            return "draw";
        }
    }
}
