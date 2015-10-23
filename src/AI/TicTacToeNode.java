package AI;

import game.Board;

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
    private char max;
    private char min;

    public TicTacToeNode(Board board, char max, char min) {
        super();
        this.board = board;
        this.max = max;
        this.min = min;
        calcScore();
    }

    private void calcScore() {
        char[][] boardArray = board.getBoardArray();
        double maxScore = 0;
        double minScore = 0;

        for (int[][] winState : winStates) {
            boolean maxwin = true;
            boolean minwin = true;

            for (int y = 0; y < winState.length; y++) {
                for (int x = 0; x < winState[y].length; x++) {
                    if (winState[y][x] == 1) {
                        if (boardArray[y][x] == min) maxwin = false;
                        if (boardArray[y][x] == max) minwin = false;
                    }
                }
            }

            maxScore += maxwin ? 1 : 0;
            minScore -= minwin ? 1 : 0;
        }

        score = maxScore + minScore;
    }

    public double getScore() {
        return score;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return board + "\nscore: " + score+"\n\n";
    }
}
