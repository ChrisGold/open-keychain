package org.sufficientlysecure.keychain.agent;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import org.sufficientlysecure.keychain.provider.KeyRepository;


/**
 * Created by Karsten on 29.06.2017.
 */



public class OpenKeyChainSSHService extends Service implements MessageTypes {

    public static final String TAG = "org.sufficientlysecure.keychain.agent";

    KeyRepository mKeyRepository;



    static SSHAgent sshAgent; //TODO: Actually implement Agent and instantiate here!
    private int auth_id = 0;
    private byte[] connectionIdentifier;



    /**
     * Handler of incoming messages from clients.
     */
    class AgentHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CLIENT_REQUEST_AUTH:
                    Toast.makeText(getApplicationContext(), "Request", Toast.LENGTH_SHORT).show();
                    requestPin(msg);
                    break;
                case CLIENT_POST_PASSWORD:
                    Toast.makeText(getApplicationContext(), "Request", Toast.LENGTH_SHORT).show();
                    //Todo
                    checkPin(msg);
                case CLIENT_REQUEST_LIST_HOSTS:
                    Toast.makeText(getApplicationContext(), "REQUEST_LIST_HOSTS", Toast.LENGTH_SHORT).show();
                    replyListHosts(msg);
                case CLIENT_REQUEST_LIST_USERS_FOR_HOST:
                    Toast.makeText(getApplicationContext(), "REQUEST_LIST_HOSTS", Toast.LENGTH_SHORT).show();
                    replyHost(msg);
                default:
                    super.handleMessage(msg);
            }
        }
    }


    /**
     * Target we publish for clients to send messages to AgentHandler.
     */
    final Messenger mMessenger = new Messenger(new AgentHandler());

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }


    private void requestPin(Message msg) {
        try {
            Message message = Message.obtain();
            Bundle dataReceived = msg.getData();
            auth_id = dataReceived.getInt("AUTH_ID");
            String user = dataReceived.getString("USER");
            String host = dataReceived.getString("HOST");
            connectionIdentifier = dataReceived.getByteArray("CONNECTION_IDENTIFIER");
            Bundle reqPassword = new Bundle();
            reqPassword.putByteArray("AUTH_ID",connectionIdentifier);
            reqPassword.putString("USER", user);
            reqPassword.putString("HOST", host);
            message.what = AGENT_REQUEST_PASSWORD;
            message.setData(reqPassword);
            msg.replyTo.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void checkPin(Message msg) {
        //TODO
    }

    public void replyAuth(Message in){
        sshAgent = new OpenKeyChainAgent();
        Bundle data = in.getData();
        String user = data.getString("USER");
        String host = data.getString("HOST");
        sshAgent.handle(auth_id, user, host, connectionIdentifier, in.replyTo);
    }
    public void replyListHosts(Message in) {

    }
    public void replyHost(Message in) { //TODO: add messages for ExceptionHandler

    }


}
