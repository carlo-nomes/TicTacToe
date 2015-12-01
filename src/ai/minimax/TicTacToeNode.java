package ai.minimax;

import game.Board;
import game.GameState;
import game.States;
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
    private char opponentPlayer;

    public TicTacToeNode(Board board, char currentPlayer, char opponentPlayer) {
        super();
        this.board = board.clone();
        this.currentPlayer = currentPlayer;
        this.opponentPlayer = opponentPlayer;
    }

    public double calcScore() {
        double boardScore = 0;
        GameState gameState = WinChecker.FIND_WINNER(board);
        if (gameState.getState() == States.NORMAL) {
            char[][] boardArray = board.getBoardArray();
            int positiveScore = 0;
            int negativeScore = 0;

            for (int[][] winState : winStates) {
                int curPoints = 0;
                int oppPoints = 0;

                for (int y = 0; y < winState.length; y++) {
                    for (int x = 0; x < winState[y].length; x++) {
                        if (winState[y][x] == 1) {
                            if (boardArray[y][x] == currentPlayer) curPoints++;
                            if (boardArray[y][x] == opponentPlayer) oppPoints++;
                        }
                    }
                }

                positiveScore += oppPoints == 0 ? Math.pow(10, curPoints) : 0;
                //Er wordt vanuit gegaan dat het score word berekend na de zet van de huidige speler,
                //dus het score van de opponent weegt zwaarder door
                negativeScore -= curPoints == 0 ? Math.pow(10, oppPoints + 1) : 0;
            }
            boardScore = (positiveScore + negativeScore);
            //returns largest/lowest possible value if currentPlayer wins/loses
            //cannot use Infinity because it needs to be divided later
        } else if (gameState.getState() == States.WON) {
            return gameState.getWinner() == currentPlayer ? Double.MAX_VALUE : Double.MIN_VALUE;
        }

        return boardScore;
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

    public char getOpponentPlayer() {
        return opponentPlayer;
    }

    @Override
    public String toString() {
        return getBoard()
                + "\nscore: " + calcScore()
                + "\nminimax: " + score;
    }
}
