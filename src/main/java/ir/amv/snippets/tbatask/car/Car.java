package ir.amv.snippets.tbatask.car;

import ir.amv.snippets.tbatask.board.Board;

/**
 * the car model.
 * @author Amir
 */
public class Car {

    private String id;
    private Integer x;
    private Integer y;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(final Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(final Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
