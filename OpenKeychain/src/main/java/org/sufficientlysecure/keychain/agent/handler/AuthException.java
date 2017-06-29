package org.sufficientlysecure.keychain.agent.handler;

import android.os.Messenger;

/**
 * Exception which has to be thrown if any error occurs in the auth process between client and agent.
 * @author Bilal Benmaghnia
 */

public class AuthException extends RuntimeException {
    /**
     * Sends a message with the what code '0xFF' (fatal error) to the reply address
     * @param msg error msg
     * @param replyTo reply address
     */
    public AuthException(String msg, Messenger replyTo) {
        ExceptionHandler eh = new AuthExceptionHandler();
        eh.sendError(msg, this, replyTo);
    }
    /**
     * Sends a message with a custom what code to the reply address
     * @param msg error message
     * @param replyTo reply address
     * @param what identifier
     */
    public AuthException(String msg, Messenger replyTo, int what) {
        ExceptionHandler eh = new AuthExceptionHandler();
        eh.sendCustomError(msg, this, replyTo, what);
    }
    /**
     * Generic Exception with string message which only calls the constructor of the superclass.
     * @param msg error message*/
    public AuthException(String msg) {
        super(msg);
    }
    /**
     * Generic Exception which only calls the constructor of the superclass.
     */
    public AuthException() {
        super();
    }
}
