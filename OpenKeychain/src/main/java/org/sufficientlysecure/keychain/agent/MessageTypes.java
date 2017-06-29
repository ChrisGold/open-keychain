package org.sufficientlysecure.keychain.agent;

/**
 * @author Bilal Benmaghnia
 */
/**
 * Interface which provides all required message codes
 */
public interface MessageTypes {
    static final int CLIENT_REQUEST_AUTH = 0x11;
    static final int CLIENT_REQUEST_LIST_HOSTS = 0x12;
    static final int CLIENT_REQUEST_LIST_USERS_FOR_HOST = 0x13;
    static final int CLIENT_POST_PASSWORD = 0x14;
    static final int AGENT_POST_LIST_HOSTS = 0x15;
    static final int AGENT_POST_LIST_USERS_FOR_HOST = 0x16;
    static final int AGENT_REPLY_AUTH = 0x21;
    static final int AGENT_REQUEST_PASSWORD = 0x22;
    static final int AGENT_AUTH_FAIL_PASSWORD = 0x23;
    static final int AGENT_AUTH_FAIL_NO_SUCH_KEY = 0x24;
    static final int FATAL_ERROR = 0xFF;
}
