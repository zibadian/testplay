package io.bart.test.encryption.impl;

import io.bart.test.encryption.EncryptionProvider;
import io.bart.test.model.Password;

public class IdentityEncryption implements EncryptionProvider {

    private byte[] internalChallenge = new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    private int counter = 0;

    @Override
    public byte[] challenge() {
        byte[] result = new byte[internalChallenge.length];
        System.arraycopy(internalChallenge, 0, result, 0, internalChallenge.length);
        result[result.length-1] = (byte) (++counter & 0xFF);
        return result;
    }

    @Override
    public byte[] response(final Password password, final byte[] challenge) {
        byte[] result = new byte[password.getPassword().length + challenge.length];
        System.arraycopy(challenge, 0, result, 0, challenge.length);
        System.arraycopy(password.getPassword(), 0, result, challenge.length, password.getPassword().length);
        return result;
    }

}
