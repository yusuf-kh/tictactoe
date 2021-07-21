package tictactoe;

import tictactoe.Move;
import tictactoe.Player;
import tictactoe.Grid;

import java.util.Scanner;

public class Game extends Thread {
    Grid grid;
    int curChance;
    Scanner s;
    Player player1, player2, winner;
    int turns = 0;
    int gameNo;
    final Object lock;

    public Game(int gameNo, String player1, String player2, Object lock) {
        this.gameNo = gameNo;
        this.player1 = new Player(player1, 1);
        this.player2 = new Player(player2, 2);
        this.lock = lock;
        grid = new Grid();
        s = new Scanner(System.in);
    }

    void getMove() {

        synchronized (lock) {
            if (gameOver()) return;

            System.out.println("Current grid for game " + gameNo + ": ");
            grid.printGrid();

            if (curChance == 0) {
                getMoveFromPlayer(player1);

            } else {
                getMoveFromPlayer(player2);
            }

            turns++;

            if (turns == 9 && winner == null) {
                System.out.println("Game is drawn");
            }

            curChance ^= 1;

        }

    }

    private void getMoveFromPlayer(Player player) {

        System.out.println("Chance for player " + player.playerName + " for game " + gameNo);

        Coordinate position = player.getInput();

        Move move = new Move(gameNo, player, position);

        grid.setMove(move);

        if (grid.winnerFound()) {
            System.out.println(player.playerName + " is the winner for game " + gameNo);
            winner = player;
        }
    }

    public boolean gameOver() {
        return winner != null || turns == 9;
    }

    @Override
    public void run() {

        while (!gameOver()) {
            getMove();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}