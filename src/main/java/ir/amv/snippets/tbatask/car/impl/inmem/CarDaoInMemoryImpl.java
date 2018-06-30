package ir.amv.snippets.tbatask.car.impl.inmem;

import ir.amv.snippets.tbatask.car.Car;
import ir.amv.snippets.tbatask.car.ICarDao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static ir.amv.snippets.tbatask.TbaTaskApplication.IN_MEMORY_PROFILE;

/**
 * In-Memory implementation of {@link ICarDao} which holds cars in memory.
 * @author Amir
 */
@Repository
@Profile(IN_MEMORY_PROFILE)
public class CarDaoInMemoryImpl
        implements ICarDao {
    private ConcurrentMap<String, Car> carIdMap = new ConcurrentHashMap<>();

    /**
     * @inheritDoc
     */
    @Override
    public void save(final Car car) {
        carIdMap.put(car.getId(), car);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Car getCarById(final String carId) {
        return carIdMap.get(carId);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeCar(final String id) {
        carIdMap.remove(id);
    }
}
