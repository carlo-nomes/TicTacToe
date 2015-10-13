import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlo on 09/10/2015.
 */
public class Game {
    private final List<Player> players;
    private Board board;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);

        board = new Board();
    }

    public Player start() {
        currentPlayer = players.get(0);
        return currentPlayer;
    }

    public Board makeMove(int pos) throws PositionTakenException {
        if (board.isFree(pos)) {
            board.setPlayer(pos, currentPlayer);
            currentPlayer = players.get(players.indexOf(currentPlayer) < players.size() ? 1 : 0);
        } else {
            throw new PositionTakenException();
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
