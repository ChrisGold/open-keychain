package org.sufficientlysecure.keychain.agent;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by Karsten on 18.06.2017.
 */

public abstract class SSHAgent implements MessageTypes{

    public void handle(int auth_id, String user, String host, byte[] sessionIdentifier, Messenger replyTo){
        Message message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putInt("AUTH_ID", auth_id);
        bundle.putString("USER", user);
        bundle.putString("HOST", host);
        try {
            //byte[] signed = sign(user, host, sessionIdentifier);
            byte[] signed = sign(user, host);
            bundle.putByteArray("SIGNED", signed);
            bundle.putByteArray("MESSAGE", getMessageEncoded());
            message.what = AGENT_REPLY_AUTH;
            message.setData(bundle);
            replyTo.send(message);
        } catch (CertificateException | NoSuchAlgorithmException | IOException | RemoteException e) {
            e.printStackTrace();
        }


    }


    public abstract byte[] sign(String user, String host) throws CertificateException, NoSuchAlgorithmException, IOException;

    public abstract byte[] getMessageEncoded();
}
