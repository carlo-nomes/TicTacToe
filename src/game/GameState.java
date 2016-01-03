package game;

/**
 * Created by Carlo on 01/12/2015.
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
