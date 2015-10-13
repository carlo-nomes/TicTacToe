package minimax;

import game.Game;
import game.GameException;
import game.Player;

/**
 * Created by Carlo on 13/10/2015.
 */
public class AI {
    public int makeMove(Game game, Player player) {
        int maxMove = -1;
        int maxScore = 0;
        char[][] boardArray = game.getBoard().getBoardArray();
        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++) {
                if (boardArray[y][x] == '\u0000') {
                    try {
                        Game newGame = new Game(game);
                        int move = (y * boardArray.length) + x;
                        int score;
                        newGame.makeMove(move);
                        score = minimax(newGame, player);
                        if (score >= maxScore) maxMove = move;
                    } catch (GameException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return maxMove;
    }

    private int minimax(Game game, Player player) {
        char[][] boardArray = game.getBoard().getBoardArray();
        int score = 0;

        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++) {
                if (boardArray[y][x] == '\u0000') {
                    try {
                        Game newGame = new Game(game);
                        int move = (y * boardArray.length) + x;
                        newGame.makeMove(move);
                        if (newGame.getGameState() == Game.GAME_STATES.WON) {
                            if (newGame.getWinner() == player)
                                return score + 1;
                            else
                                return score - 1;
                        } else if (newGame.getGameState() == Game.GAME_STATES.TIE) {
                            return score + 0;
                        } else {
                            score += minimax(newGame, player);
                        }
                    } catch (GameException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        return score;
    }
}
