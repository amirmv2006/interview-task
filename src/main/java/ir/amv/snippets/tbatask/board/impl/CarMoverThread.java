package ir.amv.snippets.tbatask.board.impl;

import ir.amv.snippets.tbatask.board.Board;
import ir.amv.snippets.tbatask.board.CarMoveType;
import ir.amv.snippets.tbatask.car.Car;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Amir
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CarMoverThread extends Thread {

    private LocalDateTime lastUpdate = LocalDateTime.now();
    private Board currentBoard;
    private Car car;
    private CarMoveType carMoveType;
    private Integer speed;
    private boolean alive = false;

    public void moveCar(Board currentBoard, Car car, CarMoveType carMoveType, final Integer speed) {
        this.currentBoard = currentBoard;
        this.car = car;
        this.carMoveType = carMoveType;
        this.speed = speed;
        if (!this.isAlive()) {
            alive = true;
            start();
        }
    }

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

    public void kill() {
        this.alive = false;
    }

    public Car getCar() {
        return car;
    }
}
