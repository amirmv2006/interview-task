package ir.amv.snippets.tbatask.board;

import ir.amv.snippets.tbatask.board.exc.InvalidCarMoveCommandException;

/**
 * the movements possible for the car.
 * @author Amir
 */
public enum CarMoveType {
    /**
     * moves the car up.
     */
    UP("U"),
    /**
     * moves the car down.
     */
    DOWN("D"),
    /**
     * moves the car left.
     */
    LEFT("L"),
    /**
     * moves the car right.
     */
    RIGHT("R");

    /**
     * the command character for the movement type.
     */
    private String command;

    CarMoveType(final String command) {
        this.command = command;
    }

    /**
     * @return the command character for the movement type.
     */
    public String getCommand() {
        return command;
    }

    /**
     * creates an {@link CarMoveType} based on the command character entered by user.
     * @param command the command character, 'u' for {@link #UP}, 'd' for {@link #DOWN}, 'l' for {@link #LEFT}, 'r'
     *                for {@link #RIGHT}
     * @return the {@link CarMoveType} associated with the command.
     * @throws InvalidCarMoveCommandException if no {@link CarMoveType} found for the command.
     */
    public static CarMoveType fromCommand(String command) throws InvalidCarMoveCommandException {
        for (CarMoveType carMoveType : values()) {
            if (carMoveType.command.equals(command)) {
                return carMoveType;
            }
        }
        throw new InvalidCarMoveCommandException(command);
    }
}
