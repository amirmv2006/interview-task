package ir.amv.snippets.tbatask.board.impl;

import ir.amv.snippets.tbatask.board.Board;
import ir.amv.snippets.tbatask.board.CarMoveType;
import ir.amv.snippets.tbatask.car.Car;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * the Thread in charge of moving a car. for each car a new instance is created.
 * @author Amir
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CarMoverThread extends Thread {

    /**
     * the last time car position was updated.
     */
    private LocalDateTime lastUpdate = LocalDateTime.now();
    /**
     * the board car is located on.
     */
    private Board currentBoard;
    /**
     * the car to be moved.
     */
    private Car car;
    /**
     * the movement which will be applied on the car.
     */
    private CarMoveType carMoveType;
    /**
     * speed of car movement.
     */
    private Integer speed;
    /**
     * whether the thread is alive or not
     */
    private boolean alive = false;

    /**
     * initialize or updates a car movement.
     * @param currentBoard the board of the car.
     * @param car          the car to be moved.
     * @param carMoveType  the movement type.
     * @param speed        the speed of car.
     */
    public synchronized void moveCar(Board currentBoard, Car car, CarMoveType carMoveType, final Integer speed) {
        this.currentBoard = currentBoard;
        this.car = car;
        this.carMoveType = carMoveType;
        this.speed = speed;
        if (!alive) {
            alive = true;
            start();
        }
    }

    /**
     * Thread method for applying the car movement.
     */
    @Override
    public void run() {
        while (alive) {
            Duration between = Duration.between(lastUpdate, LocalDateTime.now());
            lastUpdate = LocalDateTime.now();
            int compareTo = (int) between.getSeconds();
            if (compareTo > 0) {
                switch (carMoveType) {
                    case UP:
                        car.setY((car.getY() - compareTo * speed));
                        break;
                    case DOWN:
                        car.setY((car.getY() + compareTo * speed));
                        break;
                    case LEFT:
                        car.setX((car.getX() - compareTo * speed));
                        break;
                    case RIGHT:
                        car.setX((car.getX() + compareTo * speed));
                        break;
                }
                if (car.getX() < 0) {
                    car.setX(currentBoard.getWidth() + car.getX());
                } else if (car.getX() > currentBoard.getWidth()) {
                    car.setX(currentBoard.getWidth() - car.getX());
                }
                if (car.getY() < 0) {
                    car.setY(currentBoard.getHeight() + car.getY());
                } else if (car.getY() > currentBoard.getHeight()) {
                    car.setY(currentBoard.getHeight() - car.getY());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * stops moving the car.
     */
    public void kill() {
        this.alive = false;
    }

    /**
     * @return  the car.
     */
    public Car getCar() {
        return car;
    }
}
