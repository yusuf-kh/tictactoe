package tictactoe;

import tictactoe.Player;

class Move {
    int gameNo, x, y;
    Player player;

    Move(int gameNo, Player player, int x, int y) {
        this.gameNo = gameNo;
        this.player = player;
        this.x = x;
        this.y = y;
    }
}