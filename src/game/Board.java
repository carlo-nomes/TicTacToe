package game;

/**
 * Created by Nicolas on 22-9-2015.
 */
public class Board {
    private char[][] boardArray;

    public Board() {
        boardArray = new char[3][3];
    }

    public Board(Board other) {
        char[][] oldArray = other.getBoardArray();

        boardArray = new char[3][3];
        for (int y = 0; y < oldArray.length; y++) {
            for (int x = 0; x < oldArray[y].length; x++) {
                boardArray[x][y] = oldArray[x][y];
            }
        }
    }

    public boolean isFree(int pos) {
        int y = pos / boardArray.length;
        int x = pos % boardArray.length;

        if (boardArray[y][x] != '\u0000')
            return false;
        else
            return true;
    }

    public void setPlayer(int pos, Player player) {
        int y = pos / boardArray.length;
        int x = pos % boardArray.length;

        boardArray[y][x] = player.getName();
    }

    public char[][] getBoardArray() {
        return boardArray;
    }

    public void setBoardArray(char[][] boardArray) {
        this.boardArray = boardArray;
    }

    @Override
    public String toString() {
        StringBuilder boardStringBuilder = new StringBuilder();
        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++) {
                boardStringBuilder.append(" ");
                boardStringBuilder.append(boardArray[y][x] != '\u0000' ? ("" + boardArray[y][x]) : ((y * boardArray.length) + x));
                if (x != 2)
                    boardStringBuilder.append(" |");
            }
            if (y != 2)
                boardStringBuilder.append("\n---|---|---\n");
        }
        return boardStringBuilder.toString();
    }

    public Board clone() {
        Board board = new Board();
        board.setBoardArray(this.getBoardArray());
        return board;
    }
}
