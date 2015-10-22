package AI;

import game.Board;
import javafx.scene.control.Cell;

/**
 * Created by Carlo on 13/10/2015.
 */
public interface AI {

    int move(Board board);

    int getTimeSpend();

    String getName();

    char getPlayer();
}
