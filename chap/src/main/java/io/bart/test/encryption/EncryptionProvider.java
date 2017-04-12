package io.bart.test.encryption;

import io.bart.test.model.Password;

public interface EncryptionProvider {

    byte[] challenge();

    byte[] response(final Password password, final byte[] challenge);

}
