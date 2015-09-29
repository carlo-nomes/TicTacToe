import java.util.Scanner;

/**
 * Created by Nicolas on 22-9-2015.
 */
public class Board {
    private char[][] board;
    private Player currentPlayer;

    public Board() {
        board = new char[3][3];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                board[y][x] = Character.forDigit((y *board.length) + x, 10);
            }
        }
        currentPlayer = new Player("x");
    }

    public void play() {
        int keuze;
        System.out.println("Speler 'X' zal eerst gaan daarna mag currentPlayer 'O'");

        try {
            do {
                currentBoard();
                System.out.println("Speler '" + getPlayer() + "' kies een positie");
                boolean positieGenomen = true;
                while (positieGenomen) {
                    Scanner sc = new Scanner(System.in);
                    keuze = sc.nextInt();
                    positieGenomen = checkPositie(keuze);
                    if (positieGenomen == false) {
                        positie[keuze] = getPlayer().charAt(0);
                    }
                }
                nextPlayer();
            } while (checkWinner().equalsIgnoreCase(""));
        } catch (ArrayIndexOutOfBoundsException aiob) {
            System.err.println("De keuze past niet in dit bord.");
        }

    }

    public String checkWinner() {
        String winnaar = "";
        if (positie[1] == 'x' && positie[2] == 'x' && positie[3] == 'x') {
            winnaar = "Speler x wint";
            currentBoard();
        }
        if (positie[4] == 'x' && positie[5] == 'x' && positie[6] == 'x') {
            winnaar = "Speler x wint";
            currentBoard();
        }
        if (positie[7] == 'x' && positie[8] == 'x' && positie[9] == 'x') {
            winnaar = "Speler x wint";
            currentBoard();
        }
        if (positie[1] == 'x' && positie[4] == 'x' && positie[7] == 'x') {
            winnaar = "Speler x wint";
            currentBoard();
        }
        if (positie[2] == 'x' && positie[5] == 'x' && positie[8] == 'x') {
            winnaar = "Speler x wint";
            currentBoard();
        }
        if (positie[3] == 'x' && positie[6] == 'x' && positie[9] == 'x') {
            winnaar = "Speler x wint";
            currentBoard();
        }
        if (positie[3] == 'x' && positie[5] == 'x' && positie[7] == 'x') {
            winnaar = "Speler x wint";
            currentBoard();
        }
        if (positie[1] == 'x' && positie[5] == 'x' && positie[9] == 'x') {
            winnaar = "Speler x wint";
            currentBoard();
        }

        if (positie[1] == 'o' && positie[2] == 'o' && positie[3] == 'o') {
            winnaar = "Speler o wint";
            currentBoard();
        }
        if (positie[4] == 'o' && positie[5] == 'o' && positie[6] == 'o') {
            winnaar = "Speler o wint";
            currentBoard();
        }
        if (positie[7] == 'o' && positie[8] == 'o' && positie[9] == 'o') {
            winnaar = "Speler o wint";
            currentBoard();
        }
        if (positie[1] == 'o' && positie[4] == 'o' && positie[7] == 'o') {
            winnaar = "Speler o wint";
            currentBoard();
        }
        if (positie[2] == 'o' && positie[5] == 'o' && positie[8] == 'o') {
            winnaar = "Speler o wint";
            currentBoard();
        }
        if (positie[3] == 'o' && positie[6] == 'o' && positie[9] == 'o') {
            winnaar = "Speler o wint";
            currentBoard();
        }
        if (positie[3] == 'o' && positie[5] == 'o' && positie[7] == 'o') {
            winnaar = "Speler o wint";
            currentBoard();
        }
        if (positie[1] == 'o' && positie[5] == 'o' && positie[9] == 'o') {
            winnaar = "Speler o wint";
            currentBoard();
        }
        if (!winnaar.equalsIgnoreCase("")) {
            System.out.println(winnaar);
        }
        return winnaar;
    }

    public boolean checkPositie(int spot) {
        if (positie[spot] == 'x' || positie[spot] == 'o' || spot > 9) {
            System.out.println("Deze positie is al gebruikt, gelieve een andere te kiezen.");
            return true;
        } else {
            return false;
        }
    }

    public String getPlayer() {
        return currentPlayer.getNaam();
    }

    public void nextPlayer() {
        if (getPlayer().equalsIgnoreCase("x")) {
            currentPlayer.setNaam("o");
        } else currentPlayer.setNaam("x");
    }

    @Override
    public String toString() {
        StringBuilder boardStringBuilder = new StringBuilder();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                boardStringBuilder.append(" ");
                boardStringBuilder.append(board[y][x]);
                if (x != 2)
                    boardStringBuilder.append(" |");
            }
            if (y != 2)
                boardStringBuilder.append("\n---|---|---\n");
        }
        return boardStringBuilder.toString();
    }
}
