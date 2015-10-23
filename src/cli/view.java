package cli;

import AI.*;
import game.Game;
import game.GameException;

import java.util.Scanner;

/**
 * Created by Carlo on 13/10/2015.
 */
public class view {
    private static final char AIPLAYER = 'X';

    public static void main(String[] args) {
        char playerX = 'X';
        char playerO = 'O';
        Game game;
        AI ai;

        boolean cont = true;
        Scanner scanner = new Scanner(System.in);
        while (cont) {
            try {
                game = new Game(playerX, playerO);
                ai = new MiniMax(playerX, playerO);
            } catch (GameException e) {
                System.out.println(e.getMessage());
                break;
            }

            do {
                System.out.println("\n\n\n\n" + game.toString());
                try {
                    int pos;
                    if (game.getCurrentPlayer() == AIPLAYER) {
                        pos = ai.move(game.getBoard());
                        System.out.println(pos);
                    } else {
                        pos = Integer.parseInt(scanner.next());
                    }
                    game.makeMove(pos);

                } catch (GameException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                }
            } while (game.getGameState() == Game.GAME_STATES.NORMAL);

            System.out.println("\n\n\n\n" + game.getBoard());
            if (game.getGameState() == Game.GAME_STATES.TIE)
                System.out.print("The game ended in a tie, play again? ");
            else if (game.getGameState() == Game.GAME_STATES.WON)
                System.out.printf("Player %c won the game, congratulations! Play again? ", game.getWinner());
            char answer = scanner.next().charAt(0);
            cont = (answer == 'Y' || answer == 'y');
        }
    }
}
