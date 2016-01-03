package ai.minimax;

import game.Board;
import game.GameState;
import game.States;
import game.WinChecker;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: Carlo<br/>
 * Date: 23/10/2015<br/>
 * Time: 12:39<br/>
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
    private final Board board;
    private final char currentPlayer;
    private final char opponentPlayer;
    private boolean chosen;

    private TicTacToeNode bestNode;

    public TicTacToeNode(Board board, char currentPlayer, char opponentPlayer) {
        super();
        this.board = board.clone();
        this.currentPlayer = currentPlayer;
        this.opponentPlayer = opponentPlayer;
    }

    public double heuristicDriverScore() {
        double boardScore = 0;
        GameState gameState = WinChecker.FIND_WINNER(board);
        if (gameState.getState() == States.NORMAL) {
            char[][] boardArray = board.getBoardArray();
            int positiveScore = 0;
            int negativeScore = 0;

            for (int[][] winState : winStates) {
                int curPoints = 0;
                int oppPoints = 0;

                //Voor elke van de mogelijke manier om te winnen ga je nakijken hoeveel plaatsen er bezet zijn door ��n van de spelers
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

    public TicTacToeNode getBestNode() {
        return bestNode;
    }

    public void setBestNode(TicTacToeNode bestNode) {
        this.bestNode = bestNode;
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

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen() {
        this.chosen = true;
    }

    @Override
    public String toString() {
        return getBoard()
                + "\nscore: " + heuristicDriverScore()
                + "\nminimax: " + score;
    }
}
