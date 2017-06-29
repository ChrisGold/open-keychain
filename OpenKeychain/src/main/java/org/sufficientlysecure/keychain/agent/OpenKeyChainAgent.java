package org.sufficientlysecure.keychain.agent;

import org.sufficientlysecure.keychain.pgp.exception.PgpKeyNotFoundException;
import org.sufficientlysecure.keychain.provider.CachedPublicKeyRing;
import org.sufficientlysecure.keychain.provider.KeyRepository;
import org.sufficientlysecure.keychain.ui.util.KeyFormattingUtils;

/**
 * Created by Karsten on 18.06.2017.
 */

public class OpenKeyChainAgent extends SSHAgent {

    byte[] mScannedFingerprints;
    byte[] pubkey;
    @Override
    public byte[] sign(String user, String host) {
     /*   long masterKeyId = KeyFormattingUtils.getKeyIdFromFingerprint(mScannedFingerprints);
        CachedPublicKeyRing ring = KeyRepository.createDatabaseInteractor(this).getCachedPublicKeyRing(masterKeyId);
        try {
             pubkey =  ring.getFingerprint();
        } catch (PgpKeyNotFoundException e) {
            e.printStackTrace();
        }
    return pubkey;*/
     return new byte[0];
    }


    @Override
    public byte[] getMessageEncoded() {
        return new byte[0];
    }
}
