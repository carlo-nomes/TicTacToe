import java.util.Scanner;

/**
 * Created by Nicolas on 22-9-2015.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Board());

        String keuze;
        do{
            Board b = new Board();
            b.play();
            System.out.println("Nog eens spelen? y/n?");
            Scanner sc = new Scanner(System.in);
            keuze = sc.next();
        }while(keuze.equalsIgnoreCase("y"));
    }
}
