import ai.minimax.TicTacToeNode;
import game.Board;

/**
 * Created by Carlo on 17/11/2015.
 */
public class TicTacToeNodeTest {
    public static void main(String[] args) {
        Board board = new Board();
        //TicTacToeNode ticTacToeNode = new TicTacToeNode(board, 'X', 'O');
        TicTacToeNode ticTacToeNode = new TicTacToeNode(TicTacToeNode.State.MAX, board, 'O', 'X');

        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(0, 'X');
        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(4, 'O');
        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(2, 'X');
        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(7, 'O');
        System.out.println(ticTacToeNode);
    }
}
