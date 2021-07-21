import tictactoe.Game;

public class TicTacToe {
    public static void main(String[] args) {
        final Object lock = new Object();

        Game game1 = new Game(1, "p1", "p2", lock);
        Game game2 = new Game(2, "p3", "p4", lock);

        game1.start();
        game2.start();
    }
}