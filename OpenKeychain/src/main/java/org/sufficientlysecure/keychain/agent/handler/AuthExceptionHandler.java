package org.sufficientlysecure.keychain.agent.handler;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import org.sufficientlysecure.keychain.agent.MessageTypes;

/**
 * @author Bilal Benmaghnia
 */

public class AuthExceptionHandler implements ExceptionHandler {
    /**
     * Sends information about the exception with a custom message to the source of the message.
     *
     * @param msg     message
     * @param cause   exception type
     * @param replyTo destination of reply
     */
    @Override
    public void sendError(String msg, Throwable cause, Messenger replyTo) {
        try {
            Message message = Message.obtain();
            Bundle data = new Bundle();
            message.what = MessageTypes.FATAL_ERROR;
            data.putString("MESSAGE", msg);
            data.putString("HANDLER", getClass().toString());
            data.putString("CAUSE", cause.getMessage());
            message.setData(data);
            replyTo.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends information with a custom flag about the exception with a custom message to the source of the message.
     *
     * @param msg     message
     * @param cause   exception type
     * @param replyTo destination of reply
     * @param what    type of message
     */
    @Override
    public void sendCustomError(String msg, Throwable cause, Messenger replyTo, int what) {
        try {
            Message message = Message.obtain();
            Bundle data = new Bundle();
            message.what = what;
            data.putString("MESSAGE", msg);
            data.putString("HANDLER", getClass().toString());
            data.putString("CAUSE", cause.getMessage());
            message.setData(data);
            replyTo.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
