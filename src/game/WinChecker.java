package game;

/**
 * Created by Carlo on 22/10/2015.
 */
public class WinChecker {
    public static char FIND_WINNER(Board board) throws NoWinnerException {
        char[][] boardArray = board.getBoardArray();

        //Check horizontal
        for (int y = 0; y < boardArray.length; y++) {
            if (boardArray[y][0] != '\u0000' && boardArray[y][0] == boardArray[y][1] && boardArray[y][1] == boardArray[y][2]) {
                return boardArray[y][0];
            }
        }

        //Check vertical
        for (int x = 0; x < boardArray[0].length; x++) {
            if (boardArray[0][x] != '\u0000' && boardArray[0][x] == boardArray[1][x] && boardArray[1][x] == boardArray[2][x]) {
                return boardArray[0][x];
            }
        }

        //Check diagonal left->right
        if (boardArray[0][0] != '\u0000' && boardArray[0][0] == boardArray[1][1] && boardArray[1][1] == boardArray[2][2]) {
            return boardArray[0][0];
        }

        //Check diagonal right->left
        if (boardArray[0][2] != '\u0000' && boardArray[0][2] == boardArray[1][1] && boardArray[1][1] == boardArray[2][0]) {
            return boardArray[0][2];
        }

        //Check for tie
        boolean emptySpot = false;
        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++)
                if (boardArray[y][x] == '\u0000')
                    emptySpot = true;
        }
        if (!emptySpot) {
            return '\u0000';
        } else {
            throw new NoWinnerException("The game has not yet ended");
        }

    }
}
