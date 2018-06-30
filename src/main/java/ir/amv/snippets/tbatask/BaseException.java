package ir.amv.snippets.tbatask;

import java.util.UUID;

/**
 * @author Amir
 */
public class BaseException extends Exception {

    private String id = UUID.randomUUID().toString();

    public BaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public String getId() {
        return id;
    }

}
