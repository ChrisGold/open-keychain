package org.sufficientlysecure.keychain.agent.handler;

/**
 * @author Bilal Benmaghnia
 */

import android.os.Messenger;

import org.sufficientlysecure.keychain.agent.MessageTypes;

/**
 * Interface which standardizes the exception handling iff any catchable exception occurs.
 */
public interface ExceptionHandler extends MessageTypes {
    /**
     * Sends information about the exception with a custom message to the source of the message.
     * @param msg message
     * @param cause exception type
     * @param replyTo destination of reply*/
    public void sendError(String msg, Throwable cause, Messenger replyTo);
    /**
     * Sends information with a custom flag about the exception with a custom message to the source of the message.
     * @param msg message
     * @param cause exception type
     * @param replyTo destination of reply
     * @param what type of message*/
    public void sendCustomError(String msg, Throwable cause, Messenger replyTo, int what);
}
