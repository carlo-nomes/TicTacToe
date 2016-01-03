package game;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: Carlo<br/>
 * Date: 22/10/2015<br/>
 * Time: 12:39<br/>
 */
public class WinChecker {


    //returns [POSSIBLE_STATES, winner]
    public static GameState FIND_WINNER(Board board) {
        char[][] boardArray = board.getBoardArray();

        //Check horizontal
        for (int y = 0; y < boardArray.length; y++) {
            if (boardArray[y][0] != '\u0000' && boardArray[y][0] == boardArray[y][1] && boardArray[y][1] == boardArray[y][2])
                return new GameState(States.WON, boardArray[y][0]);
        }

        //Check vertical
        for (int x = 0; x < boardArray[0].length; x++) {
            if (boardArray[0][x] != '\u0000' && boardArray[0][x] == boardArray[1][x] && boardArray[1][x] == boardArray[2][x]) {
                return new GameState(States.WON, boardArray[0][x]);
            }
        }

        //Check diagonal left->right
        if (boardArray[0][0] != '\u0000' && boardArray[0][0] == boardArray[1][1] && boardArray[1][1] == boardArray[2][2]) {
            return new GameState(States.WON, boardArray[0][0]);
        }

        //Check diagonal right->left
        if (boardArray[0][2] != '\u0000' && boardArray[0][2] == boardArray[1][1] && boardArray[1][1] == boardArray[2][0]) {
            return new GameState(States.WON, boardArray[0][2]);
        }

        //Check for tie
        boolean emptySpot = false;
        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++)
                if (boardArray[y][x] == '\u0000')
                    emptySpot = true;
        }
        if (!emptySpot) {
            return new GameState(States.TIE, '\u0000');
        } else {
            return new GameState(States.NORMAL, '\u0000');
        }

    }
}
