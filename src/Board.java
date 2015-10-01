import java.util.Scanner;

/**
 * Created by Nicolas on 22-9-2015.
 */
public class Board {
    private char[][] board;
    private Player currentPlayer;

    public Board() {
        board = new char[3][3];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                board[y][x] = Character.forDigit((y * board.length) + x, 10);
            }
        }
    }

    public void play() {
        currentPlayer = new Player('X');

        Scanner scanner = new Scanner(System.in);
        while (checkWin() == '0') {
            System.out.println("geef positie om te plaatsen. speler " + currentPlayer.getNaam());
            if (!setPlayer(scanner.nextInt()))
                System.out.println("mag ni");
            System.out.println(toString());
        }
        System.out.printf("proficiat " + checkWin());
    }

    private char checkWin() {
        for (int y = 0; y < board.length; y++) {
            if (board[y][0] == board[y][1] && board[y][1] == board[y][2])
                return board[y][0];
        }
        for (int x = 0; x < board[0].length; x++) {
            if (board[0][x] == board[1][x] && board[1][x] == board[2][x])
                return board[0][x];
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];
        else
            return '0';
    }

    public boolean isFree(int pos) {
        int y = pos / board.length;
        int x = pos % board.length;

        if (board[y][x] == 'X' || board[y][x] == 'O')
            return false;
        else
            return true;
    }

    public boolean setPlayer(int pos) {
        if (!isFree(pos)) return false;

        int y = pos / board.length;
        int x = pos % board.length;

        board[y][x] = currentPlayer.getNaam();

        nextPlayer();
        return true;
    }

    public char getPlayer() {
        return currentPlayer.getNaam();
    }

    public void nextPlayer() {
        if (getPlayer() == 'X') {
            currentPlayer.setNaam('O');
        } else currentPlayer.setNaam('X');
    }

    @Override
    public String toString() {
        StringBuilder boardStringBuilder = new StringBuilder();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                boardStringBuilder.append(" ");
                boardStringBuilder.append(board[y][x]);
                if (x != 2)
                    boardStringBuilder.append(" |");
            }
            if (y != 2)
                boardStringBuilder.append("\n---|---|---\n");
        }
        return boardStringBuilder.toString();
    }
}
