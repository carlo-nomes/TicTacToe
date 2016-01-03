package game;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: Carlo<br/>
 * Date: 01/12/2015<br/>
 * Time: 12:39<br/>
 */
public class GameState {
    private final States state;
    private final char winner;

    public GameState(States state, char winner) {
        this.state = state;
        this.winner = winner;
    }

    public States getState() {
        return state;
    }

    public char getWinner() {
        return winner;
    }
}
