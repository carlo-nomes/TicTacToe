import ai.minimax.Minimax;
import ai.minimax.TicTacToeNode;
import game.Board;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;

/**
 * Created by Carlo on 26/12/2015.
 */
public class PrintTicTacToeTreeTest {
    private static final char PLAYER_1 = 'X';
    private static final char PLAYER_2 = 'O';
    private static final int DEPTH = 3;

    public static void main(String[] args) {
        Board board = new Board();
        board.setPlayer(0, PLAYER_1);
        Minimax ai = new Minimax(PLAYER_2, PLAYER_1, DEPTH);
        ai.move(board);
        printBinaryTree((DefaultMutableTreeNode) ai.getLatestNode().getFirstChild(), 0);
    }

    public static void printBinaryTree(DefaultMutableTreeNode root, int level) {
        System.out.print(root.toString() + "\t");
        printBinaryTree(root.getNextSibling(), level);
root.getLastLeaf();
    }
}
