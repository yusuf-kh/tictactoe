package tictactoe;

import tictactoe.Move;
import tictactoe.Player;

class Grid {
    private int[][] grid = new int[3][3];

    public void setMove(Move move) {
        grid[move.x][move.y] = move.player.playerNo;
    }

    public void printGrid() {
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
}