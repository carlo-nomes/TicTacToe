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

    public TicTacToeNode(Board board, char currentPlayer, char otherPlayer) {
        super();
        this.board = board.clone();
        this.currentPlayer = currentPlayer;
        this.otherPlayer = otherPlayer;
    }

    public int calcScore() {
        int score = 0;

        try {
            if (WinChecker.FIND_WINNER(board) == currentPlayer) score = Integer.MIN_VALUE;
            else if (WinChecker.FIND_WINNER(board) == otherPlayer) score = Integer.MAX_VALUE;
        } catch (NoWinnerException e) {
            char[][] boardArray = board.getBoardArray();
            int maxScore = 0;
            int minScore = 0;

            for (int[][] winState : winStates) {
                boolean maxwin = true;
                boolean minwin = true;

                for (int y = 0; y < winState.length; y++) {
                    for (int x = 0; x < winState[y].length; x++) {
                        if (winState[y][x] == 1) {
                            if (boardArray[y][x] == otherPlayer) maxwin = false;
                            if (boardArray[y][x] == currentPlayer) minwin = false;
                        }
                    }
                }

                maxScore += maxwin ? 1 : 0;
                minScore -= minwin ? 1 : 0;
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

    @Override
    public String toString() {
        return getBoard() + "\nscore: " + getScore();
    }
}
