package ir.amv.snippets.tbatask.car;

import ir.amv.snippets.tbatask.board.Board;

/**
 * @author Amir
 */
public class Car {

    private String id;
    private Board board;
    private Integer x;
    private Integer y;

    public Board getBoard() {
        return board;
    }

    public void setBoard(final Board board) {
        this.board = board;
    }

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
