package ir.amv.snippets.tbatask.board;

import ir.amv.snippets.tbatask.BaseException;
import ir.amv.snippets.tbatask.board.exc.InvalidCarMoveCommandException;

/**
 * business layer of board.
 * @author Amir
 */
public interface IBoardBusinessApi {

    /**
     * adds a car to the current board.
     * @param command the command for adding the car which consists of the {@link CarMoveType#command} and optionally
     *               a delimiter
     *                ({@link ir.amv.snippets.tbatask.board.impl.inmem.BoardBusinessApiInMemoryImpl#COMMAND_DELIMITER
     *                }) and speed which is an integer
     * @return the updated board.
     * @throws BaseException if there is a problem parsing command
     */
    Board addCar(final String command) throws BaseException;

    /**
     * updates a car on the current board.
     * @param command the command for adding the car which consists of the carId, a
     *                delimiter ({@link ir.amv.snippets.tbatask.board.impl.inmem.BoardBusinessApiInMemoryImpl#COMMAND_DELIMITER
     *                }), {@link CarMoveType#command} and optionally a delimiter
     *                ({@link ir.amv.snippets.tbatask.board.impl.inmem.BoardBusinessApiInMemoryImpl#COMMAND_DELIMITER
     *                }) and speed which is an integer
     * @return the updated board.
     * @throws BaseException if there is a problem parsing command
     */
    Board updateCar(String command) throws BaseException;

    /**
     * returns the current board.
     * @return current board.
     */
    Board getCurrentBoard();

    /**
     * removes the current board and initialize a new one.
     * @param width    the width for the new board.
     * @param height   the height for the new board.
     * @param carCount the number of cards on the new board.
     * @return the new board.
     */
    Board initBoard(Integer width, Integer height, Integer carCount);
}
