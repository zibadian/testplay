package io.bart.test.encryption.impl;

import io.bart.test.encryption.EncryptionProvider;
import io.bart.test.model.Password;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

public class DigestEncryption implements EncryptionProvider {

    private MessageDigest digest;
    private Random random = new SecureRandom();
    private int counter = 0;
    private int challengeLength;

    public DigestEncryption(final MessageDigest digest, final int challengeLength) {
        this.digest = digest;
        this.challengeLength = challengeLength;
    }

    @Override
    public byte[] challenge() {
        if (++counter%37 == 0) {
            random = new SecureRandom();
        }
        byte[] result = new byte[challengeLength];
        random.nextBytes(result);
        return result;
    }

    @Override
    public byte[] response(final Password password, final byte[] challenge) {
        digest.reset();
        digest.update(password.getPassword());
        digest.update(challenge);
        return digest.digest();
    }

}
