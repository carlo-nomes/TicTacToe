package ai.minimax;

import ai.AI;
import game.Board;
import game.NoWinnerException;
import game.WinChecker;

import java.util.Enumeration;

/**
 * Created by Carlo on 17/10/2015.
 */
public class Minimax implements AI {
    private final static String NAME = "Minimax";
    private static final int MINIMAX_TREE_DEPTH = 9;

    private final char aiPlayer;
    private final char opponent;

    private int currentMove;

    public Minimax(char aiPlayer, char opponent) {
        this.aiPlayer = aiPlayer;
        this.opponent = opponent;
    }

    @Override
    public int move(Board board) {
        TicTacToeNode node = makeTree(new TicTacToeNode(board, aiPlayer, opponent), MINIMAX_TREE_DEPTH);

        TicTacToeNode bestNode = alphaBeta(node, Integer.MIN_VALUE, Integer.MAX_VALUE, true, 1);
        Enumeration enumeration = node.children();
        while (enumeration.hasMoreElements()) {
            TicTacToeNode curNode = (TicTacToeNode) enumeration.nextElement();
            if (curNode.isBest()) bestNode = curNode;
        }

        currentMove = Board.boardsToMove(board.getBoardArray(), bestNode.getBoard().getBoardArray());
        return currentMove;
    }

    private TicTacToeNode alphaBeta(TicTacToeNode node, double alpha, double beta, boolean maximizing, int depth) {
        TicTacToeNode bestNode = null;
        double bestScore;

        if (!node.children().hasMoreElements()) {
            bestScore = node.calcScore() / depth;
            if (!maximizing) bestScore = -bestScore;
        } else if (maximizing) {
            bestScore = alpha;

            Enumeration enumeration = node.children();
            while (enumeration.hasMoreElements()) {
                TicTacToeNode childNode = (TicTacToeNode) enumeration.nextElement();
                double childScore = alphaBeta(childNode, bestScore, beta, false, depth + 1).getScore();

                if (bestScore < childScore | bestNode == null) {
                    bestScore = childScore;
                    bestNode = childNode;
                } else if (childScore == bestScore && bestNode.calcScore() < childNode.calcScore()) {
                    bestNode = childNode;
                    bestScore = childScore;
                }
            }
            bestNode.setBest(true);
        } else {
            bestScore = beta;

            Enumeration enumeration = node.children();
            while (enumeration.hasMoreElements()) {
                TicTacToeNode childNode = alphaBeta((TicTacToeNode) enumeration.nextElement(), alpha, bestScore, true, depth + 1);
                double childScore = childNode.getScore();

                if (bestScore > childScore | bestNode == null) {
                    bestScore = childScore;
                    bestNode = childNode;
                } else if (childScore == bestScore && bestNode.calcScore() > childNode.calcScore()) {
                    bestScore = childScore;
                    bestNode = childNode;
                }
            }
            bestNode.setBest(true);
        }

        node.setScore(bestScore);
        return node;
    }

    private TicTacToeNode makeTree(TicTacToeNode root, int depth) {
        Board rootBoard = root.getBoard();
        if (depth == 0) return root;

        for (int pos = 0; pos < rootBoard.getBoardSize(); pos++) {
            try {
                WinChecker.FIND_WINNER(rootBoard);
            } catch (NoWinnerException e) {
                if (rootBoard.isFree(pos)) {
                    TicTacToeNode newNode = new TicTacToeNode(rootBoard, root.getOtherPlayer(), root.getCurrentPlayer());
                    newNode.getBoard().setPlayer(pos, newNode.getCurrentPlayer());
                    root.add(makeTree(newNode, depth - 1));
                }
            }
        }
        return root;
    }

    @Override
    public int getTimeSpend() {
        return 0;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public char getPlayer() {
        return 0;
    }
}
