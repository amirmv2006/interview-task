package ir.amv.snippets.tbatask.board.exc;

import ir.amv.snippets.tbatask.BaseException;

/**
 * @author Amir
 */
public class InvalidCarMoveCommandException extends BaseException {

    public InvalidCarMoveCommandException(String command) {
        super(String.format("Invalid Car Move Command %s", command), null);
    }
}
