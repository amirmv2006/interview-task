package ir.amv.snippets.tbatask.board.exc;

import ir.amv.snippets.tbatask.BaseException;

/**
 * exception thrown when the command character for car movement is not valid.
 * @author Amir
 */
public class InvalidCarMoveCommandException extends BaseException {

    public InvalidCarMoveCommandException(String command) {
        super(String.format("Invalid Car Move Command %s", command), null);
    }
}
