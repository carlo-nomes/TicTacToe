import ai.minimax.TicTacToeNode;
import game.Board;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: Carlo<br/>
 * Date: 17/11/2015<br/>
 * Time: 12:39<br/>
 */
public class TicTacToeNodeTest {
    public static void main(String[] args) {
        Board board = new Board();
        //TicTacToeNode ticTacToeNode = new TicTacToeNode(board, 'X', 'O');
        TicTacToeNode ticTacToeNode = new TicTacToeNode(board, 'O', 'X');

        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(0, 'X');
        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(4, 'O');
        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(2, 'X');
        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(7, 'O');
        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(8, 'X');
        System.out.println(ticTacToeNode);
        ticTacToeNode.getBoard().setPlayer(1, 'O');
        System.out.println(ticTacToeNode);
    }
}
