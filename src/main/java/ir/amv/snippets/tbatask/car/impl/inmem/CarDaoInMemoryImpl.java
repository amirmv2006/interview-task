package ir.amv.snippets.tbatask.car.impl.inmem;

import ir.amv.snippets.tbatask.car.Car;
import ir.amv.snippets.tbatask.car.ICarDao;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Amir
 */
@Repository
public class CarDaoInMemoryImpl
        implements ICarDao {
    private ConcurrentMap<String, Car> carIdMap = new ConcurrentHashMap<>();

    @Override
    public void save(final Car car) {
        carIdMap.put(car.getId(), car);
    }

    @Override
    public Car getCarById(final String carId) {
        return carIdMap.get(carId);
    }
}
