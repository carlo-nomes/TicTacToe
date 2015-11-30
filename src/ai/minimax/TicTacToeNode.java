package ai.minimax;

import game.Board;
import game.NoWinnerException;
import game.WinChecker;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Carlo on 23/10/2015.
 */
public class TicTacToeNode extends DefaultMutableTreeNode {
    private final static int[][][] winStates = {{
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
    }, {
            {0, 0, 1},
            {0, 1, 0},
            {1, 0, 0}
    }, {
            {1, 0, 0},
            {1, 0, 0},
            {1, 0, 0}
    }, {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0}
    }, {
            {0, 0, 1},
            {0, 0, 1},
            {0, 0, 1}
    }, {
            {1, 1, 1},
            {0, 0, 0},
            {0, 0, 0}
    }, {
            {0, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
    }, {
            {0, 0, 0},
            {0, 0, 0},
            {1, 1, 1}
    }};

    private double score;
    private Board board;
    private char currentPlayer;
    private char otherPlayer;
    private boolean best;

    public TicTacToeNode(Board board, char currentPlayer, char otherPlayer) {
        super();
        this.board = board.clone();
        this.currentPlayer = currentPlayer;
        this.otherPlayer = otherPlayer;
    }

    public int calcScore() {
        int score = 0;

        try {
            if (WinChecker.FIND_WINNER(board) == currentPlayer) score = Integer.MAX_VALUE;
            else if (WinChecker.FIND_WINNER(board) == otherPlayer) score = Integer.MIN_VALUE;
        } catch (NoWinnerException e) {
            char[][] boardArray = board.getBoardArray();
            int maxScore = 0;
            int minScore = 0;

            for (int[][] winState : winStates) {
                int curPoints = 0;
                int othPoints = 0;

                for (int y = 0; y < winState.length; y++) {
                    for (int x = 0; x < winState[y].length; x++) {
                        if (winState[y][x] == 1) {
                            if (boardArray[y][x] == currentPlayer) curPoints++;
                            if (boardArray[y][x] == otherPlayer) othPoints++;
                        }
                    }
                }

                maxScore += othPoints == 0 ? 10 * curPoints : 0;
                minScore -= curPoints == 0 ? 10 * othPoints : 0;
            }
            score = (maxScore + minScore);
        }
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public Board getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getOtherPlayer() {
        return otherPlayer;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    public boolean isBest() {
        return best;
    }

    @Override
    public String toString() {
        return getBoard() + "\nscore: " + calcScore();
    }

}
