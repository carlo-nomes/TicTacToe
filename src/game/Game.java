package game;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: Carlo<br/>
 * Date: 09/10/2015<br/>
 * Time: 12:39<br/>
 */
public class Game {
    private final char player1;
    private final char player2;
    private final Board board;
    private char currentPlayer;

    private GameState gameState;
    private char winner;

    public Game(char player1, char player2) {
        this.player1 = player1;
        this.player2 = player2;

        board = new Board();

        gameState = new GameState(States.NORMAL, '\u0000');
        currentPlayer = player1;
    }

    public char getPlayer1() {
        return player1;
    }

    public char getPlayer2() {
        return player2;
    }

    public GameState getGameState() {
        return gameState;
    }

    public char getWinner() {
        return winner;
    }

    public Board getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void makeMove(int pos) throws GameException {
        if (pos >= board.getBoardSize() || pos < 0) {
            throw new GameException("The selected position is not within the board");
        } else if (!board.isFree(pos)) {
            throw new GameException("The selected position is taken.");
        } else {
            board.setPlayer(pos, currentPlayer);
            gameState = WinChecker.FIND_WINNER(board);
            if (gameState.getState() == States.NORMAL)
                currentPlayer = currentPlayer == player1 ? player2 : player1;
            else
                winner = gameState.getWinner();
        }
    }

    @Override
    public String toString() {
        return "Player's " + currentPlayer + " turn \n" + board;
    }
}
