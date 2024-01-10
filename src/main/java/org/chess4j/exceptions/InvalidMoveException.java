package org.chess4j.exceptions;

/**
 * Dedicated exception for an invalid move.
 */
public class InvalidMoveException extends RuntimeException {

    /**
     * Constructor without message.
     */
    public InvalidMoveException() {
        super();
    }

    /**
     * Constructor for InvalidMoveException with the given message.
     *
     * @param message the message of the exception.
     */
    public InvalidMoveException(String message) {
        super(message);
    }
}
