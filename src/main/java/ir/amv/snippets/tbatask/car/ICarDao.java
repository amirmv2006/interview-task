package ir.amv.snippets.tbatask.car;

/**
 * @author Amir
 */
public interface ICarDao {
    void save(Car car);

    Car getCarById(String carId);
}
