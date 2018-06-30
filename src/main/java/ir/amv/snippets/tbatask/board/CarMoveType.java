package ir.amv.snippets.tbatask.board;

import ir.amv.snippets.tbatask.board.exc.InvalidCarMoveCommandException;

/**
 * @author Amir
 */
public enum CarMoveType {
    UP("U"),
    DOWN("D"),
    LEFT("L"),
    RIGHT("R");

    private String command;

    CarMoveType(final String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CarMoveType fromCommand(String command) throws InvalidCarMoveCommandException {
        for (CarMoveType carMoveType : values()) {
            if (carMoveType.command.equals(command)) {
                return carMoveType;
            }
        }
        throw new InvalidCarMoveCommandException(command);
    }
}
