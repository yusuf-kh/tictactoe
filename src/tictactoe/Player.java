package tictactoe;

import tictactoe.Coordinate;

import java.util.Scanner;

class Player {
    String playerName;
    int playerNo;
    Scanner s;

    Player(String playerName, int playerNo) {
        this.playerName = playerName;
        this.playerNo = playerNo;

        s = new Scanner(System.in);
    }

    Coordinate getInput() {
        return new Coordinate(s.nextInt() - 1, s.nextInt() - 1);
    }
}

