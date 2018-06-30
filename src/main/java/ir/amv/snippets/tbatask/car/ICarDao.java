package ir.amv.snippets.tbatask.car;

/**
 * dao for {@link Car}.
 * @author Amir
 */
public interface ICarDao {
    /**
     * saves a car.
     * @param car the car to be saved
     */
    void save(Car car);

    /**
     * gets a car by id.
     * @param carId the car id.
     * @return the car associated with this id. null if no car found.
     */
    Car getCarById(String carId);

    /**
     * removes a car by id. nothing will happen if the car doesn't exist.
     * @param id the car id
     */
    void removeCar(String id);
}
