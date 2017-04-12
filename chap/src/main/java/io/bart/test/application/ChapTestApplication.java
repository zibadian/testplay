package io.bart.test.application;

import io.bart.test.encryption.EncryptionProvider;
import io.bart.test.encryption.impl.DigestEncryption;
import io.bart.test.model.Password;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.security.MessageDigest;

public class ChapTestApplication {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) {
        createPasswordHash(new SHA3.Digest224());
        createPasswordHash(new SHA3.Digest256());
        createPasswordHash(new SHA3.Digest384());
        createPasswordHash(new SHA3.Digest512());
        final Password passwordHash = new Password("test", createPasswordHash(new SHA256.Digest()));

        EncryptionProvider provider = new DigestEncryption(new SHA256.Digest(), 1);
        final byte[] response = provider.response(passwordHash, "a".getBytes());
        System.out.println(bytesToHex(response));
    }

    private static byte[] createPasswordHash(final MessageDigest md) {
        byte[] passwordHash = new byte[0];
        try {
            md.update("test".getBytes());
            md.update("a".getBytes());
            passwordHash = md.digest();
            return passwordHash;
        } finally {
            System.out.println(bytesToHex(passwordHash));
        }
    }
}
