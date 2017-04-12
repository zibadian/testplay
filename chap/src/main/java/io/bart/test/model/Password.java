package io.bart.test.model;

public class Password {

    private String username;
    private byte[] password;

    public Password(final String username, final byte[] password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return password;
    }


}
