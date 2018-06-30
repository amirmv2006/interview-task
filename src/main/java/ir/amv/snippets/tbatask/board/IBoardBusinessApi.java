package ir.amv.snippets.tbatask.board;

import ir.amv.snippets.tbatask.BaseException;
import ir.amv.snippets.tbatask.board.exc.InvalidCarMoveCommandException;

/**
 * @author Amir
 */
public interface IBoardBusinessApi {

    Board addCar(final String command) throws BaseException;
    Board moveCar(String command) throws BaseException;

    Board getCurrentBoard();

    Board initBoard(Integer width, Integer height, Integer carCount) throws BaseException;
}
