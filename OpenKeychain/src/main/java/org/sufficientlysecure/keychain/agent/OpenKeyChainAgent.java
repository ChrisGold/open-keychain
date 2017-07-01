package org.sufficientlysecure.keychain.agent;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import org.openintents.openpgp.util.OpenPgpApi;
import org.sufficientlysecure.keychain.keyimport.ParcelableHkpKeyserver;
import org.sufficientlysecure.keychain.keyimport.ParcelableKeyRing;
import org.sufficientlysecure.keychain.operations.results.ImportKeyResult;
import org.sufficientlysecure.keychain.pgp.PgpSignEncryptData;
import org.sufficientlysecure.keychain.pgp.exception.PgpKeyNotFoundException;
import org.sufficientlysecure.keychain.provider.CachedPublicKeyRing;
import org.sufficientlysecure.keychain.provider.KeyRepository;
import org.sufficientlysecure.keychain.remote.OpenPgpService;
import org.sufficientlysecure.keychain.service.ImportKeyringParcel;
import org.sufficientlysecure.keychain.service.input.CryptoInputParcel;
import org.sufficientlysecure.keychain.ui.SecurityTokenOperationActivity;
import org.sufficientlysecure.keychain.ui.base.CryptoOperationHelper;
import org.sufficientlysecure.keychain.ui.util.KeyFormattingUtils;
import org.sufficientlysecure.keychain.util.NfcHelper;

import java.util.ArrayList;


/**
 * Created by Karsten on 18.06.2017.
 */

public class OpenKeyChainAgent extends SSHAgent{

    public static final String ARG_DATA_URI = "uri";
    private Uri mDataUri;
    private static final int LOADER_ID_UNIFIED = 0;
    private Activity mActivity;
    NfcHelper mNfcHelper;
    private KeyRepository keyRepository;


    byte[] mScannedFingerprints;
    byte[] pubkey;

    private byte[] message;
    private static byte[] getMessage(byte[] sessionIdentifer, String user, String algo, byte[] privateKeyEncoded){
        /*TypesWriter tw = new TypesWriter();
        {
            tw.writeString(sessionIdentifer, 0, sessionIdentifer.length);
            tw.writeByte(Packets.SSH_MSG_USERAUTH_REQUEST);
            tw.writeString(user);
            tw.writeString("ssh-connection");
            tw.writeString("publickey");
            tw.writeBoolean(true);
            tw.writeString(algo);
            tw.writeString(privateKeyEncoded, 0, privateKeyEncoded.length);
        }
        return tw.getBytes(); */
        return new byte[0];
    }


    @Override
    public byte[] sign(String user, String host) {
        Uri dataUri = getArguments().getParcelable(ARG_DATA_URI);
        //loadData(dataUri);
        long masterKeyId = 0;
        try {
            masterKeyId = keyRepository.getCachedPublicKeyRing(dataUri)
                    .extractOrGetMasterKeyId();
            pubkey = keyRepository.loadPublicKeyRingData(masterKeyId);
            return pubkey;
        } catch (PgpKeyNotFoundException e) {
            e.printStackTrace();
        } catch (KeyRepository.NotFoundException e) {
            e.printStackTrace();
        }

     return new byte[0];
    }

    private void loadData(Uri dataUri) {
        mDataUri = dataUri;
    }


    @Override
    public byte[] getMessageEncoded() {


        return new byte[0];
    }
}
