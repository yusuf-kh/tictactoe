import java.util.Scanner;



class Game extends Thread {
    int[][] grid;
    int curChance;
    Scanner s;
    String player1, player2, winner = "";
    int turns = 0;
    int gameNo;
    final Object lock;

    Game(int gameNo, String player1, String player2, Object lock) {
        this.gameNo = gameNo;
        this.player1 = player1;
        this.player2 = player2;
        this.lock = lock;
        grid = new int[3][3];
        s = new Scanner(System.in);
    }

    void getMove() {

        synchronized (lock) {
            if (!winner.equals("") || turns == 9) return;

            System.out.println("Current grid for game " + gameNo + ": ");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == 1) {
                        System.out.print("X ");
                    } else if (grid[i][j] == 2) {
                        System.out.print("O ");
                    } else {
                        System.out.print("  ");
                    }
                    if (j != 2) {
                        System.out.print("| ");
                    }
                }
                if (i != 2) {
                    System.out.println();
                    for (int j = 0; j < 10; j++) System.out.print("_");
                }

                System.out.println();
            }

            if (curChance == 0) {
                System.out.println("Chance for player " + player1 + " for game " + gameNo);
                int r = s.nextInt() - 1, c = s.nextInt() - 1;
                grid[r][c] = 1;
                if (winnerFound()) {
                    System.out.println(player1 + " is the winner for game " + gameNo);
                    winner = player1;
                }
            } else {
                System.out.println("Chance for player " + player2 + " for game " + gameNo);
                int r = s.nextInt() - 1, c = s.nextInt() - 1;
                grid[r][c] = 2;
                if (winnerFound()) {
                    System.out.println(player2 + " is the winner");
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
        return !winner.equals("") || turns == 9;
    }

    public boolean winnerFound() {
        for (int i = 0; i < 3; i++) {
            boolean all = true;
            for (int j = 1; j < 3; j++) {
                if (grid[i][j] != grid[i][j - 1] || grid[i][j] == 0) {
                    all = false;
                    break;
                }
            }
            if (all) return true;
        }
        
        for (int i = 0; i < 3; i++) {
            boolean all = true;
            for (int j = 1; j < 3; j++) {
                if (grid[j][i] != grid[j - 1][i] || grid[i][j] == 0) {
                    all = false;
                    break;
                }
            }
            if (all) return true;
        }
        boolean all = true;

        for (int i = 1; i < 3; i++) {
            if (grid[i][i] != grid[i - 1][i - 1] || grid[i][i] == 0) {
                all = false;
                break;
            }
        }
        if (all) return true;

        all = true;
        for (int i = 1; i < 3; i++) {
            if (grid[i][2 - i] != grid[i - 1][2 - i + 1] || grid[i][2 - i] == 0) {
                all = false;
                break;
            }
        }
        if (all) return true;

        return false;
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

public class TicTacToe {
    public static void main(String[] args) {
        final Object lock = new Object();

        Game game1 = new Game(1, "p1", "p2", lock);
        Game game2 = new Game(2, "p3", "p4", lock);

        game1.start();
        game2.start();
    }
}