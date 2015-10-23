package AI;

import game.Board;
import game.NoWinnerException;
import game.WinChecker;

import java.util.*;

/**
 * Created by Carlo on 17/10/2015.
 */
public class MiniMax implements AI {
    private final static String NAME = "MiniMax";

    private final char aiPlayer;
    private final char opponent;
    private TicTacToeNode root;


    private int currentMove;

    public MiniMax(char aiPlayer, char opponent) {
        this.aiPlayer = aiPlayer;
        this.opponent = opponent;
    }

    @Override
    public int move(Board board) {
        currentMove = -1;
        //minimax(0, aiPlayer, board);
        root = new TicTacToeNode(board, aiPlayer, opponent);

        calc(root, aiPlayer);

        Enumeration en = root.depthFirstEnumeration();
        while (en.hasMoreElements()) {

            // Unfortunately the enumeration isn't genericised so we need to downcast
            // when calling nextElement():
            TicTacToeNode node = (TicTacToeNode) en.nextElement();
            System.out.println(node);
        }
        return currentMove;
    }

    private void calc(TicTacToeNode node, char player) {
        Board board = node.getBoard();
        try {
            WinChecker.FIND_WINNER(board);
        } catch (NoWinnerException e) {
            for (int pos = 0; pos < board.getBoardSize(); pos++) {
                if (board.isFree(pos)) {
                    Board boardCopy = board.clone();
                    boardCopy.setPlayer(pos, player);
                    TicTacToeNode newNode = new TicTacToeNode(boardCopy, player, player == aiPlayer ? opponent : aiPlayer);
                    calc(newNode, player == aiPlayer ? opponent : aiPlayer);
                    node.add(newNode);
                }
            }
        }
    }


//    //@return {score,pos}
//    private int minimax(int depth, char player, Board board) {
//        try {
//            int score = 0;
//            char winner = WinChecker.FIND_WINNER(board);
//            if (winner == aiPlayer)
//                score = 10 - depth;
//            else if (winner == opponent)
//                score = depth - 10;
//            else if (winner == '\u0000')
//                score = 0;
//
//            return score;
//        } catch (NoWinnerException e) {
//            //do nothing
//        }
//
//        Map<Integer, Integer> scoresAndMoves = new HashMap<Integer, Integer>();
//
//        Board boardCopy;
//
//        for (int pos = 0; pos < board.getBoardSize(); pos++) {
//            if (board.isFree(pos)) {
//                boardCopy = board.clone();
//                boardCopy.setPlayer(pos, player);
//
//                int score = minimax(depth + 1, (player == aiPlayer ? opponent : aiPlayer), boardCopy);
//                scoresAndMoves.put(score, pos);
//            }
//        }
//
//        if (player == aiPlayer) {
//            int maxScore = Collections.max(scoresAndMoves.keySet());
//            currentMove = scoresAndMoves.get(maxScore);
//            return maxScore;
//        } else {
//            int minScore = Collections.min(scoresAndMoves.keySet());
//            return minScore;
//        }
//    }

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
