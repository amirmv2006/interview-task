package ir.amv.snippets.tbatask.car.impl;

import ir.amv.snippets.tbatask.board.Board;
import ir.amv.snippets.tbatask.car.Car;
import ir.amv.snippets.tbatask.car.ICarBusinessApi;
import ir.amv.snippets.tbatask.car.ICarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * business layer for car operations.
 * @author Amir
 */
@Component
public class CarBusinessApiInMemoryImpl
        implements ICarBusinessApi {

    @Autowired
    private ICarDao carDao;

    /**
     * id generator for cars.
     */
    private AtomicInteger idGenerator = new AtomicInteger(0);

    /**
     * @inheritDoc
     */
    @Override
    public Car createCar(final Board currentBoard) {
        Car car = new Car();
        car.setId("c" + idGenerator.incrementAndGet());
        Random random = new Random();
        car.setX(random.nextInt(currentBoard.getWidth()));
        car.setY(random.nextInt(currentBoard.getHeight()));
        carDao.save(car);
        return car;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Car getCarById(final String carId) {
        return carDao.getCarById(carId);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeCar(final String id) {
        carDao.removeCar(id);
    }
}
