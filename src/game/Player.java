package game;

/**
 * Created by Nicolas on 22-9-2015.
 */
public class Player {
    private char name;

    public Player(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player)
            return name == ((Player) obj).getName();
        else
            return false;
    }
}
