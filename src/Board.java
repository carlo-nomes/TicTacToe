import java.util.Scanner;

/**
 * Created by Nicolas on 22-9-2015.
 */
public class Board {
    private char positie[];
    private Player speler;

    public Board() {
        positie = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        speler = new Player("x");
    }

    public String currentBoard() {
        System.out.println("\t\t" + positie[1] + " |" + positie[2] + " |" + positie[3]);
        System.out.println("\t\t__|__|__");
        System.out.println("\t\t" + positie[4] + " |" + positie[5] + " |" + positie[6]);
        System.out.println("\t\t__|__|__");
        System.out.println("\t\t" + positie[7] + " |" + positie[8] + " |" + positie[9]);
        System.out.println("\t\t  |  |");
        return "currentboard";
    }

    public void play() {
        int keuze;
        System.out.println("Speler 'X' zal eerst gaan daarna mag speler 'O'");

        try{do {
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
        } while (checkWinner().equalsIgnoreCase(""));}catch (ArrayIndexOutOfBoundsException aiob){
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
        if (positie[spot] == 'x' || positie[spot] == 'o'||spot>9) {
            System.out.println("Deze positie is al gebruikt, gelieve een andere te kiezen.");
            return true;
        }
        else {
            return false;
        }
    }

    public String getPlayer() {
        return speler.getNaam();
    }

    public void nextPlayer() {
        if (getPlayer().equalsIgnoreCase("x")) {
            speler.setNaam("o");
        } else speler.setNaam("x");
    }

}
