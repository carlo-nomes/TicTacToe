package ai.minimax;

import ai.AI;
import game.*;

/**
 * Created by Carlo on 17/10/2015.
 */
public class Minimax implements AI {
    private int maxTreeDepth = 9;

    private final char aiPlayer;
    private final char opponent;

    private TicTacToeNode nodeToChose;

    public Minimax(char aiPlayer, char opponent, int maxTreeDepth) {
        this.aiPlayer = aiPlayer;
        this.opponent = opponent;
        this.maxTreeDepth = maxTreeDepth;
    }

    public Minimax(char aiPlayer, char opponent) {
        this.aiPlayer = aiPlayer;
        this.opponent = opponent;
    }

    @Override
    public int move(Board board) {
        TicTacToeNode node = new TicTacToeNode(board, opponent, aiPlayer);
        double score = alphaBeta(node, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true, 0);

        return Board.boardsToMove(board.getBoardArray(), nodeToChose.getBoard().getBoardArray());
    }

    private double alphaBeta(TicTacToeNode node, double alpha, double beta, boolean maximizing, int depth) {
        Board board = node.getBoard();
        double bestScore;
        TicTacToeNode bestNode = null;

        //Stop searching
        if (depth == maxTreeDepth || WinChecker.FIND_WINNER((node.getBoard())).getState() != States.NORMAL) {
            bestScore = node.calcScore() / depth;
            if (maximizing) bestScore = -bestScore;
        }
        //MAX
        else {
            if (maximizing) {
                bestScore = alpha;

                for (int pos = 0; pos < board.getBoardSize(); pos++) {
                    if (board.isFree(pos)) {
                        //Child maken
                        TicTacToeNode childNode = new TicTacToeNode(board, node.getOpponentPlayer(), node.getCurrentPlayer());
                        childNode.getBoard().setPlayer(pos, childNode.getCurrentPlayer());
                        node.add(childNode);

                        //Berekening doen
                        double childScore = alphaBeta(childNode, bestScore, beta, false, depth + 1);

                        if (childScore > bestScore) {
                            //Vervang bestNode door childNode als deze een hoger score teruggeeft
                            bestScore = childScore;
                            bestNode = childNode;
                        } else if (childScore != alpha && childScore == bestScore) {
                            if (childNode.calcScore() > bestNode.calcScore()) {
                                //Als ze hetzelfde score hebben bereken je de heuristic value van de twee
                                bestScore = childScore;
                                bestNode = childNode;
                            }
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
                        double childScore = alphaBeta(childNode, alpha, bestScore, true, depth + 1);

                        if (childScore < bestScore) {
                            //Vervang bestNode door childNode als deze een lager score teruggeeft
                            bestScore = childScore;
                            bestNode = childNode;
                        } else if (childScore != beta && childScore == bestScore) {
                            if (childNode.calcScore() > bestNode.calcScore()) {
                                //Als ze hetzelfde score hebben bereken je de heuristic value van de twee
                                bestScore = childScore;
                                bestNode = childNode;
                            }
                        }
                    }
                    node.setScore(bestScore);

                    //Stoppen indien score lager is als alfa
                    if (bestScore <= alpha) return bestScore;
                }
            }
        }
        if (depth == 0) nodeToChose = bestNode;
        node.setScore(bestScore);
        return bestScore;
    }
}
