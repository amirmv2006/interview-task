package ir.amv.snippets.tbatask;

import java.util.UUID;

/**
 * Base Exception used in the class which uses UUID as id.
 * @author Amir
 */
public class BaseException extends Exception {

    /**
     * exception id useful for tracking exceptions.
     */
    private String id = UUID.randomUUID().toString();

    public BaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public String getId() {
        return id;
    }

}
