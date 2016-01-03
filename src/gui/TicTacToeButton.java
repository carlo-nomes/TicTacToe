package gui;

import javafx.scene.control.Button;

/**
 * Created by Carlo on 25/12/2015.
 */
public class TicTacToeButton extends Button {
    private final int pos;
    private char value;

    public TicTacToeButton(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
        super.setText(value + "");
    }
}
