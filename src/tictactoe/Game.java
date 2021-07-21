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
                System.out.println("Chance for player " + player1.playerName + " for game " + gameNo);
                int r = s.nextInt() - 1, c = s.nextInt() - 1;

                Move move = new Move(gameNo, player1, r, c);

                grid.setMove(move);

                if (grid.winnerFound()) {
                    System.out.println(player1.playerName + " is the winner for game " + gameNo);
                    winner = player1;
                }

            } else {
                System.out.println("Chance for player " + player2.playerName + " for game " + gameNo);
                int r = s.nextInt() - 1, c = s.nextInt() - 1;

                Move move = new Move(gameNo, player2, r, c);

                grid.setMove(move);

                if (grid.winnerFound()) {
                    System.out.println(player2.playerName + " is the winner");
                    winner = player2;
                }
            }

            if (turns == 9) {
                System.out.println("Game is drawn");
            }

            curChance ^= 1;

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