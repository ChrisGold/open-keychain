package org.sufficientlysecure.keychain.agent;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import org.sufficientlysecure.keychain.agent.handler.*;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;


/**
 * Created by Karsten on 29.06.2017.
 */



public class OpenKeyChainSSHService extends Service implements MessageTypes {
    /** Command to the service to display a message */
    //private SQLiteDataSource sqlSource;
    public static final String TAG = "OPENKEYCHAINSERVICE";

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
                    //requestPassword(msg);
                    break;
                case CLIENT_POST_PASSWORD:
                    Toast.makeText(getApplicationContext(), "Request", Toast.LENGTH_SHORT).show();
                    //checkPassword(msg);
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

    public void replyAuth(Message in){
        sshAgent = new OpenKeyChainAgent();
        Bundle data = in.getData();
        String user = data.getString("USER");
        String host = data.getString("HOST");
        sshAgent.handle(auth_id, user, host, connectionIdentifier, in.replyTo);
    }
    public void replyListHosts(Message in) {
        /*
        Message message = Message.obtain();
        Bundle data = new Bundle();
        //KeystoreActivity ksa = new KeystoreActivity();
        ExceptionHandler eh;
        try {
            String[] hosts = getHosts();
            data.putStringArray("HOSTS", hosts);
            message.what = AGENT_POST_LIST_HOSTS;
            message.setData(data);
            in.replyTo.send(message);
        } catch (CertificateException e) { //TODO: add messages for ExceptionHandler
            eh = new CertificateExceptionHandler();
            eh.sendError("",e,in.replyTo);
        } catch (NoSuchAlgorithmException e) {
            eh = new NoSuchAlgorithmExceptionHandler();
            eh.sendError("",e,in.replyTo);
        } catch (IOException e) {
            eh = new IOExceptionHandler();
            eh.sendError("",e,in.replyTo);
        } catch (KeyStoreException e) {
            eh = new KeyStoreExceptionHandler();
            eh.sendError("",e,in.replyTo);
        } catch (RemoteException e) {
            eh = new RemoteExceptionHandler();
            eh.sendError("",e,in.replyTo);
        }
        */
    }
    public void replyHost(Message in) { //TODO: add messages for ExceptionHandler
       /* ExceptionHandler eh;
        try {
            Message message = Message.obtain();
            Bundle data = new Bundle();
            KeystoreActivity ksa = new KeystoreActivity();
            String host = data.getString("HOST");
            String[] user = getUsers(host);
            data.putString("HOST", host);
            data.putStringArray("USER", user);
            message.what = AGENT_POST_LIST_USERS_FOR_HOST;
            message.setData(data);
            in.replyTo.send(message);
        } catch (CertificateException e) {
            eh = new CertificateExceptionHandler();
            eh.sendError("",e,in.replyTo);
        } catch (NoSuchAlgorithmException e) {
            eh = new NoSuchAlgorithmExceptionHandler();
            eh.sendError("",e,in.replyTo);
        } catch (IOException e) {
            eh = new IOExceptionHandler();
            eh.sendError("",e,in.replyTo);
        } catch (KeyStoreException e) {
            eh = new KeyStoreExceptionHandler();
            eh.sendError("",e,in.replyTo);
        } catch (RemoteException e) {
            eh = new RemoteExceptionHandler();
            eh.sendError("",e,in.replyTo);
        }

    */
    }


}
