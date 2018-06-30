package ir.amv.snippets.tbatask.car;

import ir.amv.snippets.tbatask.board.Board;

/**
 * @author Amir
 */
public interface ICarBusinessApi {

    /**
     * creates a new car on the board.
     * @param currentBoard the board which car will be added on
     * @return the created car
     */
    Car createCar(Board currentBoard);

    /**
     * gets a car by id.
     * @param carId the car id
     * @return the car associated with the id or null if not found
     */
    Car getCarById(String carId);

    /**
     * removes a car by id if found, if not, nothing will happen.
     * @param id the car id
     */
    void removeCar(String id);
}
