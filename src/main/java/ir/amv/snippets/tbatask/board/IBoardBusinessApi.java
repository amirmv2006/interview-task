package ir.amv.snippets.tbatask.board;

import ir.amv.snippets.tbatask.BaseException;

/**
 * @author Amir
 */
public interface IBoardBusinessApi {

    Board createCar();
    Board moveCar(String command) throws BaseException;
}
