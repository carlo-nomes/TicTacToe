package gui;

import javafx.scene.control.Button;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: Carlo<br/>
 * Date: 25/12/2015<br/>
 * Time: 12:39<br/>
 */
class TicTacToeButton extends Button {
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
