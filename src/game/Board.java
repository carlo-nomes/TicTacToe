package game;

/**
 * Created by Nicolas on 22-9-2015.
 */
public class Board {
    private static final int Y = 3;
    private static final int X = 3;
    private char[][] boardArray;

    public Board() {
        boardArray = new char[Y][X];
    }

    public boolean isFree(int pos) {
        int y = posToYX(pos)[0];
        int x = posToYX(pos)[1];

        if (boardArray[y][x] != '\u0000')
            return false;
        else
            return true;
    }

    public int getBoardSize() {
        return boardArray.length * boardArray[0].length;
    }

    public void setPlayer(int pos, char player) {
        int y = posToYX(pos)[0];
        int x = posToYX(pos)[1];

        boardArray[y][x] = player;
    }

    public char[][] getBoardArray() {
        return boardArray;
    }

    private int yxToPos(int y, int x) {
        return (y * X) + x;
    }

    private int[] posToYX(int pos) {
        int[] yx = new int[2];
        yx[0] = pos / boardArray.length;
        yx[1] = pos % boardArray.length;
        return yx;
    }

    @Override
    public String toString() {
        StringBuilder boardStringBuilder = new StringBuilder();
        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++) {
                boardStringBuilder.append(" ");
                boardStringBuilder.append(boardArray[y][x] != '\u0000' ? boardArray[y][x] + "" : yxToPos(y, x));
                if (x != 2)
                    boardStringBuilder.append(" |");
            }
            if (y != 2)
                boardStringBuilder.append("\n---|---|---\n");
        }
        return boardStringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Board) {
            for (int y = 0; y < boardArray.length; y++) {
                for (int x = 0; x < boardArray[y].length; x++) {
                    if (boardArray[y][x] != ((Board) obj).getBoardArray()[y][x])
                        return false;
                }
            }
            return true;
        } else return false;
    }

    public Board clone() {
        Board board = new Board();

        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++) {
                board.setPlayer(yxToPos(y, x), boardArray[y][x]);
            }
        }
        return board;
    }

}
