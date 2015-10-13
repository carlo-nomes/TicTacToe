package cli;

import game.Game;
import game.Player;
import game.GameException;

import java.util.Scanner;

/**
 * Created by Carlo on 13/10/2015.
 */
public class view {
    public static void main(String[] args) {
        boolean cont = true;
        Scanner scanner = new Scanner(System.in);
        while (cont) {
            Player playerX = new Player('X');
            Player playerO = new Player('O');
            Game game = new Game(playerX, playerO);
            do {
                System.out.println("\n\n" + game.toString());
                try {
                    int pos = Integer.parseInt(scanner.next());
                    game.makeMove(pos);
                } catch (GameException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                }
            } while (game.getGameState() == Game.GAME_STATES.NORMAL);
            System.out.println(game.getBoard());
            if (game.getGameState() == Game.GAME_STATES.TIE)
                System.out.print("The game ended in a tie, play again? ");
            else if (game.getGameState() == Game.GAME_STATES.WON)
                System.out.printf("Player %c won the game, congratulations! Play again? ", game.getWinner().getName());

            cont = (scanner.next().charAt(0) == 'Y' || scanner.next().charAt(0) == 'y');
        }
    }
}
