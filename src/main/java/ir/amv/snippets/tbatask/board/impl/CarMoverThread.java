package ir.amv.snippets.tbatask.board.impl;

import ir.amv.snippets.tbatask.board.Board;
import ir.amv.snippets.tbatask.board.CarMoveType;
import ir.amv.snippets.tbatask.car.Car;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author Amir
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CarMoverThread extends Thread {

    private Instant lastUpdate = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    private Board currentBoard;
    private Car car;
    private CarMoveType carMoveType;

    public void moveCar(Board currentBoard, Car car, CarMoveType carMoveType) {
        this.currentBoard = currentBoard;
        this.car = car;
        this.carMoveType = carMoveType;
        if (!this.isAlive()) {
            start();
        }
    }

    @Override
    public void run() {
        while (true) {
            int compareTo = Instant.now().truncatedTo(ChronoUnit.SECONDS).compareTo(lastUpdate);
            if (compareTo > 0) {
                switch (carMoveType) {
                    case UP:
                        car.setY((car.getY() - 1) % currentBoard.getHeight());
                        break;
                    case DOWN:
                        car.setY((car.getY() + 1) % currentBoard.getHeight());
                        break;
                    case LEFT:
                        car.setX((car.getX() - 1) % currentBoard.getWidth());
                        break;
                    case RIGHT:
                        car.setX((car.getX() + 1) % currentBoard.getWidth());
                        break;
                }
                System.out.println(car);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
