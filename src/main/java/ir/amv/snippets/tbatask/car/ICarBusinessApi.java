package ir.amv.snippets.tbatask.car;

import ir.amv.snippets.tbatask.board.Board;

/**
 * @author Amir
 */
public interface ICarBusinessApi {

    Car createCar(Board currentBoard);

    Car getCarById(String carId);
}
