package ai.minimax;

import ai.AI;
import game.*;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: Carlo<br/>
 * Date: 17/10/2015<br/>
 * Time: 12:39<br/>
 */
public class Minimax implements AI {
    private final int maxTreeDepth;

    private final char aiPlayer;
    private final char opponent;

    private TicTacToeNode latestNode;

    public Minimax(char aiPlayer, char opponent, int maxTreeDepth) {
        this.aiPlayer = aiPlayer;
        this.opponent = opponent;
        this.maxTreeDepth = maxTreeDepth;
    }

    @Override
    public int move(Board board) {
        TicTacToeNode node = new TicTacToeNode(board, opponent, aiPlayer);
        minimaxWithAlphaBetaPruning(node, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true, 0);
        latestNode = node;

        TicTacToeNode chosenNode = node;
        while (chosenNode != null) {
            chosenNode.setChosen();
            chosenNode = chosenNode.getBestNode();
        }

        return Board.boardsToMove(board.getBoardArray(), node.getBestNode().getBoard().getBoardArray());
    }

    private double minimaxWithAlphaBetaPruning(TicTacToeNode node, double alpha, double beta, boolean maximizing, int depth) {
        Board board = node.getBoard();
        double bestScore;
        TicTacToeNode bestNode = null;

        //Stop searching
        if (depth == maxTreeDepth || WinChecker.FIND_WINNER((node.getBoard())).getState() != States.NORMAL) {
            bestScore = node.heuristicDriverScore() / depth;
            if (maximizing) bestScore = -bestScore;
        } else {
            //MAX
            if (maximizing) {
                bestScore = alpha;

                for (int pos = 0; pos < board.getBoardSize(); pos++) {
                    if (board.isFree(pos)) {
                        //Child maken
                        TicTacToeNode childNode = new TicTacToeNode(board, node.getOpponentPlayer(), node.getCurrentPlayer());
                        childNode.getBoard().setPlayer(pos, childNode.getCurrentPlayer());
                        node.add(childNode);

                        //Berekening doen
                        double childScore = minimaxWithAlphaBetaPruning(childNode, bestScore, beta, false, depth + 1);

                        if (childScore > bestScore) {
                            //Vervang bestNode door childNode als deze een hoger score teruggeeft
                            bestScore = childScore;
                            bestNode = childNode;
                        }

                        //Stoppen indien score hoger is als beta
                        if (bestScore >= beta) return bestScore;
                    }
                }
            }
            //MIN
            else {
                bestScore = beta;

                for (int pos = 0; pos < board.getBoardSize(); pos++) {
                    if (board.isFree(pos)) {
                        //child maken
                        TicTacToeNode childNode = new TicTacToeNode(board, node.getOpponentPlayer(), node.getCurrentPlayer());
                        childNode.getBoard().setPlayer(pos, childNode.getCurrentPlayer());
                        node.add(childNode);

                        //Berekening doen
                        double childScore = minimaxWithAlphaBetaPruning(childNode, alpha, bestScore, true, depth + 1);

                        if (childScore < bestScore) {
                            //Vervang bestNode door childNode als deze een lager score teruggeeft
                            bestScore = childScore;
                            bestNode = childNode;
                        }
                    }

                    //Stoppen indien score lager is als alfa
                    if (bestScore <= alpha) return bestScore;
                }
            }
        }

        node.setBestNode(bestNode);
        node.setScore(bestScore);
        return bestScore;
    }

    public TicTacToeNode getLatestNode() {
        return latestNode;
    }

    public char getAiPlayer() {
        return aiPlayer;
    }
}
