import java.util.Scanner;

/**
 * Created by Nicolas on 22-9-2015.
 */
public class Board {
    private char[][] boardArray;

    public Board() {
        boardArray = new char[3][3];
        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++) {
                boardArray[y][x] = Character.forDigit((y * boardArray.length) + x, 10);
            }
        }
    }

    private char checkWin() {
        for (int y = 0; y < boardArray.length; y++) {
            if (boardArray[y][0] == boardArray[y][1] && boardArray[y][1] == boardArray[y][2])
                return boardArray[y][0];
        }
        for (int x = 0; x < boardArray[0].length; x++) {
            if (boardArray[0][x] == boardArray[1][x] && boardArray[1][x] == boardArray[2][x])
                return boardArray[0][x];
        }
        if (boardArray[0][0] == boardArray[1][1] && boardArray[1][1] == boardArray[2][2])
            return boardArray[0][0];
        if (boardArray[0][2] == boardArray[1][1] && boardArray[1][1] == boardArray[2][0])
            return boardArray[0][2];
        else
            return '0';
    }

    public boolean isFree(int pos) {
        int y = pos / boardArray.length;
        int x = pos % boardArray.length;

        if (boardArray[y][x] == 'X' || boardArray[y][x] == 'O')
            return false;
        else
            return true;
    }

    public void setPlayer(int pos, Player player) {
        int y = pos / boardArray.length;
        int x = pos % boardArray.length;

        boardArray[y][x] = player.getNaam();
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
        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++) {
                boardStringBuilder.append(" ");
                boardStringBuilder.append(boardArray[y][x]);
                if (x != 2)
                    boardStringBuilder.append(" |");
            }
            if (y != 2)
                boardStringBuilder.append("\n---|---|---\n");
        }
        return boardStringBuilder.toString();
    }
}
