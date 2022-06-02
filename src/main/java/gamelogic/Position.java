package gamelogic;

import java.util.Objects;

/**
 * @author Qinyi
 * get the coordinate of the points
 */
public class Position {
    public int x;
    public int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Pos(" + x + '|' + y + ')';
    }

    public int getY() {
        return y;
    }

}
