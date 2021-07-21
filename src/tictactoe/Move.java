package tictactoe;

import tictactoe.Player;

class Move {
    int gameNo;
    Player player;
    Coordinate position;

    Move(int gameNo, Player player, Coordinate position) {
        this.gameNo = gameNo;
        this.player = player;
        this.position = position;
    }
}