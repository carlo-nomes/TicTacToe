package game;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: Nicolas<br/>
 * Date: 22-9-2015<br/>
 * Time: 12:39<br/>
 */
public class Board {
    private static final int Y = 3;
    private static final int X = 3;
    private final char[][] boardArray;

    public Board() {
        boardArray = new char[Y][X];
    }

    public boolean isFree(int pos) {
        int y = posToYX(pos)[0];
        int x = posToYX(pos)[1];

        return boardArray[y][x] == '\u0000';
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

    //vergelijkt twee borden om de gemaakte zet te vinden.
    public static int boardsToMove(char[][] first, char[][] second) {
        for (int y = 0; y < first.length; y++) {
            for (int x = 0; x < first[y].length; x++) {
                if (first[y][x] != second[y][x])
                    return yxToPos(y, x);
            }
        }
        return -1;
    }

    public static int yxToPos(int y, int x) {
        return (y * X) + x;
    }

    public static int[] posToYX(int pos) {
        int[] yx = new int[2];
        yx[0] = pos / Y;
        yx[1] = pos % Y;
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

    @Override
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
