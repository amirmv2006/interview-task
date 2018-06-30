package ir.amv.snippets.tbatask.board;

import ir.amv.snippets.tbatask.car.Car;

import java.util.List;

/**
 * @author Amir
 */
public class Board {

    private Integer width;
    private Integer height;
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(final List<Car> cars) {
        this.cars = cars;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(final Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }
}
