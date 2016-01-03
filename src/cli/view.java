package cli;

import ai.*;
import ai.minimax.Minimax;
import game.Game;
import game.GameException;
import game.States;

import java.util.Scanner;

/**
 * Created by Carlo on 13/10/2015.
 */
public class view {
    private static final char AI_PLAYER_1 = 'O';
    private static final char AI_PLAYER_2 = 'X';

    public static void main(String[] args) {
        char playerX = 'X';
        char playerO = 'O';
        Game game;
        AI ai1;
        AI ai2;

        boolean cont = true;
        Scanner scanner = new Scanner(System.in);
        while (cont) {
            game = new Game(playerX, playerO);
            ai1 = new Minimax(AI_PLAYER_1, AI_PLAYER_1 == playerX ? playerO : playerX, 4);
            ai2 = new Minimax(AI_PLAYER_2, AI_PLAYER_2 == playerX ? playerO : playerX, 9);

            do {
                System.out.println("\n\n\n\n" + game.toString());
                try {
                    int pos;
                    if (game.getCurrentPlayer() == AI_PLAYER_1) {
                        pos = ai1.move(game.getBoard());
                        System.out.println(pos);
                    } else {
//                        pos = ai2.move(game.getBoard());
//                        System.out.println(pos);
                        pos = Integer.parseInt(scanner.next());
                    }
                    game.makeMove(pos);

                } catch (GameException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                }
            } while (game.getGameState().getState() == States.NORMAL);

            System.out.println("\n\n\n\n" + game.getBoard());
            if (game.getGameState().getState() == States.TIE)
                System.out.print("The game ended in a tie, play again? ");
            else if (game.getGameState().getState() == States.WON)
                System.out.printf("Player %c won the game, congratulations! Play again? ", game.getWinner());
            char answer = scanner.next().charAt(0);
            cont = (answer == 'Y' || answer == 'y');
        }
    }
}
